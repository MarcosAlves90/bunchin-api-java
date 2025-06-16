package br.com.fatecmaua.bunchin.control;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fatecmaua.bunchin.dto.FuncionarioDTO;
import br.com.fatecmaua.bunchin.dto.OrganizacaoDTO;
import br.com.fatecmaua.bunchin.dto.OrganizacaoCreateDTO;
import br.com.fatecmaua.bunchin.dto.PontoDTO;
import br.com.fatecmaua.bunchin.dto.ProjetoDTO;
import br.com.fatecmaua.bunchin.model.Funcionario;
import br.com.fatecmaua.bunchin.model.FuncionarioProjeto;
import br.com.fatecmaua.bunchin.model.Link;
import br.com.fatecmaua.bunchin.model.Organizacao;
import br.com.fatecmaua.bunchin.model.Ponto;
import br.com.fatecmaua.bunchin.model.Projeto;
import br.com.fatecmaua.bunchin.repository.FuncionarioProjetoRepository;
import br.com.fatecmaua.bunchin.repository.FuncionarioRepository;
import br.com.fatecmaua.bunchin.repository.LinkRepository;
import br.com.fatecmaua.bunchin.repository.OrganizacaoRepository;
import br.com.fatecmaua.bunchin.repository.PontoRepository;
import br.com.fatecmaua.bunchin.repository.ProjetoRepository;
import br.com.fatecmaua.bunchin.service.FuncionarioCachingService;
import br.com.fatecmaua.bunchin.service.PontoCachingService;

@RestController
@RequestMapping("/api")
public class FuncionarioController {
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private FuncionarioProjetoRepository funcionarioProjetoRepository;
    @Autowired
    private ProjetoRepository projetoRepository;
    @Autowired
    private PontoRepository pontoRepository;
    @Autowired
    private LinkRepository linkRepository;
    @Autowired
    private OrganizacaoRepository organizacaoRepository;
    @Autowired
    private FuncionarioCachingService funcionarioCachingService;
    @Autowired
    private PontoCachingService pontoCachingService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    private FuncionarioDTO toDTO(Funcionario funcionario) {
	    FuncionarioDTO dto = new FuncionarioDTO();
	    dto.setN_registro(funcionario.getN_registro());
	    dto.setNome(funcionario.getNome());
	    dto.setEmail(funcionario.getEmail());
	    dto.setFuncao(funcionario.getFuncao());
	    dto.setCargo(funcionario.getCargo());
	    dto.setDepartamento(funcionario.getDepartamento());
	    dto.setCpf(funcionario.getCpf());
	    dto.setStatus(funcionario.getStatus());
        if (funcionario.getOrganizacao() != null) {
            dto.setOrganizacao_id(funcionario.getOrganizacao().getIdOrganizacao());
        }
	    return dto;
    }

    private OrganizacaoDTO toOrganizacaoDTO(Organizacao organizacao) {
        OrganizacaoDTO dto = new OrganizacaoDTO();
        dto.setIdOrganizacao(organizacao.getIdOrganizacao());
        dto.setNome(organizacao.getNome());
        dto.setCnpj(organizacao.getCnpj());
        dto.setEndereco(organizacao.getEndereco());
        dto.setTelefone(organizacao.getTelefone());
        dto.setEmail(organizacao.getEmail());
        dto.setStatus(organizacao.getStatus());
        dto.setDataCriacao(organizacao.getDataCriacao());
        return dto;
    }

    private ProjetoDTO toProjetoDTO(Projeto projeto) {
        ProjetoDTO dto = new ProjetoDTO();
        dto.setIdProjeto(projeto.getIdProjeto());
        dto.setNome(projeto.getNome());
        dto.setDescricao(projeto.getDescricao());
        dto.setDataInicio(projeto.getDataInicio());
        dto.setDataFimPrevista(projeto.getDataFimPrevista());
        dto.setDataFimReal(projeto.getDataFimReal());
        dto.setStatus(projeto.getStatus());
        dto.setDataCriacao(projeto.getDataCriacao());
        
        if (projeto.getOrganizacao() != null) {
            dto.setOrganizacaoId(projeto.getOrganizacao().getIdOrganizacao());
            dto.setOrganizacaoNome(projeto.getOrganizacao().getNome());
        }
        
        if (projeto.getResponsavel() != null) {
            dto.setResponsavelId(projeto.getResponsavel().getN_registro());
            dto.setResponsavelNome(projeto.getResponsavel().getNome());
        }
        
        return dto;
    }

