package mx.com.ananda.primavera.azafran.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tbl_personal")
public class PersonalAlmacenModel {

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

    @Column(name = "tipo_personal")
    private String tipoPersonal;

    @OneToMany(mappedBy = "personal",cascade = CascadeType.ALL)
    private List<PickingModel> listaPicking;
}
