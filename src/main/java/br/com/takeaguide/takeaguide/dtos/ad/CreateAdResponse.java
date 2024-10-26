package br.com.takeaguide.takeaguide.dtos.ad;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.takeaguide.takeaguide.dtos.ResponseObject;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    name = "create_ad_response",
    description = "Response object for creating an advertisement"
)
public class CreateAdResponse extends ResponseObject {

    @Schema(
        name = "cpf",
        description = "CPF of the advertisement that was created"
    )
    @JsonProperty("cpf")
    private String cpf;

    public CreateAdResponse(String cpf, String success) {
        super.setSuccess(success);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }
}
