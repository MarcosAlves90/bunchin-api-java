package br.com.fatecmaua.bunchin.repository;

import br.com.fatecmaua.bunchin.model.Link;
import br.com.fatecmaua.bunchin.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface LinkRepository extends JpaRepository<Link, Integer> {
    Optional<Link> findByCodigo(String codigo);
    List<Link> findByFuncionario(Funcionario funcionario);
}
