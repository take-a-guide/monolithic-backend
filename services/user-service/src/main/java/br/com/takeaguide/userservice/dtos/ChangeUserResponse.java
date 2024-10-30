package br.com.takeaguide.userservice.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Schema(
    name = "change_user_response"
)
public class ChangeUserResponse extends ResponseObject{

    private String cpf;

    public ChangeUserResponse(String cpf, String success){

        this.cpf = cpf;
        super.setSuccess(success);

    }
    
}
