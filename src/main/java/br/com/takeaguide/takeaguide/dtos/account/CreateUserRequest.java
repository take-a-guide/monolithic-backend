package br.com.takeaguide.takeaguide.dtos.account;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonProperty;
import br.com.takeaguide.takeaguide.dtos.ResponseObject;

import static br.com.takeaguide.takeaguide.utils.ResponseUtils.formatResponse;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    name = "create_account_request",
    description = "Request containing data for creating a user account"
)
public record CreateUserRequest(

    @Schema(
        name = "username",
        nullable = false,
        description = "Name provided by the user"
    )
    @JsonProperty("username") String username,

    @Schema(
        name = "email",
        nullable = false,
        description = "Email provided by the user"
    )
    @JsonProperty("email") String email,

    @Schema(
        name = "password",
        nullable = false,
        description = "Password provided by the user"
    )
    @JsonProperty("password") String password,

    @Schema(
        name = "salary",
        nullable = false,
        description = "Salary provided by the user"
    )
    @JsonProperty("salary") String salary,

    @Schema(
        name = "type",
        nullable = false,
        description = "Type of user"
    )
    @JsonProperty("type") Integer type

) {

    public ResponseEntity<ResponseObject> validate() {

        if (username == null || username.length() < 3) {
            return formatResponse(
                HttpStatus.BAD_REQUEST, 
                ResponseObject.builder().error("Username cannot be null or less than 3 characters").build()
            );
        }

        if (email == null || email.length() < 3) {
            return formatResponse(
                HttpStatus.BAD_REQUEST, 
                ResponseObject.builder().error("Email cannot be null or less than 3 characters").build()
            );
        }

        if (password == null || password.length() < 3) {
            return formatResponse(
                HttpStatus.BAD_REQUEST, 
                ResponseObject.builder().error("Password cannot be null or less than 3 characters").build()
            );
        }

        if (type == null || type < 1) {
            return formatResponse(
                HttpStatus.BAD_REQUEST, 
                ResponseObject.builder().error("User type must be specified and greater than zero").build()
            );
        }

        return null;
    }
}
