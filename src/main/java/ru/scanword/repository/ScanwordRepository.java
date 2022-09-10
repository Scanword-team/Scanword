package ru.scanword.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.scanword.domain.Scanword;

@Repository
public interface ScanwordRepository extends JpaRepository<Scanword, Long> {
}
