package br.com.fatecmaua.bunchin.dto;

import java.time.Instant;
import java.util.UUID;

import br.com.fatecmaua.bunchin.model.Ponto;
import br.com.fatecmaua.bunchin.model.Funcionario;

public class PontoDTO {
    private String id_ponto;
    private String funcionario_fk; // Será o CPF do funcionário
    private String nome_tipo;
    private String data_hora;
    
    // Construtores
    public PontoDTO() {}
    
    public PontoDTO(String id_ponto, String funcionario_fk, String nome_tipo, String data_hora) {
        this.id_ponto = id_ponto;
        this.funcionario_fk = funcionario_fk;
        this.nome_tipo = nome_tipo;
        this.data_hora = data_hora;
    }
    
    // Getters e Setters
    public String getId_ponto() {
        return id_ponto;
    }
    
    public void setId_ponto(String id_ponto) {
        this.id_ponto = id_ponto;
    }
    
    public String getFuncionario_fk() {
        return funcionario_fk;
    }
    
    public void setFuncionario_fk(String funcionario_fk) {
        this.funcionario_fk = funcionario_fk;
    }
    
    public String getNome_tipo() {
        return nome_tipo;
    }
    
    public void setNome_tipo(String nome_tipo) {
        this.nome_tipo = nome_tipo;
    }
    
    public String getData_hora() {
        return data_hora;
    }
    
    public void setData_hora(String data_hora) {
        this.data_hora = data_hora;
    }
    
    // Método para converter de Ponto para PontoDTO
    public static PontoDTO fromPonto(Ponto ponto) {
        PontoDTO dto = new PontoDTO();
        dto.setId_ponto(ponto.getId_ponto() != null ? ponto.getId_ponto().toString() : null);
        dto.setNome_tipo(ponto.getNome_tipo());
        dto.setData_hora(ponto.getData_hora() != null ? ponto.getData_hora().toString() : null);
        
        // Pega apenas o CPF do funcionário
        if (ponto.getFuncionario_fk() != null) {
            dto.setFuncionario_fk(ponto.getFuncionario_fk().getCpf());
        }
        
        return dto;
    }
    
    // Método para converter de PontoDTO para Ponto
    public Ponto toPonto(Funcionario funcionario) {
        Ponto ponto = new Ponto();
        if (id_ponto != null && !id_ponto.isEmpty()) {
            ponto.setId_ponto(UUID.fromString(id_ponto));
        } else {
            ponto.setId_ponto(UUID.randomUUID());
        }
        ponto.setNome_tipo(nome_tipo);
        if (data_hora != null) {
            ponto.setData_hora(Instant.parse(data_hora));
        }
        ponto.setFuncionario_fk(funcionario);
        
        return ponto;
    }
}