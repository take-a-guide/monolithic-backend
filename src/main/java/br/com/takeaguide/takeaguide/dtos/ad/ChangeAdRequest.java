package br.com.takeaguide.takeaguide.dtos.ad;

import static br.com.takeaguide.takeaguide.utils.ResponseUtils.formatResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.takeaguide.takeaguide.dtos.ResponseObject;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    name = "change_ad_request",
    description = "Request containing all necessary data to change an advertisement"
)
public record ChangeAdRequest(

    @Schema(
        name = "id",
        nullable = false,
        description = "ID of the advertisement to be changed"
    )
    @JsonProperty("id") Long id,

    @Schema(
        name = "ad",
        nullable = false,
        description = "New advertisement content that will replace the old one"
    )
    @JsonProperty("ad") String ad

) {

    public ResponseEntity<ResponseObject> validate() {

        if (id == null) {
            return formatResponse(
                HttpStatus.BAD_REQUEST, 
                ResponseObject.builder().error("ID of the advertisement was not provided in the request").build()
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
