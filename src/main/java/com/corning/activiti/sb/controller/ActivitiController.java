package com.corning.activiti.sb.controller;

import com.corning.activiti.sb.repository.model.StartProcessRepresentation;
import com.corning.activiti.sb.repository.model.TaskRepresentation;
import com.corning.activiti.sb.service.ActivitiService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ActivitiController {

    @Autowired
    private ActivitiService activitiService;

    @PostMapping("/process")
    public void startProcessInstance(@RequestBody StartProcessRepresentation startProcessRepresentation) {
        activitiService.startProcess(startProcessRepresentation.getAssignee());
    }

    @GetMapping("/tasks")
    public List<TaskRepresentation> getTasks(@RequestParam String assignee) {
        List<Task> tasks = activitiService.getTasks(assignee);
        List<TaskRepresentation> dtos = new ArrayList<>();
        for (Task task : tasks) {
            dtos.add(new TaskRepresentation(task.getId(), task.getName()));
        }
        return dtos;
    }

}
