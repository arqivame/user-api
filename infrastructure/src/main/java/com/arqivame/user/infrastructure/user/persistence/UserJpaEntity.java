package com.arqivame.user.infrastructure.user.persistence;

import java.time.Instant;
import java.util.UUID;

import com.arqivame.user.domain.user.Email;
import com.arqivame.user.domain.user.Nickname;
import com.arqivame.user.domain.user.User;
import com.arqivame.user.domain.user.UserID;
import com.arqivame.user.domain.user.Username;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "User")
@Table(name = "users")
public class UserJpaEntity {

    @Id
    private UUID id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public UserJpaEntity(
            final UUID id,
            final String email,
            final String username,
            final String nickname,
            final Boolean active,
            final Instant createdAt,
            final Instant updatedAt) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.nickname = nickname;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UserJpaEntity() {
    }

    public static UserJpaEntity fromDomain(final User user) {
        return new UserJpaEntity(
                user.getId().getValue(),
                user.getEmail().value(),
                user.getUsername().value(),
                user.getNickname().value(),
                user.getActive(),
                user.getCreatedAt(),
                user.getUpdatedAt());
    }

    public User toDomain() {
        return User.with(
                UserID.of(id),
                Email.of(email),
                Username.of(username),
                Nickname.of(nickname),
                active,
                createdAt,
                updatedAt,
                null);
    }

}
