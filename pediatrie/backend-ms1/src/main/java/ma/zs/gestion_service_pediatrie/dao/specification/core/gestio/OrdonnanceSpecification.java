package  ma.zs.gestion_service_pediatrie.dao.specification.core.gestio;

import ma.zs.gestion_service_pediatrie.dao.criteria.core.gestio.OrdonnanceCriteria;
import ma.zs.gestion_service_pediatrie.bean.core.gestio.Ordonnance;
import ma.zs.gestion_service_pediatrie.zynerator.specification.AbstractSpecification;


public class OrdonnanceSpecification extends  AbstractSpecification<OrdonnanceCriteria, Ordonnance>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("ref", criteria.getRef(),criteria.getRefLike());
        addPredicate("nomHospital", criteria.getNomHospital(),criteria.getNomHospitalLike());
        addPredicate("dateOrdonnance", criteria.getDateOrdonnance(), criteria.getDateOrdonnanceFrom(), criteria.getDateOrdonnanceTo());
        addPredicate("adresseHospitla", criteria.getAdresseHospitla(),criteria.getAdresseHospitlaLike());
        addPredicate("signature", criteria.getSignature(),criteria.getSignatureLike());
        addPredicateFk("traitement","id", criteria.getTraitement()==null?null:criteria.getTraitement().getId());
        addPredicateFk("traitement","id", criteria.getTraitements());
        addPredicateFk("traitement","ref", criteria.getTraitement()==null?null:criteria.getTraitement().getRef());
        addPredicateFk("consultatuin","id", criteria.getConsultatuin()==null?null:criteria.getConsultatuin().getId());
        addPredicateFk("consultatuin","id", criteria.getConsultatuins());
        addPredicateFk("consultatuin","ref", criteria.getConsultatuin()==null?null:criteria.getConsultatuin().getRef());
    }

    public OrdonnanceSpecification(OrdonnanceCriteria criteria) {
        super(criteria);
    }

    public OrdonnanceSpecification(OrdonnanceCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
