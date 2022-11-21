package mehmetari.enocademov1.controller;

import mehmetari.enocademov1.business.abstracts.CompanyService;
import mehmetari.enocademov1.dtos.company.request.CompanyRequest;
import mehmetari.enocademov1.dtos.company.response.CompanyListResponse;
import mehmetari.enocademov1.dtos.company.response.CompanyResponse;
import mehmetari.enocademov1.dtos.worker.response.WorkerListResponse;
import mehmetari.enocademov1.entity.Company;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<CompanyListResponse>> getAll() {
        List<CompanyListResponse> companyListResponses = companyService.getALl();
        if (companyListResponses.isEmpty()) {
            ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(companyListResponses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> getById(@PathVariable Long id){
        CompanyResponse companyResponse = companyService.findById(id);
        if (Objects.nonNull(companyResponse)) {
            return new ResponseEntity<>(companyResponse, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    public ResponseEntity<CompanyResponse> add(@RequestBody CompanyRequest companyRequest) {
        CompanyResponse companyResponse = companyService.add(companyRequest);
        if (Objects.nonNull(companyResponse)) {
            return new ResponseEntity<>(companyResponse, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponse> update(@RequestBody Company company,@PathVariable Long id) {
        CompanyResponse companyResponse = companyService.update(company, id);
        if (Objects.nonNull(companyResponse)) {
            return new ResponseEntity<>(companyResponse, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<List<WorkerListResponse>> getWorkerListCompanyId(@PathVariable Long id) {
        List<WorkerListResponse> workerListResponseList = companyService.getCompanyWorkerList(id);
        if (Objects.nonNull(workerListResponseList)) {
            return new ResponseEntity<>(workerListResponseList, HttpStatus.OK);
        }
        return ResponseEntity.noContent().build();
    }

}
