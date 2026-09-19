package chamados_api.service;


import chamados_api.model.ChamadoEntity;
import chamados_api.repository.ChamadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChamadoService {

    private final ChamadoRepository repository;

    public ChamadoEntity cadastrar(ChamadoEntity chamado) {
        return repository.save(chamado);
    }

    public List<ChamadoEntity> listar() {
        return repository.findAll();
    }

    public Optional<ChamadoEntity> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public Optional<ChamadoEntity> atualizar(Integer id, ChamadoEntity dados) {

        Optional<ChamadoEntity> chamadoEncontrado = repository.findById(id);

        if (chamadoEncontrado.isPresent()) {

            ChamadoEntity chamado = chamadoEncontrado.get();

            chamado.setTitulo(dados.getTitulo());
            chamado.setDescricao(dados.getDescricao());
            chamado.setPrioridade(dados.getPrioridade());
            chamado.setSolicitante(dados.getSolicitante());
            chamado.setStatus(dados.getStatus());

            return Optional.of(repository.save(chamado));
        }

        return Optional.empty();
    }

    public boolean deletar(Integer id) {

        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }

        return false;
    }
}
