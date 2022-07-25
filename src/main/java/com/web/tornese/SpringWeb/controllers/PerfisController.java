package com.web.tornese.SpringWeb.controllers;

import java.util.List;
import java.util.Optional;

import com.web.tornese.SpringWeb.models.Perfil;
import com.web.tornese.SpringWeb.models.repositorio.PerfisRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PerfisController {

  @Autowired
  private PerfisRepo repo;

  //Mostra uma lista de perfis
  @GetMapping("/perfis")
  public String index(Model model){
    List<Perfil> perfil = (List<Perfil>)repo.findAll();
    model.addAttribute("perfil", perfil);
    return "perfis/index";
  }

  //Editar perfil
   @RequestMapping("/editar/{id}")
  public String busca(@PathVariable int id, Model model){
    Optional<Perfil> perfis = repo.findById(id);
    try{
      model.addAttribute("perfil", perfis.get());
    }
    catch(Exception err){ return "redirect:/perfis"; }

    return "/perfis/editar";
  }

  /*@RequestMapping("/perfis/{id}")
  public String busca(@PathVariable int id, Model model){
    Optional<Perfil> perfis = this.repo.findById(id);
    if(perfis.isPresent()) {
      Perfil perfil = perfis.get();
    try{
      model.addAttribute("perfil", perfis.get());
    }
    else{ return "redirect:/"; }

    return "/perfis/editar";
  }*/

  //Ver Perfil
  /*@RequestMapping("/id/{id}")
  public String ver(@PathVariable int id, Model model){
    Optional<Perfil> perfis = repo.findById(id);
    try{
      model.addAttribute("perfil", perfis.get());
    }
    catch(Exception err){ return "redirect:/perfis"; }

    return "/perfis/meuPerfil";
  }*/

  //Ver perfil 2° maneira
  @GetMapping("/id/{id}")
  public ModelAndView show(@PathVariable int id) {
    Optional<Perfil> optional = this.repo.findById(id);
    if(optional.isPresent()) {
      Perfil perfil = optional.get();
      ModelAndView mv = new ModelAndView("perfis/meuPerfil");
      mv.addObject("perfil", perfil);
      return mv;
    }
    else {
      return new ModelAndView("redirect:/perfis");
    }
  }

  //Botão de atualização de perfil
  @PostMapping("/perfis/{id}/atualizar")
  public String atualizar(@PathVariable int id, Perfil Perfil){
    //if(!repo.exist(id)){
    if(!repo.existsById(id)){
      return "redirect:/edicao/{id}";
    }
    repo.save(Perfil);
    return "redirect:/meuPerfil/{id}";
  }

  @GetMapping("/meuPerfil/{id}")
  public ModelAndView meuPerfil(@PathVariable int id) {
    Optional<Perfil> optional = this.repo.findById(id);
    if(optional.isPresent()) {
      Perfil perfil = optional.get();
      ModelAndView mv = new ModelAndView("perfis/meuPerfil");
      mv.addObject("perfil", perfil);
      return mv;
    }
    else {
      return new ModelAndView("perfis/meuPerfil");
    }
  }

  //Mudar para PostMapping
  @RequestMapping("/perfil/{id}/excluir")
  public String excluir(@PathVariable int id){
    repo.deleteById(id);
    return "redirect:/login";
  }

  
}
