package br.com.takeaguide.takeaguide.dtos.user;

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
        name = "cpf",
        nullable = false,
        description = "cpf of the user to be deleted"
    )
    @JsonProperty("cpf") String cpf

) {

    public ResponseEntity<ResponseObject> validate() {

        if (cpf == null ) {
            return formatResponse(
                HttpStatus.BAD_REQUEST, 
                ResponseObject.builder().error("CPF cannot be null").build()
            );
        }

        return null;
    }

}
