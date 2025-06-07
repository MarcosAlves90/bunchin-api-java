package br.com.fatecmaua.bunchin.repository;

import br.com.fatecmaua.bunchin.model.Ponto;
import br.com.fatecmaua.bunchin.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.Instant;

@Repository
public interface PontoRepository extends JpaRepository<Ponto, java.util.UUID> {
    List<Ponto> findByFuncionario_fkAndData_horaBetween(Funcionario funcionario, Instant dataInicio, Instant dataFim);
}