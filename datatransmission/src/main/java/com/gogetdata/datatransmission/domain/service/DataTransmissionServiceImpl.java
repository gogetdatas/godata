package com.gogetdata.datatransmission.domain.service;

import com.gogetdata.datatransmission.application.DataTransmissionService;
import com.gogetdata.datatransmission.application.dto.TransmissionData;
import com.gogetdata.datatransmission.domain.entity.*;
import com.gogetdata.datatransmission.domain.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class DataTransmissionServiceImpl implements DataTransmissionService {
    private final TypeSetRepository typeSetRepository;
    private final SubTypeSetRepository subTypeSetRepository;
    private final KeySetRepository keySetRepository;
    private final KeySetReferenceRepository keySetReferenceRepository;
    private final CompanyDataRepository companyDataRepository;
    @Override
    public void dataTransmission(Long companyId, TransmissionData transmissionData) {
        try {
            List<String> keys = transmissionData.getData().keySet().stream().toList();
            String keyHash = HashUtil.generateHash(keys);
            transmissionData.setKeyHash(keyHash);
            // mongodb 데이터 저장
            checkType(companyId, transmissionData);
            companyDataRepository.save(CompanyData.create(transmissionData,companyId));
        } catch (Exception e) {
            throw new RuntimeException("데이터 전송 중 오류가 발생했습니다.", e);
        }
    }

    private void checkType(Long companyId, TransmissionData transmissionData) {
        Optional<TypeSet> typeSetOpt = typeSetRepository.getTypeSet(companyId, transmissionData.getType());
        if (typeSetOpt.isEmpty()) {
            saveType(companyId, transmissionData);
        } else {
            checkSubType(typeSetOpt.get().getTypeSetId(), transmissionData);
        }
    }

    private void checkSubType(Long typeSetId, TransmissionData transmissionData) {
        Optional<SubTypeSet> subtypeSetOpt = typeSetRepository.getSubTypeSet(typeSetId, transmissionData.getSubtype());
        if (subtypeSetOpt.isEmpty()) {
            saveSubType(typeSetId, transmissionData);
        } else {
            checkKeySet(subtypeSetOpt.get().getSubTypesetId(), transmissionData);
        }
    }

    private void checkKeySet(Long subTypesetId, TransmissionData transmissionData) {
        String keyHash = transmissionData.getKeyHash();
        Optional<KeySetReference> existingKeySetReferenceOpt = typeSetRepository.getKeySetReference(subTypesetId, keyHash);
        if (existingKeySetReferenceOpt.isEmpty()) {
            saveKeySet(subTypesetId, transmissionData, keyHash);
        }
    }

    private void saveType(Long companyId, TransmissionData transmissionData) {
        TypeSet typeSet = TypeSet.create(companyId, transmissionData.getType());
        typeSetRepository.save(typeSet);
        saveSubType(typeSet.getTypeSetId(), transmissionData);
    }

    private void saveSubType(Long typeSetId, TransmissionData transmissionData) {
        SubTypeSet subTypeSet = SubTypeSet.create(typeSetId, transmissionData.getSubtype());
        subTypeSetRepository.save(subTypeSet);
        checkKeySet(subTypeSet.getSubTypesetId(), transmissionData);
    }

    private void saveKeySet(Long subTypesetId, TransmissionData transmissionData, String keyHash) {
        KeySetReference keySetReference = KeySetReference.create(subTypesetId, keyHash);
        keySetReferenceRepository.save(keySetReference);
        saveKey(keySetReference.getKeySetReferenceId(),transmissionData.getData().keySet().stream().toList());
    }

    private void saveKey(Long keySetReferenceId, List<String> keys) {
        List<KeySet> keySets = new ArrayList<>();
        for (String key : keys) {
            keySets.add(KeySet.create(keySetReferenceId, key));
        }
        keySetRepository.saveAll(keySets);
    }
}
