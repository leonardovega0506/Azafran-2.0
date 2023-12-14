package mx.com.ananda.primavera.azafran.controller;

import mx.com.ananda.primavera.azafran.model.dto.PickingDTO;
import mx.com.ananda.primavera.azafran.response.PickingResponse;
import mx.com.ananda.primavera.azafran.service.interfaces.IPickingService;
import mx.com.ananda.primavera.azafran.util.GlobalConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("ananda/primavera/azafran/picking")
public class PickingController {

    @Autowired
    private IPickingService sPicking;

    @GetMapping
    public ResponseEntity<PickingResponse> obtenerPicking(
            @RequestParam(value = "pageNo",defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idPicking") String orderBy,
            @RequestParam(value = "sortDir",defaultValue =  GlobalConstants.ORDENRAR_DIRECCION_DEFECTO) String sortDir
    ){
        return new ResponseEntity<>(sPicking.getAllPicking(pageNo,pageSize,orderBy,sortDir), HttpStatus.OK);
    }

    @GetMapping("/fecha")
    public ResponseEntity<PickingResponse> obtenerPickingFecha(
            @RequestParam(value = "pageNo",defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idPicking") String orderBy,
            @RequestParam(value = "sortDir",defaultValue =  GlobalConstants.ORDENRAR_DIRECCION_DEFECTO) String sortDir,
            @RequestParam(value = "date", defaultValue = "LocalDate.now()") LocalDate fecha
    ){
        return new ResponseEntity<>(sPicking.getAllPickingByDate(pageNo,pageSize,orderBy,sortDir,fecha),HttpStatus.OK);
    }

    @GetMapping("/absEntry")
    public ResponseEntity<PickingDTO> obtenerPickingAbsEntry(
            @RequestParam(value = "absEntry") Long absEntry
    ){
        return new ResponseEntity<>(sPicking.getPickingByEntry(absEntry),HttpStatus.OK);
    }

    @GetMapping("/{idPicking}")
    public ResponseEntity<PickingDTO> obtenerPickingByd(@PathVariable(value = "idPicking") Long idPicking){
        return new ResponseEntity<>(sPicking.getPickingById(idPicking),HttpStatus.OK);
    }

    @PostMapping("/verificador")
    public ResponseEntity<PickingDTO> asignarVerificadorPicking(
            @RequestParam(value = "claveVerificador") String claveVerificador,
            @RequestParam(value = "absEntry") Long absEntry
    ){
      return new ResponseEntity<>(sPicking.assingPickingVerificador(claveVerificador,absEntry),HttpStatus.CREATED);
    }

    @PostMapping("/surtidor")
    public ResponseEntity<PickingDTO> asignarSurtidorPicking(
            @RequestParam(value = "claveSurtidor") String claveSurtidor,
            @RequestParam(value = "absEntry") Long absEntry
    ){
        return new ResponseEntity<>(sPicking.assingPickingSurtidor(claveSurtidor,absEntry),HttpStatus.CREATED);
    }
}
