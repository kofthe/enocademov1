package mehmetari.enocademov1.controller;


import mehmetari.enocademov1.business.abstracts.WorkerService;
import mehmetari.enocademov1.dtos.worker.request.WorkerRequest;
import mehmetari.enocademov1.dtos.worker.response.WorkerListResponse;
import mehmetari.enocademov1.dtos.worker.response.WorkerResponse;
import mehmetari.enocademov1.entity.Company;
import mehmetari.enocademov1.entity.Worker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/worker")
public class WorkerController {
    WorkerService workerService;
    CompanyController companyController;

    public WorkerController(WorkerService workerService, CompanyController companyController) {
        this.workerService = workerService;
        this.companyController = companyController;
    }

    @GetMapping("/listall")
    public ResponseEntity<List<WorkerListResponse>> getAll() {
        List<WorkerListResponse> workerListResponses = workerService.getAll();
        if (workerListResponses.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(workerListResponses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkerResponse> getById(@PathVariable Long id){
        WorkerResponse workerResponse = workerService.getById(id);
        if (Objects.nonNull(workerResponse)) {
            return new ResponseEntity<>(workerResponse, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }
    @PostMapping
    public ResponseEntity<WorkerResponse> addWorker(@RequestBody WorkerRequest workerRequest) {
        WorkerResponse workerResponse = workerService.add(workerRequest);
        if (Objects.nonNull(workerResponse)){
            return new ResponseEntity<>(workerResponse, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<WorkerResponse> update(@RequestBody Worker worker,@PathVariable Long id){
        WorkerResponse workerResponse = workerService.update(worker, id);
        if (Objects.nonNull(workerResponse)) {
            return new ResponseEntity<>(workerResponse, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();

    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        workerService.delete(id);
    }
}
