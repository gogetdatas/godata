package com.gogetdata.datatransmission.domain.repository;

import com.gogetdata.datatransmission.domain.entity.KeySetReference;
import com.gogetdata.datatransmission.domain.entity.SubTypeSet;
import com.gogetdata.datatransmission.domain.entity.TypeSet;

import java.util.Optional;

public interface TypeSetRepositoryCustom {
    Optional<TypeSet> getTypeSet(Long companyId , String typeName);
    Optional<SubTypeSet> getSubTypeSet(Long typeSetId , String subtypeName);
    Optional<KeySetReference> getKeySetReference(Long subTypeSetId , String keySetHash);
}
