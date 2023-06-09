package com.mahesh.learningSpring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.mahesh.learningSpring.model.Content;
import com.mahesh.learningSpring.model.Status;
import com.mahesh.learningSpring.repository.ContentCollectionRepository;
import com.mahesh.learningSpring.repository.ContentRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/content")
@CrossOrigin // defaults to everything
public class ContentController {
    private final ContentRepository repository;

    public ContentController(ContentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public List<Content> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Content findById(@PathVariable Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content Not found"));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(" ")
    public void create(@Valid @RequestBody Content content) {
        repository.save(content);
    }

    // @ResponseStatus(HttpStatus.NOT_FOUND)
    // @PutMapping("/{id}")
    // public void update(@RequestBody Content content, @PathVariable Integer id) {
    //     if (!repository.doesExists(id)) {
    //         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content Not Found");
    //     }
    //     repository.save(content);
    // }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        repository.deleteById(id);

    }

    @GetMapping("/filter/{keyword}")
    public List<Content> findByTitle(@PathVariable String keyword){
        System.out.println("Hitted filter method");
        return repository.findAllByTitleContains(keyword);
    }

    // get by status
    @GetMapping("/filter/status/{keyword}")
    public List<Content> findByStatus(@PathVariable Status keyword ){
        return repository.findByStatus(keyword);
    }

}
