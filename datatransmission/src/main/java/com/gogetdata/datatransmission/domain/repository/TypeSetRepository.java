package com.gogetdata.datatransmission.domain.repository;

import com.gogetdata.datatransmission.domain.entity.TypeSet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeSetRepository extends JpaRepository<TypeSet,Long> , TypeSetRepositoryCustom {
}
