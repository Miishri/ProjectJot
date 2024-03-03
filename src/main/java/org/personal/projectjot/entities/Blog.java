package org.personal.projectjot.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Blog {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotBlank(
            message = "Blog name is required"
    )
    @Size(
            min = 10,
            max = 150
    )
    private String title;

    @NotBlank(
            message = "Blog text is required"
    )
    private String paragraphs;

    @NotNull
    private String selfReferenceLink;
    @NotNull
    private String thumbnailLink;

    @UpdateTimestamp
    private Timestamp blogLastUpdate;

    @Column(
            updatable = false
    )
    @CreationTimestamp
    private Timestamp blogCreationDate;
}
