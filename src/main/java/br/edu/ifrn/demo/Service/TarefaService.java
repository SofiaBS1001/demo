package br.edu.ifrn.demo.Service;

import br.edu.ifrn.demo.dto.TaskRequestDTO;
import br.edu.ifrn.demo.dto.TaskResponseDTO;
import br.edu.ifrn.demo.model.Tarefa;
import br.edu.ifrn.demo.repository.TarefaRepository;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class TarefaService {

    private final TarefaRepository repository;

    public TarefaService(TarefaRepository repository) {
        this.repository = repository;
    }

    public TaskResponseDTO criar(TaskRequestDTO dto) {
        Task tarefa = new Task(dto.titulo(), dto.descricao(), dto.prazo());
        Task salva = repository.salvar(tarefa);
        return toResponseDTO(salva);
    }
    public List<TaskResponseDTO> listarTodas() {
        return repository.listarTodas().stream()
                .map(this::toResponseDTO)
                .toList();
    }
    private TaskResponseDTO toResponseDTO(Task tarefa) {
        return new TaskResponseDTO(
                tarefa.getId(),
                tarefa.getTitulo(),
                tarefa.isConcluida(),
                tarefa.getPrioridade()
        );
    }
}