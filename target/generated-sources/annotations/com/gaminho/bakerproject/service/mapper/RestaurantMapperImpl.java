package com.gaminho.bakerproject.service.mapper;

import com.gaminho.bakerproject.domain.Restaurant;
import com.gaminho.bakerproject.service.dto.RestaurantDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-29T17:16:51+0200",
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

        restaurantDTO.setAddress( restaurant.getAddress() );
        restaurantDTO.setPhone( restaurant.getPhone() );
        restaurantDTO.setName( restaurant.getName() );
        restaurantDTO.setMailAddress( restaurant.getMailAddress() );
        restaurantDTO.setId( restaurant.getId() );
        restaurantDTO.setDescription( restaurant.getDescription() );

        restaurantDTO.setCommandCount( restaurant.getCommands().size() );

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
        restaurant.setDescription( restaurantDTO.getDescription() );

        return restaurant;
    }
}
