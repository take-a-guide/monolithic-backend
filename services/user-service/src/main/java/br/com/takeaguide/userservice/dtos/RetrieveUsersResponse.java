package br.com.takeaguide.userservice.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Schema(
    name = "retrieve_users_response",
    description = "Response containing all found users"
)
public class RetrieveUsersResponse extends ResponseObject {

    @Schema(
        description = "List of users retrieved"
    )
    @JsonProperty("users")
    private List<UserDto> userDtos;

    public RetrieveUsersResponse(List<UserDto> userDtos, String success) {
        this.userDtos = userDtos;
        super.setSuccess(success);
    }
}