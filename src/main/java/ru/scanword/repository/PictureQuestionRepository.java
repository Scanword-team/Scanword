package ru.scanword.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.scanword.domain.PictureQuestion;

@Repository
public interface PictureQuestionRepository extends JpaRepository<PictureQuestion, Long> {
}
