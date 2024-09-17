package br.com.takeaguide.takeaguide.dtos.usertype;

import java.math.BigInteger;
import br.com.takeaguide.takeaguide.dtos.ResponseObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@Schema(
    name = "change_user_type_response",
    description = "Response object for changing a user type"
)
@Getter
@ToString
public class ChangeUserTypeResponse extends ResponseObject {

    @Schema(description = "ID of the changed user type")
    private BigInteger id;

    private ChangeUserTypeResponse(BigInteger id, String success) {
        this.id = id;
        super.setSuccess(success);
    }

    public static ChangeUserTypeResponse success(BigInteger id, String success) {
        return new ChangeUserTypeResponse(id, success);
    }
}
