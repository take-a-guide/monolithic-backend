package br.com.takeaguide.takeaguide.dtos.account;

import static br.com.takeaguide.takeaguide.utils.ResponseUtils.formatResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.takeaguide.takeaguide.dtos.ResponseObject;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    name = "retrieve_user_request",
    description = "Request containing all necessary data to retrieve a user"
)
public record RetrieveUserRequest(
    
    @Schema(
        name = "id",
        nullable = true,
        description = "Can be null if another identifier is used"
    )
    @JsonProperty("id") Long id,

    @Schema(
        name = "username",
        nullable = true,
        description = "Can be null if another identifier is used"
    )
    @JsonProperty("username") String username,

    @Schema(
        name = "salary",
        nullable = false,
        description = "Salary provided by the user"
    )
    @JsonProperty("salary") String salary,

    @Schema(
        name = "email",
        nullable = true,
        description = "Can be null if another identifier is used"
    )
    @JsonProperty("email") String email

) {

    public ResponseEntity<ResponseObject> validate() {

        if (id == null && username == null && email == null) {
            return formatResponse(
                HttpStatus.BAD_REQUEST, 
                ResponseObject.builder().error("No user identifier was provided in the request").build()
            );
        }

        return null;
    }

}
