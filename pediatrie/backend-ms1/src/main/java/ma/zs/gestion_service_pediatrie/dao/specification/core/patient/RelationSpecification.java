package  ma.zs.gestion_service_pediatrie.dao.specification.core.patient;

import ma.zs.gestion_service_pediatrie.dao.criteria.core.patient.RelationCriteria;
import ma.zs.gestion_service_pediatrie.bean.core.patient.Relation;
import ma.zs.gestion_service_pediatrie.zynerator.specification.AbstractSpecification;


public class RelationSpecification extends  AbstractSpecification<RelationCriteria, Relation>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("ref", criteria.getRef(),criteria.getRefLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public RelationSpecification(RelationCriteria criteria) {
        super(criteria);
    }

    public RelationSpecification(RelationCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
