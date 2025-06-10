package br.com.fatecmaua.bunchin.repository;

import br.com.fatecmaua.bunchin.model.Organizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface OrganizacaoRepository extends JpaRepository<Organizacao, Integer> {
    Optional<Organizacao> findByCnpj(String cnpj);
    List<Organizacao> findByStatus(String status);
    List<Organizacao> findByNomeContainingIgnoreCase(String nome);
}
