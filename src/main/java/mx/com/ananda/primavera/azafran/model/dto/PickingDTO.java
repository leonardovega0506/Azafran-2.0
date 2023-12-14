package mx.com.ananda.primavera.azafran.model.dto;


import lombok.Data;
import mx.com.ananda.primavera.azafran.model.entity.RegistroModel;
import mx.com.ananda.primavera.azafran.model.entity.SurtidorModel;
import mx.com.ananda.primavera.azafran.model.entity.VerificadorModel;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class PickingDTO {

    private Long idPicking;
    private Long absEntry;
    private LocalDate pickDate;
    private LocalTime pickTime;
    private String estatus;
    private RegistroModel registro;
    private SurtidorModel surtidor;
    private VerificadorModel verificador;
}
