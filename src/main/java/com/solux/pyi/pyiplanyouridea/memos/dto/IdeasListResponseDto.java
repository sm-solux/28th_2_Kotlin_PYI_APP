package com.solux.pyi.pyiplanyouridea.memos.dto;

import com.solux.pyi.pyiplanyouridea.keywords.dto.KeywordDto;
import com.solux.pyi.pyiplanyouridea.memos.domain.Memos;
import com.solux.pyi.pyiplanyouridea.organize.dto.OrganizeDto;
import com.solux.pyi.pyiplanyouridea.review.dto.ReviewDto;
import com.solux.pyi.pyiplanyouridea.star.dto.StarDto;
import com.solux.pyi.pyiplanyouridea.todos.dto.TodoDto;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// 아이디어 구체화 및 실현 조회
@Getter
// - 클래스 내 모든 필드의 Getter 메소드를 자동생성
public class IdeasListResponseDto {

    private Long memoUuid;
    private String memoTitle;
    private List<KeywordDto> keywords;
    private List<TodoDto> todos;
    private List<OrganizeDto> organize;
    private List<ReviewDto> review;
    private List<StarDto> star;

    public IdeasListResponseDto(Memos entity) {
        this.memoUuid = entity.getMemoUuid();
        this.memoTitle = entity.getMemoTitle();
        this.keywords = entity.getKeywords().stream()
                .map(keyword -> new KeywordDto(keyword.getKeywordUuid(), keyword.getKeywordDetails(), keyword.getKeywordCreated()))
                .collect(Collectors.toList());
        this.todos = entity.getTodos().stream()
                .map(todo -> new TodoDto(todo.getTodoUuid(), todo.getTodoDate(), todo.getTodoDetails()))
                .collect(Collectors.toList());
        this.organize = entity.getOrganize().stream()
                .map(organize -> new OrganizeDto(organize.getOrganizeUuid(), organize.getOrganizeTitle(), organize.getOrganizeDetails(), organize.getOrganizeCreated()))
                .collect(Collectors.toList());
        this.review = Arrays.asList(new ReviewDto(entity.getReview().getReviewUuid(), entity.getReview().getReviewTitle(), entity.getReview().getReviewDetails(), entity.getReview().getReviewCreated()));
        this.star = Arrays.asList(new StarDto(entity.getStar().getStarUuid(), entity.getStar().getStarDetails()));
    }



//    private Long memoUuid;
//    private String memoTitle;
//    private List<String> keywordDetails;
//    private List<LocalDateTime> keywordCreated;
//    private List<LocalDateTime> todoDate;
//    private List<String> todoDetails;
//    private List<String> organizeTitle;
//    private List<String> organizeDetails;
//    private List<LocalDateTime> organizeCreated;
//    private String reviewTitle;
//    private String reviewDetails;
//    private LocalDateTime reviewCreated;
//    private BigDecimal starDetails;
//
//    public IdeasListResponseDto(Memos entity) {
//        this.memoUuid = entity.getMemoUuid();
//        this.memoTitle = entity.getMemoTitle();
//        if (entity.getKeywords() != null) {
//            this.keywordDetails = entity.getKeywords().stream()
//                    .map(Keywords::getKeywordDetails)
//                    .collect(Collectors.toList());
//            this.keywordCreated = entity.getKeywords().stream()
//                    .map(Keywords::getKeywordCreated)
//                    .collect(Collectors.toList());
//        }
//        if (entity.getTodos() != null) {
//            this.todoDate = entity.getTodos().stream()
//                    .map(Todos::getTodoDate)
//                    .collect(Collectors.toList());
//            this.todoDetails = entity.getTodos().stream()
//                    .map(Todos::getTodoDetails)
//                    .collect(Collectors.toList());
//        }
//        if (entity.getOrganize() != null) {
//            this.organizeTitle = entity.getOrganize().stream()
//                    .map(Organize::getOrganizeTitle)
//                    .collect(Collectors.toList());
//            this.organizeDetails = entity.getOrganize().stream()
//                    .map(Organize::getOrganizeDetails)
//                    .collect(Collectors.toList());
//            this.organizeCreated = entity.getOrganize().stream()
//                    .map(Organize::getOrganizeCreated)
//                    .collect(Collectors.toList());
//        }
//        if (entity.getReview() != null) {
//            this.reviewTitle = entity.getReview().getReviewTitle();
//            this.reviewDetails = entity.getReview().getReviewDetails();
//            this.reviewCreated = entity.getReview().getReviewCreated();
//        }
//        if (entity.getStar() != null) {
//            this.starDetails = entity.getStar().getStarDetails();
//        }
//
//    }
}
