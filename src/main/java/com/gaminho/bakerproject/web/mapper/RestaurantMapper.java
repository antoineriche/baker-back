package com.gaminho.bakerproject.web.mapper;

import com.gaminho.bakerproject.domain.Restaurant;
import com.gaminho.bakerproject.web.dto.RestaurantDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    @Mappings({
            @Mapping(target="id", source = "restaurant.id"),
            @Mapping(target="name", source = "restaurant.name"),
            @Mapping(target="address", source = "restaurant.address"),
            @Mapping(target="mailAddress", source = "restaurant.mailAddress"),
            @Mapping(target="phone", source = "restaurant.phone")})
    RestaurantDTO toDTO(Restaurant restaurant);

    @Mappings({
            @Mapping(target="id", source = "restaurantDTO.id"),
            @Mapping(target="name", source = "restaurantDTO.name"),
            @Mapping(target="address", source = "restaurantDTO.address"),
            @Mapping(target="mailAddress", source = "restaurantDTO.mailAddress"),
            @Mapping(target="phone", source = "restaurantDTO.phone")})
    Restaurant toEntity(RestaurantDTO restaurantDTO);

    default List<RestaurantDTO> toDTOs(List<Restaurant> commands) {
        return commands.stream().map(this::toDTO).collect(Collectors.toList());
    }

    default List<Restaurant> toEntities(List<RestaurantDTO> commands) {
        return commands.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
