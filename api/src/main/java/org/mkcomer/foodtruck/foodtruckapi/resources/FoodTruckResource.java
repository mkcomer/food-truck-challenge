package org.mkcomer.foodtruck.foodtruckapi.resources;

import org.mkcomer.foodtruck.foodtruckapi.exceptions.FoodTruckException;
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

    @RequestMapping(value="/healthcheck")
    public String healthcheck(){
        return "Food Truck Service Active";
    }

    @PostMapping("id")
    public String getFoodTrucks(@RequestBody Map<String,Object> foodTruckMap){

        String locationID = null;

        if (foodTruckMap !=null && !foodTruckMap.isEmpty()){
            locationID = (String) foodTruckMap.get("locationId");
            logger.info("Getting food truck by ID: " +  locationID);     }
            String foodTruckName = null;

            try{
                int locationInt =Integer.parseInt(locationID); 
                foodTruckName = foodTruckService.getFoodTruck(locationInt);
            }catch(FoodTruckException e){
                logger.error("Exception thrown querying database: {}", e.toString());
            }

        return foodTruckName;
    }

    @PostMapping("location")
    public String getFoodTrucksByLocation(@RequestBody Map<String,Object> foodTruckMap){
        
        String longitudeString = null;
        String latitudeString = null;

        if (foodTruckMap !=null && !foodTruckMap.isEmpty()){
            longitudeString= (String) foodTruckMap.get("longitude");
            latitudeString = (String) foodTruckMap.get("latitude");
            logger.info("Retrieving foodtrucks near longitude: " + longitudeString + " and latitude: " + latitudeString);
        }

        List<FoodTruck> returnList = null;
        
        try {
            Double longitudeDouble = Double.parseDouble(longitudeString);
            Double latitudeDouble = Double.parseDouble(latitudeString);
            // returnList = foodTruckService.getFoodTrucksByLong(longitudeDouble, latitudeDouble);
            returnList = foodTruckService.getFoodTrucksByLocation(longitudeDouble, latitudeDouble);
        } catch (FoodTruckException e) {
            logger.error("Exception thrown querying database: {}", e.toString());
        }

        return returnList.toString();
    }
    
}
