package org.mkcomer.foodtruck.foodtruckapi.repositories;

import org.mkcomer.foodtruck.foodtruckapi.exceptions.FoodTruckException;
import org.mkcomer.foodtruck.foodtruckapi.models.FoodTruck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FoodTruckRepositoryImpl implements FoodTruckRepository{


    private static String SQL_FIND_ID = "SELECT applicant FROM trucks WHERE locationid=?";
    private static String SQL_FIND_LONG = "SELECT * FROM trucks WHERE longitude=?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public String findById(Integer locationId) throws FoodTruckException {
        String foodTruckName =  (String) jdbcTemplate.queryForObject(
            SQL_FIND_ID, String.class, locationId);
        return foodTruckName;
    }

    @Override
    public List<FoodTruck> findbyLong(Double longitude) throws FoodTruckException {
        // TODO Auto-generated method stub
        var rowMapper = BeanPropertyRowMapper.newInstance(FoodTruck.class);

        List<FoodTruck> foodTrucks = jdbcTemplate.query(SQL_FIND_LONG, rowMapper, longitude);

        return foodTrucks;
    }
}