package br.com.takeaguide.takeaguide.dtos.ad;

import static br.com.takeaguide.takeaguide.adapters.utils.ResponseUtils.formatResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.takeaguide.takeaguide.dtos.ResponseObject;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonProperty;

@Schema(name = "create_ad_request", description = "Request containing all necessary data to create an advertisement")
public record CreateAdRequest(

    @Schema(name = "cpf", nullable = false, description = "CPF of the advertisement creator")
    @JsonProperty("cpf") String cpf,

    @Schema(name = "ad", nullable = false, description = "Image of the advertisement in Base64 format")
    @JsonProperty("ad") String ad

) {

    public ResponseEntity<ResponseObject> validate() {

        if (cpf == null || cpf.isBlank()) {
            return formatResponse(
                HttpStatus.BAD_REQUEST, 
                ResponseObject.builder().error("CPF was not provided in the request").build()
            );
        }

        if (ad == null || ad.isBlank()) {
            return formatResponse(
                HttpStatus.BAD_REQUEST, 
                ResponseObject.builder().error("Advertisement content was not provided in the request").build()
            );
        }

        return null;
    }
}
