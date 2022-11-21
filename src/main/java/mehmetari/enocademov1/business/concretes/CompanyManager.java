package mehmetari.enocademov1.business.concretes;

import mehmetari.enocademov1.business.abstracts.CompanyService;
import mehmetari.enocademov1.core.WorkerModel;
import mehmetari.enocademov1.dataAccess.CompanyRepository;
import mehmetari.enocademov1.dtos.company.request.CompanyRequest;
import mehmetari.enocademov1.dtos.company.response.CompanyListResponse;
import mehmetari.enocademov1.dtos.company.response.CompanyResponse;
import mehmetari.enocademov1.dtos.worker.response.WorkerListResponse;
import mehmetari.enocademov1.entity.Company;
import mehmetari.enocademov1.entity.Worker;
import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyManager implements CompanyService {

    CompanyRepository companyRepository;

    public CompanyManager(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<CompanyListResponse> getALl() {
        return companyRepository.findAll()
                .stream()
                .map(company -> toCompanyListResponse(company))
                .collect(Collectors.toList());
    }

    @Override
    public CompanyResponse findById(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        return company
                .map(company1 -> toCompanyResponse(company1))
                .orElse(null);
    }

    @Override
    public CompanyResponse add(CompanyRequest companyRequest) {
        Company company = new Company();
        company.setName(companyRequest.getName());
        return toCompanyResponse(companyRepository.save(company));
    }

    @Override
    public CompanyResponse update(Company company, Long id) {
        Optional<Company> inDbCompany = companyRepository.findById(id);
        if (inDbCompany.isPresent()) {
            Company company1 = inDbCompany.get();
            company1.setName(company.getName());
            return toCompanyResponse(companyRepository.save(company1));
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        companyRepository.deleteById(id);
    }

    @Override
    public CompanyResponse toCompanyResponse(Company company) {
        CompanyResponse companyResponse = new CompanyResponse();
        companyResponse.setId(company.getId());
        companyResponse.setName(company.getName());
        return companyResponse;
    }

    @Override
    public CompanyListResponse toCompanyListResponse(Company company) {
        CompanyListResponse companyListResponse = new CompanyListResponse();
        companyListResponse.setId(company.getId());
        companyListResponse.setName(company.getName());

        return companyListResponse;
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).get();
    }

    @Override
    public List<WorkerListResponse> getCompanyWorkerList(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        List<WorkerListResponse> workerListResponses = new ArrayList<>();
        if (company.isPresent()) {
            List<Worker> workerList = company.get().getWorkers();
            for (Worker worker : workerList) {
                WorkerListResponse workerListResponse = WorkerModel.toWorkerListResponse(worker);
                workerListResponses.add(workerListResponse);
            }
            return workerListResponses;
        }
        return null;
    }
}
