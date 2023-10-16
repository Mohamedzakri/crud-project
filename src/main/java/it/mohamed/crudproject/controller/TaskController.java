package it.mohamed.crudproject.controller;


import it.mohamed.crudproject.dto.TaskDto;
import it.mohamed.crudproject.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    static final String FORMATTEDDATETIME = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    /*
     * Get users Done tasks by users id
     * */
    @GetMapping(value = "/taskList/{id}")
    public ResponseEntity<List<TaskDto>> getTaskByStatus(@PathVariable Long id) {
        logger.info("### [{}] Accessed {} in class {} with id: {} ###", FORMATTEDDATETIME, Thread.currentThread().getStackTrace()[1].getMethodName(), this.getClass().getSimpleName(), id);
        return ResponseEntity.ok(taskService.getUserDoneTaskList(id));
    }

    /*
     *Get User tasks List and list them by priority
     **/
    @GetMapping(value = "/userTasks/{id}")
    public ResponseEntity<List<TaskDto>> getTaskByPriority(@PathVariable Long id) {
        logger.info("### [{}] Accessed {} in class {} with id: {} ###", FORMATTEDDATETIME, Thread.currentThread().getStackTrace()[1].getMethodName(), this.getClass().getSimpleName(), id);
        return ResponseEntity.ok(taskService.getTaskByPriority(id));
    }

}
