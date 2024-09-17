package br.com.takeaguide.takeaguide.dtos.usertype;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    name = "retrieve_user_types_request",
    description = "Contains all necessary items to retrieve user types"
)
public record RetrieveUserTypesRequest(

    @Schema(
        name = "id",
        nullable = true,
        description = "ID of the user type to be retrieved; if null, all user types will be retrieved"
    )
    @JsonProperty("id") Long id

) {

}
