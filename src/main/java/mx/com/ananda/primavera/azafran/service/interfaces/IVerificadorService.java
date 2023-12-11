package mx.com.ananda.primavera.azafran.service.interfaces;

import mx.com.ananda.primavera.azafran.model.dto.SurtidorDTO;
import mx.com.ananda.primavera.azafran.model.dto.VerificadorDTO;
import mx.com.ananda.primavera.azafran.response.SurtidorResponse;
import mx.com.ananda.primavera.azafran.response.VerificadorResponse;

public interface IVerificadorService {

    VerificadorResponse getAllPersonal(int numPage, int sizePage, String orderby, String sortDir);

    VerificadorDTO getPersonalByClave(String clavePersonal);

    VerificadorDTO getPersonalById(Long idPersonal);

    VerificadorDTO savePersonal(VerificadorDTO personalAlmacenDTO);

    void updatePersonal(VerificadorDTO personalAlmacenDTO);

    void deletePersonalById(Long idPersonal);
}
