package it.mohamed.crudproject.controller;


import it.mohamed.crudproject.enums.TaskStatus;
import it.mohamed.crudproject.model.TaskEntity;
import it.mohamed.crudproject.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;


    @GetMapping(value = "/taskList/{status}")
    public ResponseEntity<List<TaskEntity>> getTaskByStatus(@PathVariable TaskStatus status) {
        return ResponseEntity.ok(taskService.getTaskByStatus(status));
    }

    @GetMapping(value = "/userTasks/{id}")
    public ResponseEntity<List<TaskEntity>> getTaskByPriority(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskByPriority(id));
    }

}
