package com.gogetdata.verification.presentation;

import com.gogetdata.verification.application.VerificationService;
import com.gogetdata.verification.application.dto.TransmissionData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/verification")
@RequiredArgsConstructor
public class VerificationController {
    private final VerificationService verificationService;
    @PostMapping("")
    public void  receiveData(@RequestBody TransmissionData transmissionData) {
        verificationService.verify(transmissionData);
    }
}
