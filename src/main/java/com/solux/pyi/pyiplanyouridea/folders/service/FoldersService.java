package com.solux.pyi.pyiplanyouridea.folders.service;

import com.solux.pyi.pyiplanyouridea.folders.domain.Folders;
import com.solux.pyi.pyiplanyouridea.folders.domain.FoldersRepository;
import com.solux.pyi.pyiplanyouridea.folders.dto.FoldersResponseDto;
import com.solux.pyi.pyiplanyouridea.folders.dto.FoldersSaveRequestDto;
import com.solux.pyi.pyiplanyouridea.folders.dto.FoldersUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FoldersService {
    private final FoldersRepository foldersRepository;

    @Transactional
    public Long save(FoldersSaveRequestDto requestDto){
        return foldersRepository.save(requestDto.toEntity()).getFolder_id();
    }

    @Transactional
    public Long update(Long id, FoldersUpdateRequestDto requestDto){
        Folders folders = foldersRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        folders.update(requestDto.getFolder_name());
        return id;
    }

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
}
