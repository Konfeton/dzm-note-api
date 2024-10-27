package com.onkonfeton.mapper;

import com.onkonfeton.dto.NoteDto;
import com.onkonfeton.entity.Note;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface NoteMapper {

    NoteMapper INSTANCE = Mappers.getMapper(NoteMapper.class);

    NoteDto toDto(Note account);
    Note toEntity(NoteDto accountDto);
}
