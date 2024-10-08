package com.example.ecom.adapter;

import org.springframework.stereotype.Component;

import com.example.ecom.libraries.GoogleMapsApi;
import com.example.ecom.models.Address;

@Component
public class GoogleMapAdapter extends MapAdapter{

    private GoogleMapsApi googleMapsApi;
    
    public GoogleMapAdapter(){
        this.googleMapsApi = new GoogleMapsApi();
    }

    @Override
    public int estimateTime(Address src, Address dest) {
        return googleMapsApi.estimate(null, null);
    }
    
}
