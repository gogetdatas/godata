package com.gogetdata.verification.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "company")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PRIVATE)

public class Company extends BaseEntity{
    /**
     * 컬럼 - 연관관계 컬럼을 제외한 컬럼을 정의합니다.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_key")
    private String companyKey;


    /**
     * 생성자 - 약속된 형태로만 생성가능하도록 합니다.
     */
    public static Company create(String companyName,String companyToken) {
        return Company.builder()
                .companyName(companyName)
                .companyKey(companyToken)
                .build();
    }
}
