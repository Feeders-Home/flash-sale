package com.feeder.flashsale.web;

import com.feeder.flashsale.entity.Goods;
import com.feeder.flashsale.service.GoodsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class GoodsController {
    private GoodsService goodsService;

    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @GetMapping("/goods/{id}")
    public ResponseEntity<Goods> getGoods(@PathParam("id") Long id) {
        return ResponseEntity.ok(goodsService.getGoodsByID(id));
    }
}
