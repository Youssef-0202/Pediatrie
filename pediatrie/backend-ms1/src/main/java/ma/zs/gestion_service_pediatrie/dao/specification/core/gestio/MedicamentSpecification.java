package  ma.zs.gestion_service_pediatrie.dao.specification.core.gestio;

import ma.zs.gestion_service_pediatrie.dao.criteria.core.gestio.MedicamentCriteria;
import ma.zs.gestion_service_pediatrie.bean.core.gestio.Medicament;
import ma.zs.gestion_service_pediatrie.zynerator.specification.AbstractSpecification;


public class MedicamentSpecification extends  AbstractSpecification<MedicamentCriteria, Medicament>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("ref", criteria.getRef(),criteria.getRefLike());
        addPredicate("sointProduit", criteria.getSointProduit(),criteria.getSointProduitLike());
        addPredicate("duree", criteria.getDuree(),criteria.getDureeLike());
        addPredicate("consigne", criteria.getConsigne(),criteria.getConsigneLike());
        addPredicateFk("traitement","id", criteria.getTraitement()==null?null:criteria.getTraitement().getId());
        addPredicateFk("traitement","id", criteria.getTraitements());
        addPredicateFk("traitement","ref", criteria.getTraitement()==null?null:criteria.getTraitement().getRef());
    }

    public MedicamentSpecification(MedicamentCriteria criteria) {
        super(criteria);
    }

    public MedicamentSpecification(MedicamentCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
