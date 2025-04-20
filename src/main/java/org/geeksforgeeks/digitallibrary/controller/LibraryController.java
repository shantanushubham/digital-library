package org.geeksforgeeks.digitallibrary.controller;

import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibraryController {

    @GetMapping("/")
    public String welcomeMessage(HttpServletRequest request) {
        return "Welcome to Digital Library: " + request.getSession().getId();
    }

//    @GetMapping("/csrf")
//    public CsrfToken getCsrfToken(HttpServletRequest request) {
//        return (CsrfToken) request.getAttribute("_csrf");
//    }
}
