package br.com.takeaguide.takeaguide.dtos.ad;

import static br.com.takeaguide.takeaguide.utils.ResponseUtils.formatResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.takeaguide.takeaguide.dtos.ResponseObject;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    name = "create_ad_request",
    description = "Request containing all necessary data to create an advertisement"
)
public record CreateAdRequest(

    @Schema(
        name = "user_id",
        nullable = false,
        description = "ID of the user who wants to create the advertisement"
    )
    @JsonProperty("user_id") Long userId,

    @Schema(
        name = "ad",
        nullable = false,
        description = "Image of the advertisement in Base64 format"
    )
    @JsonProperty("ad") String ad

) {

    public ResponseEntity<ResponseObject> validate() {

        if (userId == null) {
            return formatResponse(
                HttpStatus.BAD_REQUEST, 
                ResponseObject.builder().error("User ID was not provided in the request").build()
            );
        }

        if (ad == null) {
            return formatResponse(
                HttpStatus.BAD_REQUEST, 
                ResponseObject.builder().error("Advertisement content was not provided in the request").build()
            );
        }

        return null;
    }

}
