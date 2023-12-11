package mx.com.ananda.primavera.azafran.service.interfaces;

import mx.com.ananda.primavera.azafran.model.dto.PickingDTO;
import mx.com.ananda.primavera.azafran.response.PickingResponse;

import java.time.LocalDate;

public interface IPickingService {

    PickingResponse getAllPicking(int numPage, int sizePage, String orderBy, String sortDir);

    PickingResponse getAllPickingByDate(int numPage, int sizePage, String orderBy, String sortDir, LocalDate date);

    PickingDTO getPickingByEntry(Long absEntry);

    PickingDTO getPickingById(Long idPicking);

    PickingDTO assingPickingSurtidor(String claveSurtidor,Long idPicking);

    PickingDTO assingPickingVerificador(String claveVerificador,Long idPicking);
}
