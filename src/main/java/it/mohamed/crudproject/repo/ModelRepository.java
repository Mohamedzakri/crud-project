package it.mohamed.crudproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yaml.snakeyaml.events.Event;

public interface ModelRepository<T, ID> extends JpaRepository<T, ID> {
}
