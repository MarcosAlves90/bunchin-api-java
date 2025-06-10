package br.com.fatecmaua.bunchin.repository;

import br.com.fatecmaua.bunchin.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Integer> {
    List<Projeto> findByOrganizacaoIdOrganizacao(Integer organizacaoId);
    
    @Query("SELECT p FROM Projeto p WHERE p.responsavel.n_registro = :responsavelId")
    List<Projeto> findByResponsavelNRegistro(@Param("responsavelId") Integer responsavelId);
    
    List<Projeto> findByStatus(String status);
    List<Projeto> findByNomeContainingIgnoreCase(String nome);
}
