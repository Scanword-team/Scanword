package ru.scanword.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.scanword.domain.SolvableScanword;
import ru.scanword.domain.User;

import java.util.Optional;

@Repository
public interface SolvableScanwordRepository extends JpaRepository<SolvableScanword, Long> {
    Optional<SolvableScanword> findByScanwordIdAndOwner(Long scanwordId, User owner);
}
