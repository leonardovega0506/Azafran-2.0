package mx.com.ananda.primavera.azafran.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "tbl_registro")
public class RegistroModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_registro")
    private Long idRegistro;

    @Column(name = "orden_venta")
    private int docNum;

    @Column(name = "fecha_surtir")
    private LocalDate dateSurtir;

    @Column(name = "hora_surtir")
    private LocalTime timeSurtir;

    @Column(name = "fecha_inicio_surtido")
    private LocalDate fechaInicioSurtido;

    @Column(name = "hora_inicio_surtido")
    private LocalTime horafechaInicioSurtido;

    @Column(name = "fecha_termino_surtido")
    private LocalDate fechaTerminoSurtido;

    @Column(name = "hora_termino_surtido")
    private LocalTime horaTerminoSurtido;

    @Column(name = "fecha_inicio_verificado")
    private LocalDate fechaInicioVerificado;

    @Column(name = "hora_inicio_verificado")
    private LocalTime horaInicioVerificado;

    @Column(name = "fecha_termino_verificado")
    private LocalDate fechaTerminoVerificado;

    @Column(name = "hora_termino_verificado")
    private LocalDate horaTerminoVerificado;

}
