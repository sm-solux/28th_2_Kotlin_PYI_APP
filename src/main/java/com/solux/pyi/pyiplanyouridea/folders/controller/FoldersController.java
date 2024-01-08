package com.solux.pyi.pyiplanyouridea.folders.controller;

import com.solux.pyi.pyiplanyouridea.folders.dto.FoldersSaveRequestDto;
import com.solux.pyi.pyiplanyouridea.folders.dto.FoldersUpdateRequestDto;
import com.solux.pyi.pyiplanyouridea.folders.service.FoldersService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Getter
public class FoldersController {
    private final FoldersService foldersService;

    // 폴더 하나 생성
    @PostMapping("https://planyouridea/category/createfolder")
    public Long save(@RequestBody FoldersSaveRequestDto requestDto) { return foldersService.save(requestDto); }

    // 폴더 리스트 조회
    //@GetMapping("https://planyouridea/category")

    // 폴더 하나 이름 수정
    @PutMapping("https://planyouridea/category/editfoldername/{folderId}")
    public Long update(@PathVariable Long folder_id, @RequestBody FoldersUpdateRequestDto requestDto){
        return foldersService.update(folder_id, requestDto);
    }

    // 폴더 하나 삭제
    @DeleteMapping("https://planyouridea/deletefolder/{folderId}")
    public Long delete(@PathVariable Long folder_id){
        foldersService.delete(folder_id);
        return folder_id;
    }
}
