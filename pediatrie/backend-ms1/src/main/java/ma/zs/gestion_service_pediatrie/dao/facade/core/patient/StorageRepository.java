package ma.zs.gestion_service_pediatrie.dao.facade.core.patient;



import ma.zs.gestion_service_pediatrie.bean.core.patient.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StorageRepository extends JpaRepository<ImageData,Long> {


    Optional<ImageData> findByName(String fileName);
}
