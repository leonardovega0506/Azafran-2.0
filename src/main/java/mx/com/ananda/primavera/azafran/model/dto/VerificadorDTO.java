package mx.com.ananda.primavera.azafran.model.dto;

import lombok.Data;
import mx.com.ananda.primavera.azafran.model.entity.PickingModel;

import java.util.List;

@Data
public class VerificadorDTO {

    private Long idPersonal;
    private String nombrePersona;
    private String estatusPersonal;
    private String clavePersonal;
    private List<PickingModel> listaPicking;
}
