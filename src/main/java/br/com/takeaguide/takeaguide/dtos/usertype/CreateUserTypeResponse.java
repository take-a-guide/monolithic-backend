package br.com.takeaguide.takeaguide.dtos.usertype;

import java.math.BigInteger;

import br.com.takeaguide.takeaguide.dtos.ResponseObject;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreateUserTypeResponse extends ResponseObject {

    private BigInteger key;

    private CreateUserTypeResponse(BigInteger key, String success){

        this.key = key;
        super.setSuccess(success);  

    }

    public static final CreateUserTypeResponse success(BigInteger key, String success){
        return new CreateUserTypeResponse(key, success);
    }
    
}
