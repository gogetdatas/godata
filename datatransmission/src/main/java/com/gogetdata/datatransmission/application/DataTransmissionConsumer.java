package com.gogetdata.datatransmission.application;

import com.gogetdata.datatransmission.application.dto.TransmissionData;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@RequiredArgsConstructor
@Service
public class DataTransmissionConsumer {
    private final DataTransmissionService dataTransmissionService;
    @KafkaListener(groupId = "data", topics = "data-transmission")
    public void dataConsumer(TransmissionData data) throws NoSuchAlgorithmException {
        dataTransmissionService.dataTransmission(data.getCompanyId(), data);
    }
}
