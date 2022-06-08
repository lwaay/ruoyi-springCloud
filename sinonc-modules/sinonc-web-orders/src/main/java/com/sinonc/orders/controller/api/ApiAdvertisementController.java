package com.sinonc.orders.controller.api;

import com.sinonc.common.core.domain.R;
import com.sinonc.orders.domain.OdAdvertisement;
import com.sinonc.orders.service.IOdAdvertisementService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * app首页广告图片接口
 *
 * @author Administrator
 * @anthor wang
 */
@RestController
@RequestMapping("api/advertisement")
public class ApiAdvertisementController {
    @Autowired
    private IOdAdvertisementService advertisementService;

    @GetMapping("/list")
    public R<List<OdAdvertisement>> list(OdAdvertisement odAdvertisement) {
        List<OdAdvertisement> list = advertisementService.selectOdAdvertisementList(odAdvertisement);
        return R.ok(list);
    }
}
