package org.example.searchbookmark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/") // 이런 경우는 생략해도 무방함
public class MainController {
    @GetMapping
    public String index() {
        return "index";
    }
}
