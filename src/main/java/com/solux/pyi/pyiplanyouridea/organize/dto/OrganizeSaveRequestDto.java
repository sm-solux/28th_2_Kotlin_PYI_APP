package com.solux.pyi.pyiplanyouridea.organize.dto;

import com.solux.pyi.pyiplanyouridea.memos.domain.Memos;
import com.solux.pyi.pyiplanyouridea.organize.domain.Organize;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class OrganizeSaveRequestDto {

    private Memos memoUuid;
    private String organizeTitle;
    private String organizeDetails;
    private LocalDateTime organizeCreated;

    @Builder
    public OrganizeSaveRequestDto(Memos memoUuid, String organizeTitle, String organizeDetails, LocalDateTime organizeCreated){
        this.organizeTitle = organizeTitle;
        this.organizeDetails = organizeDetails;
        this.memoUuid = memoUuid;
        this.organizeCreated = organizeCreated;
    }

    public Organize toEntity(Memos memoUuid){
        return Organize.builder()
                .memoUuid(memoUuid)
                .organizeTitle(organizeTitle)
                .organizeDetails(organizeDetails)
                .organizeCreated(organizeCreated)
                .build();
    }
}
