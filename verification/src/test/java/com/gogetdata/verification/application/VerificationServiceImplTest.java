package com.gogetdata.verification.application;

import com.gogetdata.verification.domain.CompanyRepository;
import com.gogetdata.verification.domain.service.VerificationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class VerificationServiceImplTest {

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private VerificationServiceImpl verificationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void verify_Success() {
    }

    @Test
    public void verify_InvalidToken() {

    }

    @Test
    public void verify_DeletedCompany() {
    }
}
