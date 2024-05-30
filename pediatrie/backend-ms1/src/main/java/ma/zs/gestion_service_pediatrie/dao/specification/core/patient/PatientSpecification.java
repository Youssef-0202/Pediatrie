package  ma.zs.gestion_service_pediatrie.dao.specification.core.patient;

import ma.zs.gestion_service_pediatrie.dao.criteria.core.patient.PatientCriteria;
import ma.zs.gestion_service_pediatrie.bean.core.patient.Patient;
import ma.zs.gestion_service_pediatrie.zynerator.specification.AbstractSpecification;


public class PatientSpecification extends  AbstractSpecification<PatientCriteria, Patient>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("numDossier", criteria.getNumDossier(),criteria.getNumDossierLike());
        addPredicate("nom", criteria.getNom(),criteria.getNomLike());
        addPredicate("prenom", criteria.getPrenom(),criteria.getPrenomLike());
        addPredicate("dateNaissance", criteria.getDateNaissance(), criteria.getDateNaissanceFrom(), criteria.getDateNaissanceTo());
        addPredicate("photoProfil", criteria.getPhotoProfil(),criteria.getPhotoProfilLike());
        addPredicateFk("sexe","id", criteria.getSexe()==null?null:criteria.getSexe().getId());
        addPredicateFk("sexe","id", criteria.getSexes());
        addPredicateFk("sexe","ref", criteria.getSexe()==null?null:criteria.getSexe().getRef());
    }

    public PatientSpecification(PatientCriteria criteria) {
        super(criteria);
    }

    public PatientSpecification(PatientCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
