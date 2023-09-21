package it.mohamed.crudproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.mohamed.crudproject.enums.*;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "task_table")
@NoArgsConstructor
@Data
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_task", unique = true)
    private int id;

    @Column(name = "task_objective")
    private String taskObj;

    @Enumerated(EnumType.STRING)
    @Column(name = "task_priority")
    private TaskPriority priority;

    @Enumerated(EnumType.STRING)
    @Column(name = "task_status")
    private TaskStatus status;

    @ManyToOne
    @JoinColumn(name = "id_user")
    @JsonIgnore //  to break the loop, without it the programme keeps looping
    private UserEntity user;

    @Column(name = "task_due_date")
    private Date taskDueDate;
}


