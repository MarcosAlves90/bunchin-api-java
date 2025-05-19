package br.com.fatecmaua.bunchin.repository;

import br.com.fatecmaua.bunchin.model.Ponto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PontoRepository extends JpaRepository<Ponto, String> {
}
