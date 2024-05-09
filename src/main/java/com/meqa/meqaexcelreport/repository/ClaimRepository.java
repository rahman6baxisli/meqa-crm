package com.meqa.meqaexcelreport.repository;

import com.meqa.meqaexcelreport.models.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Integer>, JpaSpecificationExecutor<Claim> {
}
