package mx.com.ananda.primavera.azafran.repository;

import mx.com.ananda.primavera.azafran.model.entity.RegistroModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRegistroRepository extends JpaRepository<RegistroModel,Long> {
    Page<RegistroModel> findByVerificador_IdPersonal(Long idPersonal, Pageable pageable);

    Page<RegistroModel> findBySurtidor_IdPersonal(Long idPersonal, Pageable pageable);
}
