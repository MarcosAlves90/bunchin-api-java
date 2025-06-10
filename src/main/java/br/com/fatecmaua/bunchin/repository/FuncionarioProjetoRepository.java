package br.com.fatecmaua.bunchin.repository;

import br.com.fatecmaua.bunchin.model.FuncionarioProjeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FuncionarioProjetoRepository extends JpaRepository<FuncionarioProjeto, Integer> {
    List<FuncionarioProjeto> findByFuncionarioN_registro(Integer funcionarioId);
    List<FuncionarioProjeto> findByProjetoIdProjeto(Integer projetoId);
    List<FuncionarioProjeto> findByStatus(String status);
}
