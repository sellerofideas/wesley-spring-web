package com.web.tornese.SpringWeb.Servico;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import com.web.tornese.SpringWeb.models.Perfil;
import com.web.tornese.SpringWeb.models.repositorio.PerfisRepo;

public class CalculaIdade {
    public static void calculaIdade(Date idade, Date id, String[] args) {
        PerfisRepo repo;
        Optional<Period> perfis = repo.findById(id);
        Date aniversario = perfis.get();
        String dia = aniversario.toString().substring(0, 2);
        String mes = aniversario.toString().substring(3, 5);
        String ano = aniversario.toString().substring(6, 10);
        int actualAge = Period.between(LocalDate.of(Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia)), LocalDate.now()).getYears();
        System.out.println(actualAge);
    }
    
}
