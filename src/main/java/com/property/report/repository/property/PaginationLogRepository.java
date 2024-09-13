package com.property.report.repository.property;

import com.property.report.model.PaginationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaginationLogRepository extends JpaRepository<PaginationLog, Integer> {
}
