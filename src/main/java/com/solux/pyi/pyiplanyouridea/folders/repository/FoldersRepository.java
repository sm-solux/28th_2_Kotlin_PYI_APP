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

    List<Folders> findFoldersByUsers(@Param("userUuid") Users users);

}
