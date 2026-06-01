package com.sprint.mission.discodeit.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.util.UUID;

@Getter
@NoArgsConstructor
@MappedSuperclass
public class BaseUpdatableEntity extends BaseEntity {

    @LastModifiedDate
    @Column(columnDefinition = "timestamp with time zone")
    private Instant updatedAt;
}
