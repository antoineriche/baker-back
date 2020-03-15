package com.gaminho.bakerproject.repository;

import com.gaminho.bakerproject.domain.Command;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CommandRepository extends JpaRepository<Command, Long> {

    @Override
    List<Command> findAll();

    @Override
    Optional<Command> findById(Long aLong);

    @Query(value = "SELECT * FROM command ORDER BY date DESC", nativeQuery = true)
    List<Command> findAllOrderByDateDesc();

    List<Command> findAllByRestaurant_IdOrderByDateDesc(Long restaurantId);

    List<Command> findAllByDate(LocalDate date);

    List<Command> findAllByRestaurant_IdAndDateOrderByDateDesc(Long restaurantId, LocalDate date);

}
