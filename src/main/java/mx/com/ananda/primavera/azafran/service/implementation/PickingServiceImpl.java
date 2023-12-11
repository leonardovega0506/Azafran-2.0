package mx.com.ananda.primavera.azafran.service.implementation;

import mx.com.ananda.primavera.azafran.exception.ResourceNotFoundException;
import mx.com.ananda.primavera.azafran.model.dto.PickingDTO;
import mx.com.ananda.primavera.azafran.model.entity.PickingModel;
import mx.com.ananda.primavera.azafran.model.entity.RegistroModel;
import mx.com.ananda.primavera.azafran.model.entity.SurtidorModel;
import mx.com.ananda.primavera.azafran.model.entity.VerificadorModel;
import mx.com.ananda.primavera.azafran.repository.IPickingRepository;
import mx.com.ananda.primavera.azafran.repository.ISurtidorRepository;
import mx.com.ananda.primavera.azafran.repository.IVerificadorRepository;
import mx.com.ananda.primavera.azafran.response.PickingResponse;
import mx.com.ananda.primavera.azafran.service.interfaces.IPickingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PickingServiceImpl implements IPickingService {

    @Autowired
    private IPickingRepository iPicking;

    @Autowired
    private ISurtidorRepository iSurtidor;

    @Autowired
    private IVerificadorRepository iVerificador;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PickingResponse getAllPicking(int numPage, int sizePage, String orderBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage, sizePage, sort);
        Page<PickingModel> pickings = iPicking.findAll(pageable);
        List<PickingModel> pickingList = pickings.getContent();
        List<PickingDTO> contenido = pickingList.stream().map(picking -> mapearDTOEntidad(picking)).collect(Collectors.toList());
        PickingResponse pickingResponse = new PickingResponse();
        pickingResponse.setContent(contenido);
        pickingResponse.setNumPage(pickings.getNumber());
        pickingResponse.setSizePage(pickings.getSize());
        pickingResponse.setAllElements(pickings.getTotalElements());
        pickingResponse.setAllPage(pickings.getTotalPages());
        pickingResponse.setLast(pickings.isLast());
        return pickingResponse;
    }

    @Override
    public PickingResponse getAllPickingByDate(int numPage, int sizePage, String orderBy, String sortDir, LocalDate date) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage, sizePage, sort);
        Page<PickingModel> pickings = iPicking.findByPickDate(date, pageable);
        List<PickingModel> pickingList = pickings.getContent();
        List<PickingDTO> contenido = pickingList.stream().map(picking -> mapearDTOEntidad(picking)).collect(Collectors.toList());
        PickingResponse pickingResponse = new PickingResponse();
        pickingResponse.setContent(contenido);
        pickingResponse.setNumPage(pickings.getNumber());
        pickingResponse.setSizePage(pickings.getSize());
        pickingResponse.setAllElements(pickings.getTotalElements());
        pickingResponse.setAllPage(pickings.getTotalPages());
        pickingResponse.setLast(pickings.isLast());
        return pickingResponse;
    }

    @Override
    public PickingDTO getPickingByEntry(Long absEntry) {
        PickingModel pickingTraido = iPicking.findByAbsEntry(absEntry).orElseGet(() -> {
            return null;
        });
        return mapearDTOEntidad(pickingTraido);
    }

    @Override
    public PickingDTO getPickingById(Long idPicking) {
        PickingModel pickingTraido = iPicking.findById(idPicking).orElseThrow(() -> new ResourceNotFoundException("Picking", "id", idPicking));
        return mapearDTOEntidad(pickingTraido);
    }

    @Override
    public PickingDTO assingPickingSurtidor(String claveSurtidor, Long absEntry) {
        List<PickingModel> listaPickingModel = iPicking.findBySurtidor_ClavePersonal(claveSurtidor);
        SurtidorModel surtidorTraido = iSurtidor.findByClavePersonal(claveSurtidor).orElseThrow(() -> new ResourceNotFoundException("clave", "Surtidor", claveSurtidor));
        PickingModel pickingTraido = iPicking.findByAbsEntry(absEntry).orElseGet(() -> {
            return null;
        });
        pickingTraido.setSurtidor(surtidorTraido);
        iPicking.save(pickingTraido);
        listaPickingModel.add(pickingTraido);
        surtidorTraido.setListaPicking(listaPickingModel);
        iSurtidor.save(surtidorTraido);
        return mapearDTOEntidad(pickingTraido);
    }

    @Override
    public PickingDTO assingPickingVerificador(String claveVerificador, Long absEntry) {
        List<PickingModel> listaPickingModel = iPicking.findByVerificador_ClavePersonal(claveVerificador);
        VerificadorModel verificadorTraido = iVerificador.findByClavePersonal(claveVerificador).orElseThrow(() -> new ResourceNotFoundException("clave", "Verificador", claveVerificador));
        PickingModel pickingTraido = iPicking.findByAbsEntry(absEntry).orElseGet(() -> {
            return null;
        });
        pickingTraido.setVerificador(verificadorTraido);
        iPicking.save(pickingTraido);
        listaPickingModel.add(pickingTraido);
        verificadorTraido.setListaPicking(listaPickingModel);
        iVerificador.save(verificadorTraido);
        return mapearDTOEntidad(pickingTraido);
    }

    private PickingDTO mapearDTOEntidad(PickingModel pickingModel) {
        PickingDTO pickingDTO = modelMapper.map(pickingModel, PickingDTO.class);
        return pickingDTO;
    }

    private PickingModel mapearEntidadDTO(PickingDTO pickingDTO) {
        PickingModel pickingModel = modelMapper.map(pickingDTO, PickingModel.class);
        return pickingModel;
    }
}
