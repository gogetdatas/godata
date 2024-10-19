package com.gogetdata.datatransmission.application;

import com.gogetdata.datatransmission.application.dto.TransmissionData;

import java.security.NoSuchAlgorithmException;

public interface DataTransmissionService {
    void dataTransmission(Long companyId,TransmissionData transmissionData) throws NoSuchAlgorithmException;
}
