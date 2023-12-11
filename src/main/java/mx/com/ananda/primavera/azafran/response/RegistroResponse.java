package mx.com.ananda.primavera.azafran.response;

import lombok.Data;
import mx.com.ananda.primavera.azafran.model.dto.PickingDTO;
import mx.com.ananda.primavera.azafran.model.dto.RegistroDTO;

import java.util.List;

@Data
public class RegistroResponse {
    private List<RegistroDTO> content;
    private int numPage;
    private int sizePage;
    private long allElements;
    private int allPage;
    private boolean last;
}
