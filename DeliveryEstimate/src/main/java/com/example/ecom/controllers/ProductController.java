package com.example.ecom.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.example.ecom.dtos.DeliveryEstimateRequestDto;
import com.example.ecom.dtos.DeliveryEstimateResponseDto;
import com.example.ecom.dtos.ResponseStatus;
import com.example.ecom.services.ProductService;

@Component
public class ProductController {

    @Autowired
    private ProductService productService;

    public DeliveryEstimateResponseDto estimateDeliveryTime(DeliveryEstimateRequestDto requestDto){

        DeliveryEstimateResponseDto responseDto = new DeliveryEstimateResponseDto();
        try{
            Date estimateDeliveryDate = productService.estimateDeliveryDate(requestDto.getProductId(), 
            requestDto.getAddressId());
            responseDto.setExpectedDeliveryDate(estimateDeliveryDate);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }catch(Exception exception){
            responseDto.setExpectedDeliveryDate(null);
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }

        return responseDto;
    }
}
