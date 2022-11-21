package mehmetari.enocademov1.business.concretes;

import mehmetari.enocademov1.business.abstracts.CompanyService;
import mehmetari.enocademov1.business.abstracts.WorkerService;
import mehmetari.enocademov1.core.WorkerModel;
import mehmetari.enocademov1.dataAccess.WorkerRepository;
import mehmetari.enocademov1.dtos.worker.request.WorkerRequest;
import mehmetari.enocademov1.dtos.worker.response.WorkerListResponse;
import mehmetari.enocademov1.dtos.worker.response.WorkerResponse;
import mehmetari.enocademov1.entity.Company;
import mehmetari.enocademov1.entity.Worker;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkerManager implements WorkerService {

    WorkerRepository workerRepository;
    CompanyService companyService;

    public WorkerManager(WorkerRepository workerRepository, CompanyService companyService) {
        this.workerRepository = workerRepository;
        this.companyService = companyService;
    }

    @Override
    public List<WorkerListResponse> getAll() {
        List<Worker> workerList = workerRepository.findAll();
        return workerList.stream().map(WorkerModel :: toWorkerListResponse).collect(Collectors.toList());
    }


    @Override
    public WorkerResponse getById(Long id) {
        Optional<Worker> worker = workerRepository.findById(id);

        return worker.map(WorkerModel :: toWorkerResponse).orElse(null);
    }

    @Override
    public WorkerResponse add(WorkerRequest workerRequest) {
        Worker worker = new Worker();
        worker.setFirstName(workerRequest.getFirstName());
        worker.setLastName(workerRequest.getLastName());
        Company company = companyService.getCompanyById(workerRequest.getCompanyId());
        if (Objects.nonNull(company)) {
            worker.setCompany(company);
        }
        return WorkerModel.toWorkerResponse(workerRepository.save(worker));
    }

    @Override
    public WorkerResponse update(Worker worker, Long id) {
        Optional<Worker> inDbWorker = workerRepository.findById(id);
        if (inDbWorker.isPresent()) {
            Worker worker1 = new Worker();
            worker1.setFirstName(worker.getFirstName());
            worker1.setLastName(worker.getLastName());
            worker1.setCompany(companyService.getCompanyById(worker.getCompany().getId()));
            return WorkerModel.toWorkerResponse(workerRepository.save(worker1));
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        workerRepository.deleteById(id);
    }
}
