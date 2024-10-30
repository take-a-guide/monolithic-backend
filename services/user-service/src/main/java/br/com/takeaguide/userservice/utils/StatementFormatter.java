package br.com.takeaguide.userservice.utils;

import br.com.takeaguide.userservice.dtos.ChangeUserRequest;

public class StatementFormatter {

    public static final String format(ChangeUserRequest request) {

        StringBuilder sb = new StringBuilder();

        if (request.email() != null) {
            formatCall(sb, "email", "'" + request.email() + "'");
        }
        if (request.password() != null) {
            formatCall(sb, "password", "'" + request.password() + "'");
        }
        if (request.name() != null) {
            formatCall(sb, "name", "'" + request.name() + "'");
        }
        if (request.phone() != null) {
            formatCall(sb, "phone", "'" + request.phone() + "'");
        }

        return crop(sb);
    }

    private static final String crop(StringBuilder updateStatement) {

        int position = updateStatement.lastIndexOf(",");

        return String.valueOf(
            updateStatement.replace(
                position,
                position + 1,
                ""
            )
        );

    }

    private static final void formatCall(StringBuilder sb, String name, Object value) {

        if (value != null && !value.equals("null")) {
            sb.append(name + " = " + value + ",\n");
        }

    }

}