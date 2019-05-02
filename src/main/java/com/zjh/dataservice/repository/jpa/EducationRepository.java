package com.zjh.dataservice.repository.jpa;

import com.zjh.dataservice.entity.jpa.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface EducationRepository extends JpaRepository<Education, String> {
}
