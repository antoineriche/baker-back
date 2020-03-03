package com.gaminho.bakerproject.service;

import com.gaminho.bakerproject.domain.Restaurant;
import com.gaminho.bakerproject.repository.RestaurantRepository;
import com.gaminho.bakerproject.web.dto.RestaurantDTO;
import com.gaminho.bakerproject.web.mapper.RestaurantMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    private static final Logger log = LoggerFactory.getLogger(RestaurantService.class);

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantMapper restaurantMapper;

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Optional<Restaurant> getRestaurant(Long id) {
        return restaurantRepository.findById(id);
    }

    public Restaurant saveNewRestaurant(RestaurantDTO restaurantDTO) {
        return restaurantRepository.save(restaurantMapper.toEntity(restaurantDTO));
    }

}
