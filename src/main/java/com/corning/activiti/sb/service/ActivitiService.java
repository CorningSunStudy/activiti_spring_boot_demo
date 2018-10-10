package com.corning.activiti.sb.service;

import com.corning.activiti.sb.repository.dao.PersonRepository;
import com.corning.activiti.sb.repository.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class ActivitiService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private PersonRepository personRepository;

    @Transactional
    public void startProcess(String assignee) {
        Person person = personRepository.findByUsername(assignee);
        HashMap<String, Object> variables = new HashMap<>();
        variables.put("person", person);
        runtimeService.startProcessInstanceByKey("oneTaskProcess", variables);
    }

    @Transactional
    public List<Task> getTasks(String assignee) {
        return taskService.createTaskQuery().taskAssignee(assignee).list();
    }

    public void createDemoUsers() {
        if (personRepository.findAll().size() == 0) {
            personRepository.save(new Person("jbarrez", "Joram", "Barrez", new Date()));
            personRepository.save(new Person("trademakers", "Tijs", "Rademakers", new Date()));
        }
    }
}
