package br.com.fatecmaua.bunchin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.fatecmaua.bunchin.model.Ponto;
import br.com.fatecmaua.bunchin.repository.PontoRepository;

@Service
public class PontoCachingService {

    @Autowired
    private PontoRepository pontoRepository;

    @Cacheable(value = "TodosPontosCacheable")
    public List<Ponto> findAll() {
        return pontoRepository.findAll();
    }

    @CacheEvict(value = "TodosPontosCacheable", allEntries = true)
    public void removerCache() {
        System.out.println("Removendo cache de pontos!");
    }
}