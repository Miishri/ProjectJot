package org.personal.projectjot.entities;


import jakarta.persistence.*;
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

    private String title;
    private String paragraphs;
    private String selfReferenceLink;
    private String thumbnailLink;

    @UpdateTimestamp
    private Timestamp blogLastUpdate;

    @Column(
            updatable = false
    )
    @CreationTimestamp
    private Timestamp blogCreationDate;
}
