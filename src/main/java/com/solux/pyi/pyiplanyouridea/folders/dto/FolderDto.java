package com.solux.pyi.pyiplanyouridea.folders.dto;

import com.solux.pyi.pyiplanyouridea.folders.domain.Folders;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FolderDto {

    /*
    private Long folderUuid;
    private String folderName;
    private LocalDateTime folderCreated;

    public FolderDto(Folders entity) {
        this.folderUuid = entity.getFolderUuid();
        this.folderName = entity.getFolderName();
        this.folderCreated = entity.getFolderCreated();
    }

     */



    private Long folderUuid;
    private String folderName;
    private LocalDateTime folderCreated;

    public FolderDto(Folders entity) {
        this.folderUuid = entity.getFolderUuid();
        this.folderName = entity.getFolderName();
        this.folderCreated = entity.getFolderCreated();
    }

}
