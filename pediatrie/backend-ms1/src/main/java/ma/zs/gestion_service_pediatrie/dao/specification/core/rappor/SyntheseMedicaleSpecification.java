package  ma.zs.gestion_service_pediatrie.dao.specification.core.rappor;

import ma.zs.gestion_service_pediatrie.dao.criteria.core.rappor.SyntheseMedicaleCriteria;
import ma.zs.gestion_service_pediatrie.bean.core.rappor.SyntheseMedicale;
import ma.zs.gestion_service_pediatrie.zynerator.specification.AbstractSpecification;


public class SyntheseMedicaleSpecification extends  AbstractSpecification<SyntheseMedicaleCriteria, SyntheseMedicale>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("ref", criteria.getRef(),criteria.getRefLike());
        addPredicate("dateSyntheseMedicale", criteria.getDateSyntheseMedicale(), criteria.getDateSyntheseMedicaleFrom(), criteria.getDateSyntheseMedicaleTo());
        addPredicate("description", criteria.getDescription(),criteria.getDescriptionLike());
        addPredicateFk("consultation","id", criteria.getConsultation()==null?null:criteria.getConsultation().getId());
        addPredicateFk("consultation","id", criteria.getConsultations());
        addPredicateFk("consultation","ref", criteria.getConsultation()==null?null:criteria.getConsultation().getRef());
    }

    public SyntheseMedicaleSpecification(SyntheseMedicaleCriteria criteria) {
        super(criteria);
    }

    public SyntheseMedicaleSpecification(SyntheseMedicaleCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
