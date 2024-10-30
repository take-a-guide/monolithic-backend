package br.com.takeaguide.userservice.dtos;


import static br.com.takeaguide.userservice.utils.ResponseUtils.formatResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    name = "change_account_request",
    description = "Request containing all necessary data to change a user's details"
)
public record ChangeUserRequest(

    @Schema(
        name = "cpf",
        nullable = false,
        description = "CPF of the user to be changed"
    )
    @JsonProperty("cpf") String cpf,

    @Schema(
        name = "name",
        nullable = true,
        description = "name to be changed; only required if it needs to be updated, ignored if null"
    )
    @JsonProperty("name") String name,

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
    @JsonProperty("type") Integer type,

    @Schema (
        name = "phone",
        nullable = true,
        description = "Phone to be changed; only required if it needs to be updated, ignored if null"
    )
    @JsonProperty("phone") String phone

) {

    public ResponseEntity<ResponseObject> validate() {

        if (cpf == null) {

       
            return formatResponse(
                HttpStatus.BAD_REQUEST, 
                ResponseObject.builder().error("CPF cannot be null").build()
            );
        }

        if (name == null && email == null && password == null && type == null && phone == null) {
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

        if (name != null && name.length() < 3) {
            return formatResponse(
                HttpStatus.BAD_REQUEST, 
                ResponseObject.builder().error("Name must be at least 3 characters long").build()
            );
        }

        if (email != null && email.length() < 3) {
            return formatResponse(
                HttpStatus.BAD_REQUEST, 
                ResponseObject.builder().error("Email must be at least 3 characters long").build()
            );
        }

        if (password != null && password.length() < 8) {
            return formatResponse(
                HttpStatus.BAD_REQUEST, 
                ResponseObject.builder().error("Password must be at least 8 characters long").build()
            );
        }

        return null;
    }

 

}
