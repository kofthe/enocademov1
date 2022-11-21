package mehmetari.enocademov1.business.abstracts;

import mehmetari.enocademov1.dtos.worker.request.WorkerRequest;
import mehmetari.enocademov1.dtos.worker.response.WorkerListResponse;
import mehmetari.enocademov1.dtos.worker.response.WorkerResponse;
import mehmetari.enocademov1.entity.Worker;

import java.util.List;

public interface WorkerService {

    List<WorkerListResponse> getAll();

    WorkerResponse getById(Long id);

    WorkerResponse add(WorkerRequest workerRequest);

    WorkerResponse update(Worker worker, Long id);

    void delete(Long id);

}
