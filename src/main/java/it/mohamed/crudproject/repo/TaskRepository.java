package it.mohamed.crudproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository<T, ID> extends JpaRepository<T, ID> {
}
