package br.com.takeaguide.userservice.dtos;

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
        description = "CPF of the newly created user"
    )
    private String cpf;

    public CreateUserResponse(String cpf, String success) {
        this.cpf = cpf;
        super.setSuccess(success);
    }
}
