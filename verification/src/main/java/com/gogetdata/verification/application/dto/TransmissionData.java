package com.gogetdata.verification.application.dto;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class TransmissionData {
    @NotNull
    private String type;

    @NotNull
    private String subtype;

    @NotNull
    private Map<String, Object> data;

    @NotNull
    private String token;
    private Long companyId;
}
