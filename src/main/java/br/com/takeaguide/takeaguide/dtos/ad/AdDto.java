package br.com.takeaguide.takeaguide.dtos.ad;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    name = "Ad",
    description = "Data Transfer Object representing an advertisement"
)
public record AdDto(

    @Schema(
        name = "id",
        description = "ID of the advertisement"
    )
    @JsonProperty("id") long id,

    @Schema(
        name = "user_id",
        description = "ID of the user who created the advertisement"
    )
    @JsonProperty("user_id") long userId,

    @Schema(
        name = "image",
        description = "Base64-encoded image of the advertisement"
    )
    @JsonProperty("ad") String ad

) {}
