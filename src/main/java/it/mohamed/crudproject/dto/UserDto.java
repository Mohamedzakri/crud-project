package it.mohamed.crudproject.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {

    private int id;

    private String userName;

    private List<TaskDto> taskDtos = new ArrayList<>();
}
