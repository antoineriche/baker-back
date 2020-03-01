package com.gaminho.bakerproject.repository;

import com.gaminho.bakerproject.domain.Command;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CommandRepository extends JpaRepository<Command, Long> {

    @Override
    List<Command> findAll();

    @Override
    Optional<Command> findById(Long aLong);

    List<Command> findAllByRestaurant_Id(Long restaurantId);

    List<Command> findAllByDate(LocalDate date);

    List<Command> findAllByRestaurant_IdAndDate(Long restaurantId, LocalDate date);

}
