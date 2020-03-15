package com.gaminho.bakerproject.web.mapper;

import com.gaminho.bakerproject.domain.Command;
import com.gaminho.bakerproject.domain.Restaurant;
import com.gaminho.bakerproject.web.dto.CommandDTO;
import java.time.format.DateTimeFormatter;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-15T15:26:52+0100",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_111 (Oracle Corporation)"
)
@Component
public class CommandMapperImpl implements CommandMapper {

    @Override
    public CommandDTO toDTO(Command command) {
        if ( command == null ) {
            return null;
        }

        CommandDTO commandDTO = new CommandDTO();

        if ( command.getDate() != null ) {
            commandDTO.setDate( DateTimeFormatter.ofPattern( "yyyy-MM-dd" ).format( command.getDate() ) );
        }
        commandDTO.setCount( command.getCount() );
        commandDTO.setId( command.getId() );
        long id = commandRestaurantId( command );
        commandDTO.setRestaurantId( id );

        return commandDTO;
    }

    @Override
    public Command toEntity(CommandDTO commandDTO) {
        if ( commandDTO == null ) {
            return null;
        }

        Command command = new Command();

        command.setRestaurant( commandDTOToRestaurant( commandDTO ) );
        if ( commandDTO.getDate() != null ) {
            command.setDate( java.time.LocalDate.parse( commandDTO.getDate(), DateTimeFormatter.ofPattern( "yyyy-MM-dd" ) ) );
        }
        command.setCount( commandDTO.getCount() );
        command.setId( commandDTO.getId() );

        return command;
    }

    private long commandRestaurantId(Command command) {
        if ( command == null ) {
            return 0L;
        }
        Restaurant restaurant = command.getRestaurant();
        if ( restaurant == null ) {
            return 0L;
        }
        long id = restaurant.getId();
        return id;
    }

    protected Restaurant commandDTOToRestaurant(CommandDTO commandDTO) {
        if ( commandDTO == null ) {
            return null;
        }

        Restaurant restaurant = new Restaurant();

        restaurant.setId( commandDTO.getRestaurantId() );

        return restaurant;
    }
}
