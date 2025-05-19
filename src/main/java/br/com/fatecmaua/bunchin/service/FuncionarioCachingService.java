package br.com.fatecmaua.bunchin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.fatecmaua.bunchin.model.Funcionario;
import br.com.fatecmaua.bunchin.repository.FuncionarioRepository;

@Service
public class FuncionarioCachingService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Cacheable(value = "TodosFuncionariosCacheable")
    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll();
    }

    @Cacheable(value = "FuncionarioPorSubstring", key = "#substring")
    public List<Funcionario> buscaPorSubstring(String substring) {
        return funcionarioRepository.findByNomeContainingIgnoreCase(substring);
    }

    @CacheEvict(value = { "TodosFuncionariosCacheable", "FuncionarioPorSubstring" }, allEntries = true)
    public void removerCache() {
        System.out.println("Removendo cache de funcionarios!");
    }
}
