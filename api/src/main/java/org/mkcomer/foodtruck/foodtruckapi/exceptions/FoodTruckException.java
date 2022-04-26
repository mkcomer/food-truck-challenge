package org.mkcomer.foodtruck.foodtruckapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FoodTruckException extends RuntimeException{

    public FoodTruckException(String message){
        super(message);
    }
}
