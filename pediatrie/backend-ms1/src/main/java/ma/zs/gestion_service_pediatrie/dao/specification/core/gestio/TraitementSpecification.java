package  ma.zs.gestion_service_pediatrie.dao.specification.core.gestio;

import ma.zs.gestion_service_pediatrie.dao.criteria.core.gestio.TraitementCriteria;
import ma.zs.gestion_service_pediatrie.bean.core.gestio.Traitement;
import ma.zs.gestion_service_pediatrie.zynerator.specification.AbstractSpecification;


public class TraitementSpecification extends  AbstractSpecification<TraitementCriteria, Traitement>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("ref", criteria.getRef(),criteria.getRefLike());
        addPredicate("imageOrdonnance", criteria.getImageOrdonnance(),criteria.getImageOrdonnanceLike());
        addPredicateFk("ordonnance","id", criteria.getOrdonnance()==null?null:criteria.getOrdonnance().getId());
        addPredicateFk("ordonnance","id", criteria.getOrdonnances());
        addPredicateFk("ordonnance","ref", criteria.getOrdonnance()==null?null:criteria.getOrdonnance().getRef());
        addPredicateFk("consultatuin","id", criteria.getConsultatuin()==null?null:criteria.getConsultatuin().getId());
        addPredicateFk("consultatuin","id", criteria.getConsultatuins());
        addPredicateFk("consultatuin","ref", criteria.getConsultatuin()==null?null:criteria.getConsultatuin().getRef());
    }

    public TraitementSpecification(TraitementCriteria criteria) {
        super(criteria);
    }

    public TraitementSpecification(TraitementCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
