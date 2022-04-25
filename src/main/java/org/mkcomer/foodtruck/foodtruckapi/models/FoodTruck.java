package org.mkcomer.foodtruck.foodtruckapi.models;

import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;

@Getter
@Setter 
@NoArgsConstructor 
@EqualsAndHashCode
@ToString 
public class FoodTruck implements Serializable {

    @ToString.Exclude
    private Long id;
    
    @ToString.Exclude
    private Long locationid;

    private String applicant;

    private String address;
  
    private String fooditems;
  
    @ToString.Exclude
    private Double latitude;
  
    @ToString.Exclude
    private Double longitude;
  
    private Double zipcodes;

    @ToString.Exclude
    private Double geom;
}