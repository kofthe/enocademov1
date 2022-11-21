package mehmetari.enocademov1.dtos.worker.response;

import lombok.Data;
import mehmetari.enocademov1.dtos.company.response.CompanyResponse;

import java.util.List;

@Data
public class WorkerResponse {

    private long id;
    private String firstName;
    private String lastName;
    private List<CompanyResponse> companyId;
    private Long companyById;

}
