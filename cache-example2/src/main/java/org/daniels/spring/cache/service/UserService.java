package org.daniels.spring.cache.service;

import org.daniels.spring.cache.domain.User;
import org.daniels.spring.cache.mapper.UserMapper;
import org.daniels.spring.cache.model.UserDTO;
import org.daniels.spring.cache.repository.UserRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDTO add(UserDTO userDTO) {
        User createdEntity = userRepository.save(userMapper.toUser(userDTO));

        return userMapper.toUserDTO(createdEntity);
    }

    public List<UserDTO> retrieveAll() {
        List<UserDTO> todoList = userRepository.findAll()
                .stream()
                .map(userMapper::toUserDTO)
                .collect(Collectors.toList());
        return todoList;
    }

    public UserDTO update(UserDTO userDTO) {
        User updateEntity = userRepository.save(userMapper.toUser(userDTO));

        return userMapper.toUserDTO(updateEntity);
    }

    @Cacheable(value = "userCache", key = "#id")
    public UserDTO findById(Long id) {
        Optional<User> todo = userRepository.findById(id);

        return userMapper.toUserDTO(todo.get());
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
