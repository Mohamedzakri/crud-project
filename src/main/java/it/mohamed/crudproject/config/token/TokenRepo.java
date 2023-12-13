package it.mohamed.crudproject.config.token;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepo extends JpaRepository<Token, Integer> {

    @Query(value = """
      select t from Token t inner join UserEntity u
      on t.userEntity.id = u.id
      where u.id = :id and (t.expired = false or t.revoked = false)
      """)
    List<Token> findAllValidTokenByUser(int id);

    @Query(value = "Select t from Token t where (t.tokenCode = :tokenCode )")
    Optional<Token> findByToken(String tokenCode);
}
