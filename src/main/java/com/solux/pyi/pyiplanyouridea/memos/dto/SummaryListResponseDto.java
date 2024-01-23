package com.solux.pyi.pyiplanyouridea.memos.dto;

import com.solux.pyi.pyiplanyouridea.memos.domain.Memos;
import com.solux.pyi.pyiplanyouridea.organize.domain.Organize;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class SummaryListResponseDto {

    private Long memoUuid;
    private String memoTitle;
    private String memoDetails;
    private LocalDateTime memoCreated;
//    private List<String> keywordDetails;
//    private List<LocalDateTime> keywordCreated;
//    private List<LocalDateTime> todoDate;
//    private List<String> todoDetails;
    private List<String> organizeTitle;
//    private List<String> organizeDetails;
    private List<LocalDateTime> organizeCreated;
    private String reviewTitle;
    private String reviewDetails;
    private LocalDateTime reviewCreated;
    private BigDecimal starDetails;

    public SummaryListResponseDto(Memos entity) {
        this.memoUuid = entity.getMemoUuid();
        this.memoTitle = entity.getMemoTitle();
        this.memoDetails = entity.getMemoDetails();
        this.memoCreated = entity.getMemoCreated();
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
        if (entity.getOrganize() != null) {
            this.organizeTitle = entity.getOrganize().stream()
                    .map(Organize::getOrganizeTitle)
                    .collect(Collectors.toList());
//            this.organizeDetails = entity.getOrganize().stream()
//                    .map(Organize::getOrganizeDetails)
//                    .collect(Collectors.toList());
            this.organizeCreated = entity.getOrganize().stream()
                    .map(Organize::getOrganizeCreated)
                    .collect(Collectors.toList());
        }
        if (entity.getReview() != null) {
            this.reviewTitle = entity.getReview().getReviewTitle();
            this.reviewDetails = entity.getReview().getReviewDetails();
            this.reviewCreated = entity.getReview().getReviewCreated();
        }
        if (entity.getStar() != null) {
            this.starDetails = entity.getStar().getStarDetails();
        }
    }



//    public SummaryListResponseDto(Memos entity){
//        this.memoUuid = entity.getMemoUuid();
//        this.keywordDetails = entity.getKeywords().stream()
//                .map(Keywords::getKeywordDetails)
//                .collect(Collectors.toList());
//        this.keywordCreated = entity.getKeywords().stream()
//                .map(Keywords::getKeywordCreated)
//                .collect(Collectors.toList());
//        this.todoDate = entity.getTodos().stream()
//                .map(Todos::getTodoDate)
//                .collect(Collectors.toList());
//        this.todoDetails = entity.getTodos().stream()
//                .map(Todos::getTodoDetails)
//                .collect(Collectors.toList());
//        this.organizeTitle = entity.getOrganize().stream()
//                .map(Organize::getOrganizeTitle)
//                .collect(Collectors.toList());
//        this.organizeDetails = entity.getOrganize().stream()
//                .map(Organize::getOrganizeDetails)
//                .collect(Collectors.toList());
//        this.organizeCreated = entity.getOrganize().stream()
//                .map(Organize::getOrganizeCreated)
//                .collect(Collectors.toList());
//        this.reviewTitle = entity.getReview().getReviewTitle();
//        this.reviewDetails = entity.getReview().getReviewDetails();
//        this.reviewCreated = entity.getReview().getReviewCreated();
//        this.starDetails = entity.getStar().getStarDetails();
//    }
}