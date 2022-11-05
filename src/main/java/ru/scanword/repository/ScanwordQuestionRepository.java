package ru.scanword.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.scanword.domain.ScanwordQuestion;
import ru.scanword.domain.keys.ScanwordQuestionKey;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScanwordQuestionRepository extends JpaRepository<ScanwordQuestion, Long> {

    Optional<ScanwordQuestion> findById(ScanwordQuestionKey id);
    Optional<ScanwordQuestion> findByScanwordIdAndQuestionId(Long scanwordId, Long questionId);
    List<ScanwordQuestion> findAllByScanwordId(Long scanwordId);


    void deleteById(ScanwordQuestionKey id);
    void deleteByScanwordIdAndQuestionId(Long scanwordId, Long questionId);
}
