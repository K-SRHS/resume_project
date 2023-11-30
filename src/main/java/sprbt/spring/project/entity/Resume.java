package sprbt.spring.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resume_id")
    private Long id; // 이력서 번호

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

//    @CreationTimestamp
//    private LocalDateTime regTime; // 상품 등록 시간
//
//    @LastModifiedDate
//    private LocalDateTime updateTime; // 상품 수정 시간

}
