package com.solux.pyi.pyiplanyouridea.keywords.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
public class Keywords {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long keyword_id;

    @Column(nullable = false)
    private String keyword;

    @Builder
    public Keywords(String keyword){
        this.keyword = keyword;
    }

    public void update(String keyword){
        this.keyword = keyword;
    }
}
