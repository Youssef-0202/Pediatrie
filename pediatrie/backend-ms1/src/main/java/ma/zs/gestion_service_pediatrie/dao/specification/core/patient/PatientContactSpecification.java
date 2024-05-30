package  ma.zs.gestion_service_pediatrie.dao.specification.core.patient;

import ma.zs.gestion_service_pediatrie.dao.criteria.core.patient.PatientContactCriteria;
import ma.zs.gestion_service_pediatrie.bean.core.patient.PatientContact;
import ma.zs.gestion_service_pediatrie.zynerator.specification.AbstractSpecification;


public class PatientContactSpecification extends  AbstractSpecification<PatientContactCriteria, PatientContact>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("cin", criteria.getCin(),criteria.getCinLike());
        addPredicate("email", criteria.getEmail(),criteria.getEmailLike());
        addPredicate("nom", criteria.getNom(),criteria.getNomLike());
        addPredicate("prenom", criteria.getPrenom(),criteria.getPrenomLike());
        addPredicate("telephone", criteria.getTelephone(),criteria.getTelephoneLike());
        addPredicate("adresse", criteria.getAdresse(),criteria.getAdresseLike());
        addPredicateFk("relation","id", criteria.getRelation()==null?null:criteria.getRelation().getId());
        addPredicateFk("relation","id", criteria.getRelations());
        addPredicateFk("relation","ref", criteria.getRelation()==null?null:criteria.getRelation().getRef());
        addPredicateFk("patient","id", criteria.getPatient()==null?null:criteria.getPatient().getId());
        addPredicateFk("patient","id", criteria.getPatients());
        addPredicateFk("patient","numDossier", criteria.getPatient()==null?null:criteria.getPatient().getNumDossier());
    }

    public PatientContactSpecification(PatientContactCriteria criteria) {
        super(criteria);
    }

    public PatientContactSpecification(PatientContactCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
