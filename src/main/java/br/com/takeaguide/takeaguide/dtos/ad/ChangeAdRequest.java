package br.com.takeaguide.takeaguide.dtos.ad;

import static br.com.takeaguide.takeaguide.utils.ResponseUtils.formatResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.takeaguide.takeaguide.dtos.ResponseObject;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonProperty;

@Schema(name = "change_ad_request", description = "Request containing all necessary data to change an advertisement")
public record ChangeAdRequest(

    @Schema(name = "cpf", nullable = false, description = "CPF of the advertisement creator to be changed")
    @JsonProperty("cpf") String cpf,

    @Schema(name = "ad", nullable = false, description = "New advertisement content that will replace the old one")
    @JsonProperty("ad") String ad

) {

    public ResponseEntity<ResponseObject> validate() {
        if (cpf == null || cpf.isBlank()) {
            return formatResponse(
                HttpStatus.BAD_REQUEST, 
                ResponseObject.builder().error("CPF of the advertisement creator was not provided in the request").build()
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
