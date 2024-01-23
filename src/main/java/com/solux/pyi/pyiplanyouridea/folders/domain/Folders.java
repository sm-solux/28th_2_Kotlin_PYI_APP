package com.solux.pyi.pyiplanyouridea.folders.domain;

import com.solux.pyi.pyiplanyouridea.users.domain.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


// Domain Model
// (domain services, entities, and value objects)

@Getter
// - 클래스 내 모든 필드의 Getter 메소드를 자동생성
@NoArgsConstructor
// - 기본 생성자 자동 추가
// - public Memos() {} 와 같은 효과
@Entity
// - 테이블과 링크될 클래스임을 나타낸다.
// - 기본값으로 클래스의 카멜케이스 이름을 언더스코어( _ ) 네이밍으로 테이블 이름을 매칭한다.
// ex) SalesManager.java -> sales_manager table
@Table(name = "folders")
public class Folders {
    @Id
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
    @Column(name = "folder_uuid", columnDefinition = "bigint(16)", nullable = false)
    private Long folderUuid;

    //@ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_uuid", columnDefinition = "bigint(16)", nullable = false)
    private Users userUuid;

    @Column(name = "folder_name", columnDefinition = "varchar(30)", nullable = false)
    private String folderName;

    @CreationTimestamp
    @Column(name = "folder_created", columnDefinition = "timestamp", nullable = false)
    private LocalDateTime folderCreated;
}
