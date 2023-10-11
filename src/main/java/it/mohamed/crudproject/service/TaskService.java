package it.mohamed.crudproject.service;


import it.mohamed.crudproject.dto.TaskDto;
import it.mohamed.crudproject.model.TaskEntity;
import it.mohamed.crudproject.repo.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public TaskService(TaskRepository taskRepository, ModelMapper modelMapper) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
    }

    public List<TaskDto> getTaskByPriority(Long id) {
        List<TaskEntity> taskEntities = taskRepository.getTaskByPriority(id);
        //for java versions prior to java 16 use "collect(Collectors.toList())"
        return taskEntities.stream().map(taskEntity -> modelMapper.map(taskEntity, TaskDto.class)).toList();
    }

    public List<TaskDto> getUserDoneTaskList(Long id) {
        List<TaskEntity> taskEntities = taskRepository.getUserDoneTasks(id);
        //for java versions prior to java 16 use "collect(Collectors.toList())"
        return taskEntities.stream().map(taskEntity -> modelMapper.map(taskEntity, TaskDto.class)).toList();
    }

}
