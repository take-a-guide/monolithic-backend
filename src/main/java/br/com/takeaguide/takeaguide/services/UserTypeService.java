package br.com.takeaguide.takeaguide.services;

import br.com.takeaguide.takeaguide.dtos.usertype.ChangeUserTypeRequest;
import br.com.takeaguide.takeaguide.dtos.usertype.CreateUserTypeRequest;
import br.com.takeaguide.takeaguide.dtos.usertype.RetrieveUserTypesResponse;
import br.com.takeaguide.takeaguide.dtos.usertype.UserTypeDto;
import br.com.takeaguide.takeaguide.entities.UserType;
import br.com.takeaguide.takeaguide.repositories.mysql.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserTypeService {

    @Autowired
    private UserTypeRepository userTypeRepository;

    public List<UserTypeDto> retrieveUserTypes() {
        List<UserType> userTypes = userTypeRepository.findAll();

        return userTypes.stream()
                .map(userType -> new UserTypeDto(userType.getId(), userType.getName()))
                .collect(Collectors.toList());
    }

    public List<UserTypeDto> retrieveUserType(Long id) {
        Optional<UserType> userType = userTypeRepository.findById(id);

        if (userType.isPresent()) {
            return List.of(new UserTypeDto(userType.get().getId(), userType.get().getName()));
        } else {
            return Collections.emptyList();
        }
    }

    public UserTypeDto changeUserType(Long id, String name) {
        Optional<UserType> userTypeOptional = userTypeRepository.findById(id);

        if (userTypeOptional.isPresent()) {
            UserType userType = userTypeOptional.get();
            userType.setName(name);
            userTypeRepository.save(userType);
            return convertToDto(userType);
        } else {
            throw new RuntimeException("User type not found"); // Ou uma exceção mais específica
        }
    }

    public UserTypeDto createNewUserType(String name) {
        UserType newUserType = new UserType();
        newUserType.setName(name);
        UserType savedUserType = userTypeRepository.save(newUserType);
        return convertToDto(savedUserType);
    }

    private UserTypeDto convertToDto(UserType userType) {
        return new UserTypeDto(userType.getId(), userType.getName());
    }
}
