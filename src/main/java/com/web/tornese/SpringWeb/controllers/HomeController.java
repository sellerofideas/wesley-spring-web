package com.web.tornese.SpringWeb.controllers;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import com.web.tornese.SpringWeb.Servico.CookieService;
import com.web.tornese.SpringWeb.models.Perfil;
import com.web.tornese.SpringWeb.repositorio.PerfisRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

  @Autowired
  private PerfisRepo repo;

  @GetMapping("/")
  public String index(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
    model.addAttribute("nome", CookieService.getCookie(request, "nomeUsuario"));
    return "home/index";
  }

  // Retorna um unico id
  @RequestMapping("/{id}")
  public ModelAndView recebeParam(@PathVariable Integer id, @RequestParam String nome, Model model) {
    Perfil perfil = repo.findById(id).get();
    model.addAttribute("perfil", perfil);
    return new ModelAndView("home/index");
  }

}
