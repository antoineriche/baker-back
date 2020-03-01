package com.gaminho.bakerproject.web.mapper;

import com.gaminho.bakerproject.domain.Command;
import com.gaminho.bakerproject.web.dto.CommandDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Mapper(componentModel = "spring")
public interface CommandMapper {

    @Mappings({
            @Mapping(target="id", source = "command.id"),
            @Mapping(target="count", source = "command.count"),
            @Mapping(target="restaurantId", source = "command.restaurant.id"),
            @Mapping(target="date", source = "command.date", dateFormat = "yyyy-MM-dd")})
    CommandDTO toDTO(Command command);

    @Mappings({
            @Mapping(target="id", source = "commandDTO.id"),
            @Mapping(target="count", source = "commandDTO.count"),
            @Mapping(target="restaurant.id", source = "commandDTO.restaurantId"),
            @Mapping(target="date", source = "commandDTO.date", dateFormat = "yyyy-MM-dd")})
    Command toEntity(CommandDTO commandDTO);

    default List<CommandDTO> toDTOs(List<Command> commands) {
        return commands.stream().map(this::toDTO).collect(Collectors.toList());
    }

    default List<Command> toEntities(List<CommandDTO> commands) {
        return commands.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
