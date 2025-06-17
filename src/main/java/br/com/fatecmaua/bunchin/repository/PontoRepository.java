package br.com.fatecmaua.bunchin.repository;

import br.com.fatecmaua.bunchin.model.Ponto;
import br.com.fatecmaua.bunchin.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.Instant;

@Repository
public interface PontoRepository extends JpaRepository<Ponto, java.util.UUID> {
    @Query("SELECT p FROM Ponto p WHERE p.funcionario = :funcionario AND p.data_hora BETWEEN :dataInicio AND :dataFim")
    List<Ponto> findByFuncionarioAndData_horaBetween(@Param("funcionario") Funcionario funcionario, @Param("dataInicio") Instant dataInicio, @Param("dataFim") Instant dataFim);
    
    List<Ponto> findByFuncionario(Funcionario funcionario);
}