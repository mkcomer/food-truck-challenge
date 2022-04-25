package org.mkcomer.foodtruck.foodtruckapi.repositories;

import org.mkcomer.foodtruck.foodtruckapi.exceptions.FoodTruckException;
import org.mkcomer.foodtruck.foodtruckapi.models.FoodTruck;

import java.util.List;

public interface FoodTruckRepository {
    
    String findById(Integer locationId) throws FoodTruckException;

    List<FoodTruck> findbyLongLat(Double longitude, Double latitude) throws FoodTruckException;

}
