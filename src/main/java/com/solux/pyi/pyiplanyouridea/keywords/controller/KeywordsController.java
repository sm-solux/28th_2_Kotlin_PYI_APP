package com.solux.pyi.pyiplanyouridea.keywords.controller;

import com.solux.pyi.pyiplanyouridea.keywords.dto.KeywordsSaveRequestDto;
import com.solux.pyi.pyiplanyouridea.keywords.dto.KeywordsUpdateRequestDto;
import com.solux.pyi.pyiplanyouridea.keywords.service.KeywordsService;
import com.solux.pyi.pyiplanyouridea.memos.domain.Memos;
import com.solux.pyi.pyiplanyouridea.memos.repository.MemosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class KeywordsController {
    private final KeywordsService keywordsService;
    private final MemosRepository memosRepository;

    // 키워드 하나 생성
    @PostMapping("/createkeyword/{memoUuid}")
    public Long save(@PathVariable Long memoUuid, @RequestBody KeywordsSaveRequestDto requestDto){
        Memos memos = memosRepository.getReferenceById(memoUuid);
        return keywordsService.save(memos, requestDto);
    }

    // 키워드 하나 수정
    @PutMapping("/editkeyword/{keywordUuid}")
    public Long update(@PathVariable Long keywordUuid, @RequestBody KeywordsUpdateRequestDto requestDto){
        return keywordsService.update(keywordUuid, requestDto);
    }

    // 키워드 하나 삭제
    @DeleteMapping("/deletekeyword/{keywordUuid}")
    public Long delete(@PathVariable Long keywordUuid){
        keywordsService.delete(keywordUuid);
        return keywordUuid;
    }

}
