package com.sinonc.orders.controller.api;

import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.security.service.TokenService;
import com.sinonc.order.api.domain.Shop;
import com.sinonc.orders.domain.*;
import com.sinonc.orders.mapper.UserRelationMapper;
import com.sinonc.orders.service.AccountFlowService;
import com.sinonc.orders.service.AccountTaskService;
import com.sinonc.orders.service.IShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "账户提现")
@Controller
@RequestMapping("api/seller/orders/accountTask")
public class ApiAccountTaskController extends BaseController {

    @Autowired
    private AccountTaskService accountTaskService;

    @Autowired
    private TokenService tokenService;

    @Resource
    private UserRelationMapper userRelationMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private IShopService shopService;

    @Resource
    private AccountFlowService accountFlowService;

    //redis key前缀
    private String prefix = "Member_" ;

    /**
     * 新增提现申请
     */
    @ApiOperation(value = "新增提现申请")
    @PostMapping("/addAccountTask")
    @ResponseBody
    public AjaxResult addAccountTask(AccountTask accountTask) {
        if (StringUtils.isEmpty(accountTask.getPhoneCode())) {
            return AjaxResult.error("验证码不能为空");
        }
        try {
            Long deptId = tokenService.getLoginUser().getSysUser().getDeptId();
            UserRelation userRelation = userRelationMapper.selectByDeptId(deptId);
            //获取店铺
            Shop shopById = shopService.selectShopById(userRelation.getShopId());
            String key = prefix + shopById.getConcatNumber();
            //验证短信验证码是否正确
            String rdsCode = redisTemplate.opsForValue().get(key);

            if (!accountTask.getPhoneCode().equals(rdsCode)) {
                return AjaxResult.error("短信验证码错误");
            }
            //验证成功之后删除redis中的验证码
            redisTemplate.delete(key);
            accountTask.setShopIdP(userRelation.getShopId());

            int row = accountTaskService.addAccountTask(accountTask);
            if (row > 0) {
                return AjaxResult.success("提现申请成功");
            } else {
                return AjaxResult.error("提现金额不得大于可用金额");
            }

        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }

    }

    /**
     * 查询提现记录
     */
    @ApiOperation(value = "查询提现记录")
    @GetMapping("/queryAccountTaskList")
    @ResponseBody
    public AjaxResult queryAccountTaskList(AccountTask accountTask) {
        try {
            Long userId = tokenService.getLoginUser().getUserid();
            accountTask.setUserIdP(userId);
            List<AccountTask> accountTaskList = accountTaskService.queryAccountTaskList(accountTask);
            Map rsMap = new HashMap(1);
            rsMap.put("accountTaskList", accountTaskList);
            return AjaxResult.success(rsMap);
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }

    }

    /**
     * 查询账户信息接口
     */
    @ApiOperation(value = "查询账户信息接口")
    @GetMapping("/queryAccountTaskStatistic")
    @ResponseBody
    public AjaxResult queryAccountTaskStatistic() {
        Long deptId = tokenService.getLoginUser().getSysUser().getDeptId();
        Long userId = tokenService.getLoginUser().getUserid();;

        UserRelation userRelation = userRelationMapper.selectByDeptId(deptId);
        //判断是否存在店铺关联关系
        if (userRelation != null) {
            //存在则返回
            AccountTaskStatisticData statisticData = accountTaskService.queryAccountTaskStatistic(userId, userRelation.getShopId());

            AjaxResult success = AjaxResult.success(statisticData);
            success.put("hasShop", true);

            return success;
        } else {

            AjaxResult success = AjaxResult.success("没有店铺信息");

            success.put("hasShop", false);

            return success;
        }

    }

    /**
     * 查询账户交易流水
     */
    @ApiOperation(value = "查询账户交易流水")
    @GetMapping("/queryAccountFlow")
    @ResponseBody
    public AjaxResult queryAccountFlow(AccountFlow accountFlow) {
        try {
//			Long userId = ShiroUtils.getSysUser().getUserId();
//			//accountFlow.setUserIdP(userId);
            List<AccountFlow> accountTaskList = accountFlowService.listAccountFlow(accountFlow);
            Map rsMap = new HashMap(1);
            rsMap.put("accountTaskList", accountTaskList);
            return AjaxResult.success(rsMap);
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }

    }

}
