package com.solux.pyi.pyiplanyouridea.keywords.repository;

import com.solux.pyi.pyiplanyouridea.keywords.domain.Keywords;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeywordsRepository extends JpaRepository<Keywords, Long> {
}
