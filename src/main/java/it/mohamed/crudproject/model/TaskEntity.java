package it.mohamed.crudproject.model;

import it.mohamed.crudproject.enums.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
    private long id;

    @Column(name = "task_objective")
    private String taskObj;

    @Enumerated(EnumType.STRING)
    @Column(name = "task_priority")
    private TaskPriority priority;

    @Enumerated(EnumType.STRING)
    @Column(name = "task_status")
    private TaskStatus status;

    @ManyToMany
    @JoinColumn(name = "id_user")
    private UserEntity user;

    @Column(name = "task_due_date")
    private Date taskDueDate;
}


