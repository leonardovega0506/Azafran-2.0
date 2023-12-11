package mx.com.ananda.primavera.azafran.service.interfaces;

import mx.com.ananda.primavera.azafran.model.dto.SurtidorDTO;
import mx.com.ananda.primavera.azafran.response.SurtidorResponse;

public interface ISurtidorService {

    SurtidorResponse getAllPersonal(int numPage, int sizePage, String orderby, String sortDir);

    SurtidorDTO getPersonalByClave(String clavePersonal);

    SurtidorDTO getPersonalById(Long idPersonal);

    SurtidorDTO savePersonal(SurtidorDTO personalAlmacenDTO);

    void updatePersonal(SurtidorDTO personalAlmacenDTO);

    void deletePersonalById(Long idPersonal);

}
