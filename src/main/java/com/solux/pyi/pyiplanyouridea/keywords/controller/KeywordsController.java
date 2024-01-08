package com.solux.pyi.pyiplanyouridea.keywords.controller;

import com.solux.pyi.pyiplanyouridea.keywords.dto.KeywordsSaveRequestDto;
import com.solux.pyi.pyiplanyouridea.keywords.dto.KeywordsUpdateRequestDto;
import com.solux.pyi.pyiplanyouridea.keywords.service.KeywordsService;
import com.solux.pyi.pyiplanyouridea.organize.dto.OrganizeSaveRequestDto;
import com.solux.pyi.pyiplanyouridea.organize.dto.OrganizeUpdateRequestDto;
import com.solux.pyi.pyiplanyouridea.organize.service.OrganizeService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Getter
public class KeywordsController {
    private final KeywordsService keywordsService;

    // 키워드 하나 생성
    @PostMapping("https://planyouridea/createkeyword/{folderId}/{memoId}")
    public Long save(@RequestBody KeywordsSaveRequestDto requestDto){
        return keywordsService.save(requestDto);
    }

    // 키워드 하나 수정
    @PutMapping("https://planyouridea/editkeyword/{folderId}/{memoId]/{keywordId}")
    public Long update(@PathVariable Long keywordId, @RequestBody KeywordsUpdateRequestDto requestDto){
        return keywordsService.update(keywordId, requestDto);
    }

    // 키워드 하나 삭제
    @DeleteMapping("https://planyouridea/deletekeyword/{keywordId}")
    public Long delete(@PathVariable Long keywordId){
        keywordsService.delete(keywordId);
        return keywordId;
    }
}
