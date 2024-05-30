package  ma.zs.gestion_service_pediatrie.dao.specification.core.commun;

import ma.zs.gestion_service_pediatrie.dao.criteria.core.commun.SexeCriteria;
import ma.zs.gestion_service_pediatrie.bean.core.commun.Sexe;
import ma.zs.gestion_service_pediatrie.zynerator.specification.AbstractSpecification;


public class SexeSpecification extends  AbstractSpecification<SexeCriteria, Sexe>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("ref", criteria.getRef(),criteria.getRefLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public SexeSpecification(SexeCriteria criteria) {
        super(criteria);
    }

    public SexeSpecification(SexeCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
