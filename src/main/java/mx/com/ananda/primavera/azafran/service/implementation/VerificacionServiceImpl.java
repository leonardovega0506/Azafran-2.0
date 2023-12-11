package mx.com.ananda.primavera.azafran.service.implementation;

import mx.com.ananda.primavera.azafran.exception.ResourceNotFoundException;
import mx.com.ananda.primavera.azafran.model.dto.SurtidorDTO;
import mx.com.ananda.primavera.azafran.model.dto.VerificadorDTO;
import mx.com.ananda.primavera.azafran.model.entity.SurtidorModel;
import mx.com.ananda.primavera.azafran.model.entity.VerificadorModel;
import mx.com.ananda.primavera.azafran.repository.IVerificadorRepository;
import mx.com.ananda.primavera.azafran.response.SurtidorResponse;
import mx.com.ananda.primavera.azafran.response.VerificadorResponse;
import mx.com.ananda.primavera.azafran.service.interfaces.IVerificadorService;
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
public class VerificacionServiceImpl implements IVerificadorService {

    @Autowired
    private IVerificadorRepository iVerificador;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public VerificadorResponse getAllPersonal(int numPage, int sizePage, String orderBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage, sizePage, sort);
        Page<VerificadorModel> registros = iVerificador.findAll(pageable);
        List<VerificadorModel> registroList = registros.getContent();
        List<VerificadorDTO> contenido = registroList.stream().map(registro -> mapearDTOVerificador(registro)).collect(Collectors.toList());
        SurtidorResponse surtidorResponse = new SurtidorResponse();
        VerificadorResponse verificadorResponse = new VerificadorResponse();
        verificadorResponse.setContent(contenido);
        verificadorResponse.setNumPage(registros.getNumber());
        verificadorResponse.setSizePage(registros.getSize());
        verificadorResponse.setAllElements(registros.getTotalElements());
        verificadorResponse.setAllPage(registros.getTotalPages());
        verificadorResponse.setLast(registros.isLast());
        return verificadorResponse;
    }

    @Override
    public VerificadorDTO getPersonalByClave(String clavePersonal) {
        VerificadorModel verificadorTraido = iVerificador.findByClavePersonal(clavePersonal).orElseThrow(()->new ResourceNotFoundException("verificador","clave",clavePersonal));
        return mapearDTOVerificador(verificadorTraido);
    }

    @Override
    public VerificadorDTO getPersonalById(Long idPersonal) {
        VerificadorModel verificadorTraido = iVerificador.findById(idPersonal).orElseThrow(()->new ResourceNotFoundException("id","verificador",idPersonal));
        return null;
    }

    @Override
    public VerificadorDTO savePersonal(VerificadorDTO personalAlmacenDTO) {
        VerificadorModel verificadorNuevo = iVerificador.save(mapearEntidadverificador(personalAlmacenDTO));
        return mapearDTOVerificador(verificadorNuevo);
    }

    @Override
    public void updatePersonal(VerificadorDTO personalAlmacenDTO) {
        iVerificador.save(mapearEntidadverificador(personalAlmacenDTO));
    }

    @Override
    public void deletePersonalById(Long idPersonal) {
        iVerificador.deleteById(idPersonal);
    }

    private VerificadorDTO mapearDTOVerificador(VerificadorModel verificadorModel){
        VerificadorDTO verificadorDTO = modelMapper.map(verificadorModel,VerificadorDTO.class);
        return verificadorDTO;
    }

    private VerificadorModel mapearEntidadverificador(VerificadorDTO verificadorDTO){
        VerificadorModel verificadorModel = modelMapper.map(verificadorDTO,VerificadorModel.class);
        return verificadorModel;
    }
}
