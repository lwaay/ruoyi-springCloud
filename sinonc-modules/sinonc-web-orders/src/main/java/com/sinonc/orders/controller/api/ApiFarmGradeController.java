package com.sinonc.orders.controller.api;

import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.orders.domain.FarmGrade;
import com.sinonc.orders.domain.GradeCategory;
import com.sinonc.orders.service.FarmGradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "基地评级接口")
@RestController
@RequestMapping("/api/farmGrade")
public class ApiFarmGradeController extends BaseController {

    @Autowired
    private FarmGradeService farmGradeService;


    @ApiOperation("根据基地ID查询评级类别")
    @GetMapping("selectGradeCategoryByFarmId")
    @SuppressWarnings("unchecked")
    public AjaxResult selectGradeCategoryByFarmId(String farmId) {
        try{
            FarmGrade farmGrade=farmGradeService.selectGradeCategoryByFarmId(farmId);
            return AjaxResult.success(farmGrade);
        }catch (Exception e){
            FarmGrade farmGrade=new FarmGrade();
            return AjaxResult.success(farmGrade);
        }

    }

    @ApiOperation("根据基地ID及评级类型查询详细项目")
    @GetMapping("selectCategoryItemByFarmId")
    @SuppressWarnings("unchecked")
    public AjaxResult selectCategoryItemByFarmId(String farmId,String categoryName) {
        try{
            GradeCategory gradeCategory=farmGradeService.selectCategoryItemByFarmId(farmId,categoryName);
            return AjaxResult.success(gradeCategory);
        }catch (Exception e){
            GradeCategory gradeCategory=new GradeCategory();
            return AjaxResult.error(e.getMessage());
        }

    }


}