    // --- FUNCIONARIO CRUD ---
    @GetMapping("/funcionario")
    public List<FuncionarioDTO> getAllFuncionarios() {
        return funcionarioCachingService.findAll()
            .stream()
            .map(this::toDTO)
            .toList();
    }

    @GetMapping("/funcionario/{cpf}")
    public FuncionarioDTO getFuncionarioByCpf(@PathVariable String cpf) {
        Funcionario funcionario = funcionarioRepository.findByCpf(cpf)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return toDTO(funcionario);
    }

    @GetMapping("/funcionario/busca")
    public List<FuncionarioDTO> buscaFuncionariosPorSubstring(@RequestParam("nome") String nome) {
        return funcionarioCachingService.buscaPorSubstring(nome)
            .stream()
            .map(this::toDTO)
            .toList();
    }

    @GetMapping("/funcionario/organizacao/{organizacaoId}")
    public List<FuncionarioDTO> getFuncionariosByOrganizacao(@PathVariable Integer organizacaoId) {
        List<Funcionario> funcionarios = funcionarioRepository.findByOrganizacaoId(organizacaoId);
        return funcionarios.stream()
            .map(this::toDTO)
            .toList();
    }

    @PostMapping("/funcionario")
    public ResponseEntity<?> createFuncionario(@RequestBody Funcionario funcionario) {
        funcionario.setStatus("0");
        String randomPassword = funcionario.getSenha();
        funcionario.setSenha(passwordEncoder.encode(randomPassword));
        funcionario.setOrganizacao(organizacaoRepository.findById(funcionario.getOrganizacao().getIdOrganizacao()).orElse(null));
        funcionarioRepository.save(funcionario);
        funcionarioCachingService.removerCache();
        return ResponseEntity.ok().body("Funcionário criado com sucesso.");
    }

