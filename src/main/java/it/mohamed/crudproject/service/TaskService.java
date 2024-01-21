package it.mohamed.crudproject.service;


import it.mohamed.crudproject.dto.TaskDto;
import it.mohamed.crudproject.enums.LogMessage;
import it.mohamed.crudproject.model.TaskEntity;
import it.mohamed.crudproject.repo.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
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
        log.info(LogMessage.INVOKE_SERVICE_SELECT_TASK_BY_PRIORITY_METHOD.toString());
        List<TaskEntity> taskEntities = taskRepository.getTaskByPriority(id);
        //for java versions prior to java 16 use "collect(Collectors.toList())"
        log.info(LogMessage.EXIT_SERVICE_SELECT_TASKS_BY_PRIORITY_METHOD.toString());
        return taskEntities.stream().map(taskEntity -> modelMapper.map(taskEntity, TaskDto.class)).toList();
    }

    public List<TaskDto> getUserDoneTaskList(Long id) {
        log.info(LogMessage.INVOKE_SERVICE_SELECT_DONE_TASKS_BY_USER_METHOD.toString());
        List<TaskEntity> taskEntities = taskRepository.getUserDoneTasks(id);
        log.info(LogMessage.EXIT_SERVICE_SELECT_DONE_TASKS_BY_USER_METHOD.toString());
        return taskEntities.stream().map(taskEntity -> modelMapper.map(taskEntity, TaskDto.class)).toList();
    }

}
