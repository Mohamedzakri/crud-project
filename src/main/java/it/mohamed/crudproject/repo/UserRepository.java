package it.mohamed.crudproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository<T, ID> extends JpaRepository<T, ID> {
}
