package org.daniels.spring.cache.web.rest.v1;

import org.daniels.spring.cache.model.UserDTO;
import org.daniels.spring.cache.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(UserResource.BASE_URL)
public class UserResource {

    private final Logger log = LoggerFactory.getLogger(UserResource.class);
    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    public static final String BASE_URL = "/api/v1";

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> retrieveAll() {
        final List<UserDTO> list = userService.retrieveAll();
        return list;
    }

    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO retrieveUser(@PathVariable Long id) {
        final UserDTO userDTO = userService.findById(id);
        return userDTO;
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDTO> create(@Valid @RequestBody final UserDTO userDTO)
            throws URISyntaxException {
        if (userDTO.getId() != null) {
            throw new RuntimeException("A new user cannot have an ID");
        }
        UserDTO created = userService.add(userDTO);
        return ResponseEntity.created(new URI(BASE_URL + created.getId())).body(created);
    }

    @PutMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO update(@RequestBody final UserDTO userDTO) {
        if (userDTO.getId() == null) {
            throw new RuntimeException("Invalid id");
        }
        UserDTO updated = userService.update(userDTO);
        return updated;
    }


    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

}
