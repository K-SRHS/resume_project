package sprbt.spring.project.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.modelmapper.ModelMapper;
import sprbt.spring.project.entity.Resume;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResumeFormDto {

    private Long id;             // 이력서 번호

    @NotBlank(message = "최종학력은 필수 입력 값입니다.")
    private String lastedu;           // 최종학력

    @NotBlank(message = "학교명은 필수 입력 값입니다.")
    private String uniname;       // 학교명

    @NotBlank(message = "전공은 필수 입력 값입니다.")
    private String major;     // 전공

    @NotBlank(message = "최종학력은 필수 입력 값입니다.")
    private String resumename;        //이력서 이름

    private String interest;   // 관심분야

    private String userEmail;   // 사용자 이름 또는 ID (로그인한 사용자 정보)

    private Long memberId; // 사용자 ID 추가

    private List<ProfileImgDto> profileImgDtoList = new ArrayList<>();

    private List<Long> profileImgIds = new ArrayList<>();

    private String profileImgUrl;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private String name;

    private String email;

    private String address;

    private static ModelMapper modelMapper = new ModelMapper();

//    public Item createItem(){
//        return modelMapper.map(this,Item.class);
//    }
    public static ResumeFormDto entityToDto(Resume resume){
        return modelMapper.map(resume, ResumeFormDto.class);
    }
}
