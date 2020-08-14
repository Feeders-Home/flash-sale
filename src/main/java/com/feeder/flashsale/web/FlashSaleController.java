package com.feeder.flashsale.web;


import com.feeder.flashsale.service.FlashSaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FlashSaleController {

    private FlashSaleService flashSaleService;

    public FlashSaleController(FlashSaleService flashSaleService) {
        this.flashSaleService = flashSaleService;
    }

    @PostMapping(value = "/orders",consumes = "application/json")
    @ResponseBody
    public ResponseEntity addOrder(@RequestParam("goodsId") Long goodsId,
                                   @RequestParam( "quantity") Integer quantity,
                                   @RequestParam("customerId") Long customerId){

        return ResponseEntity.ok(flashSaleService.addOrder(goodsId,quantity,customerId));
    }
}
