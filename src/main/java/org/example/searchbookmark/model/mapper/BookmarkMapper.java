package org.example.searchbookmark.model.mapper;

import org.example.searchbookmark.model.vo.KeywordSearch;
import org.springframework.stereotype.Repository;

public interface BookmarkMapper {
    int insertBookmark(KeywordSearch keywordSearch);
    KeywordSearch getOneBookmark(String uuid);
    String checkLastID();
}
