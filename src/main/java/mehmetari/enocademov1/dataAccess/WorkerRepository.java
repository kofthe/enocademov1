package mehmetari.enocademov1.dataAccess;

import mehmetari.enocademov1.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
}
