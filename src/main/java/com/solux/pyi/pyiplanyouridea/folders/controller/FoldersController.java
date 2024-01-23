package com.solux.pyi.pyiplanyouridea.folders.controller;

import com.solux.pyi.pyiplanyouridea.folders.dto.FoldersListResponseDto;
import com.solux.pyi.pyiplanyouridea.folders.dto.FoldersSaveRequestDto;
import com.solux.pyi.pyiplanyouridea.folders.dto.FoldersUpdateRequestDto;
import com.solux.pyi.pyiplanyouridea.folders.dto.MainListResponseDto;
import com.solux.pyi.pyiplanyouridea.folders.service.FoldersService;
import com.solux.pyi.pyiplanyouridea.users.domain.Users;
import com.solux.pyi.pyiplanyouridea.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FoldersController {
    private final FoldersService foldersService;
    private final UsersRepository usersRepository;

    // 폴더 하나 생성
    @PostMapping("/category/createfolder/{userUuid}")
    public Long save(@PathVariable Long userUuid, @RequestBody FoldersSaveRequestDto requestDto) {
        Users users = usersRepository.getReferenceById(userUuid);
        return foldersService.save(users, requestDto);
    }

    // 카테고리에서 폴더 리스트 조회
    @GetMapping("/category/{userUuid}")
    public List<FoldersListResponseDto> getCategoryFoldersList(@PathVariable Users userUuid){
        return foldersService.findByUsers(userUuid);
    }

    // 메인 페이지에서 전체 폴더 리스트 퀵메모 리스트 조회
    @GetMapping("/mainpage/{userUuid}")
    public List<MainListResponseDto> getMainpageFoldersList(@PathVariable Users userUuid) {
        return foldersService.getMainByUserId(userUuid);
    }

    // 폴더 하나 이름 수정
    @PutMapping("/category/editfoldername/{folderUuid}")
    public Long update(@PathVariable Long folderUuid, @RequestBody FoldersUpdateRequestDto requestDto){
        return foldersService.update(folderUuid, requestDto);
    }

    // 폴더 하나 삭제
    @DeleteMapping("/deletefolder/{folderUuid}")
    public Long delete(@PathVariable Long folderUuid){
        foldersService.delete(folderUuid);
        return folderUuid;
    }
}