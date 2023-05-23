package com.in28minutes.rest.webservices.restfulwebservices.jpa;

import com.in28minutes.rest.webservices.restfulwebservices.user.Post;
import com.in28minutes.rest.webservices.restfulwebservices.user.User;
import com.in28minutes.rest.webservices.restfulwebservices.user.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaResource {
    UserJpaRepository repository;

    PostJpaRepository postJpaRepository;

    @Autowired
    public UserJpaResource(UserJpaRepository repository, PostJpaRepository postJpaRepository) {
        this.repository = repository;
        this.postJpaRepository = postJpaRepository;
    }

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return repository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        Optional<User> user = repository.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException("id:" + id);
        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all_users"));
        return entityModel;
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        repository.deleteById(id);
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> getUserPosts(@PathVariable int id) {
        Optional<User> user = repository.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException("id:" + id);

        return user.get().getPosts();


    }


    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        repository.save(user);

        User savedUser = repository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public List<Post> createUserPosts(@PathVariable int id, @Valid @RequestBody Post post) {
        Optional<User> user = repository.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException("id:" + id);

        return user.get().getPosts();


    }
}
