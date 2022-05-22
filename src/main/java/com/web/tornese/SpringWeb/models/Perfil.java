package com.web.tornese.SpringWeb.models;

import java.sql.Date;
import java.time.Year;
import java.util.Calendar;

import javax.persistence.*;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "perfis")
public class Perfil {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "nome", length = 100, nullable = false)
  private String nome;

  
  @Column(name = "nomeArtistico", length = 100, nullable = false)
  private String nomeArtistico;

  @Column(name = "email", length = 180, nullable = false)
  private String email;

  @Column(name = "senha", length = 255, nullable = false)
  private String senha;

  @Column(name = "bio")
  @Type(type = "text")
  private String bio;

  @Column(name="idade")
  private Date idade;

}
