package com.solux.pyi.pyiplanyouridea.keywords.domain;

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
@Table(name = "keywords")
public class Keywords {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "keyword_uuid", columnDefinition = "bigint(16)", nullable = false)
    private Long keywordUuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memo_uuid", columnDefinition = "bigint(16)", nullable = false)
    private Memos memos;

    @Column(name = "keyword_details", columnDefinition = "varchar(30)", nullable = false)
    private String keywordDetails;

    @CreationTimestamp
    @Column(name = "keyword_created", columnDefinition = "timestamp", nullable = false)
    private LocalDateTime keywordCreated;

    @Builder
    public Keywords(Memos memos, String keywordDetails, LocalDateTime keywordCreated) {
        this.memos = memos;
        this.keywordDetails = keywordDetails;
        this.keywordCreated = keywordCreated;
    }

    public void update(String keywordDetails){
        this.keywordDetails = keywordDetails;
    }
  
}
