package com.solux.pyi.pyiplanyouridea.folders.domain;

import com.solux.pyi.pyiplanyouridea.memos.domain.Memos;
import com.solux.pyi.pyiplanyouridea.users.domain.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "folders")
public class Folders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "folder_uuid", columnDefinition = "bigint(16)", nullable = false)
    private Long folderUuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_uuid", columnDefinition = "bigint(16)", nullable = false)
    private Users userUuid;

    @Column(name = "folder_name", columnDefinition = "varchar(30)", nullable = false)
    private String folderName;

    @CreationTimestamp
    @Column(name = "folder_created", columnDefinition = "timestamp", nullable = false)
    private LocalDateTime folderCreated;



    //////////////////
    @OneToMany(mappedBy = "folderUuid")
    private List<Memos> memos = new ArrayList<Memos>();
    //////////////////



    @Builder
    public Folders(Users userUuid, String folderName, LocalDateTime folderCreated, List<Memos> memos){
        this.userUuid = userUuid;
        this.folderName = folderName;
        this.folderCreated = folderCreated;
        this.memos = memos;
    }

    public void update(String folderName){
        this.folderName = folderName;
    }
  
}