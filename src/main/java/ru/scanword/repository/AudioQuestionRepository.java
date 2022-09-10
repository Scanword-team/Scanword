package ru.scanword.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.scanword.domain.AudioQuestion;

@Repository
public interface AudioQuestionRepository extends JpaRepository<AudioQuestion, Long> {
}
