package com.gogetdata.verification.domain;


import com.gogetdata.verification.domain.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company,Long> {
    Optional<Company> findByCompanyKey(String token);

}
