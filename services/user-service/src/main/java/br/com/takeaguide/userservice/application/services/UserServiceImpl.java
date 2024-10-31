package br.com.takeaguide.userservice.application.services;


import br.com.takeaguide.userservice.utils.ResponseUtils;
import br.com.takeaguide.userservice.domain.entities.User;
import br.com.takeaguide.userservice.domain.repositories.UserRepository;
import br.com.takeaguide.userservice.domain.services.UserService;
import br.com.takeaguide.userservice.dtos.ResponseObject;
import br.com.takeaguide.userservice.dtos.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<ResponseObject> login(LoginRequest request) {

        ResponseEntity<ResponseObject> validationResponse = request.validate();
            if (validationResponse != null) {
            return validationResponse; 
        }

        User user = userRepository.login(request.email(), request.password());

        if (user == null) {
            return ResponseUtils.formatResponse(
                HttpStatus.FORBIDDEN,
                ResponseObject.builder().error("User not found").build()
            );
        }

        UserDto userDto = new UserDto(
            user.getCpf(), user.getName(), user.getEmail(), user.getPassword(),
            user.getUserTypeId(), user.getPhone(), user.getDeletedAt()
        );

        return ResponseUtils.formatResponse(
            HttpStatus.OK,
            new LoginResponse(userDto, "User successfully logged in")
        );
    }

    @Override
    public ResponseEntity<ResponseObject> createUser(CreateUserRequest request) {
    
        ResponseEntity<ResponseObject> validationResponse = request.validate();
            if (validationResponse != null) {
            return validationResponse; 
        }

        int existingUsers = userRepository.checkIfUserIsAllowed(request.email(), request.name());

        if (existingUsers > 0) {
            return ResponseUtils.formatResponse(
                HttpStatus.CONFLICT,
                ResponseObject.builder().error("User or email already in use").build()
            );
        }

        String cpf = userRepository.insertUser(request);

        return ResponseUtils.formatResponse(
            HttpStatus.CREATED,
            new CreateUserResponse(cpf, "User created successfully")
        );
    }

    @Override
    public ResponseEntity<ResponseObject> changeUser(ChangeUserRequest request) {

        ResponseEntity<ResponseObject> validationResponse = request.validate();
            if (validationResponse != null) {
            return validationResponse; 
        }

        boolean isUserExistent = userRepository.updateUser(request);

        if (!isUserExistent) {
            return ResponseUtils.formatResponse(
                HttpStatus.NOT_FOUND,
                ResponseObject.builder().error("User not found").build()
            );
        }

        return ResponseUtils.formatResponse(
            HttpStatus.OK,
            new ChangeUserResponse(request.cpf(), "User successfully updated")
        );
    }

    @Override
    public ResponseEntity<ResponseObject> removeUser(DeleteUserRequest request) {

        
        boolean removed = userRepository.removeUser(request.cpf());

        if (!removed) {
            return ResponseUtils.formatResponse(
                HttpStatus.NOT_FOUND,
                ResponseObject.builder().error("User not found").build()
            );
        }

        return ResponseUtils.formatResponse(
            HttpStatus.OK,
            ResponseObject.builder().success("User successfully removed").build()
        );
    }

    @Override
    public ResponseEntity<ResponseObject> retrieveUser(RetrieveUserRequest request) {
        List<User> users = null;

        if (request.cpf() != null) {
            users = userRepository.retrieveUserByCpf(request.cpf());
        } else if (request.email() != null) {
            users = userRepository.retrieveUserByEmail(request.email());
        } else if (request.name() != null) {
            users = userRepository.retrieveUserByName(request.name());
        }

        if (users == null || users.isEmpty()) {
            return ResponseUtils.formatResponse(
                HttpStatus.NOT_FOUND,
                ResponseObject.builder().error("No user found").build()
            );
        }

        List<UserDto> userDtos = users.stream()
            .map(user -> new UserDto(
                user.getCpf(), user.getName(), user.getEmail(), user.getPassword(),
                user.getUserTypeId(), user.getPhone(), user.getDeletedAt()
            ))
            .collect(Collectors.toList());

        return ResponseUtils.formatResponse(
            HttpStatus.OK,
            new RetrieveUsersResponse(userDtos, "Users found")
        );
    }

   
}
