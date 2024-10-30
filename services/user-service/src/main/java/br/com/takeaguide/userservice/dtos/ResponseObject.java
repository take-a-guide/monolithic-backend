package br.com.takeaguide.userservice.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "response_object")
public class ResponseObject {

    private String error;
    private String success;
    private Object data; 

    public static final ResponseObject error(String error) {
        return ResponseObject.builder().error(error).build();
    }

    public static final ResponseObject success(String success) {
        return ResponseObject.builder().success(success).build();
    }

    public static final ResponseObject successWithData(String success, Object data) {
        return ResponseObject.builder().success(success).data(data).build();
    }

    public static final ResponseObject withData(Object data) {
        return ResponseObject.builder().data(data).build();
    }
}