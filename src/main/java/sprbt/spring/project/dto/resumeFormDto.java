package sprbt.spring.project.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.modelmapper.ModelMapper;

@Data
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class resumeFormDto {

    private Long id;             // 이력서 번호

    @NotBlank(message = "최종학력은 필수 입력 값입니다.")
    private String lastedu;           // 최종학력

    @NotBlank(message = "학교명은 필수 입력 값입니다.")
    private String uniname;       // 학교명

    @NotBlank(message = "전공은 필수 입력 값입니다.")
    private String major;     // 전공

    private String interest;   // 관심분야

    private static ModelMapper modelMapper = new ModelMapper();

//    public Item createItem(){
//        return modelMapper.map(this,Item.class);
//    }
//    public static ItemFormDto entityToDto(Item item){
//        return modelMapper.map(item, ItemFormDto.class);
//    }
}
