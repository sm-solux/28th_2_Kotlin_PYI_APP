package com.solux.pyi.pyiplanyouridea.organize.dto;

import com.solux.pyi.pyiplanyouridea.organize.domain.Organize;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OrganizeResponseDto {

    private Long organizeUuid;
    private Long memoUuid;
    private String organizeTitle;
    private String organizeDetails;
    private LocalDateTime organizeCreated;

    public OrganizeResponseDto(Organize entity){
        this.organizeUuid = entity.getOrganizeUuid();
        this.memoUuid = entity.getMemos().getMemoUuid();
        this.organizeTitle = entity.getOrganizeTitle();
        this.organizeDetails = entity.getOrganizeDetails();
        this.organizeCreated = entity.getOrganizeCreated();
    }
}
