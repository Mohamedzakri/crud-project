package it.mohamed.crudproject.repo;

import it.mohamed.crudproject.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("Select u from UserEntity u where u.userName = :param OR u.id = :param")
    UserEntity getUserByParam(@Param("param") Object param);

}
