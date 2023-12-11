package mx.com.ananda.primavera.azafran.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "tbl_surtidor")
public class SurtidorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_personal")
    private Long idPersonal;

    @Column(name = "nombre_personal")
    private String nombrePersona;

    @Column(name = "estatus_personal")
    private String estatusPersonal;

    @Column(name = "clave_personal")
    private String clavePersonal;

    @OneToMany(mappedBy = "personal",cascade = CascadeType.ALL)
    private List<PickingModel> listaPicking;
}
