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
    // private static String SQL_NOW = "SELECT * FROM trucks ORDER BY geom <-> ST_GeomFromText ('POINT(longitude=? latitude=?)', 4326)";
    private static String SQL_NOW = "SELECT applicant, address, fooditems, zipcodes FROM trucks ORDER BY geom <-> ST_GeomFromText ('POINT(-122.4273064 37.7620192)', 4326) LIMIT 5";
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public String findById(Integer locationId) throws FoodTruckException {
        String foodTruckName =  (String) jdbcTemplate.queryForObject(
            SQL_FIND_ID, String.class, locationId);
        return foodTruckName;
    }

    @Override
    public List<FoodTruck> findbyLong(Double longitude, Double latitude) throws FoodTruckException {
        // TODO Auto-generated method stub
        var rowMapper = BeanPropertyRowMapper.newInstance(FoodTruck.class);
        // TODO parameterize long and lat into sql query
        List<FoodTruck> foodTrucks = jdbcTemplate.query(SQL_NOW, rowMapper);

        return foodTrucks;
    }


}