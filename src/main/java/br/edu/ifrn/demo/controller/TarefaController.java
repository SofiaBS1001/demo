package br.edu.ifrn.demo.controller;

import br.edu.ifrn.demo.Service.TarefaService;
import br.edu.ifrn.demo.dto.TaskRequestDTO;
import br.edu.ifrn.demo.dto.TaskResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {
    private final TarefaService service;
    public TarefaController(TarefaService service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<TaskResponseDTO> criar(@RequestBody
                                                 TaskRequestDTO dto) {
        TaskResponseDTO criada = service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }
    @GetMapping
    public List<TaskResponseDTO> listar() {
        return service.listarTodas();
    }
}