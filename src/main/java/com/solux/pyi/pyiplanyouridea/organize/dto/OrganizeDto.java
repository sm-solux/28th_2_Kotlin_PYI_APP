package com.solux.pyi.pyiplanyouridea.organize.dto;

import com.solux.pyi.pyiplanyouridea.organize.domain.Organize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizeDto {

    private Long organizeUuid;
    private String organizeTitle;
    private String organizeDetails;
    private LocalDateTime organizeCreated;

    public OrganizeDto(Organize entity) {
        this.organizeUuid = entity.getOrganizeUuid();
        this.organizeTitle = entity.getOrganizeTitle();
        this.organizeDetails = entity.getOrganizeDetails();
        this.organizeCreated = entity.getOrganizeCreated();
    }

}
