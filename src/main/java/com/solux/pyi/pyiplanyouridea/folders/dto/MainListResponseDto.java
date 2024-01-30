package com.solux.pyi.pyiplanyouridea.folders.dto;

import com.solux.pyi.pyiplanyouridea.folders.domain.Folders;
import com.solux.pyi.pyiplanyouridea.memos.dto.MemoDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// 메인 페이지에서 전체 폴더 리스트 퀵메모 리스트 조회
@Getter
@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class MainListResponseDto {
    private Long userUuid;
    private List<FolderDto> folders;
    private List<MemoDto> memos;

    public MainListResponseDto(Folders entity) {
        this.userUuid = entity.getUsers().getUserUuid();
        this.folders = Arrays.asList(new FolderDto(entity.getFolderUuid(), entity.getFolderName(), entity.getFolderCreated()));
        this.memos = entity.getMemos().stream()
                .map(memo -> new MemoDto(memo.getMemoUuid(), memo.getMemoTitle(), memo.getMemoDetails(), memo.getMemoCreated()))
                .collect(Collectors.toList());
    }

    /*
    private Long userUuid;
    private Long folderUuid;
    private String folderName;
    private LocalDateTime folderCreated;
    private List<Long> memoUuid;
    private List<String> memoTitle;
    private List<String> memoDetails;
    private List<LocalDateTime> memoCreated;

    public MainListResponseDto(Folders entity) {
        this.userUuid = entity.getUsers().getUserUuid();
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

     */

}
