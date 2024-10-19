package com.gogetdata.datatransmission.domain.repository;

import com.gogetdata.datatransmission.domain.entity.KeySetReference;
import com.gogetdata.datatransmission.domain.entity.SubTypeSet;
import com.gogetdata.datatransmission.domain.entity.TypeSet;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.gogetdata.datatransmission.domain.entity.QSubTypeSet.subTypeSet;
import static com.gogetdata.datatransmission.domain.entity.QTypeSet.typeSet;
import static com.gogetdata.datatransmission.domain.entity.QKeySetReference.keySetReference;
@Service
@RequiredArgsConstructor
public class TypeSetRepositoryImpl implements TypeSetRepositoryCustom{
    private final JPAQueryFactory queryFactory;
    @Override
    public Optional<TypeSet> getTypeSet(Long companyId, String typeName) {

        return Optional.ofNullable(queryFactory.selectFrom(typeSet)
                .where(typeSet.companyId.eq(companyId)
                        .and(typeSet.typeName.eq(typeName))
                )
                .fetchOne());
    }

    @Override
    public Optional<SubTypeSet> getSubTypeSet(Long typeSetId, String subtypeName) {
        return Optional.ofNullable(queryFactory.selectFrom(subTypeSet)
                .where(subTypeSet.typeId.eq(typeSetId)
                        .and(subTypeSet.subtypeName.eq(subtypeName))
                )
                .fetchOne());
    }

    @Override
    public Optional<KeySetReference> getKeySetReference(Long subTypeSetId, String keySetHash) {
        return Optional.ofNullable(queryFactory.selectFrom(keySetReference)
                .where(keySetReference.subtypeKeySetId.eq(subTypeSetId)
                        .and(keySetReference.keySetHash.eq(keySetHash))
                )
                .fetchOne());
    }
}
