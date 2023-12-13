package it.mohamed.crudproject.config.token;

import it.mohamed.crudproject.model.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Token implements Serializable{

    @Serial
    private static final long serialVersionUID = 1905122041950251207L;
    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;
    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String tokenCode;
    private boolean revoked;

    private boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private UserEntity userEntity;
}
