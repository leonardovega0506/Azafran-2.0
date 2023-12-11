package mx.com.ananda.primavera.azafran.response;

import lombok.Data;
import mx.com.ananda.primavera.azafran.model.dto.SurtidorDTO;
import mx.com.ananda.primavera.azafran.model.dto.VerificadorDTO;

import java.util.List;

@Data
public class VerificadorResponse {
    private List<VerificadorDTO> content;
    private int numPage;
    private int sizePage;
    private long allElements;
    private int allPage;
    private boolean last;
}
