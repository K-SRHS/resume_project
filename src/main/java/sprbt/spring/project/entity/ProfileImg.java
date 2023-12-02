package sprbt.spring.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import sprbt.spring.project.common.entity.BaseEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileImg extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Profile_img_id")
    private Long id;

    private String imgName;
    private String oriImgName;
    private String imgUrl;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id")
    @JsonIgnore
    private Resume resume;

    public void updateProfileImg(String imgName, String oriImgName, String imgUrl){
        this.imgName = imgName;
        this.oriImgName = oriImgName;
        this. imgUrl = imgUrl;
    }
}
