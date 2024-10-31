package br.com.takeaguide.userservice.dtos;

import static br.com.takeaguide.userservice.utils.ResponseUtils.formatResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    name = "create_account_request",
    description = "Request containing data for creating a user account"
)
public record CreateUserRequest(

    @Schema(
        name = "cpf",
        nullable = false,
        description = "CPF provided by the user"
    )
    @JsonProperty("cpf") String cpf,

    @Schema(
        name = "name",
        nullable = false,
        description = "Name provided by the user"
    )
    @JsonProperty("name") String name,

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
        name = "phone",
        nullable = false,
        description = "Phone number provided by the user"
    )
    @JsonProperty("phone") String phone,

    @Schema(
        name = "type",
        nullable = false,
        description = "Type of user"
    )
    @JsonProperty("type") Integer type

) {

    public ResponseEntity<ResponseObject> validate() {


        if (cpf == null || cpf.length() != 11) {
            return formatResponse(
                HttpStatus.BAD_REQUEST, 
                ResponseObject.builder().error("CPF cannot be null and must be 11 characters").build()
            );
        }

        if (name == null || name.length() < 3) {
            return formatResponse(
                HttpStatus.BAD_REQUEST, 
                ResponseObject.builder().error("Name cannot be null or less than 3 characters").build()
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

        if (phone == null || phone.length() < 10) {
            return formatResponse(
                HttpStatus.BAD_REQUEST, 
                ResponseObject.builder().error("Phone number cannot be null or less than 10 characters").build()
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
