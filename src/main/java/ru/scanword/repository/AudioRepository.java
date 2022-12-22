package ru.scanword.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.scanword.domain.Audio;

public interface AudioRepository extends JpaRepository<Audio, Long> {
}
