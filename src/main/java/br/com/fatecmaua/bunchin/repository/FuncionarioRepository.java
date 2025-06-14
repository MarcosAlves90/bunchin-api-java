package br.com.fatecmaua.bunchin.repository;

import br.com.fatecmaua.bunchin.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;
import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    @Query("SELECT * FROM tb_funcionario WHERE n_registro = :n_registro")
    List<Funcionario> findByNRegistro(@Param("n_registro") Integer n_registro);
    Optional<Funcionario> findByCpf(String cpf);
    Optional<Funcionario> findByEmail(String email);
    Optional<Funcionario> findByEmailOrCpf(String email, String cpf);
    List<Funcionario> findByNomeContainingIgnoreCase(String substring);
}
