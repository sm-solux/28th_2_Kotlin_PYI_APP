package com.solux.pyi.pyiplanyouridea.organize.controller;

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

    // 자료정리 및 구체화 하나 생성
    @PostMapping("/createorganize/{folderId}/{memoId}") //https://planyouridea/createorganize/{folderId}/{memoId}
    public Long save(@RequestBody OrganizeSaveRequestDto requestDto){
        return organizeService.save(requestDto);
    }

    // 자료정리 및 구체화 하나 조회
    @GetMapping("/vieworganize/{folderId}/{memoId}/{organizeId}") //https://planyouridea/vieworganize/{folderId}/{memoId}/{organizeId}
    public OrganizeResponseDto findById(@PathVariable Long organize_id){
        return organizeService.findById(organize_id);
    }

    // 자료정리 및 구체화 하나 수정
    @PutMapping("/editorganize/{folderId}/{memoId}/{organizeId}") //https://planyouridea/editorganize/{folderId}/{memoId}/{organizeId}
    public Long update(@PathVariable Long organize_id, @RequestBody OrganizeUpdateRequestDto requestDto){
        return organizeService.update(organize_id, requestDto);
    }

    // 자료정리 및 구체화 하나 삭제
    @DeleteMapping("/deleteorganize/{organizeId}") //https://planyouridea/deleteorganize/{organizeId}
    public  Long delete(@PathVariable Long organize_id){
        organizeService.delete(organize_id);
        return organize_id;
    }


}
