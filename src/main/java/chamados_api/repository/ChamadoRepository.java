package chamados_api.repository;


import chamados_api.model.ChamadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<ChamadoEntity, Integer> {
}

