package com.solux.pyi.pyiplanyouridea.folders.dto;

import com.solux.pyi.pyiplanyouridea.folders.domain.Folders;
import com.solux.pyi.pyiplanyouridea.keywords.domain.Keywords;
import com.solux.pyi.pyiplanyouridea.memos.domain.Memos;
import com.solux.pyi.pyiplanyouridea.users.domain.Users;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MainListResponseDto {

    private Users userUuid;
    //private List<Long> folderUuid;
    private Long folderUuid;
    //private List<String> folderName;
    private String folderName;
    //private List<LocalDateTime> folderCreated;
    private LocalDateTime folderCreated;
    private List<Long> memoUuid;
    private List<String> memoTitle;
    private List<String> memoDetails;
    private List<LocalDateTime> memoCreated;

    public MainListResponseDto(Folders entity) {
        this.userUuid = entity.getUserUuid();
        this.folderUuid = entity.getFolderUuid();
        this.folderName = entity.getFolderName();
        this.folderCreated = entity.getFolderCreated();
        this.memoUuid = entity.getMemos().stream()
                .map(Memos::getMemoUuid)
                .collect(Collectors.toList());
        this.memoTitle = entity.getMemos().stream()
                .map(Memos::getMemoTitle)
                .collect(Collectors.toList());
        this.memoDetails = entity.getMemos().stream()
                .map(Memos::getMemoDetails)
                .collect(Collectors.toList());
        this.memoCreated = entity.getMemos().stream()
                .map(Memos::getMemoCreated)
                .collect(Collectors.toList());
    }

}
