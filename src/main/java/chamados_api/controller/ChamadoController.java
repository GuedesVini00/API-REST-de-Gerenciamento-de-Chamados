package chamados_api.controller;

import chamados_api.model.ChamadoEntity;
import chamados_api.service.ChamadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chamados")
@RequiredArgsConstructor
public class ChamadoController {

    private final ChamadoService service;

    @PostMapping
    public ResponseEntity<ChamadoEntity> cadastrar(@RequestBody ChamadoEntity chamado) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.cadastrar(chamado));
    }

    @GetMapping
    public ResponseEntity<List<ChamadoEntity>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChamadoEntity> buscarPorId(@PathVariable Integer id) {

        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChamadoEntity> atualizar(
            @PathVariable Integer id,
            @RequestBody ChamadoEntity chamado) {

        return service.atualizar(id, chamado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {

        if (service.deletar(id)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}

