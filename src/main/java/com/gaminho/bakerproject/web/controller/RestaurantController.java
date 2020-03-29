package com.gaminho.bakerproject.web.controller;

import com.gaminho.bakerproject.domain.Restaurant;
import com.gaminho.bakerproject.service.RestaurantService;
import com.gaminho.bakerproject.service.dto.CommandDTO;
import com.gaminho.bakerproject.service.dto.RestaurantDTO;
import com.gaminho.bakerproject.service.mapper.RestaurantMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/restaurants")
public class RestaurantController {

    private static final Logger log = LoggerFactory.getLogger(RestaurantController.class);

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RestaurantMapper restaurantMapper;

    @GetMapping
    public ResponseEntity<?> getAllRestaurants() {
        return ResponseEntity.ok(restaurantMapper.toDTOs(restaurantService.getAllRestaurants()));
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<?> getRestaurant(@PathVariable Long restaurantId) {
        log.info("Getting restaurant: {}", restaurantId);
        Optional<Restaurant> optRestaurant = restaurantService.getRestaurant(restaurantId);
        if(optRestaurant.isPresent()) {
            return ResponseEntity.ok(restaurantMapper.toDTO(optRestaurant.get()));
        } else {
            throw new IllegalArgumentException("Can not find restaurant with id " + restaurantId);
        }
    }

    @PostMapping
    public ResponseEntity<?> createRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        log.info("Creating restaurant: {}", restaurantDTO);
        return ResponseEntity.ok(restaurantMapper.toDTO(restaurantService.saveNewRestaurant(restaurantDTO)));
    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<?> updateCommand(@PathVariable Long restaurantId,
                                           @RequestBody RestaurantDTO restaurantDTO) {
        log.info("Updating restaurant: {} ({})", restaurantDTO, restaurantId);
        if (restaurantService.getRestaurant(restaurantId).isPresent()) {
            return ResponseEntity.ok(restaurantMapper.toDTO(restaurantService.updateRestaurant(restaurantDTO)));
        } else {
            return ResponseEntity.badRequest().body("Can not find restaurant with id: " + restaurantId);
        }
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable Long restaurantId) {
        log.info("Deleting restaurant: {}", restaurantId);
        if(restaurantService.getRestaurant(restaurantId).isPresent()) {
            restaurantService.deleteRestaurant(restaurantId);
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.badRequest().body("Can not find restaurant with id: " + restaurantId);
        }
    }
}
