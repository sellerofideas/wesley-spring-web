package com.web.tornese.SpringWeb.repositorio;

import java.sql.Date;
import java.time.Period;
import java.util.Optional;

import com.web.tornese.SpringWeb.models.Perfil;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PerfisRepo extends CrudRepository<Perfil, Integer> {
  @Query(value="select CASE WHEN count(1) > 0 THEN 'true' ELSE 'false' END  from perfis where id = :id", nativeQuery = true)
  public boolean exist(int id);

  @Query(value="select * from perfis where email = :email and senha = :senha", nativeQuery = true)
  public Perfil Login(String email, String senha);
  
  //@Query(value="select * from perfis where nome like %:nome% or email like %:email% ", nativeQuery = true)
	//public ArrayList<Administrador> findAllByNomeEmail(@Param("nome") String nome, @Param("email") String email);

  Optional<Perfil> findById(Integer id);


  Optional<Period> findById(Date id);
  @Query(value="select * from perfis where idade = :idade", nativeQuery = true)
  public Perfil CalculaIdade(Date idade);
}
