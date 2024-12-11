package org.msd.ebankingbackend.domain.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class AbstractModel {

    private Long id;

    private LocalDateTime createdAt;

    private LocalDateTime lastModifiedDate;
}
