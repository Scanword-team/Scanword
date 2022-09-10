package ru.scanword.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.scanword.domain.SolvableScanword;

@Repository
public interface SolvableScanwordRepository extends JpaRepository<SolvableScanword, Long> {
}
