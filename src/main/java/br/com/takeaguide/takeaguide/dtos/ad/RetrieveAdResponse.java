package br.com.takeaguide.takeaguide.dtos.ad;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import br.com.takeaguide.takeaguide.dtos.ResponseObject;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    name = "retrieve_ad_response",
    description = "Response object containing a list of retrieved advertisements"
)
public class RetrieveAdResponse extends ResponseObject {

    @Schema(
        name = "ads",
        description = "List of advertisements retrieved"
    )
    @JsonProperty("ads")
    private List<AdDto> ads;

    public RetrieveAdResponse(List<AdDto> ads, String success) {
        this.ads = ads;
        super.setSuccess(success);
    }

    public List<AdDto> getAds() {
        return ads;
    }
}
