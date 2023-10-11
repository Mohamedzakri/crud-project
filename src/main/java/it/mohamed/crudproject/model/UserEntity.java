package it.mohamed.crudproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user", unique = true)
    private int id;

    @Column(name = "user_name")
    private String userName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<TaskEntity> taskEntities = new ArrayList<>();

}