    @PutMapping("/funcionario/{n_registro}")
    public ResponseEntity<?> updateFuncionario(@PathVariable String n_registro, @RequestBody Funcionario funcionario) {
        Optional<Funcionario> existing = funcionarioRepository.findByNRegistro(Integer.parseInt(n_registro));
        if (existing.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Funcionario f = existing.get();
        f.setN_registro(funcionario.getN_registro());
        f.setNome(funcionario.getNome());
        f.setEmail(funcionario.getEmail());
        f.setFuncao(funcionario.getFuncao());
        f.setCargo(funcionario.getCargo());
        f.setDepartamento(funcionario.getDepartamento());
        if (funcionario.getSenha() != null && !funcionario.getSenha().isEmpty()) {
            f.setSenha(passwordEncoder.encode(funcionario.getSenha()));
        }
        funcionarioRepository.save(f);
        funcionarioCachingService.removerCache();
        return ResponseEntity.ok().body("Funcionário atualizado com sucesso.");
    }

    @DeleteMapping("/funcionario/{n_registro}")
    public ResponseEntity<?> deleteFuncionario(@PathVariable String n_registro) {
        Optional<Funcionario> existing = funcionarioRepository.findByNRegistro(Integer.parseInt(n_registro));
        if (existing.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        funcionarioRepository.delete(existing.get());
        funcionarioCachingService.removerCache();
        return ResponseEntity.ok().body("Record deleted successfully.");
    }

    // --- ORGANIZACAO POST ---
    @PostMapping("/organizacao")
    public ResponseEntity<?> createOrganizacao(@RequestBody OrganizacaoCreateDTO request) {
        try {
            Organizacao organizacao = new Organizacao();
            organizacao.setNome(request.getNome());
            organizacao.setCnpj(request.getCnpj());
            organizacao.setEndereco(request.getEndereco());
            organizacao.setTelefone(request.getTelefone());
            organizacao.setEmail(request.getEmail());
            organizacao.setStatus("1");
            
            Organizacao organizacaoSalva = organizacaoRepository.save(organizacao);
            
            Funcionario admin = new Funcionario();
            admin.setNome(request.getAdminNome());
            admin.setEmail(request.getAdminEmail());
            admin.setCpf(request.getAdminCpf());
            admin.setFuncao(request.getAdminFuncao() != null ? request.getAdminFuncao() : "Administrador");
            admin.setCargo(request.getAdminCargo() != null ? request.getAdminCargo() : "Gerente");
            admin.setDepartamento(request.getAdminDepartamento() != null ? request.getAdminDepartamento() : "Administrativo");
            admin.setStatus("1");
            admin.setSenha(passwordEncoder.encode(request.getAdminSenha()));
            admin.setOrganizacao(organizacaoSalva);
            
            funcionarioRepository.save(admin);
            funcionarioCachingService.removerCache();
            
            return ResponseEntity.ok().body("Organização e administrador criados com sucesso.");
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao criar organização: " + e.getMessage());
        }
    }

    // --- PROJETO CRUD ---
    @GetMapping("/funcionario/{funcionarioId}/organizacao/{organizacaoId}/projetos")
    public List<ProjetoDTO> getProjetosByFuncionarioAndOrganizacao(@PathVariable Integer funcionarioId, @PathVariable Integer organizacaoId) {
        
        Funcionario funcionario = funcionarioRepository.findById(funcionarioId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário não encontrado"));
        
        Organizacao organizacao = organizacaoRepository.findById(organizacaoId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Organização não encontrada"));
        
        List<FuncionarioProjeto> funcionarioProjetos = funcionarioProjetoRepository
            .findByFuncionarioAndOrganizacao(funcionarioId, organizacaoId);
        
        return funcionarioProjetos.stream()
            .map(fp -> toProjetoDTO(fp.getProjeto()))
            .toList();
    }

    @PostMapping("/projeto")
    public ResponseEntity<?> createProjeto(@RequestBody ProjetoDTO projetoDTO) {
        try {
            if (projetoDTO.getOrganizacaoId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("ID da organização é obrigatório");
            }
            
            if (projetoDTO.getResponsavelId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("ID do responsável é obrigatório");
            }
            
            Organizacao organizacao = organizacaoRepository.findById(projetoDTO.getOrganizacaoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                    "Organização não encontrada"));
            
            Funcionario responsavel = funcionarioRepository.findByNRegistro(projetoDTO.getResponsavelId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                    "Funcionário responsável não encontrado"));
            
            Projeto projeto = new Projeto();
            projeto.setNome(projetoDTO.getNome());
            projeto.setDescricao(projetoDTO.getDescricao());
            projeto.setOrganizacao(organizacao);
            projeto.setResponsavel(responsavel);
            projeto.setDataInicio(projetoDTO.getDataInicio());
            projeto.setDataFimPrevista(projetoDTO.getDataFimPrevista());
            projeto.setStatus(projetoDTO.getStatus() != null ? projetoDTO.getStatus() : "planejamento");
            
            projetoRepository.save(projeto);
            
            return ResponseEntity.ok().body("Projeto criado com sucesso.");
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao criar projeto: " + e.getMessage());
        }
    }

    // --- LOGIN ---
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<Funcionario> funcionarioOpt = funcionarioRepository.findByEmailOrCpf(loginRequest.getEmail(), loginRequest.getEmail());
        if (funcionarioOpt.isPresent()) {
            Funcionario funcionario = funcionarioOpt.get();
            if (passwordEncoder.matches(loginRequest.getSenha(), funcionario.getSenha())) {
                FuncionarioDTO funcionarioDTO = toDTO(funcionario);
                return ResponseEntity.ok().body(new LoginResponse(1, "Login realizado com sucesso.", funcionarioDTO));
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse(0, "Credenciais inválidas.", null));
    }

    // --- PONTO CRUD ---
    @GetMapping("/ponto")
    public List<PontoDTO> getAllPontos() {
        return pontoCachingService.findAll().stream()
                .map(PontoDTO::fromPonto)
                .collect(java.util.stream.Collectors.toList());
    }

    @GetMapping("/ponto/{id}")
    public PontoDTO getPontoById(@PathVariable UUID id) {
        Ponto ponto = pontoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return PontoDTO.fromPonto(ponto);
    }

    @GetMapping("/ponto/filtro")
    public List<PontoDTO> getPontosByFuncionarioAndData(@RequestParam("funcionario_id") Integer funcionarioId, @RequestParam("dia") String dia) {
        
        Funcionario funcionario = funcionarioRepository.findByNRegistro(funcionarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
                        "Funcionário não encontrado com ID: " + funcionarioId));
        
        try {
            Instant dataRecebida = Instant.parse(dia);
            
            ZoneId fusoHorarioBrasil = ZoneId.of("America/Sao_Paulo");
            LocalDate dataLocal = dataRecebida.atZone(fusoHorarioBrasil).toLocalDate();
            
            Instant dataInicio = dataLocal.atStartOfDay(fusoHorarioBrasil).toInstant();
            Instant dataFim = dataLocal.plusDays(1).atStartOfDay(fusoHorarioBrasil).toInstant().minusNanos(1);
            
            List<Ponto> pontos = pontoRepository.findByFuncionarioAndData_horaBetween(
                    funcionario, dataInicio, dataFim);
            
            return pontos.stream()
                    .map(PontoDTO::fromPonto)
                    .collect(java.util.stream.Collectors.toList());
                    
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato de data inválido: " + dia);
        }
    }

    @PostMapping("/ponto")
    public ResponseEntity<?> createPonto(@RequestBody PontoDTO pontoDTO) {
        if (pontoDTO.getFuncionario_fk() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "funcionario_fk (n_registro) é obrigatório");
        }
        
        Funcionario funcionario = funcionarioRepository.findByNRegistro(pontoDTO.getFuncionario_fk())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                        "funcionario_fk (n_registro) inválido: " + pontoDTO.getFuncionario_fk()));
        
        Ponto ponto = pontoDTO.toPonto(funcionario);
        
        pontoRepository.save(ponto);
        pontoCachingService.removerCache();
        return ResponseEntity.ok().body("Registro criado com êxito.");
    }

    @PutMapping("/ponto/{id}")
    public ResponseEntity<?> updatePonto(@PathVariable UUID id, @RequestBody PontoDTO pontoDTO) {
        Ponto ponto = pontoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ponto não encontrado"));
        
        ponto.setNome_tipo(pontoDTO.getNome_tipo());
        
        if (pontoDTO.getData_hora() != null) {
            try {
                ponto.setData_hora(Instant.parse(pontoDTO.getData_hora()));
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato de data inválido");
            }
        }
        
        if (pontoDTO.getFuncionario_fk() != null) {
            Funcionario funcionario = funcionarioRepository.findByNRegistro(pontoDTO.getFuncionario_fk())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                            "funcionario_fk (n_registro) inválido: " + pontoDTO.getFuncionario_fk()));
            ponto.setFuncionario(funcionario);
        }
        
        pontoRepository.save(ponto);
        pontoCachingService.removerCache();
        return ResponseEntity.ok().body("Registro atualizado com êxito.");
    }
    
    @DeleteMapping("/ponto/{id}")
    public ResponseEntity<?> deletePonto(@PathVariable UUID id) {
        Optional<Ponto> existing = pontoRepository.findById(id);
        if (existing.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        pontoRepository.delete(existing.get());
        pontoCachingService.removerCache();
        return ResponseEntity.ok().body("Registro deletado com êxito.");
    }
    
    // --- RESET DE SENHA E LINK ---
    @PostMapping("/storeResetCode")
    public ResponseEntity<?> storeResetCode(@RequestBody Link link) {
        link.setDataCriacao(Instant.now());
        linkRepository.save(link);
        return ResponseEntity.ok().body("Registro criado com êxito.");
    }
    
    @PostMapping("/checkEmailExists")
    public ResponseEntity<?> checkEmailExists(@RequestBody EmailRequest emailRequest) {
        Optional<Funcionario> funcionario = funcionarioRepository.findByEmail(emailRequest.getEmail());
        if (funcionario.isPresent()) {
            Funcionario f = funcionario.get();
            return ResponseEntity.ok().body(new FuncionarioBasicDTO(f.getN_registro(), f.getNome()));
        }
        return ResponseEntity.ok().body(null);
    }
    
    @PostMapping("/verifyResetCode")
    public ResponseEntity<?> verifyResetCode(@RequestBody CodeRequest codeRequest) {
        Optional<Link> link = linkRepository.findByCodigo(codeRequest.getCodigo());
        return ResponseEntity.ok().body(link.isPresent() ? new ValidResponse(true) : new ValidResponse(false));
    }
    
    @PutMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest req) {
        Optional<Link> linkOpt = linkRepository.findByCodigo(req.getCodigo());
        if (linkOpt.isPresent()) {
            Link link = linkOpt.get();
            Optional<Funcionario> funcionarioOpt = funcionarioRepository.findById(link.getFuncionario().getN_registro());
            if (funcionarioOpt.isPresent()) {
                Funcionario funcionario = funcionarioOpt.get();
                funcionario.setSenha(passwordEncoder.encode(req.getSenha()));
                funcionarioRepository.save(funcionario);
                linkRepository.delete(link);
                return ResponseEntity.ok().body("Senha alterada com sucesso.");
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Código inválido.");
    }
    
    @PutMapping("/newPassword")
    public ResponseEntity<?> newPassword(@RequestBody NewPasswordRequest req) {
        Optional<Funcionario> funcionarioOpt = funcionarioRepository.findById(req.getN_registro());
        if (funcionarioOpt.isPresent()) {
            Funcionario funcionario = funcionarioOpt.get();
            funcionario.setSenha(passwordEncoder.encode(req.getSenha()));
            funcionario.setStatus("1");
            funcionarioRepository.save(funcionario);
            return ResponseEntity.ok().body("Senha alterada com sucesso.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao alterar a senha.");
    }
    
    // --- DTOs ---
    public static class LoginRequest {
        private String email;
        private String senha;
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getSenha() { return senha; }
        public void setSenha(String senha) { this.senha = senha; }
    }
    
    public static class LoginResponse {
        private int status;
        private String message;
        private FuncionarioDTO funcionario;
        public LoginResponse(int status, String message, FuncionarioDTO funcionario) {
            this.status = status; this.message = message; this.funcionario = funcionario;
        }
        public int getStatus() { return status; }
        public String getMessage() { return message; }
        public FuncionarioDTO getFuncionario() { return funcionario; }
    }

    public static class EmailRequest {
        private String email;
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
    }

    public static class CodeRequest {
        private String codigo;
        public String getCodigo() { return codigo; }
        public void setCodigo(String codigo) { this.codigo = codigo; }
    }

    public static class ValidResponse {
        private boolean valid;
        public ValidResponse(boolean valid) { this.valid = valid; }
        public boolean isValid() { return valid; }
    }

    public static class ResetPasswordRequest {
        private String codigo;
        private String senha;
        public String getCodigo() { return codigo; }
        public void setCodigo(String codigo) { this.codigo = codigo; }
        public String getSenha() { return senha; }
        public void setSenha(String senha) { this.senha = senha; }
    }

    public static class NewPasswordRequest {
        private Integer n_registro;
        private String senha;
        public Integer getN_registro() { return n_registro; }
        public void setN_registro(Integer n_registro) { this.n_registro = n_registro; }
        public String getSenha() { return senha; }
        public void setSenha(String senha) { this.senha = senha; }
    }

    public static class FuncionarioBasicDTO {
        private Integer n_registro;
        private String nome;
        public FuncionarioBasicDTO(Integer n_registro, String nome) {
            this.n_registro = n_registro; this.nome = nome;
        }
        public Integer getN_registro() { return n_registro; }
        public String getNome() { return nome; }
    }
}
