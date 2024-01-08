package com.solux.pyi.pyiplanyouridea.organize.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
//@Entity
public class Organize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long organize_id;

    @Column(nullable = false)
    private String organize_title;

    @Column(nullable = false)
    private String organize; // ERD 상 domain으로 명시되어 있음

    @Builder
    public Organize(String title, String content){
        this.organize_title = title;
        this.organize = content;
    }

    public void update(String title, String content){
        this.organize_title = title;
        this.organize = content;
    }
}
