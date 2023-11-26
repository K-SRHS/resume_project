package sprbt.spring.project.entity;

import sprbt.spring.project.constant.ItemSellStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id; // 상품 번호

    @Column(nullable = false, length = 50)
    private String itemNm; // 상품 이름

    @Column(nullable = false)
    private int price; // 상품 가격

    @Column(nullable = false, name = "number")
    private int stockNumber; // 상품 재고 수량

    @Column(nullable = false)
    @Lob // Large Object - CLOB, BLOB
    private String itemDetail; // 상품 상세 설명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus; // 상품 판매 상태

//    @CreationTimestamp
//    private LocalDateTime regTime; // 상품 등록 시간
//
//    @LastModifiedDate
//    private LocalDateTime updateTime; // 상품 수정 시간

}
