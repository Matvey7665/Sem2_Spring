package ru.gb.timesheet.model;

import lombok.Data;

import java.time.LocalDate;
@Data
public class TimeSheet {
    private Long id;
    private Long projectId;
    private LocalDate createdAt;
    private int minutes;
}
