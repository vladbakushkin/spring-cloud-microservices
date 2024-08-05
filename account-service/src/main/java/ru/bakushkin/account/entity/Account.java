package ru.bakushkin.account.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    private String name;

    private String email;

    private String phone;

    @CreationTimestamp
    private OffsetDateTime createdDate;

    @ElementCollection
    private List<Long> bills;
}
