package mehmetari.enocademov1.dtos.company.response;

import lombok.Data;
import mehmetari.enocademov1.dtos.worker.response.WorkerListResponse;

import java.util.List;

@Data
public class CompanyResponse {

    private Long id;
    private String name;
    private List<WorkerListResponse> workerListResponseList;

}
