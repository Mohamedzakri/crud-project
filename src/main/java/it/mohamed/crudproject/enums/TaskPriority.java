package it.mohamed.crudproject.enums;

public enum TaskPriority {
    LOW("low"),
    MEDIUM("medium"),
    HIGH("high");
    private final String description;

    TaskPriority(String description) {
        this.description = description;
    }
}
