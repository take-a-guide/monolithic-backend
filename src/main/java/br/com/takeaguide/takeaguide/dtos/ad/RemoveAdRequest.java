package br.com.takeaguide.takeaguide.dtos.ad;

import static br.com.takeaguide.takeaguide.utils.ResponseUtils.formatResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.takeaguide.takeaguide.dtos.ResponseObject;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    name = "remove_ad_request",
    description = "Request containing the ID of the advertisement to be removed"
)
public record RemoveAdRequest(

    @Schema(
        name = "id",
        nullable = false,
        description = "ID of the advertisement to be removed"
    )
    @JsonProperty("id") Long id

) {

    public ResponseEntity<ResponseObject> validate() {

        if (id == null) {
            return formatResponse(
                HttpStatus.BAD_REQUEST, 
                ResponseObject.builder().error("ID of the advertisement was not provided in the request").build()
            );
        }

        return null;
    }

}
