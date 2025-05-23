package br.com.fatecmaua.bunchin.repository;

import br.com.fatecmaua.bunchin.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    Optional<Funcionario> findByCpf(String cpf);
    Optional<Funcionario> findByEmail(String email);
    Optional<Funcionario> findByEmailOrCpf(String email, String cpf);
    List<Funcionario> findByNomeContainingIgnoreCase(String substring);
}
