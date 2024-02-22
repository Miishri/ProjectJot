package org.personal.projectjot.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class CustomUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String password;
    private boolean active;
    private String roles;
}
