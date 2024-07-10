package ru.gb.timesheet.service;

import org.springframework.stereotype.Service;
import ru.gb.timesheet.model.TimeSheet;
import ru.gb.timesheet.repository.TimeSheetRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.time.LocalDate.now;

@Service //тоже самое , что и @Component
public class TimeSheetService {
    private final TimeSheetRepository repository;
    private final ProjectService serviceProject;


    public TimeSheetService(TimeSheetRepository repository,ProjectService service){
        this.repository = repository;
        this.serviceProject = service;
    }

    public Optional<TimeSheet> getById(Long id){

        return repository.getById(id);

    }


    public List<TimeSheet> getAll(){
        return  repository.getAll();
    }

    // создание нового русурса
    public TimeSheet create(TimeSheet timeSheet){
      if (serviceProject.getById(timeSheet.getProjectId()).isEmpty())
          return null;
        timeSheet.setCreatedAt(now());
        return repository.create(timeSheet);
    }

    public  void delete( Long id){
        repository.delete(id);
    }

    public Optional<List<TimeSheet>> getTimeSheetsByProjectId(Long id) {

        return repository.getTimeSheetsByProjectId(id);
    }
}
