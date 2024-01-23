package com.solux.pyi.pyiplanyouridea.organize.dto;

import com.solux.pyi.pyiplanyouridea.memos.domain.Memos;
import com.solux.pyi.pyiplanyouridea.organize.domain.Organize;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OrganizeResponseDto {

    private Long organizeUuid;
    private Memos memoUuid;
    private String organizeTitle;
    private String organizeDetails;
    private LocalDateTime organizeCreated;

    public OrganizeResponseDto(Organize entity){
        this.organizeUuid = entity.getOrganizeUuid();
        this.memoUuid = entity.getMemoUuid();
        this.organizeTitle = entity.getOrganizeTitle();
        this.organizeDetails = entity.getOrganizeDetails();
        this.organizeCreated = entity.getOrganizeCreated();
    }
}
