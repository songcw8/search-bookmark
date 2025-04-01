package org.example.searchbookmark.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.searchbookmark.model.vo.KeywordSearch;
import org.example.searchbookmark.model.vo.NaverSearchParam;
import org.example.searchbookmark.model.vo.NaverSearchResult;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class NaverSearchAPI implements DotenvMixin, ObjectMapperMixin{
    private static final Log log = LogFactory.getLog(NaverSearchAPI.class);
    private final MyLogger logger = new MyLogger(this.getClass().getSimpleName());
    private final HttpClient httpClient = HttpClient.newHttpClient();

    public List<KeywordSearch> callAPI(NaverSearchParam param) throws Exception {
        String url = "https://openapi.naver.com/v1/search/blog.json";
        String query = URLEncoder.encode(param.query(), StandardCharsets.UTF_8);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("%s?query=%s".formatted(url, query))) // URL로부 호출을 시도할 URI 생성
                .header("X-Naver-Client-Id", dotenv.get("NAVER_CLIENT_ID"))
                .header("X-Naver-Client-Secret", dotenv.get("NAVER_CLIENT_SECRET"))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();
        //logger.info(responseBody);
        NaverSearchResult naverSearchResult = objectMapper.readValue(responseBody, NaverSearchResult.class);
        return naverSearchResult.items().stream().map(item -> new KeywordSearch(
                "",
                item.title(),
                item.link(),
                item.description(),
                item.postdate(),
                ""
        )).toList();
    }
}
