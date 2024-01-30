package com.solux.pyi.pyiplanyouridea.folders.repository;

import com.solux.pyi.pyiplanyouridea.folders.domain.Folders;
import com.solux.pyi.pyiplanyouridea.users.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoldersRepository extends JpaRepository<Folders, Long> {
    @Query("SELECT f FROM Folders f")
    List<Folders> findAllDesc();

    // 폴더 리스트 조회
    // 메인 페이지에서 전체 폴더 리스트 퀵메모 리스트 조회
    List<Folders> findFoldersByUsers(@Param("userUuid") Users users);

}
