package ma.zs.gestion_service_pediatrie.zynerator.service;

import ma.zs.gestion_service_pediatrie.zynerator.bean.BaseEntity;
import ma.zs.gestion_service_pediatrie.zynerator.criteria.BaseCriteria;
import ma.zs.gestion_service_pediatrie.zynerator.repository.AbstractRepository;

public abstract class AbstractReferentielServiceImpl<T extends BaseEntity, CRITERIA extends BaseCriteria, REPO extends AbstractRepository<T, Long>> extends AbstractServiceImpl<T, CRITERIA, REPO> {

    public AbstractReferentielServiceImpl(REPO dao) {
        super(dao);
    }

}
