package br.com.fatecmaua.bunchin.repository;

import br.com.fatecmaua.bunchin.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Integer> {
    List<Projeto> findByOrganizacaoIdOrganizacao(Integer organizacaoId);
    List<Projeto> findByResponsavelN_registro(Integer responsavelId);
    List<Projeto> findByStatus(String status);
    List<Projeto> findByNomeContainingIgnoreCase(String nome);
}
