package com.gaminho.bakerproject.repository;

import com.gaminho.bakerproject.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Override
    Optional<Restaurant> findById(Long aLong);

    @Override
    List<Restaurant> findAll();

    List<Restaurant> findAllByName(String name);
}
