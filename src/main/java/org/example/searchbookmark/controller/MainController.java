package org.example.searchbookmark.controller;

import org.example.searchbookmark.model.vo.KeywordSearch;
import org.example.searchbookmark.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
//@RequestMapping("/") // 이런 경우는 생략해도 무방함
public class MainController {
    private final SearchService searchService;

    // 생성자 주입 -> 의존성 주입을
    public MainController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public String index(Model model) throws Exception {
        List<KeywordSearch> result = searchService.searchByKeyWord("4월은 너의 거짓말");
        model.addAttribute("result", result);
        return "index";
    }
}
