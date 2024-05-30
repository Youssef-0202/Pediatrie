package  ma.zs.gestion_service_pediatrie.dao.specification.core.rappor;

import ma.zs.gestion_service_pediatrie.dao.criteria.core.rappor.DiagnosticCriteria;
import ma.zs.gestion_service_pediatrie.bean.core.rappor.Diagnostic;
import ma.zs.gestion_service_pediatrie.zynerator.specification.AbstractSpecification;


public class DiagnosticSpecification extends  AbstractSpecification<DiagnosticCriteria, Diagnostic>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("ref", criteria.getRef(),criteria.getRefLike());
        addPredicate("dateDiagnostic", criteria.getDateDiagnostic(), criteria.getDateDiagnosticFrom(), criteria.getDateDiagnosticTo());
        addPredicate("description", criteria.getDescription(),criteria.getDescriptionLike());
        addPredicateFk("consultation","id", criteria.getConsultation()==null?null:criteria.getConsultation().getId());
        addPredicateFk("consultation","id", criteria.getConsultations());
        addPredicateFk("consultation","ref", criteria.getConsultation()==null?null:criteria.getConsultation().getRef());
    }

    public DiagnosticSpecification(DiagnosticCriteria criteria) {
        super(criteria);
    }

    public DiagnosticSpecification(DiagnosticCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
