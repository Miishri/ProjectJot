package org.personal.projectjot.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.personal.projectjot.entities.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Transactional
class BlogRepositoryTest {

    @Autowired
    private BlogRepository blogRepository;

    private final String expectedTitle = "How do you create a Spring Docker Compose File?";

    @BeforeEach
    void setUp() {
        blogRepository.save(
                Blog.builder()
                        .title(expectedTitle)
                        .paragraphs("To really understand what Spring does under the hood is quite complex...")
                        .selfReferenceLink("https")
                        .thumbnailLink("https")
                        .build()
        );
    }

    @Test
    void findByTitle() {
        String title = blogRepository.findByTitle(expectedTitle).get().getTitle();
        assertThat(title).isEqualTo(expectedTitle);
    }

    @Test
    void createTitle() {
        assertThat(blogRepository.findAll().size()).isEqualTo(1);
    }
}