package br.com.takeaguide.takeaguide.dtos.user;

import java.math.BigInteger;
import br.com.takeaguide.takeaguide.dtos.ResponseObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Schema(
    name = "create_user_response",
    description = "Response object for creating a user"
)
public class CreateUserResponse extends ResponseObject {

    @Schema(
        description = "ID of the newly created user"
    )
    private BigInteger id;

    public CreateUserResponse(BigInteger id, String success) {
        this.id = id;
        super.setSuccess(success);
    }
}
