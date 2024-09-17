package br.com.takeaguide.takeaguide.dtos.ad;

import static br.com.takeaguide.takeaguide.utils.ResponseUtils.formatResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.takeaguide.takeaguide.dtos.ResponseObject;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    name = "retrieve_ad_request",
    description = "Request containing identifiers for retrieving an advertisement"
)
public record RetrieveAdRequest(

    @Schema(
        name = "user_id",
        nullable = true,
        description = "User ID; can be null if another identifier is used"
    )
    @JsonProperty("user_id") Long userId,

    @Schema(
        name = "id_ad",
        nullable = true,
        description = "Advertisement ID; can be null if another identifier is used"
    )
    @JsonProperty("id_ad") Long idAd

) {

    public ResponseEntity<ResponseObject> validate() {

        if (userId == null && idAd == null) {
            return formatResponse(
                HttpStatus.BAD_REQUEST, 
                ResponseObject.builder().error("No identifier was provided in the request").build()
            );
        }

        return null;
    }

}
