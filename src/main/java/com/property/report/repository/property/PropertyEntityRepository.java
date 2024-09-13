package com.property.report.repository.property;

import com.property.report.model.PropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyEntityRepository extends JpaRepository<PropertyEntity, Integer> {
}
