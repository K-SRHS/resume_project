package sprbt.spring.project.service;

import org.springframework.stereotype.Service;
import sprbt.spring.project.dto.ResumeFormDto;
import sprbt.spring.project.entity.Resume;
import sprbt.spring.project.repository.ResumeRepository;

@Service
public class ResumeService {

    private final ResumeRepository resumeRepository;

    public ResumeService(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    public void saveResume(ResumeFormDto resumeFormDto) {
        // ResumeFormDto를 Resume 엔티티로 변환하거나 필요한 처리를 수행한 후 저장
        Resume resume = convertToResumeEntity(resumeFormDto);
        resumeRepository.save(resume);
    }

    // 다른 필요한 메서드들...

    private Resume convertToResumeEntity(ResumeFormDto resumeFormDto) {
        Resume resume = new Resume();
        // ResumeFormDto의 필드 값을 Resume 엔티티에 설정
        resume.setLastedu(resumeFormDto.getLastedu());
        resume.setUniname(resumeFormDto.getUniname());
        resume.setMajor(resumeFormDto.getMajor());
        resume.setInterest(resumeFormDto.getInterest());
        // 다른 필드 설정...

        return resume;
    }
}