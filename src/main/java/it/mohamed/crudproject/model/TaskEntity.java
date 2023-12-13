package it.mohamed.crudproject.model;

import it.mohamed.crudproject.enums.TaskPriority;
import it.mohamed.crudproject.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "task_table")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskEntity implements Serializable{

    @Serial
    private static final long serialVersionUID = 1905122041950251202L;

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
    private UserEntity userEntity;

    @Column(name = "task_due_date")
    private Date taskDueDate;
}


