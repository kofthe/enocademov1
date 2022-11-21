package mehmetari.enocademov1.dataAccess;

import mehmetari.enocademov1.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
