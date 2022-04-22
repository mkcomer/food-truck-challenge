package org.mkcomer.foodtruck.foodtruckapi.models;

import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


// @Entity
// @Table(name = "trucks")
@Getter
@Setter 
@NoArgsConstructor 
@EqualsAndHashCode
@ToString 
public class FoodTruck implements Serializable {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "index")
    @ToString.Exclude
    private Long id;
    
    // @Column(name = "locationid")
    @ToString.Exclude
    private Long locationid;
  
    // @Column(name = "applicant")
    private String applicant;

    // @Column(name = "address")
    private String address;
  
    // @Column(name = "fooditems")
    private String fooditems;
  
    // @Column(name = "latitude")
    @ToString.Exclude
    private Double latitude;
  
    // @Column(name = "longitude")
    @ToString.Exclude
    private Double longitude;
  
    // @Column(name = "zipcodes")
    private Double zipcodes;

    @ToString.Exclude
    private Double geom;
}