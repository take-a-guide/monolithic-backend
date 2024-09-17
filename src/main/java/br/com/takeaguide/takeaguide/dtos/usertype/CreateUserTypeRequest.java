package br.com.takeaguide.takeaguide.dtos.usertype;

import static br.com.takeaguide.takeaguide.utils.ResponseUtils.formatResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.takeaguide.takeaguide.dtos.ResponseObject;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    name = "create_user_type_request",
    description = "Request containing all necessary data for creating a user type"
)
public record CreateUserTypeRequest(

    @Schema(
        name = "name",
        nullable = false,
        description = "Name of the user type to be created"
    )
    @JsonProperty("name") String name

) {

    public ResponseEntity<ResponseObject> validate() {

        if (name == null) {
            return formatResponse(
                HttpStatus.BAD_REQUEST, 
                ResponseObject.error("The name field cannot be null")
            );
        }

        return null;
    }
}
