package ru.scanword.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.scanword.domain.Audio;
import ru.scanword.domain.Dictionary;
import ru.scanword.domain.Image;
import ru.scanword.domain.Question;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findQuestionByImage(Image image);
    List<Question> findQuestionByAudio(Audio audio);
    List<Question> findQuestionByDictionary(Dictionary dictionary);

    List<Question> findAllByAudio(Audio audio);
    List<Question> findAllByImage(Image image);
}
