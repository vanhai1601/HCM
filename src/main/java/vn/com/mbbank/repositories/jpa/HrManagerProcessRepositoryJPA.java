package vn.com.mbbank.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.com.mbbank.entities.HrManagerProcessEntity;

@Repository
public interface HrManagerProcessRepositoryJPA extends JpaRepository<HrManagerProcessEntity, Long> {
}
