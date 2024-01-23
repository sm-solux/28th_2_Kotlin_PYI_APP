package com.solux.pyi.pyiplanyouridea.keywords.dto;

import com.solux.pyi.pyiplanyouridea.keywords.domain.Keywords;

import java.time.LocalDateTime;

public class KeywordsResponseDto {
    private Long memoUuid;
    private Long keywordsUuid;
    private String keywordDetails;
    private LocalDateTime keywordCreated;

    public KeywordsResponseDto(Keywords entity){
        this.keywordsUuid = entity.getKeywordUuid();
        this.memoUuid = entity.getMemos().getMemoUuid();
        this.keywordDetails = entity.getKeywordDetails();
        this.keywordCreated = entity.getKeywordCreated();
    }
}
