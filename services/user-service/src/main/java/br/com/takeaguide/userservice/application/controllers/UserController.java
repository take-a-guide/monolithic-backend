package br.com.takeaguide.userservice.application.controllers;

import br.com.takeaguide.userservice.application.services.UserServiceImpl;
import br.com.takeaguide.userservice.dtos.ResponseObject;
import br.com.takeaguide.userservice.dtos.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.GET, RequestMethod.DELETE})
@RequestMapping("/api/v1/take_a_guide/user")
@Tag(name = "APIs-TAKE-A-GUIDE: USER", description = "CONTAINS ALL USER-ACCOUNT-RELATED ENDPOINTS")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    @Operation(summary = "API USED FOR USER LOGIN", responses = {
        @ApiResponse(responseCode = "200", description = "USER SUCCESSFULLY LOGGED IN", content = @Content(schema = @Schema(implementation = LoginResponse.class))),
        @ApiResponse(responseCode = "400", description = "SOME OF THE REQUEST ITEMS ARE INVALID", content = @Content(schema = @Schema(implementation = ResponseObject.class))),
        @ApiResponse(responseCode = "500", description = "AN ISSUE OCCURRED ON THE SERVER", content = @Content(schema = @Schema(implementation = ResponseObject.class))),
        @ApiResponse(responseCode = "403", description = "USER NOT FOUND", content = @Content(schema = @Schema(implementation = ResponseObject.class)))
    })
    public ResponseEntity<ResponseObject> login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }

    @PostMapping("/create")
    @Operation(summary = "API USED TO CREATE A NEW USER ACCOUNT", responses = {
        @ApiResponse(responseCode = "201", description = "USER CREATED SUCCESSFULLY", content = @Content(schema = @Schema(implementation = CreateUserResponse.class))),
        @ApiResponse(responseCode = "409", description = "SOME OF THE ITEMS IN THE REQUEST HAVE CONFLICTS", content = @Content(schema = @Schema(implementation = ResponseObject.class))),
        @ApiResponse(responseCode = "400", description = "SOME OF THE ITEMS IN THE REQUEST ARE INVALID", content = @Content(schema = @Schema(implementation = ResponseObject.class))),
        @ApiResponse(responseCode = "500", description = "SOME PROBLEM OCCURRED ON THE SERVER", content = @Content(schema = @Schema(implementation = ResponseObject.class)))
    })
    public ResponseEntity<ResponseObject> createUser(@RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }

    @PutMapping("/change")
    @Operation(summary = "API USED TO CHANGE A USER ACCOUNT", responses = {
        @ApiResponse(responseCode = "200", description = "USER SUCCESSFULLY CHANGED", content = @Content(schema = @Schema(implementation = ChangeUserResponse.class))),
        @ApiResponse(responseCode = "409", description = "SOME OF THE ITEMS DESIRED BY THE USER HAVE A CONFLICT", content = @Content(schema = @Schema(implementation = ResponseObject.class))),
        @ApiResponse(responseCode = "400", description = "SOME OF THE REQUEST ITEMS ARE INVALID", content = @Content(schema = @Schema(implementation = ResponseObject.class))),
        @ApiResponse(responseCode = "500", description = "AN ISSUE OCCURRED ON THE SERVER", content = @Content(schema = @Schema(implementation = ResponseObject.class)))
    })
    public ResponseEntity<ResponseObject> changeUser(@RequestBody ChangeUserRequest request) {
        return userService.changeUser(request);
    }

    @DeleteMapping("/remove")
    @Operation(summary = "API USED TO REMOVE A USER ACCOUNT", responses = {
        @ApiResponse(responseCode = "200", description = "USER SUCCESSFULLY REMOVED", content = @Content(schema = @Schema(implementation = ResponseObject.class))),
        @ApiResponse(responseCode = "400", description = "SOME OF THE REQUEST ITEMS ARE INVALID", content = @Content(schema = @Schema(implementation = ResponseObject.class)))
    })
    public ResponseEntity<ResponseObject> removeUser(@RequestBody DeleteUserRequest request) {
        return userService.removeUser(request);
    }

    @PostMapping("/retrieve")
    @Operation(summary = "API USED TO RETRIEVE USERS", responses = {
        @ApiResponse(responseCode = "200", description = "USER SUCCESSFULLY RETRIEVED", content = @Content(schema = @Schema(implementation = RetrieveUsersResponse.class))),
        @ApiResponse(responseCode = "400", description = "SOME ERROR IN THE REQUEST ITEMS", content = @Content(schema = @Schema(implementation = ResponseObject.class))),
        @ApiResponse(responseCode = "404", description = "NO USER FOUND", content = @Content(schema = @Schema(implementation = ResponseObject.class)))
    })
    public ResponseEntity<ResponseObject> retrieveUser(@RequestBody RetrieveUserRequest request) {
        return userService.retrieveUser(request);
    }
}
