package br.com.takeaguide.takeaguide.dtos.account;

import br.com.takeaguide.takeaguide.dtos.ResponseObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Schema(
    name = "login_response"
)
public class LoginResponse extends ResponseObject {

    private UserDto user;

    public LoginResponse(UserDto UserDto, String success){

        this.user = UserDto;
        super.setSuccess(success);

    }
    
}
