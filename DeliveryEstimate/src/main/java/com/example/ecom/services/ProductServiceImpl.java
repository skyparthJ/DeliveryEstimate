package com.example.ecom.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecom.adapter.GoogleMapAdapter;
import com.example.ecom.adapter.MapAdapter;
import com.example.ecom.exceptions.AddressNotFoundException;
import com.example.ecom.exceptions.ProductNotFoundException;
import com.example.ecom.models.Address;
import com.example.ecom.models.DeliveryHub;
import com.example.ecom.models.Product;
import com.example.ecom.repositories.AddressRepository;
import com.example.ecom.repositories.DeliveryHubRepository;
import com.example.ecom.repositories.ProductRepository;
import com.example.ecom.repositories.SellerRepository;
import com.example.ecom.repositories.UserRepository;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private DeliveryHubRepository deliveryHubRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private MapAdapter mapAdapter;

    @Override
    public Date estimateDeliveryDate(int productId, int addressId)
            throws ProductNotFoundException, AddressNotFoundException {

            Optional<Address> addressOptional = addressRepository.findById(addressId);
            if(addressOptional.isEmpty()){
                throw new AddressNotFoundException("Address not found");
            }

            Address userAddress = addressOptional.get();

            Optional<Product> optionalProduct = productRepository.findById(productId);
            if(optionalProduct.isEmpty()){
                throw new ProductNotFoundException("Product not found");
            }

            Product product = optionalProduct.get();
            Address sellerAddress =  product.getSeller().getAddress();

            List<DeliveryHub> deliveryHubList = deliveryHubRepository.findAll();

            if(deliveryHubList.isEmpty()){
                throw new AddressNotFoundException("DeliveryHubs are not available");
            }

            Address deliveryHubAddress = null;
            for(DeliveryHub deliveryHub : deliveryHubList){
                if(deliveryHub.getAddress().getZipCode().equals(sellerAddress.getZipCode())){
                    deliveryHubAddress = deliveryHub.getAddress();
                    break;
                }
            }

            if(deliveryHubAddress == null){
                throw new AddressNotFoundException("DeliveryHub not found in seller address zip code");
            }

            int deliveryTime = mapAdapter.estimateTime(sellerAddress, deliveryHubAddress) +
                                mapAdapter.estimateTime(deliveryHubAddress, userAddress); 

            Date currentDate = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);
            calendar.add(Calendar.SECOND, deliveryTime);
            
            return calendar.getTime();
    }
    
}
