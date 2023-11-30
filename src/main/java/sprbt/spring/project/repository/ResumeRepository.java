package sprbt.spring.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sprbt.spring.project.entity.Resume;

import java.util.List;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
    List<Resume> findByUserEmail(String userEmail);
    // 추가적인 메서드가 필요한 경우 여기에 정의
}