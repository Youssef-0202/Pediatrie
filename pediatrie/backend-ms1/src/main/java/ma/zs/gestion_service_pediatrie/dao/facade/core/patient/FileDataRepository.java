package ma.zs.gestion_service_pediatrie.dao.facade.core.patient;

import ma.zs.gestion_service_pediatrie.bean.core.patient.FileData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface FileDataRepository extends JpaRepository<FileData,Integer> {
    Optional<FileData> findByName(String fileName);
}
