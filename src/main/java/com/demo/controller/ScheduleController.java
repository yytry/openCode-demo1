package com.demo.controller;

import com.demo.entity.Label;
import com.demo.entity.Schedule;
import com.demo.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.demo.constants.pubConstants;

import java.sql.Date;

@Controller
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleRepository scheduleRepository;

    public ScheduleController(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @GetMapping
    public String listSchedules(Model model) {
        model.addAttribute("schedules", scheduleRepository.findByScheduleStatus(pubConstants.STATUS_ACTIVE));
        return "schedules";
    }

    @PostMapping("/add")
    public String addSchedule(@RequestParam String event,
                              @RequestParam(required = false) String personnel,
                              @RequestParam(required = false) String startTime,
                              @RequestParam(required = false) String endTime,
                              @RequestParam(required = false) String comment) {
        Schedule schedule = new Schedule();
        schedule.setEvent(event);
        schedule.setPersonnel(personnel);
        schedule.setScheduleStatus(pubConstants.STATUS_ACTIVE);
        if (startTime != null && !startTime.isEmpty()) {
            schedule.setStartTime(Date.valueOf(startTime));
        }
        if (endTime != null && !endTime.isEmpty()) {
            schedule.setEndTime(Date.valueOf(endTime));
        }
        schedule.setComment(comment);
        scheduleRepository.save(schedule);
        return "redirect:/schedules";
    }

    @PostMapping("/delete/{id}")
    public String deleteSchedule(@PathVariable Integer id) {
        Schedule schedule = scheduleRepository.findById(id).orElse(null);
        if (schedule!=null) {
            schedule.setScheduleStatus(pubConstants.STATUS_INACTIVE);
            scheduleRepository.save(schedule);
        }
        return "redirect:/schedules";
    }
}
