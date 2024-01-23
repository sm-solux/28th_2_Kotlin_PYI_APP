package com.solux.pyi.pyiplanyouridea.keywords.dto;

import com.solux.pyi.pyiplanyouridea.keywords.domain.Keywords;
import com.solux.pyi.pyiplanyouridea.memos.domain.Memos;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class KeywordsSaveRequestDto {
    private Long memoUuid;
    private String keywordDetails;
    private LocalDateTime keywordCreated;

    @Builder
    public KeywordsSaveRequestDto(Long memoUuid, String keywordDetails, LocalDateTime keywordCreated){

        this.memoUuid = memoUuid;
        this.keywordDetails = keywordDetails;
        this.keywordCreated = keywordCreated;
    }

    public Keywords toEntity(Memos memoUuid){
        return Keywords.builder()
                .memos(memoUuid)
                .keywordDetails(keywordDetails)
                .keywordCreated(keywordCreated)
                .build();
    }
}
