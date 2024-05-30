package  ma.zs.gestion_service_pediatrie.dao.specification.core.dossie;

import ma.zs.gestion_service_pediatrie.dao.criteria.core.dossie.RadiologieCriteria;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.Radiologie;
import ma.zs.gestion_service_pediatrie.zynerator.specification.AbstractSpecification;


public class RadiologieSpecification extends  AbstractSpecification<RadiologieCriteria, Radiologie>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("ref", criteria.getRef(),criteria.getRefLike());
        addPredicate("dateRadiologie", criteria.getDateRadiologie(), criteria.getDateRadiologieFrom(), criteria.getDateRadiologieTo());
        addPredicate("commentaire", criteria.getCommentaire(),criteria.getCommentaireLike());
        addPredicate("imageScann", criteria.getImageScann(),criteria.getImageScannLike());
        addPredicateFk("consultation","id", criteria.getConsultation()==null?null:criteria.getConsultation().getId());
        addPredicateFk("consultation","id", criteria.getConsultations());
        addPredicateFk("consultation","ref", criteria.getConsultation()==null?null:criteria.getConsultation().getRef());
        addPredicateFk("typeImage","id", criteria.getTypeImage()==null?null:criteria.getTypeImage().getId());
        addPredicateFk("typeImage","id", criteria.getTypeImages());
        addPredicateFk("typeImage","ref", criteria.getTypeImage()==null?null:criteria.getTypeImage().getRef());
    }

    public RadiologieSpecification(RadiologieCriteria criteria) {
        super(criteria);
    }

    public RadiologieSpecification(RadiologieCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
