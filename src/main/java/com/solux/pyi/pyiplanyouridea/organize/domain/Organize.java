package com.solux.pyi.pyiplanyouridea.organize.domain;

import com.solux.pyi.pyiplanyouridea.memos.domain.Memos;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "organize")
public class Organize {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organize_uuid", columnDefinition = "bigint(16)", nullable = false)
    private Long organizeUuid;
  
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memo_uuid", columnDefinition = "bigint(16)", nullable = false)
    private Memos memoUuid;

    @Column(name = "organize_title", columnDefinition = "varchar(30)", nullable = false)
    private String organizeTitle;

    @Column(name = "organize_details", columnDefinition = "text", nullable = false)
    private String organizeDetails;

    @CreationTimestamp
    @Column(name = "organize_created", columnDefinition = "timestamp", nullable = false)
    private LocalDateTime organizeCreated;

    @Builder
    public Organize(Memos memoUuid, String organizeTitle, String organizeDetails, LocalDateTime organizeCreated) {
        //this.userUuid = userUuid;
        this.memoUuid = memoUuid;
        this.organizeTitle = organizeTitle;
        this.organizeDetails = organizeDetails;
        this.organizeCreated = organizeCreated;
    }

    public void update(String organizeTitle, String organizeDetails){
        this.organizeTitle = organizeTitle;
        this.organizeDetails = organizeDetails;
    }

}
