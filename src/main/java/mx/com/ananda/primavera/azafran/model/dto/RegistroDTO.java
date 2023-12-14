package mx.com.ananda.primavera.azafran.model.dto;


import lombok.Data;
import mx.com.ananda.primavera.azafran.model.entity.SurtidorModel;
import mx.com.ananda.primavera.azafran.model.entity.VerificadorModel;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class RegistroDTO {


    private Long idRegistro;
    private int docNum;
    private LocalDate dateSurtir;
    private LocalTime timeSurtir;
    private LocalDate fechaInicioSurtido;
    private LocalTime horafechaInicioSurtido;
    private LocalDate fechaTerminoSurtido;
    private LocalTime horaTerminoSurtido;
    private String estatusRegistro;
    private LocalDate fechaInicioVerificado;
    private LocalTime horaInicioVerificado;
    private LocalDate fechaTerminoVerificado;
    private LocalTime horaTerminoVerificado;
    private SurtidorModel surtidor;
    private VerificadorModel verificador;
}
