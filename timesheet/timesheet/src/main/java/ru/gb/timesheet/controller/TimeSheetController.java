package ru.gb.timesheet.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.timesheet.model.TimeSheet;
import ru.gb.timesheet.service.TimeSheetService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/projects/timesheets")
public class TimeSheetController {

    private final TimeSheetService service;

    public TimeSheetController(TimeSheetService service){
        this.service = service;
    }

    	/*
	GET - ПОЛУЧИТЬ (НЕ СОДЕРЖИТ ТЕЛА)!!!
	POST - CREATE
	PUT - ИЗМИНЕНИЕ
	PATCH - ИЗМИНЕНИЕ
	DELETE - УДАЛЕНИЕ
	 */
// @GetMapping ("/timesheets/{id}") - получить определенный кусок по индификатору
   // @GetMapping("/timesheets") // ПОЛУЧИТЬ ВСЕ
   // public TimeSheet get(){
   //     TimeSheet timeSheet = new TimeSheet();
   //     timeSheet.setId(1L);
   //     timeSheet.setProject("spring");
   //     timeSheet.setMinutes(200);
   //     timeSheet.setCreatedAt(LocalDate.now());
   //     return timeSheet;
   // }


    @GetMapping("/{id}")
    public ResponseEntity<TimeSheet> get(@PathVariable Long id){
        Optional<TimeSheet> timeSheet = service.getById(id);

        if (timeSheet.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(timeSheet.get());
        }
        return ResponseEntity.notFound().build();

    }

    @GetMapping
    public ResponseEntity<List<TimeSheet>> getAll(){


        return  ResponseEntity.ok(service.getAll());

    }

    @PostMapping // создание нового русурса
    public ResponseEntity<TimeSheet> create(@RequestBody TimeSheet timeSheet){
        timeSheet = service.create(timeSheet);
        if (timeSheet == null) {
            return ResponseEntity.noContent().build();
        }
        // 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(timeSheet);


    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/projects/{id}/timesheets")
    public ResponseEntity<List<TimeSheet>> getTimeSheetsByProjectId(@PathParam("{id}") Long id) {
        Optional<List<TimeSheet>> timeSheetList = service.getTimeSheetsByProjectId(id);
        if (timeSheetList.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(timeSheetList.get());
        }
        return ResponseEntity.notFound().build();

    }

}
