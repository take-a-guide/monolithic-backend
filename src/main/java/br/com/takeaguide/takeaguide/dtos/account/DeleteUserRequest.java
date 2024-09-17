package br.com.takeaguide.takeaguide.dtos.account;

import static br.com.takeaguide.takeaguide.utils.ResponseUtils.formatResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.takeaguide.takeaguide.dtos.ResponseObject;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    name = "delete_account_request",
    description = "Request containing all necessary data to delete a user account"
)
public record DeleteUserRequest(

    @Schema(
        name = "id",
        nullable = false,
        description = "ID of the user to be deleted"
    )
    @JsonProperty("id") Long id

) {

    public ResponseEntity<ResponseObject> validate() {

        if (id == null || id < 1) {
            return formatResponse(
                HttpStatus.BAD_REQUEST, 
                ResponseObject.builder().error("ID cannot be null or less than one").build()
            );
        }

        return null;
    }

}
