package br.com.takeaguide.takeaguide.dtos.ad;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    name = "ad",
    description = "Data Transfer Object representing an advertisement"
)
public record AdDto(

    @Schema(
        name = "cpf",
        description = "CPF of the advertisement creator"
    )
    @JsonProperty("cpf") String cpf,

    @Schema(
        name = "image",
        description = "Base64-encoded image of the advertisement"
    )
    @JsonProperty("ad") String ad

) {}
