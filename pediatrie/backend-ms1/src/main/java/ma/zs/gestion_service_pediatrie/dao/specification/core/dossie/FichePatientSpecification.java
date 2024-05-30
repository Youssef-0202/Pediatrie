package  ma.zs.gestion_service_pediatrie.dao.specification.core.dossie;

import ma.zs.gestion_service_pediatrie.dao.criteria.core.dossie.FichePatientCriteria;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.FichePatient;
import ma.zs.gestion_service_pediatrie.zynerator.specification.AbstractSpecification;


public class FichePatientSpecification extends  AbstractSpecification<FichePatientCriteria, FichePatient>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("ref", criteria.getRef(),criteria.getRefLike());
        addPredicate("dateFichePatient", criteria.getDateFichePatient(), criteria.getDateFichePatientFrom(), criteria.getDateFichePatientTo());
        addPredicate("antecedentFamillial", criteria.getAntecedentFamillial(),criteria.getAntecedentFamillialLike());
        addPredicate("histoireMaladie", criteria.getHistoireMaladie(),criteria.getHistoireMaladieLike());
        addPredicate("examenFichePatient", criteria.getExamenFichePatient(),criteria.getExamenFichePatientLike());
        addPredicate("conclusionFichePatient", criteria.getConclusionFichePatient(),criteria.getConclusionFichePatientLike());
        addPredicateFk("antecedent","id", criteria.getAntecedent()==null?null:criteria.getAntecedent().getId());
        addPredicateFk("antecedent","id", criteria.getAntecedents());
        addPredicateFk("antecedent","ref", criteria.getAntecedent()==null?null:criteria.getAntecedent().getRef());
        addPredicateFk("consultation","id", criteria.getConsultation()==null?null:criteria.getConsultation().getId());
        addPredicateFk("consultation","id", criteria.getConsultations());
        addPredicateFk("consultation","ref", criteria.getConsultation()==null?null:criteria.getConsultation().getRef());
    }

    public FichePatientSpecification(FichePatientCriteria criteria) {
        super(criteria);
    }

    public FichePatientSpecification(FichePatientCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
