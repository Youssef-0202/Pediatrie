package  ma.zs.gestion_service_pediatrie.dao.specification.core.dossie;

import ma.zs.gestion_service_pediatrie.dao.criteria.core.dossie.TypeImageCriteria;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.TypeImage;
import ma.zs.gestion_service_pediatrie.zynerator.specification.AbstractSpecification;


public class TypeImageSpecification extends  AbstractSpecification<TypeImageCriteria, TypeImage>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("ref", criteria.getRef(),criteria.getRefLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public TypeImageSpecification(TypeImageCriteria criteria) {
        super(criteria);
    }

    public TypeImageSpecification(TypeImageCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
