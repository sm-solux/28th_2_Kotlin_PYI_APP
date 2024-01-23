package com.solux.pyi.pyiplanyouridea.folders.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FoldersUpdateRequestDto {
    private String folderName;

    @Builder
    public FoldersUpdateRequestDto(String folderName){
        this.folderName = folderName;
    }

}