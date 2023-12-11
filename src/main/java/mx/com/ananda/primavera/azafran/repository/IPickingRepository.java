package mx.com.ananda.primavera.azafran.repository;

import mx.com.ananda.primavera.azafran.model.entity.PickingModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IPickingRepository extends JpaRepository<PickingModel,Long> {
    List<PickingModel> findByVerificador_ClavePersonal(String clavePersonal);
    List<PickingModel> findBySurtidor_ClavePersonal(String clavePersonal);
    Optional<PickingModel> findByAbsEntry(Long absEntry);
    Page<PickingModel> findByPickDate(LocalDate pickDate, Pageable pageable);
}
