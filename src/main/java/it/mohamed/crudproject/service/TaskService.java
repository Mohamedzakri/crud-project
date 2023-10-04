package it.mohamed.crudproject.service;


import it.mohamed.crudproject.enums.TaskStatus;
import it.mohamed.crudproject.model.TaskEntity;
import it.mohamed.crudproject.repo.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TaskService {
    private final TaskRepository taskRepository;
    //private final ModelMapper modelMapper;
    @Autowired
    public TaskService(TaskRepository taskRepository, ModelMapper modelMapper) {
        this.taskRepository = taskRepository;
        //this.modelMapper = modelMapper;
        System.out.println("ModelMapper instance present: " + (modelMapper != null));
    }

    public List<TaskEntity> getTaskByStatus(TaskStatus status) {
        return taskRepository.getTaskByStatus(status);
    }

    public List<TaskEntity> getTaskByPriority(Long id) {
        return taskRepository.getTaskByPriority(id);
    }

    public List<TaskEntity> getUserDoneTaskList(Long id) {
        return taskRepository.getUserDoneTasks(id);
    }
}
