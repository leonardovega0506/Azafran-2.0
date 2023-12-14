package mx.com.ananda.primavera.azafran.controller;

import mx.com.ananda.primavera.azafran.model.dto.SurtidorDTO;
import mx.com.ananda.primavera.azafran.response.SurtidorResponse;
import mx.com.ananda.primavera.azafran.service.interfaces.ISurtidorService;
import mx.com.ananda.primavera.azafran.util.GlobalConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ananda/primavera/azafran/surtidor")
public class SurtidorController {

    @Autowired
    private ISurtidorService sPersonal;

    @GetMapping
    public ResponseEntity<SurtidorResponse> obtenerPersonales(
            @RequestParam(value = "pageNo",defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idSurtidor") String orderBy,
            @RequestParam(value = "sortDir",defaultValue =  GlobalConstants.ORDENRAR_DIRECCION_DEFECTO) String sortDir

    ){
        return new ResponseEntity<>(sPersonal.getAllPersonal(pageNo,pageSize,orderBy,sortDir), HttpStatus.OK);
    }

    @GetMapping("/clave")
    public ResponseEntity<SurtidorDTO> obtenerPersonalyClave(@RequestParam String clavePersonal){
        return new ResponseEntity<>(sPersonal.getPersonalByClave(clavePersonal),HttpStatus.OK);
    }


    @GetMapping("/{idPersonal}")
    public ResponseEntity<SurtidorDTO> obtenerPersonalById(@PathVariable(value = "idPersonal") Long idPersonal){
        return new ResponseEntity<>(sPersonal.getPersonalById(idPersonal),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SurtidorDTO> guardarPersonal(@RequestBody SurtidorDTO surtidorDTO){
        return new ResponseEntity<>(sPersonal.savePersonal(surtidorDTO),HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> actualizarPersonal(@RequestBody SurtidorDTO surtidorDTO){
        sPersonal.updatePersonal(surtidorDTO);
        return new ResponseEntity<>("Actualizado correctamente",HttpStatus.OK);
    }

    @DeleteMapping("/{idPersonal}")
    public ResponseEntity<String> eliminarPersonaByID(@PathVariable(value = "idPersonal") Long idPersonal){
        sPersonal.deletePersonalById(idPersonal);
        return new ResponseEntity<>("Eliminado correctamente",HttpStatus.OK);
    }
}
