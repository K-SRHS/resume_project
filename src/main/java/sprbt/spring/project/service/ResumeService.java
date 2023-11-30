package sprbt.spring.project.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import sprbt.spring.project.dto.ResumeFormDto;
import sprbt.spring.project.entity.Member;
import sprbt.spring.project.entity.Resume;
import sprbt.spring.project.repository.MemberRepository;
import sprbt.spring.project.repository.ResumeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final MemberRepository memberRepository;

    public ResumeService(MemberRepository memberRepository, ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
        this.memberRepository = memberRepository;
    }

    public void saveResume(ResumeFormDto resumeFormDto) {
        // ResumeFormDto를 Resume 엔티티로 변환하거나 필요한 처리를 수행한 후 저장
        Resume resume = convertToResumeEntity(resumeFormDto);

        // 현재 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        // 현재 사용자 정보를 Resume 엔티티에 추가
        resume.setUserEmail(currentPrincipalName);

        // Resume를 저장하고 Member 엔티티에도 추가
        Resume savedResume = resumeRepository.save(resume);
        Member member = memberRepository.findByEmail(currentPrincipalName)
                .orElseThrow(() -> new EntityNotFoundException("해당 이메일을 찾을 수 없습니다: " + currentPrincipalName));
        member.addResume(savedResume);
    }

    // 다른 필요한 메서드들...

    private Resume convertToResumeEntity(ResumeFormDto resumeFormDto) {
        // 현재 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Resume resume = new Resume();
        // ResumeFormDto의 필드 값을 Resume 엔티티에 설정
        resume.setMember(memberRepository.findMemberByEmail(currentPrincipalName));
        resume.setLastedu(resumeFormDto.getLastedu());
        resume.setUniname(resumeFormDto.getUniname());
        resume.setMajor(resumeFormDto.getMajor());
        resume.setInterest(resumeFormDto.getInterest());
        // 다른 필드 설정...

        return resume;
    }

    // 이력서 목록 가져오기
    public List<Resume> getAllResumes() {
        return resumeRepository.findAll();
    }
    // 이력서 수정하기
    public void updateResume(Long resumeId, ResumeFormDto resumeFormDto) {
        Optional<Resume> optionalResume = resumeRepository.findById(resumeId);
        if (optionalResume.isPresent()) {
            Resume existingResume = optionalResume.get();

            // 기존 이력서 엔티티를 새로운 데이터로 업데이트
            existingResume.setLastedu(resumeFormDto.getLastedu());
            existingResume.setUniname(resumeFormDto.getUniname());
            existingResume.setMajor(resumeFormDto.getMajor());
            existingResume.setInterest(resumeFormDto.getInterest());

            // 다른 필드 설정...

            // 엔티티 저장
            resumeRepository.save(existingResume);
        } else {
            // 이력서를 찾을 수 없는 경우 예외 처리
            throw new EntityNotFoundException("해당 이력서를 찾을 수 없습니다: " + resumeId);
        }
    }

    public ResumeFormDto getResumeById(Long resumeId) {
        // resumeId에 해당하는 이력서 정보를 데이터베이스에서 가져오는 로직을 여기에 작성합니다.
        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new EntityNotFoundException("이력서를 찾을 수 없습니다. ID: " + resumeId));

        // Resume 엔티티를 ResumeFormDto로 변환하여 반환합니다.
        return convertToResumeFormDto(resume);
    }

    // Resume 엔티티를 ResumeFormDto로 변환하는 메서드
    private ResumeFormDto convertToResumeFormDto(Resume resume) {
        ResumeFormDto resumeFormDto = new ResumeFormDto();
        // Resume 엔티티의 필드 값을 ResumeFormDto로 설정하는 코드를 작성합니다.
        resumeFormDto.setId(resume.getId());
        resumeFormDto.setLastedu(resume.getLastedu());
        resumeFormDto.setUniname(resume.getUniname());
        resumeFormDto.setMajor(resume.getMajor());
        resumeFormDto.setInterest(resume.getInterest());
        // 필요한 다른 필드들을 설정합니다.

        return resumeFormDto;
    }

    public List<Resume> getResumesByUser(String userEmail) {
        return resumeRepository.findByUserEmail(userEmail);
    }


    // 다른 필요한 메서드들...
}

    // 다른 메서드들...