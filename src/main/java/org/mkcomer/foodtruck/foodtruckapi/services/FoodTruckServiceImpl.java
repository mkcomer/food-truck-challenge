package org.mkcomer.foodtruck.foodtruckapi.services;

import javax.transaction.Transactional;

import org.mkcomer.foodtruck.foodtruckapi.exceptions.FoodTruckException;
import org.mkcomer.foodtruck.foodtruckapi.models.FoodTruck;
import org.mkcomer.foodtruck.foodtruckapi.repositories.FoodTruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// single transaction on the DB
@Transactional
public class FoodTruckServiceImpl implements FoodTruckService{

    @Autowired
    FoodTruckRepository foodTruckRepository;

    @Override
    public String getFoodTruck(Integer locationId) throws FoodTruckException {
        String foodTruck = foodTruckRepository.findById(locationId);
        return foodTruck;
    }
    // 

    @Override
    public List<FoodTruck> getFoodTrucksByLong(Double longitude) throws FoodTruckException {
        List<FoodTruck> foodTruckList = foodTruckRepository.findbyLong(longitude);
        return foodTruckList;
    }

}
