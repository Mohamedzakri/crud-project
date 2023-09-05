package it.mohamed.crudproject.enums;

public enum TaskStatus {
    TO_DO("to do"),
    DOING("doing"),
    DONE("done");
    private final String description;
    TaskStatus(String description ){
        this.description = description;
    }
}
