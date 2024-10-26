package br.com.takeaguide.takeaguide.dtos.ad;

import com.fasterxml.jackson.annotation.JsonProperty;
import br.com.takeaguide.takeaguide.dtos.ResponseObject;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    name = "change_ad_response",
    description = "Response object for changing an advertisement"
)
public class ChangeAdResponse extends ResponseObject {

    @Schema(
        name = "cpf",
        description = "CPF of the advertisement creator"
    )
    @JsonProperty("cpf") 
    private String cpf;

    public ChangeAdResponse(String cpf, String success) {
        this.cpf = cpf;
        super.setSuccess(success);
    }

    public String getCpf() {
        return cpf;
    }
}
