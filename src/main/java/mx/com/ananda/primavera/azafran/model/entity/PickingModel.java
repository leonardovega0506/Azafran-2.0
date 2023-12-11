package mx.com.ananda.primavera.azafran.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "tbl_picking")
public class PickingModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_picking")
    private Long idPicking;

    @Column(name = "abs_entry")
    private Long absEntry;

    @Column(name = "fecha_picking")
    private LocalDate pickDate;

    @Column(name = "hora_pciking")
    private LocalTime pickTime;

    @Column(name = "estatus_OV")
    private String estatus;

    @OneToOne
    @JoinColumn(name = "registro")
    private RegistroModel registro;

    @ManyToOne
    @JoinColumn(name = "surtidor")
    private SurtidorModel surtidor;

    @ManyToOne
    @JoinColumn(name = "verificador")
    private VerificadorModel verificador;
}
