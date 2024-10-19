package com.gogetdata.verification.domain.service;

import com.gogetdata.verification.application.VerificationService;
import com.gogetdata.verification.application.dto.TransmissionData;
import com.gogetdata.verification.domain.CompanyRepository;
import com.gogetdata.verification.domain.entity.Company;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;
@Service
@RequiredArgsConstructor
public class VerificationServiceImpl implements VerificationService {
    private final CompanyRepository companyRepository;
    private final KafkaTemplate<String, TransmissionData> kafkaTemplate;
    @Override
    public void verify(TransmissionData transmissionData) {
        Company company =validateCompanyNotDeleted(findCompany(transmissionData.getToken()));
        transmissionData.setCompanyId(company.getCompanyId());
        kafkaTemplate.send("data-transmission",transmissionData);
    }
    private Company findCompany(String key) {
        return companyRepository.findByCompanyKey(key)
                .orElseThrow(() -> new NoSuchElementException("해당 토큰으로 회사를 찾을 수 없습니다: " + key));
    }
    private Company validateCompanyNotDeleted(Company company) {
        if (company.isDeleted()) {
            throw new NoSuchElementException("삭제된 회사입니다: " + company.getCompanyId());
        }
        return company;
    }
}
