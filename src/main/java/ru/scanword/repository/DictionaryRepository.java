package ru.scanword.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.scanword.domain.Dictionary;
import ru.scanword.domain.Question;

@Repository
public interface DictionaryRepository extends JpaRepository<Dictionary, Long> {
}
