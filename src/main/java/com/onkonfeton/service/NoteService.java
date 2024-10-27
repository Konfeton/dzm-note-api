package com.onkonfeton.service;



import com.onkonfeton.dto.NoteDto;
import com.onkonfeton.entity.Note;
import com.onkonfeton.mapper.NoteMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public final class NoteService {

    private final List<Note> db = new ArrayList<>();

    {
        db.add(new Note(UUID.randomUUID(), "How to fight", "Kick", "Bruce Lee"));
        db.add(new Note(UUID.randomUUID(), "Sense of humor", "Laugh more", "Dzmitry"));
        db.add(new Note(UUID.randomUUID(), "How to make notes", "Just take notes and organize them", "Zettelkasten"));
    }

    private NoteMapper noteMapper = NoteMapper.INSTANCE;

    public Note save(NoteDto noteDto) {
        Note note = noteMapper.toEntity(noteDto);
        note.setId(UUID.randomUUID());
        db.add(note);
        return note;
    }

    public List<NoteDto> findAll() {
        return db.stream()
                .filter(note -> !note.getAuthor().equalsIgnoreCase("Dzmitry"))
                .map(noteMapper::toDto)
                .toList();
    }

    public List<NoteDto> findByAuthor(String author) {
        return db.stream()
                .filter(note -> note.getAuthor().equalsIgnoreCase(author))
                .map(noteMapper::toDto)
                .toList();
    }
}
