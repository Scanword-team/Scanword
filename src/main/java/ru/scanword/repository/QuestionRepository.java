package ru.scanword.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.scanword.domain.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
