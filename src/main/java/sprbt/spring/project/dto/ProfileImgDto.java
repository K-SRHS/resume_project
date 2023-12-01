package sprbt.spring.project.dto;

import lombok.*;
import org.modelmapper.ModelMapper;
import sprbt.spring.project.entity.ProfileImg;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileImgDto {
    private Long id;

    private String imgName;
    private String oriImgName;
    private String imgUrl;

    private static ModelMapper modelMapper = new ModelMapper();
    public static ProfileImgDto entitytoDto(ProfileImg profileImg) {
        return modelMapper.map(profileImg, ProfileImgDto.class);
    }
}
