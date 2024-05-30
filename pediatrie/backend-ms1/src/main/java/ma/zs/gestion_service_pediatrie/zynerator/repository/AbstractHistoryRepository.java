package ma.zs.gestion_service_pediatrie.zynerator.repository;

import ma.zs.gestion_service_pediatrie.zynerator.bean.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractHistoryRepository<H extends BaseEntity, ID> extends JpaRepository<H, ID>, JpaSpecificationExecutor<H> {
}
