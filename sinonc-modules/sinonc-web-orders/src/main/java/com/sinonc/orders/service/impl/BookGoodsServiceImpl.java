package com.sinonc.orders.service.impl;

import com.sinonc.common.core.text.Convert;
import com.sinonc.common.core.exception.BusinessException;
import com.sinonc.common.security.utils.SecurityUtils;
import com.sinonc.orders.delaytask.DelayTask;
import com.sinonc.orders.delaytask.DelayTaskServiceImpl;
import com.sinonc.orders.delaytask.TaskTypeConstants;
import com.sinonc.orders.domain.BookGoods;
import com.sinonc.orders.domain.Goods;
import com.sinonc.orders.domain.Specs;
import com.sinonc.orders.mapper.BookGoodsMapper;
import com.sinonc.orders.mapper.GoodsMapper;
import com.sinonc.orders.mapper.SpecsMapper;
import com.sinonc.orders.service.BookGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 商品和预订活动 服务层实现
 *
 * @author sinonc
 * @date 2019-09-25
 */
@Service("bookGoodsService")
public class BookGoodsServiceImpl implements BookGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private SpecsMapper specsMapper;

    @Autowired
    private BookGoodsMapper bookGoodsMapper;

    @Autowired
    private DelayTaskServiceImpl delayTaskService;

    /**
     * 查询商品和预订活动信息
     *
     * @param bgId 商品和预订活动ID
     * @return 商品和预订活动信息
     */
    @Override
    public BookGoods getBookGoodsById(Long bgId) {
        return bookGoodsMapper.selectBookGoodsById(bgId);
    }

    /**
     * 查询商品和预订活动列表
     *
     * @param bookGoods 商品和预订活动信息
     * @return 商品和预订活动集合
     */
    @Override
    public List<BookGoods> listBookGoods(BookGoods bookGoods) {
        return bookGoodsMapper.selectBookGoodsList(bookGoods);
    }

    /**
     * 保存商品预订活动
     *
     * @param bookGoods 商品和预订活动信息
     * @return 结果
     */
    @Override
    public int saveBookGoods(BookGoods bookGoods) {

        Date date = new Date();
        //创建定时任务，用于更新预订状态
        if (bookGoods.getStartTime().getTime() >= bookGoods.getEndTime().getTime()) {
            throw new BusinessException("开始时间必须小于结束时间");
        }

        if (bookGoods.getStartTime().getTime() < System.currentTimeMillis()) {
            throw new BusinessException("开始时间必须大于当前系统时间");
        }

        bookGoods.setStatus(0);
        bookGoods.setCreateBy(SecurityUtils.getUsername());
        bookGoods.setCreateTime(date);

        BookGoods bg = bookGoodsMapper.selectByGoodsId(bookGoods.getGoodsId());

        if (bg != null) {
            int i = bookGoodsMapper.deleteBookGoodsById(bg.getBgId());
            if (i == 0) {
                throw new BusinessException("更新预订商品失败");
            }
        }
        int rows = bookGoodsMapper.insertBookGoods(bookGoods);

        if (rows > 0){
            //添加至延时任务
            //预订活动开始
            delayTaskService.addTask(DelayTask.getInstance(TaskTypeConstants.BOOK_START_TASK, bookGoods.getStartTime().getTime(), date.getTime(), bookGoods));

            //预订活动结束
            delayTaskService.addTask(DelayTask.getInstance(TaskTypeConstants.BOOK_END_TASK, bookGoods.getEndTime().getTime(), date.getTime(), bookGoods));
        }

        return rows;
    }

    /**
     * 修改商品和预订活动
     *
     * @param bookGoods 商品和预订活动信息
     * @return 结果
     */
    @Override
    public int updateBookGoods(BookGoods bookGoods) {

        //创建定时任务，用于更新预订状态
        if (bookGoods.getStartTime().getTime() >= bookGoods.getEndTime().getTime()) {
            throw new BusinessException("预订开始时间必须小于预订结束时间");
        }

        if (bookGoods.getStartTime().getTime() < System.currentTimeMillis()) {
            throw new BusinessException("预订开始时间必须大于当前系统时间");
        }
        bookGoods.setUpdateTime(new Date());
        Goods goods = goodsMapper.selectGoodsById(bookGoods.getGoodsId());
        if (goods == null) {
            throw new BusinessException("商品不存在，请重试");
        }
        BookGoods oldBooks = bookGoodsMapper.selectBookGoodsById(goods.getGoodsId());
        if (Optional.ofNullable(oldBooks).isPresent()){
            bookGoods.setBgId(oldBooks.getBgId());
            if (oldBooks.getStatus() == 1){
                throw new BusinessException("预订商品已开始，不能修改");
            }
        }
        List<Specs> specsList = specsMapper.selectSpecsByIds(goods.getSpecsIds().split(","));

        if (bookGoods.getEarnestPrice() == null){
            bookGoods.setEarnestPrice(new BigDecimal(0));
        }
        for (Specs specs : specsList) {
            if (specs.getSpecsPrice().compareTo(bookGoods.getEarnestPrice()) < 0) {
                throw new BusinessException("定金金额必须小于商品最低价格");
            }
        }

        return bookGoodsMapper.updateBookGoods(bookGoods);
    }

    /**
     * 更新订单状态
     *
     * @param bookId 预订活动ID
     * @param status 活动状态
     * @return
     */
    @Override
    public int updateBookGoodsStatus(Long bookId, Integer status) {
        BookGoods bookGoods = new BookGoods();
        bookGoods.setBgId(bookId);
        bookGoods.setStatus(status);
        bookGoods.setUpdateTime(new Date());
        return bookGoodsMapper.updateBookGoods(bookGoods);

    }

    /**
     * 删除商品和预订活动对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBookGoodsByIds(String ids) {
        return bookGoodsMapper.deleteBookGoodsByIds(Convert.toStrArray(ids));
    }

    /**
     * 根据商品ID删除预订活动
     * @param goodsIds 商品ID
     * @return
     */
    @Override
    public int deleteBookGoodsByGoodsIds(String[] goodsIds) {
        return bookGoodsMapper.deleteBookGoodsByGoodsIds(goodsIds);
    }

    @Override
    public BookGoods getByGoodsId(Long goodsId) {
        return bookGoodsMapper.selectByGoodsId(goodsId);
    }

}
