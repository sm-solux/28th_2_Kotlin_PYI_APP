package com.solux.pyi.pyiplanyouridea.organize.domain;

import com.solux.pyi.pyiplanyouridea.memos.domain.Memos;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "organize")
public class Organize {

    @Id
    // - 해당 테이블의 PK 필드를 나타낸다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @GeneratedValue
    // - PK의 생성 규칙을 나타낸다.
    // - 스프링 부트 2.0에서는 GenerationType.IDENTITY 옵션을 추가해야만 auto_increment가 된다.
    // 웬만하면 Entity의 PK는 Long 타입의 Auto_increment를 추천한다.
    // (MySQL 기준으로 이렇게 하면 bigint 타입이 된다.)
    // 주민등록번호와 같이 비즈니스상 유니크 키나,
    // 여러 키를 조합한 복합키로 PK를 잡을 경우 난감한 상황이 종종 발생한다.
    // (1) FK를 맺을 때 다른 테이블에서 복합키 전부를 갖고 있거나,
    // 중간 테이블을 하나 더 둬야 하는 상황이 발생한다.
    // (2) 인덱스에 좋은 영향을 끼치지 못한다.
    // (3) 유니크한 조건이 변경될 경우 PK 전체를 수정해야 하는 일이 발생한다.
    // 주민등록번호, 복합키 등은 유니크 키로 별도로 추가하는 것이 추천된다.
    @Column(name = "organize_uuid", columnDefinition = "bigint(16)", nullable = false)
    private Long organizeUuid;

//    @ManyToOne
//    @JoinColumn(name = "user_uuid", columnDefinition = "bigint(16)", nullable = false)
//    private Users userUuid;

    //@ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memo_uuid", columnDefinition = "bigint(16)", nullable = false)
    private Memos memoUuid;

    @Column(name = "organize_title", columnDefinition = "varchar(30)", nullable = false)
    private String organizeTitle;

    @Column(name = "organize_details", columnDefinition = "text", nullable = false)
    private String organizeDetails;

    @CreationTimestamp
    @Column(name = "organize_created", columnDefinition = "timestamp", nullable = false)
    private LocalDateTime organizeCreated;

    @Builder
    // @Builder
    // - 해당 클래스의 빌더 패턴 클래스를 생성
    // - 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    //public Organize(Users userUuid, Memos memoUuid, String organizeTitle, String organizeDetails, LocalDateTime organizeCreated) {
    public Organize(Memos memoUuid, String organizeTitle, String organizeDetails, LocalDateTime organizeCreated) {
        //this.userUuid = userUuid;
        this.memoUuid = memoUuid;
        this.organizeTitle = organizeTitle;
        this.organizeDetails = organizeDetails;
        this.organizeCreated = organizeCreated;
    }

    public void update(String organizeTitle, String organizeDetails){
        this.organizeTitle = organizeTitle;
        this.organizeDetails = organizeDetails;
    }

}
