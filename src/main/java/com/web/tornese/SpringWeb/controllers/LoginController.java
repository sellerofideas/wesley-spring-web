package com.web.tornese.SpringWeb.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.web.tornese.SpringWeb.Servico.CookieService;
import com.web.tornese.SpringWeb.models.Perfil;
import com.web.tornese.SpringWeb.models.repositorio.PerfisRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
  @Autowired
  private PerfisRepo repo;

  @GetMapping("/login")
  public String index(){
    return "login/index";
  }

  @PostMapping("/logar")
  public String logar(Model model, Perfil admParam, String lembrar, HttpServletResponse response) throws IOException{
    Perfil adm = this.repo.Login(admParam.getEmail(), admParam.getSenha());
    if(adm != null){
      int tempoLogado = (60*60); // 1 hora de cookie
      if(lembrar != null) tempoLogado = (60*60*24*365); // 1 ano de cookie
      CookieService.setCookie(response, "usuarioId", String.valueOf(adm.getId()), tempoLogado);
      CookieService.setCookie(response, "userName", String.valueOf(adm.getNome()), tempoLogado);
      CookieService.setCookie(response, "nomeArtistico", String.valueOf(adm.getNomeArtistico()), tempoLogado);
      CookieService.setCookie(response, "userEmail", String.valueOf(adm.getEmail()), tempoLogado);
      CookieService.setCookie(response, "userBio", String.valueOf(adm.getBio()), tempoLogado);
      CookieService.setCookie(response, "userIdade", String.valueOf(adm.getIdade()), tempoLogado);
      return "redirect:/";
    }
    model.addAttribute("erro", "Usuário ou senha inválidos");
    return "login/index";
  }
  
  @GetMapping("/novo")
  public String novo(){
    return "perfis/novo";
  }

  @PostMapping("/criar")
  public String criar(Perfil perfil){
    repo.save(perfil);
    return "redirect:/login";
  }

  @GetMapping("/sair")
  public String logout(HttpServletResponse response) throws IOException{
    CookieService.setCookie(response, "usuarioId", "", 0);
    return "redirect:/login";
  }
}
