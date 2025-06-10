package br.com.fatecmaua.bunchin.repository;

import br.com.fatecmaua.bunchin.model.FuncionarioProjeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface FuncionarioProjetoRepository extends JpaRepository<FuncionarioProjeto, Integer> {
    @Query("SELECT fp FROM FuncionarioProjeto fp WHERE fp.funcionario.n_registro = :nRegistro")
    List<FuncionarioProjeto> findByFuncionarioNRegistro(@Param("nRegistro") Integer nRegistro);
    List<FuncionarioProjeto> findByProjetoIdProjeto(Integer projetoId);
    List<FuncionarioProjeto> findByStatus(String status);
}
