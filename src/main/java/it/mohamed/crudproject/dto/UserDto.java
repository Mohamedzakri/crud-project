package it.mohamed.crudproject.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class UserDto {

    private int id;

    private String userName;

    private String login;

    private List<TaskDto> taskDtos = new ArrayList<>();
}
