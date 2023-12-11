package mx.com.ananda.primavera.azafran.repository;

import mx.com.ananda.primavera.azafran.model.entity.VerificadorModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IVerificadorRepository extends JpaRepository<VerificadorModel,Long> {
    Optional<VerificadorModel> findByClavePersonal(String clavePersonal);
}
