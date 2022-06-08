package com.sinonc.base.controller.api;

import com.sinonc.base.api.domain.CropDict;
import com.sinonc.base.service.ICropDictService;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.web.controller.BaseController;
import com.sun.scenario.effect.Crop;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author huanghao
 * @apiNote TODO
 * @date 2022/2/16 15:55
 */
@Api(tags = "农作物字典")
@RestController
@RequestMapping("api/dict/")
public class ApiCropDictController extends BaseController {

    @Autowired
    private ICropDictService cropDictService;


    /**
     * 获取作物字典详细信息
     */
    @GetMapping(value = "/{cropId}")
    public R<CropDict> getInfo(@PathVariable("cropId") Long cropId) {
        CropDict cropDict = cropDictService.selectCropDictById(cropId);
        return R.ok(cropDict);
    }

    @GetMapping(value = "/getchild/{parentId}")
    public R<Long[]> getchildId(@PathVariable("parentId") Long parentId) {
        CropDict param = new CropDict();
        param.setParentId(parentId);
        List<CropDict> list = cropDictService.selectCropDictList(param);
        if(list.size()>0){
            Long[] child = new Long[list.size()];
            int a = 0;
            for(CropDict tmp : list){
                child[a] = tmp.getCropId();
                a++;
            }
            return R.ok(child);
        }else{
            return R.fail("无数据");
        }

    }

    @GetMapping(value = "/getchildnode/{parentId}")
    public R<List<CropDict>> getchild(@PathVariable("parentId") Long parentId) {
        CropDict param = new CropDict();
        param.setParentId(parentId);
        List<CropDict> list = cropDictService.selectCropDictList(param);
        if(list.size()>0){
            return R.ok(list);
        }else{
            return R.fail("无数据");
        }

    }
}
