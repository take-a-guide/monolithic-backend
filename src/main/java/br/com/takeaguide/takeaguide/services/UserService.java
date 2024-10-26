package br.com.takeaguide.takeaguide.services;

import br.com.takeaguide.takeaguide.dtos.ResponseObject;
import br.com.takeaguide.takeaguide.dtos.account.*;
import br.com.takeaguide.takeaguide.repositories.mysql.UserRepository;
import br.com.takeaguide.takeaguide.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<ResponseObject> login(LoginRequest request) {
        UserDto userDto = userRepository.login(request.email(), request.password());

        if (userDto == null) {
            return ResponseUtils.formatResponse(
                HttpStatus.FORBIDDEN,
                ResponseObject.builder().error("User not found").build()
            );
        }

        return ResponseUtils.formatResponse(
            HttpStatus.OK,
            new LoginResponse(userDto, "User successfully logged in")
        );
    }

    public ResponseEntity<ResponseObject> createAccount(CreateUserRequest request) {
        Integer occurrences = userRepository.checkIfUserIsAllowed(request.email(), request.name());

        if (occurrences == null || occurrences > 0) {
            return ResponseUtils.formatResponse(
                occurrences == null ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.CONFLICT,
                ResponseObject.builder().error("User or email already in use").build()
            );
        }

        BigInteger userCpf = userRepository.insertUser(request);
        return ResponseUtils.formatResponse(
            HttpStatus.CREATED,
            new CreateUserResponse(userCpf, "User created successfully")
        );
    }

    public ResponseEntity<ResponseObject> changeUser(ChangeUserRequest request) {
        Integer occurrences = userRepository.checkIfUserIsAllowed(request.email(), request.name());

        if (occurrences == null || occurrences > 0) {
            return ResponseUtils.formatResponse(
                occurrences == null ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.CONFLICT,
                ResponseObject.builder().error("User or email already in use").build()
            );
        }

        userRepository.updateUser(request);
        return ResponseUtils.formatResponse(
            HttpStatus.OK,
            new ChangeUserResponse(request.cpf().toString(), "User successfully changed")
        );
    }

    public ResponseEntity<ResponseObject> removeUser(DeleteUserRequest request) {
        userRepository.removeUser(request.cpf().toString());
        return ResponseUtils.formatResponse(
            HttpStatus.OK,
            ResponseObject.builder().success("User successfully removed").build()
        );
    }

    public ResponseEntity<ResponseObject> retrieveUser(RetrieveUserRequest request) {
        List<UserDto> users = null;

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

        return ResponseUtils.formatResponse(
            HttpStatus.OK,
            new RetrieveUsersResponse(users, "User(s) found")
        );
    }
}
