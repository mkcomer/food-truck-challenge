package org.mkcomer.foodtruck.foodtruckapi.resources;

import org.mkcomer.foodtruck.foodtruckapi.models.FoodTruck;
import org.mkcomer.foodtruck.foodtruckapi.services.FoodTruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/foodtrucks")
public class FoodTruckResource {

    private static final Logger logger = LoggerFactory.getLogger(FoodTruckResource.class);

    @Autowired
    FoodTruckService foodTruckService;

    @RequestMapping(value="/hello")
    public String sayHello(){
        return "Hello";
    }

    @PostMapping("foodtrucks")
    public String getFoodTrucks(@RequestBody Map<String,Object> foodTruckMap){

        String locationID = (String) foodTruckMap.get("locationId");

        logger.info("Getting food truck by ID: " +  locationID);
        int locationInt =Integer.parseInt(locationID); 
        

        String foodTruckName = foodTruckService.getFoodTruck(locationInt);
        // String longitudeString = (String) foodTruckMap.get("long");
        // String latitudeString = (String) foodTruckMap.get("lat");

        return foodTruckName;
    }

    @PostMapping("long")
    public String getFoodTrucksByLongitude(@RequestBody Map<String,Object> foodTruckMap){
        
        String longitudeString = (String) foodTruckMap.get("longitude");
        Double longitudeDouble = Double.parseDouble(longitudeString);

        String latitudeString = (String) foodTruckMap.get("latitude");
        Double latitudeDouble = Double.parseDouble(latitudeString);
        logger.info("Getting food truck by longitude: " + longitudeString + " and latitude: " + latitudeString);

        List<FoodTruck> returnedTrucks = foodTruckService.getFoodTrucksByLong(longitudeDouble, latitudeDouble);

        String returnData = returnedTrucks.toString();

        return returnData;
    }
    
}
