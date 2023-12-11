package mx.com.ananda.primavera.azafran.service.interfaces;

import mx.com.ananda.primavera.azafran.model.dto.RegistroDTO;
import mx.com.ananda.primavera.azafran.response.RegistroResponse;

public interface IRegistroService {

    RegistroResponse getAllRegistros(int numPage, int sizePage, String orderBy, String sortDir);

    RegistroResponse getAllRegistroBySurtidor(int numPage, int sizedPage, String orderBy, String sortDir,Long idSurtidor);

    RegistroResponse getAllRegistroByVerificador(int numPage, int sizedPage, String orderBy, String sortDir,Long idVerificador);

    RegistroDTO getRegistroById(Long idRegistro);

    RegistroDTO saveRegistro(RegistroDTO registroDTO);

    void updateRegistro(RegistroDTO registroDTO);

    void deleteRegistroById(Long idRegistro);
}
