package sprbt.spring.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import sprbt.spring.project.dto.ResumeFormDto;
import sprbt.spring.project.service.ResumeService;

@Controller
public class ResumeController {

    private final ResumeService resumeService;

    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @GetMapping("/resume")
    public String itemForm(Model model) {
        model.addAttribute("resumeFormDto", new ResumeFormDto());
        return "resume/resumeForm";
    }
    @PostMapping("/resume")
    public String saveResume(@ModelAttribute("resumeFormDto") ResumeFormDto resumeFormDto) {
        resumeService.saveResume(resumeFormDto); // 서비스를 호출하여 데이터 저장
        return "redirect:/"; // 저장 후 이동할 페이지
    }
//    @PostMapping("/resume")
//    public String itemNew(@Valid resumeFormDto resumeFormDto,
//                          @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList,
//                          BindingResult bindingResult,
//                          Model model) {
//
//        if(bindingResult.hasErrors()) {
//            return "resume/resumeForm";
//        }
//
//        if(itemImgFileList.get(0).isEmpty() && itemFormDto.getId() == null) {
//            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입니다.");
//            return "resume/resumeForm";
//        }
//
////        try {
////            itemService.saveItem(itemFormDto, itemImgFileList);
////        } catch (IOException e) {
////            model.addAttribute("errorMessage", "상품 등록중 오류가 발생했습니다.");
////            return "item/itemForm";
////        }
//
//        return "redirect:/";
//    }
}