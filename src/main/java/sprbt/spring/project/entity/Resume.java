package sprbt.spring.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import sprbt.spring.project.common.entity.BaseEntity;
import sprbt.spring.project.dto.ResumeFormDto;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resume extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resume_id")
    private Long id; // 이력서 번호

    @Column(length = 20)
    private String resumename; //이력서 이름

    @Column(nullable = false, length = 20)
    private String lastedu; // 최종 학력

    @Column(nullable = false, length = 20)
    private String uniname; // 학교 이름

    @Column(nullable = false)
    private String major; // 전공

    @Column(nullable = true)
    private String interest; // 상품 상세 설명

    @Column(nullable = false, length = 50)
    private String userEmail; // 사용자명 또는 사용자 ID

    // 다대일 관계 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") // Member 엔티티의 PK
    @JsonIgnore
    private Member member; // Member와의 연관 관계

    @OneToOne
    @JoinColumn(name = "Profile_img_id")
    @JsonIgnore
    private ProfileImg profileImg;

    private String profileImgUrl;
    public void updateResume(ResumeFormDto resumeFormDto) {
        this.resumename = resumeFormDto.getResumename();
        this.lastedu = resumeFormDto.getLastedu();
        this.uniname = resumeFormDto.getUniname();
        this.major = resumeFormDto.getMajor();
        this.interest = resumeFormDto.getInterest();
        this.profileImgUrl = resumeFormDto.getProfileImgUrl();
    }
    // getter 메서드를 통해 profileImgs에 접근할 수 있도록 합니다.
    // profileImgs 리스트를 정의합니다.
    // ProfileImg를 profileImgs 리스트에 추가하는 메서드를 정의합니다.

}
