package mx.com.ananda.primavera.azafran.repository;

import mx.com.ananda.primavera.azafran.model.entity.SurtidorModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ISurtidorRepository extends JpaRepository<SurtidorModel,Long> {
    Optional<SurtidorModel> findByClavePersonal(String clavePersonal);
}
