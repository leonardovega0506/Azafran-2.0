package mx.com.ananda.primavera.azafran.controller;

import lombok.extern.slf4j.Slf4j;
import mx.com.ananda.primavera.azafran.model.dto.RegistroDTO;
import mx.com.ananda.primavera.azafran.response.RegistroResponse;
import mx.com.ananda.primavera.azafran.service.interfaces.IRegistroService;
import mx.com.ananda.primavera.azafran.util.GlobalConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Slf4j
@RestController
@RequestMapping("ananda/primavera/azafran/registro")
public class RegistroController {

    @Autowired
    private IRegistroService sRegistro;

    @GetMapping
    public ResponseEntity<RegistroResponse> obtenerRegistros(
            @RequestParam(value = "pageNo",defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idRegistro") String orderBy,
            @RequestParam(value = "sortDir",defaultValue =  GlobalConstants.ORDENRAR_DIRECCION_DEFECTO) String sortDir
    ){
        return new ResponseEntity<>(sRegistro.getAllRegistros(pageNo,pageSize,orderBy,sortDir), HttpStatus.OK);
    }

    @GetMapping("/surtidor")
    public ResponseEntity<RegistroResponse> obtenerRegistrosBySurtidor(
            @RequestParam(value = "pageNo",defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idPicking") String orderBy,
            @RequestParam(value = "sortDir",defaultValue =  GlobalConstants.ORDENRAR_DIRECCION_DEFECTO) String sortDir,
            @RequestParam(value = "idSurtidor") Long idSurtidor
    ){
        return new ResponseEntity<>(sRegistro.getAllRegistroBySurtidor(pageNo,pageSize,orderBy,sortDir,idSurtidor),HttpStatus.OK);
    }

    @GetMapping("/verificador")
    public ResponseEntity<RegistroResponse> obtenerRegistrosByVerificador(
            @RequestParam(value = "pageNo",defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idPicking") String orderBy,
            @RequestParam(value = "sortDir",defaultValue =  GlobalConstants.ORDENRAR_DIRECCION_DEFECTO) String sortDir,
            @RequestParam(value = "idVerificador") Long idVerificador
    ){
        return new ResponseEntity<>(sRegistro.getAllRegistroByVerificador(pageNo,pageSize,orderBy,sortDir,idVerificador),HttpStatus.OK);
    }

    @GetMapping("/{idRegistro}")
    public ResponseEntity<RegistroDTO> obtenerRegistroById(@PathVariable(value = "idRegistro") Long idRegistro){
        return new ResponseEntity<>(sRegistro.getRegistroById(idRegistro),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RegistroDTO> guardarRegistro(@RequestBody RegistroDTO registroDTO){
        return new ResponseEntity<>(sRegistro.saveRegistro(registroDTO),HttpStatus.CREATED);
    }

    @PutMapping("/inicio_surtido")
    public ResponseEntity<String> generarTiempoInicioSurtido(@RequestBody RegistroDTO registroDTO) {
        LocalTime tiempo= LocalTime.now();
        LocalTime tiempoMostrado = tiempo.minus(1, ChronoUnit.HOURS);
        registroDTO.setFechaInicioSurtido(LocalDate.now());
        registroDTO.setEstatusRegistro("Inicio Surtido");
        registroDTO.setHorafechaInicioSurtido(tiempoMostrado);
        sRegistro.updateRegistro(registroDTO);
        return new ResponseEntity<>("Inicio Surtido",HttpStatus.OK);


    }

    @PutMapping("/termino_surtido")
    public ResponseEntity<String> generarTiempoTerminoSurtido(@RequestBody RegistroDTO registroDTO) {
        LocalTime tiempo= LocalTime.now();
        LocalTime tiempoMostrado = tiempo.minus(1, ChronoUnit.HOURS);
        registroDTO.setFechaTerminoSurtido(LocalDate.now());
        registroDTO.setHoraTerminoSurtido(tiempoMostrado);
        registroDTO.setEstatusRegistro("Termino surtir");
        sRegistro.updateRegistro(registroDTO);
        return new ResponseEntity<>("Termino Surtido",HttpStatus.OK);
    }



    @PutMapping("/inicio_verificado")
    public ResponseEntity<String> generarTiempoInicioVerificado(@RequestBody RegistroDTO registroDTO){
        LocalTime tiempo= LocalTime.now();
        LocalTime tiempoMostrado = tiempo.minus(1, ChronoUnit.HOURS);
        registroDTO.setEstatusRegistro("Inicio Verificado");
        registroDTO.setFechaInicioVerificado(LocalDate.now());
        registroDTO.setHoraInicioVerificado(tiempoMostrado);
        sRegistro.updateRegistro(registroDTO);
        return new ResponseEntity<>("Inicio Verificado",HttpStatus.OK);
    }

    @PutMapping("/termino_verificado")
    public ResponseEntity<String> generarTiempoTerminoVetrificado(@RequestBody RegistroDTO registroDTO){
        LocalTime tiempo= LocalTime.now();
        LocalTime tiempoMostrado = tiempo.minus(1, ChronoUnit.HOURS);
        registroDTO.setEstatusRegistro("Termino Verificado");
        registroDTO.setFechaTerminoVerificado(LocalDate.now());
        registroDTO.setHoraTerminoVerificado(tiempoMostrado);
        sRegistro.updateRegistro(registroDTO);
        return new ResponseEntity<>("Termino Verificado",HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> actualizarRegistro(@RequestBody RegistroDTO registroDTO){
        sRegistro.updateRegistro(registroDTO);
        return new ResponseEntity<>("Actualizado correctamente",HttpStatus.OK);
    }

    @DeleteMapping("/{idRegistro}")
    public ResponseEntity<String> eliminarRegistroById(@PathVariable(value = "idRegistro") Long idRegistro){
        sRegistro.deleteRegistroById(idRegistro);
        return new ResponseEntity<>("Eliminado correctamente",HttpStatus.OK);
    }
}
