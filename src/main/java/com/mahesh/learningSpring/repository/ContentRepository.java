package com.mahesh.learningSpring.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import com.mahesh.learningSpring.model.Content;
import com.mahesh.learningSpring.model.Status;

public interface ContentRepository extends ListCrudRepository<Content, Integer> {

    // given by the parentclass
    List<Content> findAllByTitleContains(String keyword);

    @Query("""
            SELECT * FROM Content WHERE status = :status
            """)
    List<Content> findByStatus(@Param("status") Status status);

}
