package br.com.takeaguide.takeaguide.dtos.guide_listing;

import br.com.takeaguide.takeaguide.dtos.ResponseObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Schema(
    name = "create_guide_listing_response"
)
public class CreateGuideListingResponse extends ResponseObject {

    private String cpf;

    public CreateGuideListingResponse(String cpf, String success) {
        this.cpf = cpf;
        super.setSuccess(success);
    }
}