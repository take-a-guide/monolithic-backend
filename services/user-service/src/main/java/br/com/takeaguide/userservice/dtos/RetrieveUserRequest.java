package br.com.takeaguide.userservice.dtos;

import static br.com.takeaguide.userservice.utils.ResponseUtils.formatResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonProperty;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    name = "retrieve_user_request",
    description = "Request containing all necessary data to retrieve a user"
)
public record RetrieveUserRequest(
    
    @Schema(
        name = "cpf",
        nullable = true,
        description = "Can be null if another identifier is used"
    )
    @JsonProperty("cpf") String cpf,

    @Schema(
        name = "name",
        nullable = true,
        description = "Can be null if another identifier is used"
    )
    @JsonProperty("name") String name,

    @Schema(
        name = "email",
        nullable = true,
        description = "Can be null if another identifier is used"
    )
    @JsonProperty("email") String email

) {

    public ResponseEntity<ResponseObject> validate() {

        if (cpf == null && name == null && email == null) {
            return formatResponse(
                HttpStatus.BAD_REQUEST, 
                ResponseObject.builder().error("No user identifier was provided in the request").build()
            );
        }

        return null;
    }


}
