package com.solux.pyi.pyiplanyouridea.keywords.dto;

import com.solux.pyi.pyiplanyouridea.keywords.domain.Keywords;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class KeywordDto {

    private Long keywordUuid;
    private String keywordDetails;
    private LocalDateTime keywordCreated;

    public KeywordDto(Keywords entity) {
        this.keywordUuid = entity.getKeywordUuid();
        this.keywordDetails = entity.getKeywordDetails();
        this.keywordCreated = entity.getKeywordCreated();
    }

}
