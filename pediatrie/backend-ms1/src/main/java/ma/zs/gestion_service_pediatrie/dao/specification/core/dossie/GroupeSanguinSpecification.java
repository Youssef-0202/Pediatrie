package  ma.zs.gestion_service_pediatrie.dao.specification.core.dossie;

import ma.zs.gestion_service_pediatrie.dao.criteria.core.dossie.GroupeSanguinCriteria;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.GroupeSanguin;
import ma.zs.gestion_service_pediatrie.zynerator.specification.AbstractSpecification;


public class GroupeSanguinSpecification extends  AbstractSpecification<GroupeSanguinCriteria, GroupeSanguin>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("ref", criteria.getRef(),criteria.getRefLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public GroupeSanguinSpecification(GroupeSanguinCriteria criteria) {
        super(criteria);
    }

    public GroupeSanguinSpecification(GroupeSanguinCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
