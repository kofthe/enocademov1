package mehmetari.enocademov1.business.abstracts;

import mehmetari.enocademov1.dtos.company.request.CompanyRequest;
import mehmetari.enocademov1.dtos.company.response.CompanyListResponse;
import mehmetari.enocademov1.dtos.company.response.CompanyResponse;
import mehmetari.enocademov1.dtos.worker.response.WorkerListResponse;
import mehmetari.enocademov1.entity.Company;

import java.util.List;

public interface CompanyService {

    List<CompanyListResponse> getALl();

    CompanyResponse findById(Long id);

    CompanyResponse add(CompanyRequest companyRequest);

    CompanyResponse update(Company company, Long id);

    void delete(Long id);

    CompanyResponse toCompanyResponse(Company company);

    CompanyListResponse toCompanyListResponse(Company company);

    Company getCompanyById(Long id);

    List<WorkerListResponse> getCompanyWorkerList(Long id);



}
