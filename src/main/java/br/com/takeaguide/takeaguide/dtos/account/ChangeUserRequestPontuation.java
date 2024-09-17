package br.com.takeaguide.takeaguide.dtos.account;

import static br.com.takeaguide.takeaguide.utils.ResponseUtils.formatResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.takeaguide.takeaguide.dtos.ResponseObject;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    name = "add_pontuation_to_user_request",
    description = "Request containing all necessary data to add or remove a user's pontuation"
)
public record ChangeUserRequestPontuation(
    
    @Schema(
        name = "id",
        nullable = false,
        description = "ID of the user whose pontuation will be changed"
    )
    @JsonProperty("id") Long id,
    
    @Schema(
        name = "pontuation",
        nullable = false,
        description = "pontuation to be added or removed"
    )
    @JsonProperty("pontuation") Long pontuation

) {

    public ResponseEntity<ResponseObject> validate() {

        if (id == null) {
            return formatResponse(
                HttpStatus.BAD_REQUEST, 
                ResponseObject.builder().error("User ID cannot be null").build()
            );
        }

        if (pontuation == null) {
            return formatResponse(
                HttpStatus.BAD_REQUEST, 
                ResponseObject.builder().error("Pontuation cannot be null").build()
            );
        }

        return null;
    }
}
