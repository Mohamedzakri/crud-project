package it.mohamed.crudproject.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TaskPriority {

    LOW("low"),
    MEDIUM("medium"),
    HIGH("high");

    private final String description;
}
