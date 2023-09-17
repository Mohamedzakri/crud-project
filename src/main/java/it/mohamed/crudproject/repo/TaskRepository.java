package it.mohamed.crudproject.repo;

import it.mohamed.crudproject.enums.TaskStatus;
import it.mohamed.crudproject.model.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {
    @Query(value = "Select t from TaskEntity t where (t.status = :status )")
    List<TaskEntity> getTaskByStatus(@Param("status") TaskStatus status);
}
