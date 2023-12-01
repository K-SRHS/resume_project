package sprbt.spring.project.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sprbt.spring.project.dto.ResumeFormDto;
import sprbt.spring.project.entity.Resume;
import sprbt.spring.project.service.ResumeService;

import java.io.IOException;
import java.util.List;

@Controller
public class ResumeController {

    private final ResumeService resumeService;

    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @GetMapping("/resume")
    public String resumeForm(Model model) {
        model.addAttribute("resumeFormDto", new ResumeFormDto());
        return "resume/resumeForm";
    }
    @PostMapping("/resume")
    public String saveResume(@ModelAttribute("resumeFormDto") ResumeFormDto resumeFormDto,
                             @RequestParam("profileImgFile") MultipartFile profileImgFile) {
        try {
            resumeService.saveResume(resumeFormDto, profileImgFile); // 서비스를 호출하여 데이터 저장
            return "redirect:/"; // 저장 후 이동할 페이지
        } catch (IOException e) {
            // 오류 처리 로직 추가
            return "errorPage"; // 오류 발생 시 보여줄 페이지
        }
    }

    // 생성자 주입

    @GetMapping("/edit/{resumeId}")
    public String editResume(@PathVariable Long resumeId, Model model) {
        ResumeFormDto resumeFormDto = resumeService.getResumeById(resumeId); // 예시로, 이력서 정보 가져오는 메서드 호출
                model.addAttribute("resumeFormDto", resumeFormDto);
        return "resume/editResumeForm"; // 수정할 이력서의 폼 페이지로 이동
    }

    @PostMapping("/edit/{resumeId}")
    public String resumeNew(@Valid ResumeFormDto resumeFormDto,
                          @RequestParam("profileImgFile")MultipartFile profileImgFile,
                          BindingResult bindingResult,
                          Model model) {

        if(bindingResult.hasErrors()) {
            return "resume/editResumeForm";
        }

        if(profileImgFile.isEmpty() && resumeFormDto.getId() == null) {
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입니다.");
            return "resume/editResumeForm";
        }

        try {
            resumeService.saveResume(resumeFormDto, profileImgFile);
        } catch (IOException e) {
            model.addAttribute("errorMessage", "상품 등록중 오류가 발생했습니다.");
            return "resume/editResumeForm";
        }

        return "redirect:/";
    }
    @PostMapping("/update/{resumeId}")
    public String updateResume(@PathVariable Long resumeId, @ModelAttribute("resumeFormDto") ResumeFormDto resumeFormDto) {
        resumeService.updateResume(resumeId, resumeFormDto); // 이력서 업데이트 서비스 호출
        return "redirect:/list"; // 수정 완료 후 이력서 목록 페이지로 리다이렉트
    }

    @GetMapping("/list")
    public String showResumes(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        if (currentPrincipalName != null && !currentPrincipalName.isEmpty()) {
            List<Resume> userResumes = resumeService.getResumesByUser(currentPrincipalName);
            model.addAttribute("resumes", userResumes);
        } else {
            List<Resume> resumes = resumeService.getAllResumes();
            model.addAttribute("resumes", resumes);
        }

        return "resume/resumeList";
    }


    // 다른 메서드들...

}