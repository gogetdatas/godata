    package com.gogetdata.datatransmission.domain.entity;

    import com.fasterxml.jackson.databind.JsonNode;
    import com.gogetdata.datatransmission.application.dto.TransmissionData;
    import jakarta.validation.constraints.NotNull;
    import lombok.*;
    import org.springframework.data.annotation.Id;
    import org.springframework.data.mongodb.core.index.Indexed;
    import org.springframework.data.mongodb.core.mapping.Document;

    import java.util.Map;

    @Document(collection = "company_data")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class CompanyData {

        @Id
        private String id; // MongoDB의 기본 ObjectId

        private Long companyId;
        @NotNull
        @Indexed
        private String type;

        @NotNull
        @Indexed
        private String subtype;

        @NotNull
        private Map<String, Object> data;

        private String keyHash;
        public static CompanyData create(TransmissionData dto,Long companyId) {
            return CompanyData.builder()
                    .companyId(companyId)
                    .type(dto.getType())
                    .subtype(dto.getSubtype())
                    .data(dto.getData())
                    .keyHash(dto.getKeyHash())
                    .build();
        }
    }
