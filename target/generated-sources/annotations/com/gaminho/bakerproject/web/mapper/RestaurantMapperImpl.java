package com.gaminho.bakerproject.web.mapper;

import com.gaminho.bakerproject.domain.Restaurant;
import com.gaminho.bakerproject.web.dto.RestaurantDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-15T15:26:52+0100",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_111 (Oracle Corporation)"
)
@Component
public class RestaurantMapperImpl implements RestaurantMapper {

    @Override
    public RestaurantDTO toDTO(Restaurant restaurant) {
        if ( restaurant == null ) {
            return null;
        }

        RestaurantDTO restaurantDTO = new RestaurantDTO();

        restaurantDTO.setName( restaurant.getName() );
        restaurantDTO.setMailAddress( restaurant.getMailAddress() );
        restaurantDTO.setAddress( restaurant.getAddress() );
        restaurantDTO.setId( restaurant.getId() );
        restaurantDTO.setPhone( restaurant.getPhone() );

        return restaurantDTO;
    }

    @Override
    public Restaurant toEntity(RestaurantDTO restaurantDTO) {
        if ( restaurantDTO == null ) {
            return null;
        }

        Restaurant restaurant = new Restaurant();

        restaurant.setName( restaurantDTO.getName() );
        restaurant.setMailAddress( restaurantDTO.getMailAddress() );
        restaurant.setAddress( restaurantDTO.getAddress() );
        restaurant.setId( restaurantDTO.getId() );
        restaurant.setPhone( restaurantDTO.getPhone() );

        return restaurant;
    }
}
