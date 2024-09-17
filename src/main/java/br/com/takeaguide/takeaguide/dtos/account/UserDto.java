package br.com.takeaguide.takeaguide.dtos.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    name = "user",
    description = "User Data Transfer Object"
)
public record UserDto(

    @Schema(
        name = "id",
        description = "User ID"
    )
    @JsonProperty("id") long id,

    @Schema(
        name = "username",
        description = "Username"
    )
    @JsonProperty("username") String username,

    @Schema(
        name = "email",
        description = "User email"
    )
    @JsonProperty("email") String email,

    @Schema(
        name = "salary",
        nullable = false,
        description = "User salary"
    )
    @JsonProperty("salary") String salary,

    @Schema(
        name = "points",
        description = "User points"
    )
    @JsonProperty("points") long points,

    @Schema(
        name = "user_type_id",
        description = "User type (1: Hirer, 2: Guide)"
    )
    @JsonProperty("user_type_id") int userTypeId,

    @Schema(
        name = "items",
        description = "Number of items associated with the user"
    )
    @JsonProperty("items") long items

) {}
