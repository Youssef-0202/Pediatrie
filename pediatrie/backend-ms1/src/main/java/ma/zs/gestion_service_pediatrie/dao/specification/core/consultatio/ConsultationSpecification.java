package  ma.zs.gestion_service_pediatrie.dao.specification.core.consultatio;

import ma.zs.gestion_service_pediatrie.dao.criteria.core.consultatio.ConsultationCriteria;
import ma.zs.gestion_service_pediatrie.bean.core.consultatio.Consultation;
import ma.zs.gestion_service_pediatrie.zynerator.specification.AbstractSpecification;


public class ConsultationSpecification extends  AbstractSpecification<ConsultationCriteria, Consultation>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("ref", criteria.getRef(),criteria.getRefLike());
        addPredicate("dateConsultation", criteria.getDateConsultation(), criteria.getDateConsultationFrom(), criteria.getDateConsultationTo());
        addPredicate("heureConsultation", criteria.getHeureConsultation(), criteria.getHeureConsultationFrom(), criteria.getHeureConsultationTo());
        addPredicate("typeConsultation", criteria.getTypeConsultation(),criteria.getTypeConsultationLike());
        addPredicateFk("medecin","id", criteria.getMedecin()==null?null:criteria.getMedecin().getId());
        addPredicateFk("medecin","id", criteria.getMedecins());
        addPredicateFk("medecin","ref", criteria.getMedecin()==null?null:criteria.getMedecin().getRef());
        addPredicateFk("infermier","id", criteria.getInfermier()==null?null:criteria.getInfermier().getId());
        addPredicateFk("infermier","id", criteria.getInfermiers());
        addPredicateFk("infermier","ref", criteria.getInfermier()==null?null:criteria.getInfermier().getRef());
        addPredicateFk("patient","id", criteria.getPatient()==null?null:criteria.getPatient().getId());
        addPredicateFk("patient","id", criteria.getPatients());
        addPredicateFk("patient","numDossier", criteria.getPatient()==null?null:criteria.getPatient().getNumDossier());
    }

    public ConsultationSpecification(ConsultationCriteria criteria) {
        super(criteria);
    }

    public ConsultationSpecification(ConsultationCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
