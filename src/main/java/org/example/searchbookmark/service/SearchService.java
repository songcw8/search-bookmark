package org.example.searchbookmark.service;

import org.example.searchbookmark.model.vo.KeywordSearch;
import org.springframework.stereotype.Service;

import java.util.List;

// Spring Container에 Component로 등록
public interface SearchService {
    List<KeywordSearch>searchByKeyWord(String keyWord);
}
