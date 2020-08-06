package com.feeder.flashsale.web;

import com.feeder.flashsale.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class GoodsController {
    GoodsService goodsService;

    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @GetMapping
    public String getGoods(long id) {
        return "hello";
    }
}
