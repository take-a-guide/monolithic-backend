package br.com.takeaguide.takeaguide.dtos.usertype;

import java.util.List;
import br.com.takeaguide.takeaguide.dtos.ResponseObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@Schema(
    name = "retrieve_user_type_response",
    description = "Response object containing a list of user types and a success message"
)
@Getter
@ToString
public class RetrieveUserTypesResponse extends ResponseObject {

    @Schema(description = "List of user types retrieved")
    private List<UserTypeDto> userTypes;

    private RetrieveUserTypesResponse(List<UserTypeDto> userTypes, String success) {
        this.userTypes = userTypes;
        super.setSuccess(success);
    }

    public static RetrieveUserTypesResponse success(List<UserTypeDto> userTypeDtos, String success) {
        return new RetrieveUserTypesResponse(userTypeDtos, success);
    }
}
