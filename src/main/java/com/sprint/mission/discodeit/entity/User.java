package com.sprint.mission.discodeit.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sprint.mission.discodeit.entity.base.BaseUpdatableEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseUpdatableEntity {

    @Column(length = 50, nullable = false, unique = true)
    private String username;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 60, nullable = false)
    private String password;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", unique = true)
    private BinaryContent profile;

    @JsonManagedReference
    @Setter(AccessLevel.PROTECTED)
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private UserStatus status;

    public User(String username, String email, String password, BinaryContent profile) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.profile = profile;
    }

    public void update(String newUsername, String newEmail, String newPassword, BinaryContent newProfile) {
        if (newUsername != null && !newUsername.equals(this.username)) {
            this.username = newUsername;
        }
        if (newEmail != null && !newEmail.equals(this.email)) {
            this.email = newEmail;
        }
        if (newPassword != null && !newPassword.equals(this.password)) {
            this.password = newPassword;
        }
        if (newProfile != null && !Objects.equals(this.profile, newProfile)) {
            this.profile = newProfile;
        }
    }
}
