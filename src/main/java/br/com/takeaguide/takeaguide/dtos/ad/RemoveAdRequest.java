package br.com.takeaguide.takeaguide.dtos.ad;

import static br.com.takeaguide.takeaguide.adapters.utils.ResponseUtils.formatResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.takeaguide.takeaguide.dtos.ResponseObject;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    name = "remove_ad_request",
    description = "Request containing the CPF of the advertisement creator to be removed"
)
public record RemoveAdRequest(

    @Schema(
        name = "cpf",
        nullable = false,
        description = "CPF of the advertisement creator to be removed"
    )
    @JsonProperty("cpf") String cpf

) {

    public ResponseEntity<ResponseObject> validate() {

        if (cpf == null || cpf.isBlank()) {
            return formatResponse(
                HttpStatus.BAD_REQUEST, 
                ResponseObject.builder().error("CPF of the advertisement creator was not provided in the request").build()
            );
        }

        return null;
    }

}
