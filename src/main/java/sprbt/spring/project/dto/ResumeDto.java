package sprbt.spring.project.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResumeDto {
    private Long id;             // 이력서 번호

    private String lastedu;           // 최종학력

    private String uniname;       // 학교명

    private String major;     // 전공

    private String interest;   // 관심분야
}
