package ru.scanword.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.scanword.domain.User;
import ru.scanword.domain.enums.Role;
import ru.scanword.domain.enums.Status;
import ru.scanword.dto.UserDTO;
import ru.scanword.exceptions.ResourceNotFoundException;
import ru.scanword.mapper.UserMapper;
import ru.scanword.repository.UserRepository;
import ru.scanword.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    //private final UserServiceImpl audioQuestionService;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('read')")
    public List<UserDTO> getAll() {
        return allToDTO(userRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('read')")
    public UserDTO getById(Long id) {
        if (!(userRepository.findById(id).isPresent())) {
            throw new ResourceNotFoundException("AudioQuestion with id = " + id + " not found", "");
        }
        return toDTO(userRepository.getById(id));
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('write')")
    public UserDTO create(UserDTO userDTO) {
        userRepository.save(toEntity(userDTO));
        return userDTO;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('write')")
    public UserDTO update(UserDTO userDTO) {
        if(!(userRepository.findById(userDTO.getId()).isPresent())) {
            throw new ResourceNotFoundException("AudioQuestion with id = " + userDTO.getId() + " not found", "");
        }
        return create(userDTO);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('write')")
    public boolean deleteById(Long id) {
        if (!(userRepository.findById(id).isPresent())) {
            throw new ResourceNotFoundException("AudioQuestion with id = " + id + " not found", "");
        }
        userRepository.deleteById(id);
        return (userRepository.findById(id).isPresent());
    }

    @Override
    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        userDTO.setRole(Role.USER);
        userDTO.setStatus(Status.ACTIVE);
        userRepository.save(toEntity(userDTO));
        return userDTO;
    }

    private User toEntity(UserDTO userDTO){
        return UserMapper.USER_MAPPER.toEntity(userDTO);
    }

    private UserDTO toDTO(User user){
        return UserMapper.USER_MAPPER.toDTO(user);
    }

    private List<User> allToEntity(List<UserDTO> userDTOList){
        return UserMapper.USER_MAPPER.allToEntity(userDTOList);
    }

    private List<UserDTO> allToDTO(List<User> userList){
        return UserMapper.USER_MAPPER.allToDTO(userList);
    }
}
