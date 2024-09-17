package br.com.takeaguide.takeaguide.dtos.account;

import java.math.BigInteger;

import br.com.takeaguide.takeaguide.dtos.ResponseObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Schema(
    name = "Change_user_response"
)
public class ChangeUserResponse extends ResponseObject{

    private BigInteger id;

    public ChangeUserResponse(BigInteger id, String success){

        this.id = id;
        super.setSuccess(success);

    }
    
}
