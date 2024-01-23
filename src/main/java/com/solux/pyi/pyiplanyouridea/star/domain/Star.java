package com.solux.pyi.pyiplanyouridea.star.domain;

import com.solux.pyi.pyiplanyouridea.memos.domain.Memos;
import com.solux.pyi.pyiplanyouridea.users.domain.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;



// 1

// Domain Model
// (domain services, entities, and value objects)
// - 도메인이라 불리는 개발 대상을 모든 사람이 동일한 관점에서
// 이해할 수 있고 공유할 수 있도록 단순화시킨 것을
// 도메인 모델이라고 한다.
// - 이를테면 택시 앱이라고 하면 배차, 탑승, 요금 등이
// 모두 도메인이 될 수 있다.
// - @Entity가 사용된 영역 역시 도메인 모델이라고 이해하면 된다.
// - 다만, 무조건 데이터베이스의 테이블과
// 관계가 있어야만 하는 것은 아니다.
// - VO처럼 값 객체들도 이 영역에 해당하기 때문이다.

// Domain에서 비지니스 처리를 담당해야 한다.



// Entity 클래스에서는 절대 Setter 메소드를 만들지 않는다.
// 대신, 해당 필드의 값 변경이 필요하면 명확히 그 목적과 의도를 나타낼 수 있는 메소드를 추가해야만 한다.
// Setter가 없는 이 상황에서 어떻게 값을 채워 DB에 삽입해야 할까?
// 기본적인 구조는 생성자를 통해 최종값을 채운 후 DB에 삽입하는 것이며,
// 값 변경이 필요한 경우 해당 이벤트에 맞는 public 메소드를 호출하여 변경하는 것을 전제로 한다.
// 이 책에서는 생성자 대신에 @Builder를 통해 제공되는 빌더 클래스를 사용한다.
@Getter
// - 클래스 내 모든 필드의 Getter 메소드를 자동생성
@NoArgsConstructor
// - 기본 생성자 자동 추가
// - public Memos() {} 와 같은 효과
@Entity
// - 테이블과 링크될 클래스임을 나타낸다.
// - 기본값으로 클래스의 카멜케이스 이름을 언더스코어( _ ) 네이밍으로 테이블 이름을 매칭한다.
// ex) SalesManager.java -> sales_manager table
@Table(name = "star")
public class Star {
    // Entity 클래스는 실제 DB 테이블과 매칭될 클래스
    // JPA를 사용하면 DB 데이터에 작업할 경우
    // 실제 쿼리를 날리기보다는,
    // 이 Entity 클래스의 수정을 통해 작업한다.

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
    @Column(name = "star_uuid", columnDefinition = "bigint(16)", nullable = false)
    private Long starUuid;

//    @ManyToOne
//    @JoinColumn(name = "user_uuid", columnDefinition = "bigint(16)", nullable = false)
//    private Users userUuid;

    //@OneToOne
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memo_uuid", columnDefinition = "bigint(16)", nullable = false)
    private Memos memoUuid;

    @Column(name = "star_details", columnDefinition = "decimal(2,1)", nullable = false)
    private BigDecimal starDetails;

    @Builder
    // @Builder
    // - 해당 클래스의 빌더 패턴 클래스를 생성
    // - 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    //public Star(Users userUuid, Memos memoUuid, BigDecimal starDetails) {
    public Star(Memos memoUuid, BigDecimal starDetails) {
        //this.userUuid = userUuid;
        this.memoUuid = memoUuid;
        this.starDetails = starDetails;
    }

    // 별점 수정
    public void update(BigDecimal starDetails) {
        this.starDetails = starDetails;
    }

}

// Memos 클래스 생성이 끝났다면,
// Memos 클래스로 Database를 접근하게 해줄 JpaRepository를 생성한다.
// MemosRepository 생성
