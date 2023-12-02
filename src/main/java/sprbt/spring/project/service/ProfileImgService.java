package sprbt.spring.project.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;
import sprbt.spring.project.entity.ProfileImg;
import sprbt.spring.project.entity.Resume;
import sprbt.spring.project.repository.ProfileImgRepository;
import sprbt.spring.project.repository.ResumeRepository;

import java.io.IOException;

import static sprbt.spring.project.entity.QProfileImg.profileImg;

@Service
@RequiredArgsConstructor
public class ProfileImgService {
    @Value(value = "${profileImgLocation}")
    private String profileImgLocation;

    private final ProfileImgRepository profileImgRepository;

    private final ResumeRepository resumeRepository;

    private final FileService fileService;

    public void saveProfileImg(ProfileImg profileImg, MultipartFile profileImgFile, Long resumeId) throws IOException {
        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new EntityNotFoundException("해당 ID에 해당하는 이력서를 찾을 수 없습니다: " + resumeId));
        String oriImgName = profileImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        if(!StringUtils.isEmpty(oriImgName)) {
            imgName = fileService.uploadFile(profileImgLocation, oriImgName, profileImgFile.getBytes());
            imgUrl = "/images/item/" + imgName;
        }

        profileImg.updateProfileImg(oriImgName, imgName, imgUrl);
        profileImg.setResume(resume);
        profileImgRepository.save(profileImg);
    }

    public void updateProfileImg(Long ProfileImgId, MultipartFile profileImgFile) throws IOException {
        if(!profileImgFile.isEmpty()){
            ProfileImg profileImg = profileImgRepository.findById(ProfileImgId).orElseThrow(EntityNotFoundException::new);

            // 파일 이름이 존재하는 경우
            if(!StringUtils.isEmpty(profileImg.getImgName())) {
                fileService.deleteFile(profileImgLocation + "/" + profileImg.getImgName());   // 기존 파일 삭제
            }

            String oriImgName = profileImgFile.getOriginalFilename();
            String imgName = fileService.uploadFile(profileImgLocation, oriImgName, profileImgFile.getBytes());
            String imgUrl = "/images/item/" + imgName;

            // 더티체킹
            profileImg.updateProfileImg(oriImgName, imgName, imgUrl);  // 파일 정보 업데이트
            profileImgRepository.save(profileImg);

        }
    }
}
