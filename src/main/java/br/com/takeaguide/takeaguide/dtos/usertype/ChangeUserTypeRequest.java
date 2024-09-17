package br.com.takeaguide.takeaguide.dtos.usertype;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static br.com.takeaguide.takeaguide.utils.ResponseUtils.formatResponse;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.takeaguide.takeaguide.dtos.ResponseObject;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    name = "change_user_type_request",
    description = "Request containing all necessary data to change a user type"
)
public record ChangeUserTypeRequest(

    @Schema(
        name = "id",
        nullable = false,
        description = "ID of the user type to be modified"
    )
    @JsonProperty("id") Long id,

    @Schema(
        name = "name",
        nullable = false,
        description = "Name of the user type"
    )
    @JsonProperty("name") String name

) {

    public ResponseEntity<ResponseObject> validate() {

        if (id == null) {
            return formatResponse(
                HttpStatus.BAD_REQUEST, 
                ResponseObject.error("ID cannot be null")
            );
        }

        if (name == null) {
            return formatResponse(
                HttpStatus.BAD_REQUEST, 
                ResponseObject.error("Name cannot be null")
            );
        }

        return null;
    }
}
