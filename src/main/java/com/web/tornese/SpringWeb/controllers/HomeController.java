package com.web.tornese.SpringWeb.controllers;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

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

@Controller
public class HomeController {

  
  @Autowired
  private PerfisRepo repo;

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

  @RequestMapping("/perfil/{id}")
  public String busca(@PathVariable int id, Model model){
    Optional<Perfil> perfil = repo.findById(id);
    try{
      model.addAttribute("perfil", perfil.get());
    }
    catch(Exception err){ return "redirect:/perfis"; }

    return "/perfis/editar";
  }

}
