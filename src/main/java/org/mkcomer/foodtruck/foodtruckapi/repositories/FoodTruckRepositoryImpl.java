package org.mkcomer.foodtruck.foodtruckapi.repositories;

import org.mkcomer.foodtruck.foodtruckapi.exceptions.FoodTruckException;
import org.mkcomer.foodtruck.foodtruckapi.models.FoodTruck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.Console;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Repository
public class FoodTruckRepositoryImpl implements FoodTruckRepository{
    private static final Logger logger = LoggerFactory.getLogger(FoodTruckRepositoryImpl.class);

    private static String FIND_TRUCK_BY_ID = "SELECT applicant FROM trucks WHERE locationid=?";
    // TODO parameterize number of nearest trucks 
    private static final Integer NEAREST_TRUCKS = 5;
    private static final String NUM_QUERY = " LIMIT " + NEAREST_TRUCKS;
    private static final String NEAREST_TRUCKS_BY_LONG_LAT = "SELECT applicant, address, fooditems, zipcodes FROM trucks ORDER BY geom <-> ST_GeomFromText ";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public String findById(Integer locationId) throws FoodTruckException {
        String foodTruckName =  (String) jdbcTemplate.queryForObject(
            FIND_TRUCK_BY_ID, String.class, locationId);
        return foodTruckName;
    }

    @Override
    public List<FoodTruck> findbyLongLat(Double longitude, Double latitude) throws FoodTruckException {
        //format for location point query: ('POINT(-122.4273064 37.7620192)', 4326)
        String pointLongLat = "('POINT(" + longitude + " " + latitude +")', 4326)";  
        StringBuilder stringBuilder = new StringBuilder(100);
        String query = stringBuilder.append(NEAREST_TRUCKS_BY_LONG_LAT).append(pointLongLat).append(NUM_QUERY).toString();
        logger.debug(query);

        var rowMapper = BeanPropertyRowMapper.newInstance(FoodTruck.class);
        List<FoodTruck> foodTrucks = jdbcTemplate.query(query, rowMapper);

        return foodTrucks;
    }


}