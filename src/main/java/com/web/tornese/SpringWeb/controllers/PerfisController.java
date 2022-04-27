package com.web.tornese.SpringWeb.controllers;

import java.util.List;
import java.util.Optional;

import com.web.tornese.SpringWeb.models.Perfil;
import com.web.tornese.SpringWeb.repositorio.PerfisRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

  @GetMapping("/perfis/novo")
  public String novo(){
    return "perfis/novo";
  }

  @PostMapping("/perfis/criar")
  public String criar(Perfil perfil){
    repo.save(perfil);
    return "redirect:/";
  }

  //Editar perfil
  @RequestMapping("/perfis/{id}")
  public String busca(@PathVariable int id, Model model){
    Optional<Perfil> perfis = repo.findById(id);
    try{
      model.addAttribute("perfil", perfis.get());
    }
    catch(Exception err){ return "redirect:/perfis"; }

    return "/perfis/editar";
  }

  //Botão de atualização de perfil
  @PostMapping("/perfis/{id}/atualizar")
  public String atualizar(@PathVariable int id, Perfil Perfil){
    // if(!repo.exist(id)){
    if(!repo.existsById(id)){
      return "redirect:/perfis";
    }

    repo.save(Perfil);

    return "redirect:/perfis";
  }

  //Mudar para PostMapping
  @RequestMapping("/perfis/{id}/excluir")
  public String excluir(@PathVariable int id){
    repo.deleteById(id);
    return "redirect:/perfis";
  }
}
