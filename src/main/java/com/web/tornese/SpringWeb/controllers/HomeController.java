package com.web.tornese.SpringWeb.controllers;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import com.web.tornese.SpringWeb.Servico.CookieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping("/")
  public String index(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
    model.addAttribute("id", CookieService.getCookie(request, "usuarioId"));
    model.addAttribute("nome", CookieService.getCookie(request, "userName"));
    model.addAttribute("nomeArtistico", CookieService.getCookie(request, "nomeArtistico"));
    model.addAttribute("email", CookieService.getCookie(request, "userEmail"));
    model.addAttribute("bio", CookieService.getCookie(request, "userBio"));
    model.addAttribute("idade", CookieService.getCookie(request, "userIdade"));
    return "home/index";
  }
}
