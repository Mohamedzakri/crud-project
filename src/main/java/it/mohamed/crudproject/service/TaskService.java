package it.mohamed.crudproject.service;

import it.mohamed.crudproject.dto.TaskDto;
import it.mohamed.crudproject.enums.TaskStatus;
import it.mohamed.crudproject.model.TaskEntity;
import it.mohamed.crudproject.repo.TaskRepository;
import org.modelmapper.ModelMapper;
import it.mohamed.crudproject.modelmapper.ModelMapperConfig;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public TaskService(TaskRepository taskRepository, ModelMapper modelMapper) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
        System.out.println("ModelMapper instance present: " + (modelMapper != null));
    }
    public TaskDto getTask(int id) {
        TaskEntity taskEntity = taskRepository.getById(id);
        if (taskEntity != null){
            ModelMapper mapper = new ModelMapper();
            mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            return modelMapper.map(taskEntity, TaskDto.class);
        }
        else{
            throw new RuntimeException(String.format("Task by id: %s not found", id));
        }
    }
    public List<TaskEntity> getTaskByStatus(TaskStatus status) {
        return taskRepository.getTaskByStatus(status);
    }
}
