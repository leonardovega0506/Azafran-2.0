package mx.com.ananda.primavera.azafran.service.implementation;

import mx.com.ananda.primavera.azafran.exception.ResourceNotFoundException;
import mx.com.ananda.primavera.azafran.model.dto.RegistroDTO;
import mx.com.ananda.primavera.azafran.model.dto.SurtidorDTO;
import mx.com.ananda.primavera.azafran.model.entity.RegistroModel;
import mx.com.ananda.primavera.azafran.model.entity.SurtidorModel;
import mx.com.ananda.primavera.azafran.repository.ISurtidorRepository;
import mx.com.ananda.primavera.azafran.response.RegistroResponse;
import mx.com.ananda.primavera.azafran.response.SurtidorResponse;
import mx.com.ananda.primavera.azafran.service.interfaces.ISurtidorService;
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
public class SurtidorServiceImpl implements ISurtidorService {

    @Autowired
    private ISurtidorRepository iSurtidor;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public SurtidorResponse getAllPersonal(int numPage, int sizePage, String orderBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage, sizePage, sort);
        Page<SurtidorModel> registros = iSurtidor.findAll(pageable);
        List<SurtidorModel> registroList = registros.getContent();
        List<SurtidorDTO> contenido = registroList.stream().map(registro -> mapearDTOSurtidor(registro)).collect(Collectors.toList());
        SurtidorResponse surtidorResponse = new SurtidorResponse();
        surtidorResponse.setContent(contenido);
        surtidorResponse.setNumPage(registros.getNumber());
        surtidorResponse.setSizePage(registros.getSize());
        surtidorResponse.setAllElements(registros.getTotalElements());
        surtidorResponse.setAllPage(registros.getTotalPages());
        surtidorResponse.setLast(registros.isLast());
        return surtidorResponse;
    }

    @Override
    public SurtidorDTO getPersonalByClave(String clavePersonal) {
        SurtidorModel surtidorTraido = iSurtidor.findByClavePersonal(clavePersonal).orElseThrow(()->new ResourceNotFoundException("clave","Personal",clavePersonal));
        return mapearDTOSurtidor(surtidorTraido);
    }

    @Override
    public SurtidorDTO getPersonalById(Long idPersonal) {
        SurtidorModel surtidorTraido = iSurtidor.findById(idPersonal).orElseThrow(()->new ResourceNotFoundException("id","personal",idPersonal));
        return null;
    }

    @Override
    public SurtidorDTO savePersonal(SurtidorDTO personalAlmacenDTO) {
        SurtidorModel surtidorNuevo = iSurtidor.save(mapearEntidadSurtidor(personalAlmacenDTO));
        return mapearDTOSurtidor(surtidorNuevo);
    }

    @Override
    public void updatePersonal(SurtidorDTO personalAlmacenDTO) {
         iSurtidor.save(mapearEntidadSurtidor(personalAlmacenDTO));

    }

    @Override
    public void deletePersonalById(Long idPersonal) {
        iSurtidor.deleteById(idPersonal);
    }

    private SurtidorDTO mapearDTOSurtidor(SurtidorModel surtidorModel){
        SurtidorDTO surtidorDTO = modelMapper.map(surtidorModel,SurtidorDTO.class);
        return surtidorDTO;
    }

    private SurtidorModel mapearEntidadSurtidor(SurtidorDTO surtidorDTO){
        SurtidorModel surtidorModel = modelMapper.map(surtidorDTO,SurtidorModel.class);
        return surtidorModel;
    }
}
