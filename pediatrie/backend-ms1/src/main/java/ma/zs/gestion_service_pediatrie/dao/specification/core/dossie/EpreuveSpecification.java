package  ma.zs.gestion_service_pediatrie.dao.specification.core.dossie;

import ma.zs.gestion_service_pediatrie.dao.criteria.core.dossie.EpreuveCriteria;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.Epreuve;
import ma.zs.gestion_service_pediatrie.zynerator.specification.AbstractSpecification;


public class EpreuveSpecification extends  AbstractSpecification<EpreuveCriteria, Epreuve>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("ref", criteria.getRef(),criteria.getRefLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public EpreuveSpecification(EpreuveCriteria criteria) {
        super(criteria);
    }

    public EpreuveSpecification(EpreuveCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
