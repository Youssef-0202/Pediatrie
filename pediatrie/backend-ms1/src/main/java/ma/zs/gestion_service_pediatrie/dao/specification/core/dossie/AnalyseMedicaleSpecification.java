package  ma.zs.gestion_service_pediatrie.dao.specification.core.dossie;

import ma.zs.gestion_service_pediatrie.dao.criteria.core.dossie.AnalyseMedicaleCriteria;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.AnalyseMedicale;
import ma.zs.gestion_service_pediatrie.zynerator.specification.AbstractSpecification;


public class AnalyseMedicaleSpecification extends  AbstractSpecification<AnalyseMedicaleCriteria, AnalyseMedicale>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("ref", criteria.getRef(),criteria.getRefLike());
        addPredicate("dateAnalyseMedicale", criteria.getDateAnalyseMedicale(), criteria.getDateAnalyseMedicaleFrom(), criteria.getDateAnalyseMedicaleTo());
        addPredicateBigDecimal("valeur", criteria.getValeur(), criteria.getValeurMin(), criteria.getValeurMax());
        addPredicate("valeurRang", criteria.getValeurRang(),criteria.getValeurRangLike());
        addPredicateFk("epreuve","id", criteria.getEpreuve()==null?null:criteria.getEpreuve().getId());
        addPredicateFk("epreuve","id", criteria.getEpreuves());
        addPredicateFk("epreuve","ref", criteria.getEpreuve()==null?null:criteria.getEpreuve().getRef());
        addPredicateFk("consultation","id", criteria.getConsultation()==null?null:criteria.getConsultation().getId());
        addPredicateFk("consultation","id", criteria.getConsultations());
        addPredicateFk("consultation","ref", criteria.getConsultation()==null?null:criteria.getConsultation().getRef());
    }

    public AnalyseMedicaleSpecification(AnalyseMedicaleCriteria criteria) {
        super(criteria);
    }

    public AnalyseMedicaleSpecification(AnalyseMedicaleCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
