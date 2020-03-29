package com.gaminho.bakerproject.service;

import com.gaminho.bakerproject.domain.Command;
import com.gaminho.bakerproject.domain.Restaurant;
import com.gaminho.bakerproject.repository.CommandRepository;
import com.gaminho.bakerproject.repository.RestaurantRepository;
import com.gaminho.bakerproject.service.dto.CommandDTO;
import com.gaminho.bakerproject.service.mapper.CommandMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CommandService {

    private static final Logger log = LoggerFactory.getLogger(CommandService.class);


    @Autowired
    private CommandRepository commandRepository;

    @Autowired
    private CommandMapper commandMapper;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Command> getAllCommands() {
        return commandRepository.findAllOrderByDateDesc();
    }

    public Optional<Command> getCommand(Long id) {
        return commandRepository.findById(id);
    }

    public Command saveNewCommand(CommandDTO commandDTO) {

        final Restaurant restaurant = restaurantRepository.findById(commandDTO.getRestaurantId()).orElseThrow(
                () -> new IllegalArgumentException("Can not find restaurant with id " + commandDTO.getRestaurantId()));
        final Command command =  commandRepository.save(commandMapper.toEntity(commandDTO));
//        restaurant.getCommands().add(command);
//        restaurantRepository.save(restaurant);
        return command;
    }

    public Command updateCommand(CommandDTO commandDTO) {
        final Command command = commandMapper.toEntity(commandDTO);
        return commandRepository.save(command);
    }

    public void deleteCommand(Long commandId) {
        commandRepository.deleteById(commandId);
    }

    public List<Command> findByDate(LocalDate date) {
        return commandRepository.findAllByDate(date);
    }

    public List<Command> findByRestaurant(Long restaurantId) {
        if (restaurantRepository.findById(restaurantId).isPresent()) {
            return commandRepository.findAllByRestaurant_IdOrderByDateDesc(restaurantId);
        } else {
            log.error("No restaurant found with id: {}", restaurantId);
            return Collections.emptyList();
        }
    }

    public List<Command> findByDateAndRestaurant(Long restaurantId, LocalDate date) {
        if (restaurantRepository.findById(restaurantId).isPresent()) {
            return commandRepository.findAllByRestaurant_IdAndDateOrderByDateDesc(restaurantId, date);
        } else {
            log.error("No restaurant found with id: {}", restaurantId);
            return Collections.emptyList();
        }
    }


}
