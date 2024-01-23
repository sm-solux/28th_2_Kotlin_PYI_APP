package com.solux.pyi.pyiplanyouridea.todos.service;

import com.solux.pyi.pyiplanyouridea.memos.domain.Memos;
import com.solux.pyi.pyiplanyouridea.todos.domain.Todos;
import com.solux.pyi.pyiplanyouridea.todos.dto.TodosSaveRequestDto;
import com.solux.pyi.pyiplanyouridea.todos.dto.TodosUpdateRequestDto;
import com.solux.pyi.pyiplanyouridea.todos.repository.TodosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


// 4

// 트랜잭션, 도메인 기능 간의 순서를 보장하는 Service

// Service는 비지니스 로직을 처리하는 것이 아닌,
// 트랜잭션, 도메인 간 순서 보장의 역할만 한다.

// Domain Model (domain services, entitles, and value objects)
// > Service Layer
// (application services and infrastructure servies)
// - @Service에 사용되는 서비스 영역
// - 일반적으로 Controller와 Dao(Data Acces Object,
// Repository Layer,
// Database와 같이 데이터 저장소에 접근하는 영역)의
// 중간 영역에서 사용된다.
// - @Transactional이 사용되어야 하는 영역이기도 하다.

@RequiredArgsConstructor
// 스프링에서 Bean을 주입받는 방식들 : @Autowired, setter, 생성자
// 이 중 가장 권장하는 방식이 생성자로 주입받는 방식이다.
// (@Autowired는 권장하지 않는다.)
// 즉 생성자로 Bean 객체를 받도록 하면
// @Autowired와 동일한 효과를 볼 수 있다는 것이다.
// 생성자는 @RequiredArgsConstructor에서 해결해 준다.
// final이 선언된 모든 필드를 인자값으로 하는 생성자를
// 롬복의 @RequiredArgsConstructor가 대신 생성해준다.
// 생성자를 직접 안 쓰고 롬복 어노테이션을 사용한 이유 :
// 해당 클래스의 의존성 관계가 변경될 때마다
// 생성자 코드를 계속해서 수정하는 번거로움을 해결하기 위해
// 롬복 어노테이션이 있으면 해당 컨트롤러에 새로운 서비스를 추가하거나,
// 기존 컴포넌트를 제거하는 등의 상황이 발생해도
// 생성자 코드는 전혀 손대지 않아도 된다.
@Service
public class TodosService {

    private final TodosRepository todosRepository;

    // 할 일 하나 생성
    @Transactional
    public Long save(Memos memoUuid, TodosSaveRequestDto requestDto) {
        return todosRepository.save(requestDto.toEntity(memoUuid)).getTodoUuid();
    }

    // 할 일 하나 수정
    @Transactional
    public Long update(Long todoUuid, TodosUpdateRequestDto requestDto) {
    // update 기능에서 데이터베이스에 쿼리를 날리는 부분이 없다.
    // 이것이 가능한 이유는 JPA의 영속성 컨텍스트 때문이다.
    // 영속성 컨텍스트란, 엔티티를 영구 저장하는 환경이다.
    // 일종의 논리적 개념이라고 보면 되며, JPA의 핵심 내용은
    // 엔티티가 영속성 컨텍스트에 포함되어 있냐 아니냐로 갈린다.
    // JPA의 엔티티 매니저(EntityManager)가 활성화된 상태로
    // (Spring Data Jpa를 쓴다면 기본 옵션)
    // 트랜잭션 안에서 데이터베이스에서 데이터를 가져오면
    // 이 데이터는 영속성 컨텍스트가 유지된 상태이다.
    // 이 상태에서 해당 데이터의 값을 변경하면
    // 트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영한다.
    // 즉, Entity 객체의 값만 변경하면 별도로
    // Update 쿼리를 날릴 필요가 없다는 것이다.
    // 이 개념을 더티 체킹(dirty checking)이라고 한다.
        Todos todos = todosRepository.findById(todoUuid)
                .orElseThrow(() -> new IllegalArgumentException("해당 할 일이 없습니다. todoId = " + todoUuid));

        todos.update(requestDto.getTodoDate(), requestDto.getTodoDetails());

        return todoUuid;
    }

    // 할 일 하나 삭제
    @Transactional
    public void delete(Long todoUuid) {
        Todos todos = todosRepository.findById(todoUuid)
                .orElseThrow(() -> new IllegalArgumentException("해당 할 일이 없습니다. todoId = " + todoUuid));

        todosRepository.delete(todos);
        // - JpaRepository에서 이미 delete 메소드를 지원하고 있으니 이를 활용한다.
        // - 엔티티를 파라미터로 삭제할 수도 있고, deleteById 메소드를 이용하면 id로 삭제할 수도 있다.
        // - 존재하는 Review인지 확인을 위해 엔티티 조회 후 그대로 삭제한다.
    }



//    // Postman Test
//    // 할 일 하나 조회
//    @Transactional
//    public TodosResponseDto findById(Long todoId) {
//        Todos entity = todosRepository.findById(todoId)
//                .orElseThrow(() -> new IllegalArgumentException("해당 할 일이 없습니다. todoId = " + todoId));
//
//        return new TodosResponseDto(entity);
//    }
}

// 서비스에서 만든 delete 메소드를
// 컨트롤러가 사용하도록 코드를 추가한다.