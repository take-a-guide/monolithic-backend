package br.com.takeaguide.takeaguide.dtos.account;

import static br.com.takeaguide.takeaguide.utils.ResponseUtils.formatResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.takeaguide.takeaguide.dtos.ResponseObject;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    name = "change_account_request",
    description = "Request containing all necessary data to change a user's details"
)
public record ChangeUserRequest(

    @Schema(
        name = "id",
        nullable = false,
        description = "ID of the user to be changed"
    )
    @JsonProperty("id") Long id,

    @Schema(
        name = "username",
        nullable = true,
        description = "Username to be changed; only required if it needs to be updated, ignored if null"
    )
    @JsonProperty("username") String username,

    @Schema(
        name = "email",
        nullable = true,
        description = "Email to be changed; only required if it needs to be updated, ignored if null"
    )
    @JsonProperty("email") String email,

    @Schema(
        name = "password",
        nullable = true,
        description = "Password to be changed; only required if it needs to be updated, ignored if null"
    )
    @JsonProperty("password") String password,

    @Schema(
        name = "type",
        nullable = true,
        description = "Type to be changed; only required if it needs to be updated, ignored if null"
    )
    @JsonProperty("type") Integer type

) {

    public ResponseEntity<ResponseObject> validate() {

        if (id == null || id < 1) {
            return formatResponse(
                HttpStatus.BAD_REQUEST, 
                ResponseObject.builder().error("ID cannot be null or less than one").build()
            );
        }

        if (username == null && email == null && password == null && type == null) {
            return formatResponse(
                HttpStatus.BAD_REQUEST, 
                ResponseObject.builder().error("Request does not contain any fields in the body").build()
            );
        }

        if (type != null && type < 1) {
            return formatResponse(
                HttpStatus.BAD_REQUEST, 
                ResponseObject.builder().error("All user types must be greater than zero").build()
            );
        }

        if (username != null && username.length() < 3) {
            return formatResponse(
                HttpStatus.BAD_REQUEST, 
                ResponseObject.builder().error("Username must be at least 3 characters long").build()
            );
        }

        if (email != null && email.length() < 3) {
            return formatResponse(
                HttpStatus.BAD_REQUEST, 
                ResponseObject.builder().error("Email must be at least 3 characters long").build()
            );
        }

        if (password != null && password.length() < 3) {
            return formatResponse(
                HttpStatus.BAD_REQUEST, 
                ResponseObject.builder().error("Password must be at least 3 characters long").build()
            );
        }

        return null;
    }

}
