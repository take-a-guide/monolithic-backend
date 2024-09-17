package br.com.takeaguide.takeaguide.dtos.ad;

import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.takeaguide.takeaguide.dtos.ResponseObject;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    name = "create_ad_response",
    description = "Response object for creating an advertisement"
)
public class CreateAdResponse extends ResponseObject {

    @Schema(
        name = "id",
        description = "ID of the advertisement that was created"
    )
    @JsonProperty("id")
    private BigInteger id;

    public CreateAdResponse(BigInteger id, String success) {
        super.setSuccess(success);
        this.id = id;
    }

    public BigInteger getId() {
        return id;
    }
}
