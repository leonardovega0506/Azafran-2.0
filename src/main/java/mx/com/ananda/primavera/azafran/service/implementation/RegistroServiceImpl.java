package mx.com.ananda.primavera.azafran.service.implementation;

import mx.com.ananda.primavera.azafran.exception.ResourceNotFoundException;
import mx.com.ananda.primavera.azafran.model.dto.PickingDTO;
import mx.com.ananda.primavera.azafran.model.dto.RegistroDTO;
import mx.com.ananda.primavera.azafran.model.entity.PickingModel;
import mx.com.ananda.primavera.azafran.model.entity.RegistroModel;
import mx.com.ananda.primavera.azafran.repository.IRegistroRepository;
import mx.com.ananda.primavera.azafran.response.PickingResponse;
import mx.com.ananda.primavera.azafran.response.RegistroResponse;
import mx.com.ananda.primavera.azafran.service.interfaces.IRegistroService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistroServiceImpl implements IRegistroService {

    @Autowired
    private IRegistroRepository iRegistro;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RegistroResponse getAllRegistros(int numPage, int sizePage, String orderBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage, sizePage, sort);
        Page<RegistroModel> registros = iRegistro.findAll(pageable);
        List<RegistroModel> registroList = registros.getContent();
        List<RegistroDTO> contenido = registroList.stream().map(registro -> mapearDTO(registro)).collect(Collectors.toList());
        RegistroResponse registroResponse = new RegistroResponse();
        registroResponse.setContent(contenido);
        registroResponse.setNumPage(registros.getNumber());
        registroResponse.setSizePage(registros.getSize());
        registroResponse.setAllElements(registros.getTotalElements());
        registroResponse.setAllPage(registros.getTotalPages());
        registroResponse.setLast(registros.isLast());
        return registroResponse;
    }

    @Override
    public RegistroResponse getAllRegistroBySurtidor(int numPage, int sizedPage, String orderBy, String sortDir, Long idSurtidor) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage, sizedPage, sort);
        Page<RegistroModel> registros = iRegistro.findBySurtidor_IdPersonal(idSurtidor,pageable);
        List<RegistroModel> registroList = registros.getContent();
        List<RegistroDTO> contenido = registroList.stream().map(registro -> mapearDTO(registro)).collect(Collectors.toList());
        RegistroResponse registroResponse = new RegistroResponse();
        registroResponse.setContent(contenido);
        registroResponse.setNumPage(registros.getNumber());
        registroResponse.setSizePage(registros.getSize());
        registroResponse.setAllElements(registros.getTotalElements());
        registroResponse.setAllPage(registros.getTotalPages());
        registroResponse.setLast(registros.isLast());
        return registroResponse;
    }

    @Override
    public RegistroResponse getAllRegistroByVerificador(int numPage, int sizedPage, String orderBy, String sortDir,Long idVerificador) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage, sizedPage, sort);
        Page<RegistroModel> registros = iRegistro.findByVerificador_IdPersonal(idVerificador,pageable);
        List<RegistroModel> registroList = registros.getContent();
        List<RegistroDTO> contenido = registroList.stream().map(registro -> mapearDTO(registro)).collect(Collectors.toList());
        RegistroResponse registroResponse = new RegistroResponse();
        registroResponse.setContent(contenido);
        registroResponse.setNumPage(registros.getNumber());
        registroResponse.setSizePage(registros.getSize());
        registroResponse.setAllElements(registros.getTotalElements());
        registroResponse.setAllPage(registros.getTotalPages());
        registroResponse.setLast(registros.isLast());
        return registroResponse;
    }

    @Override
    public RegistroDTO getRegistroById(Long idRegistro) {
        RegistroModel registroTraido = iRegistro.findById(idRegistro).orElseThrow(()->new ResourceNotFoundException("Registro","id",idRegistro));
        return mapearDTO(registroTraido);
    }

    @Override
    public RegistroDTO saveRegistro(RegistroDTO registroDTO) {
        RegistroModel registroNuevo = iRegistro.save(mapearEntidad(registroDTO));
        return mapearDTO(registroNuevo);
    }

    @Override
    public void updateRegistro(RegistroDTO registroDTO) {
        iRegistro.save(mapearEntidad(registroDTO));
    }

    @Override
    public void deleteRegistroById(Long idRegistro) {
        iRegistro.deleteById(idRegistro);
    }

    private RegistroDTO mapearDTO(RegistroModel registroModel){
        RegistroDTO registroDTO = modelMapper.map(registroModel,RegistroDTO.class);
        return registroDTO;
    }

    private RegistroModel mapearEntidad(RegistroDTO registroDTO){
        RegistroModel registroModel = modelMapper.map(registroDTO, RegistroModel.class);
        return registroModel;
    }
}
