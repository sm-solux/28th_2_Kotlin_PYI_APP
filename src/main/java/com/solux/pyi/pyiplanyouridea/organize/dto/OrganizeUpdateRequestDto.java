package com.solux.pyi.pyiplanyouridea.organize.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrganizeUpdateRequestDto {

    private String organizeTitle;
    private String organizeDetails;

    @Builder
    public OrganizeUpdateRequestDto(String organizeTitle, String organizeDetails){
        this.organizeTitle = organizeTitle;
        this.organizeDetails = organizeDetails;
    }
}
