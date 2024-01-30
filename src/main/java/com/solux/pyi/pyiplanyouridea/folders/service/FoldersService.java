package com.solux.pyi.pyiplanyouridea.folders.service;

import com.solux.pyi.pyiplanyouridea.folders.domain.Folders;
import com.solux.pyi.pyiplanyouridea.folders.dto.*;
import com.solux.pyi.pyiplanyouridea.folders.repository.FoldersRepository;
import com.solux.pyi.pyiplanyouridea.memos.dto.MemoDto;
import com.solux.pyi.pyiplanyouridea.memos.repository.MemosRepository;
import com.solux.pyi.pyiplanyouridea.users.domain.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FoldersService {
    private final FoldersRepository foldersRepository;
    private final MemosRepository memosRepository;

    // 폴더 하나 생성
    @Transactional
    public Long save(Users userUuid, FoldersSaveRequestDto requestDto){
        return foldersRepository.save(requestDto.toEntity(userUuid)).getFolderUuid();
    }

    // 폴더 하나 이름 수정
    @Transactional
    public Long update(Long id, FoldersUpdateRequestDto requestDto){
        Folders folders = foldersRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        folders.update(requestDto.getFolderName());
        return id;
    }

    @Transactional
    public FoldersResponseDto findById(Long id){
        Folders entity = foldersRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new FoldersResponseDto(entity);
    }

    @Transactional
    public void delete(Long id){
        Folders folders = foldersRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        foldersRepository.delete(folders);
    }

    // 폴더 리스트 조회
    @Transactional(readOnly = true)
    public List<FoldersListResponseDto> findByUsers(Users userUuid){
        return foldersRepository.findFoldersByUsers(userUuid).stream()
                .map(FoldersListResponseDto::new)
                .collect(Collectors.toList());
    }



    // 메인 페이지에서 전체 폴더 리스트 퀵메모 리스트 조회
    @Transactional(readOnly = true)
    public List<MainListResponseDto> getMainByUserId(Users users) {

        return foldersRepository.findFoldersByUsers(users).stream()
                .map(MainListResponseDto::new)
                .collect(Collectors.toList());

    }

}