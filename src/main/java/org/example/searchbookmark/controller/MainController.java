package org.example.searchbookmark.controller;

import com.mysql.cj.Session;
import jakarta.servlet.http.HttpSession;
import org.example.searchbookmark.model.vo.KeywordSearch;
import org.example.searchbookmark.service.SearchService;
import org.example.searchbookmark.util.MyLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

@Controller
//@RequestMapping("/") // 이런 경우는 생략해도 무방함
public class MainController {
    private final MyLogger logger = new MyLogger(this.getClass().getName());
    private final SearchService searchService;

    // 생성자 주입 -> 의존성 주입을
    public MainController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public String index(Model model, @RequestParam(value = "keyword",
            defaultValue = "아이돌 에스파", required = false) String keyword,
                        HttpSession session) throws Exception {
        logger.info(keyword);
        if (keyword == null) {
            return "index";
        }
        List<KeywordSearch> result = searchService.searchByKeyWord(keyword);
        Map<String, KeywordSearch> map = new HashMap<>();
        for (KeywordSearch keywordSearch : result) {
            map.put(keywordSearch.uuid(), keywordSearch);
        }
        // 임시 uuid만 가지고 있으면 검색 결과를 쉽게 불러오게 하려고
        session.setAttribute("temp", map); // 일종의 캐싱
        model.addAttribute("result", result);
        return "index";
    }

    @PostMapping("/bookmark")
    public String bookmark(@RequestParam("uuid") String uuid, Model model, HttpSession session) throws Exception {
        Map<String, KeywordSearch> temp =
                (HashMap<String, KeywordSearch>) session.getAttribute("temp");
        logger.info(temp.get(uuid).link());
        return "redirect:/";
    }
}
