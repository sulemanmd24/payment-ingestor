package com.payment.ingestor.repository;


import com.payment.ingestor.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity,String>, JpaSpecificationExecutor<AccountEntity> {
}
