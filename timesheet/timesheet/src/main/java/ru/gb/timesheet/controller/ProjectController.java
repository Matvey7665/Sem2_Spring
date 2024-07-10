package ru.gb.timesheet.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.model.TimeSheet;
import ru.gb.timesheet.repository.ProjectRepository;
import ru.gb.timesheet.service.ProjectService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")

public class ProjectController {
    private final ProjectService service;


    public ProjectController(ProjectService service) {
        this.service = service;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Project> getById( @PathVariable Long id){
        Optional<Project> project = service.getById(id);
        if (project.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(project.get());
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping
    public ResponseEntity<List<Project>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }
    @PostMapping
    public ResponseEntity<Project> create(@RequestBody Project project){
        project = service.create(project);

        return ResponseEntity.status(HttpStatus.CREATED).body(project);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete( @PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
