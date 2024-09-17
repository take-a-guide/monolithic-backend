package br.com.takeaguide.takeaguide.utils;

import br.com.takeaguide.takeaguide.dtos.account.ChangeUserRequest;

public class StatementFormatter {

    public static final String format(ChangeUserRequest request) {

        StringBuilder sb = new StringBuilder();

        formatCall(sb, "email", "'" + request.email() + "'");
        formatCall(sb, "password", "'" + request.password() + "'");
        formatCall(sb, "username", "'" + request.name() + "'");

        return crop(sb);
        
	}

    private static final String crop(StringBuilder updateStatement){

        int position = updateStatement.lastIndexOf(",");

        return String.valueOf(
            updateStatement.replace(
                position, 
                position + 1, 
                ""
            )
        );

    }

    private static final void formatCall(StringBuilder sb, String name, Object value){

        if(value != null && !value.equals("null")){

            sb.append(
                value != null ? name + " = " + value + ",\n" : ""
            );

        }

    }
    
}
