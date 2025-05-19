package br.com.fatecmaua.bunchin.repository;

import br.com.fatecmaua.bunchin.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface LinkRepository extends JpaRepository<Link, Integer> {
    Optional<Link> findByCodigo(String codigo);
}
