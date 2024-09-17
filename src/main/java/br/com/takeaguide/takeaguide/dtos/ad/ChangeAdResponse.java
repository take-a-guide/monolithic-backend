package br.com.takeaguide.takeaguide.dtos.ad;

import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonProperty;
import br.com.takeaguide.takeaguide.dtos.ResponseObject;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    name = "change_ad_response",
    description = "Response object for changing an advertisement"
)
public class ChangeAdResponse extends ResponseObject {

    @Schema(
        name = "id",
        description = "ID of the advertisement that was changed"
    )
    @JsonProperty("id") 
    private BigInteger id;

    public ChangeAdResponse(BigInteger id, String success) {
        this.id = id;
        super.setSuccess(success);
    }

    public BigInteger getId() {
        return id;
    }
}
