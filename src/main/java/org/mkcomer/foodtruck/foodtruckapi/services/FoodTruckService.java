package org.mkcomer.foodtruck.foodtruckapi.services;

import java.util.List;

import org.mkcomer.foodtruck.foodtruckapi.exceptions.FoodTruckException;
import org.mkcomer.foodtruck.foodtruckapi.models.FoodTruck;

public interface FoodTruckService {

    String getFoodTruck(Integer locationId) throws FoodTruckException;

    List<FoodTruck> getFoodTrucksByLong(Double longitude) throws FoodTruckException;
    
}
