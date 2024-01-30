package com.solux.pyi.pyiplanyouridea.organize.controller;

import com.solux.pyi.pyiplanyouridea.memos.domain.Memos;
import com.solux.pyi.pyiplanyouridea.memos.repository.MemosRepository;
import com.solux.pyi.pyiplanyouridea.organize.dto.OrganizeResponseDto;
import com.solux.pyi.pyiplanyouridea.organize.dto.OrganizeSaveRequestDto;
import com.solux.pyi.pyiplanyouridea.organize.dto.OrganizeUpdateRequestDto;
import com.solux.pyi.pyiplanyouridea.organize.service.OrganizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class OrganizeController {

    private final OrganizeService organizeService;
    private final MemosRepository memosRepository;

    // 자료정리 및 구체화 하나 생성
    @PostMapping("/createorganize/{memoUuid}") //https://planyouridea/createorganize/{folderId}/{memoId}
    public Long save(@PathVariable Long memoUuid, @RequestBody OrganizeSaveRequestDto requestDto){
        Memos memos = memosRepository.getReferenceById(memoUuid);
        return organizeService.save(memos, requestDto);
    }

    // 자료정리 및 구체화 하나 조회
    @GetMapping("/vieworganize/{organizeUuid}") //https://planyouridea/vieworganize/{folderId}/{memoId}/{organizeId}
    public OrganizeResponseDto findById(@PathVariable Long organizeUuid){
        return organizeService.findById(organizeUuid);
    }

    // 자료정리 및 구체화 하나 수정
    @PutMapping("/editorganize/{organizeUuid}") //https://planyouridea/editorganize/{folderId}/{memoId}/{organizeId}
    public Long update(@PathVariable Long organizeUuid, @RequestBody OrganizeUpdateRequestDto requestDto){
        return organizeService.update(organizeUuid, requestDto);
    }

    // 자료정리 및 구체화 하나 삭제
    @DeleteMapping("/deleteorganize/{organizeUuid}") //https://planyouridea/deleteorganize/{organizeId}
    public  Long delete(@PathVariable Long organizeUuid){
        organizeService.delete(organizeUuid);
        return organizeUuid;
    }

}
