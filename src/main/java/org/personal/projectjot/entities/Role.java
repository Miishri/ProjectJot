package org.personal.projectjot.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class Role {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private Collection<JotUser> jotUsers;
}
