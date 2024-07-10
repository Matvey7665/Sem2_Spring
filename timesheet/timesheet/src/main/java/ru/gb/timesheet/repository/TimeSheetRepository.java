package ru.gb.timesheet.repository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import ru.gb.timesheet.model.TimeSheet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository // @Component для классов , работающих с базой данных
public class TimeSheetRepository {

    private final List<TimeSheet> timeSheets = new ArrayList<>();
    private Long sequence = 1L;





    public Optional<TimeSheet> getById( Long id){
        //select * from timesheets where id = $id
        return timeSheets.stream()
                .filter(it -> Objects.equals(it.getId(),id))
                .findFirst();



    }


    public List<TimeSheet> getAll(){
        return  List.copyOf(timeSheets);
    }

    // создание нового русурса
    public TimeSheet create( TimeSheet timeSheet){
        timeSheet.setId(sequence ++);
        timeSheets.add(timeSheet);
        return timeSheet;
    }

    public  void delete( Long id){
        timeSheets.stream()
                .filter(it -> Objects.equals(it.getId(),id))
                .findFirst()
                .ifPresent(timeSheets::remove);//если нет иногда посылают 404 Not Found
    }
    public Optional<List<TimeSheet>> getTimeSheetsByProjectId(Long id) {

        List<TimeSheet> timeSheetList = timeSheets.stream()
                .filter(it -> Objects.equals(it.getProjectId(), id))
                .toList();

        return Optional.of(timeSheetList);
    }


}
