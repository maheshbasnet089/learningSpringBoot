package com.mahesh.learningSpring.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.mahesh.learningSpring.model.Content;
import com.mahesh.learningSpring.model.Status;
import com.mahesh.learningSpring.model.Type;

import jakarta.annotation.PostConstruct;

@Repository
public class ContentCollectionRepository {
    private final List<Content> content = new ArrayList<>();

    public ContentCollectionRepository() {

    }

    public List<Content> findAll() {
        return content;
    }

    public Optional<Content> findById(Integer id) {
        return content.stream().filter(c -> c.id().equals(id)).findFirst();

    }

    public void save(Content c) {
        content.removeIf(co->co.id().equals(c.id()));
        content.add(c);
    }
    public boolean doesExists(Integer id){
        return content.stream().filter(c->c.id().equals(id)).count()== 1;
    }

    public void delete(Integer id){
        content.removeIf(c->c.id().equals(id));
    }
    @PostConstruct
    private void init() {
        Content c = new Content(1, "test world", "test", Status.IDEA, Type.ARTICLE, LocalDateTime.now(), null, "");
        content.add(c);
    }

}
