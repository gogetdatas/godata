package com.gogetdata.verification.application.dto;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransmissionData {
    @NotNull
    private String type;

    @NotNull
    private String subtype;

    @NotNull
    private JsonNode data;

    @NotNull
    private String token;
}
