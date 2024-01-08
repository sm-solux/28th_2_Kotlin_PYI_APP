package com.solux.pyi.pyiplanyouridea.folders.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
public class Folders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long folder_id;

    @Column(nullable = false)
    private String folder_name;

    @Builder
    public Folders(String folder_name){ this.folder_name = folder_name; }

    public void update(String folder_name){
        this.folder_name = folder_name;
    }
}
