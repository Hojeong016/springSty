package org.cos.security1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

// ORM - Object Relation Mapping

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;

   // @Pattern(regexp = "@") // 정규표현식
    private String email;
    private String role; //ROLE_USER, ROLE_ADMIN

    @CreationTimestamp
    private Timestamp createDate;
}