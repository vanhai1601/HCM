package vn.com.mbbank.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.com.mbbank.entities.HrEducationDegreeEntity;

@Repository
public interface HrEducationDegreeRepositoryJPA extends JpaRepository<HrEducationDegreeEntity, Long> {
}
