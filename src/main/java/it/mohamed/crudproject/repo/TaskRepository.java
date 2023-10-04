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

    @Query(value = """
            SELECT t FROM TaskEntity t WHERE (t.user.id = :id ) ORDER BY CASE t.priority
                WHEN 'LOW' THEN 3
                WHEN 'MEDIUM' THEN 2
                WHEN 'HIGH' THEN 1
                ELSE 4
            END""")
    List<TaskEntity> getTaskByPriority(@Param("id") Long id);

    @Query(value =" SELECT t from TaskEntity t WHERE (t.user.id =:id) AND (t.status = DONE)" )
    List<TaskEntity> getUserDoneTasks(@Param("id")Long id);
}
