package sprbt.spring.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sprbt.spring.project.entity.ProfileImg;

import java.util.List;

public interface ProfileImgRepository extends JpaRepository<ProfileImg, Long> {
    List<ProfileImg> findByResumeIdOrderByIdAsc(Long resumeId);
}
