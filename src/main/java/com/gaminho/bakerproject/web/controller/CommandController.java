package com.gaminho.bakerproject.web.controller;

import com.gaminho.bakerproject.domain.Command;
import com.gaminho.bakerproject.service.CommandService;
import com.gaminho.bakerproject.utils.DateUtils;
import com.gaminho.bakerproject.service.dto.CommandDTO;
import com.gaminho.bakerproject.service.mapper.CommandMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/commands")
public class CommandController {

    private static final Logger log = LoggerFactory.getLogger(CommandController.class);

    @Autowired
    private CommandService commandService;

    @Autowired
    private CommandMapper commandMapper;

    @GetMapping
    public ResponseEntity<?> getAllCommands(
            @RequestParam(name = "date", required = false) String strDate,
            @RequestParam(name = "restaurant", required = false) Long restaurantId) {

        final LocalDate date = DateUtils.fromString(strDate);
        final List<Command> commandList;

        if(null != date && null != restaurantId) {
            log.info("Getting all commands with date ({}) and restaurant ({})", date, restaurantId);
            commandList = commandService.findByDateAndRestaurant(restaurantId, date);
        } else  if(null != restaurantId) {
            log.info("Getting all commands with restaurant ({})", restaurantId);
            commandList = commandService.findByRestaurant(restaurantId);
        } else if(null != date) {
            log.info("Getting all commands with date ({})", date);
            commandList = commandService.findByDate(date);
        } else {
            log.info("Getting all commands");
            commandList = commandService.getAllCommands();
        }

        return ResponseEntity.ok(commandMapper.toDTOs(commandList));
    }

    @GetMapping("/{commandId}")
    public ResponseEntity<?> getCommand(@PathVariable Long commandId) {
        log.info("Getting command: {}", commandId);
        Optional<Command> optCommand = commandService.getCommand(commandId);
        if(optCommand.isPresent()) {
            return ResponseEntity.ok(commandMapper.toDTO(optCommand.get()));
        } else {
            throw new IllegalArgumentException("Can not find command with id: " + commandId);
        }
    }

    @DeleteMapping("/{commandId}")
    public ResponseEntity<?> deleteCommand(@PathVariable Long commandId) {
        log.info("Deleting command: {}", commandId);
        if(commandService.getCommand(commandId).isPresent()) {
            commandService.deleteCommand(commandId);
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.badRequest().body("Can not find command with id: " + commandId);
        }
    }

    @PostMapping
    public ResponseEntity<?> createCommand(@RequestBody CommandDTO commandDTO) {
        log.info("Creating command: {}", commandDTO);
        return ResponseEntity.ok(commandMapper.toDTO(commandService.saveNewCommand(commandDTO)));
    }

    @PutMapping("/{commandId}")
    public ResponseEntity<?> updateCommand(@PathVariable Long commandId,
                                           @RequestBody CommandDTO commandDTO) {
        log.info("Updating command: {}", commandId);
        if (commandService.getCommand(commandId).isPresent()) {
            return ResponseEntity.ok(commandMapper.toDTO(commandService.updateCommand(commandDTO)));
        } else {
            return ResponseEntity.badRequest().body("Can not find command with id: " + commandId);
        }
    }
}
