package br.com.takeaguide.takeaguide.controller;

import static br.com.takeaguide.takeaguide.utils.ResponseUtils.formatResponse;
import br.com.takeaguide.takeaguide.dtos.usertype.ChangeUserTypeResponse;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.takeaguide.takeaguide.dtos.ResponseObject;
import br.com.takeaguide.takeaguide.dtos.usertype.ChangeUserTypeRequest;
import br.com.takeaguide.takeaguide.dtos.usertype.RetrieveUserTypesRequest;
import br.com.takeaguide.takeaguide.dtos.usertype.RetrieveUserTypesResponse;
import br.com.takeaguide.takeaguide.dtos.usertype.CreateUserTypeRequest;
import br.com.takeaguide.takeaguide.dtos.usertype.CreateUserTypeResponse;
import br.com.takeaguide.takeaguide.dtos.usertype.UserTypeDto;
import br.com.takeaguide.takeaguide.repositories.mysql.UserTypeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/take_a_guide/user_type")
@Tag(
    name = "APIs-TAKE-A-GUIDE: USER TYPE",
    description = "CONTAINS ALL USER-TYPE-RELATED ENDPOINTS"
)
public class UserTypeController {

    @Autowired
    private UserTypeRepository userTypeRepository;

    @PostMapping("/change")
    @Operation(
        summary = "API used to change a user type",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "User type successfully changed",
                content = @Content(
                    schema = @Schema(implementation = ResponseObject.class)
                )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Some of the request items are invalid",
                content = @Content(
                    schema = @Schema(implementation = ResponseObject.class)   
                )
            ),
            @ApiResponse(
                responseCode = "500",
                description = "Key not returned after operation",
                content = @Content(
                    schema = @Schema(implementation = ResponseObject.class)
                )
            )
        }
    )
    public ResponseEntity<ResponseObject> changeUserType(
        @io.swagger.v3.oas.annotations.parameters.RequestBody
        @RequestBody ChangeUserTypeRequest request){

        ResponseEntity<ResponseObject> validate = request.validate();

        if(validate != null){
            return validate;
        }

        userTypeRepository.ChangeUserType(request.id(), request.name());

        return formatResponse(
            HttpStatus.OK, 
            ChangeUserTypeResponse.success(new BigInteger(request.id() + ""), "User type successfully changed")
        );
    }

    @PostMapping("/create")
    @Operation(
        summary = "API used to create a user type",
        responses = {
            @ApiResponse(
                responseCode = "201",
                description = "User type successfully created",
                content = @Content(
                    schema = @Schema(implementation = CreateUserTypeResponse.class)
                )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Some of the request items are invalid",
                content = @Content(
                    schema = @Schema(implementation = ResponseObject.class)   
                )
            ),
            @ApiResponse(
                responseCode = "500",
                description = "An issue occurred on the server",
                content = @Content(
                    schema = @Schema(implementation = ResponseObject.class)
                )
            )
        }
    )
    public ResponseEntity<ResponseObject> createUserType(
        @io.swagger.v3.oas.annotations.parameters.RequestBody
        @RequestBody CreateUserTypeRequest request){

        ResponseEntity<ResponseObject> validate = request.validate();

        if(validate != null){
            return validate;
        }

        BigInteger id = userTypeRepository.CreateNewUserType(request.name());

        if(id == null){
            return formatResponse(
                HttpStatus.INTERNAL_SERVER_ERROR, 
                ResponseObject.error("No key returned from the server after creating the user")
            );
        }

        return formatResponse(
            HttpStatus.OK, 
            CreateUserTypeResponse.success(id, "User type successfully created")
        );
    }

    @GetMapping("/retrieve")
    @Operation(
        summary = "API used to retrieve a user type",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "User type successfully retrieved",
                content = @Content(
                    schema = @Schema(implementation = RetrieveUserTypesResponse.class)
                )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Some of the request items are invalid",
                content = @Content(
                    schema = @Schema(implementation = ResponseObject.class)   
                )
            ),
            @ApiResponse(
                responseCode = "500",
                description = "An issue occurred on the server",
                content = @Content(
                    schema = @Schema(implementation = ResponseObject.class)
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "User type not found",
                content = @Content(
                    schema = @Schema(implementation = ResponseObject.class)
                )
            )
        }
    )
    public ResponseEntity<ResponseObject> retrieveUserTypes(
        @io.swagger.v3.oas.annotations.parameters.RequestBody
        @RequestBody RetrieveUserTypesRequest request){

        List<UserTypeDto> userTypes;

        if(request.id() == null){
            userTypes = userTypeRepository.retrieveUserTypes();
        } else {
            userTypes = userTypeRepository.retrieveUserType(request.id());
        }

        if(userTypes == null){
            return formatResponse(
                HttpStatus.NOT_FOUND, 
                ResponseObject.error("No user type found")
            );
        }

        return formatResponse(
            HttpStatus.OK, 
            RetrieveUserTypesResponse.success(userTypes, "Users successfully retrieved")
        );
    }
}
