package com.solux.pyi.pyiplanyouridea.memos.dto;

import com.solux.pyi.pyiplanyouridea.memos.domain.Memos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

// 메인 페이지에서 전체 폴더 리스트 퀵메모 리스트 조회
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemoDto {

    private Long memoUuid;
    private String memoTitle;
    private String memoDetails;
    private LocalDateTime memoCreated;

    public MemoDto(Memos entity) {
        memoUuid = entity.getMemoUuid();
        memoTitle = entity.getMemoTitle();
        memoDetails = entity.getMemoDetails();
        memoCreated = entity.getMemoCreated();
    }

}
