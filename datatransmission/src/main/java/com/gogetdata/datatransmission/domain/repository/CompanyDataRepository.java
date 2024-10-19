package com.gogetdata.datatransmission.domain.repository;

import com.gogetdata.datatransmission.domain.entity.CompanyData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyDataRepository extends MongoRepository<CompanyData, Long> {
}
