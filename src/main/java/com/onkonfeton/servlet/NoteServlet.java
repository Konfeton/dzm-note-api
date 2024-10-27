package com.onkonfeton.servlet;

import com.google.gson.Gson;
import com.onkonfeton.dto.NoteDto;
import com.onkonfeton.service.NoteService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static javax.servlet.http.HttpServletResponse.SC_CREATED;
import static javax.servlet.http.HttpServletResponse.SC_OK;

@WebServlet("/notes")
public class NoteServlet extends HttpServlet {


    private final NoteService noteService = new NoteService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String author = req.getParameter("author");
        List<NoteDto> notes;
        if (author != null){
            notes = noteService.findByAuthor(author);
        }
        else {
            notes = noteService.findAll();
        }
        String json = new Gson().toJson(notes);
        try (PrintWriter out = resp.getWriter()) {
            out.write(json);
            resp.setStatus(SC_OK);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        NoteDto accountDto = new Gson().fromJson(req.getReader(), NoteDto.class);
        String json = new Gson().toJson(noteService.save(accountDto));
        try (PrintWriter out = resp.getWriter()) {
            out.write(json);
            resp.setStatus(SC_CREATED);
        }
    }
}
