package com.devmarvin.pizzalicious.persistence.entity;

import com.devmarvin.pizzalicious.persistence.converter.BooleanToIntConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {

    @Id
    @Column(nullable = false, length = 20)
    private String username;

    @Column(nullable = false, length = 200)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(columnDefinition = "Smallint")
    @Convert(converter = BooleanToIntConverter.class)
    private Boolean locked;

    @Column(columnDefinition = "Smallint")
    @Convert(converter = BooleanToIntConverter.class)
    private Boolean disabled;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserRoleEntity> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity that)) return false;
        return Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(email, that.email) && Objects.equals(locked, that.locked) && Objects.equals(disabled, that.disabled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, email, locked, disabled);
    }
}

