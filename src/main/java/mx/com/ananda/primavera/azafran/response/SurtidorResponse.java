package mx.com.ananda.primavera.azafran.response;

import lombok.Data;
import mx.com.ananda.primavera.azafran.model.dto.RegistroDTO;
import mx.com.ananda.primavera.azafran.model.dto.SurtidorDTO;

import java.util.List;

@Data
public class SurtidorResponse {
    private List<SurtidorDTO> content;
    private int numPage;
    private int sizePage;
    private long allElements;
    private int allPage;
    private boolean last;
}
