package org.example.searchbookmark.service;

import org.example.searchbookmark.model.vo.KeywordSearch;
import org.example.searchbookmark.util.MyLogger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NaverSearchService implements SearchService {

    final MyLogger logger = new MyLogger(this.getClass().getName());

    @Override
    public List<KeywordSearch> searchByKeyWord(String keyWord) {
        logger.info("searchByKeyword: %s".formatted(keyWord));
        return List.of();
    }
}
