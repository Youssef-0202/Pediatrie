package  ma.zs.gestion_service_pediatrie.dao.specification.core.gestio;

import ma.zs.gestion_service_pediatrie.dao.criteria.core.gestio.CertificatCriteria;
import ma.zs.gestion_service_pediatrie.bean.core.gestio.Certificat;
import ma.zs.gestion_service_pediatrie.zynerator.specification.AbstractSpecification;


public class CertificatSpecification extends  AbstractSpecification<CertificatCriteria, Certificat>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("ref", criteria.getRef(),criteria.getRefLike());
        addPredicate("dateDebut", criteria.getDateDebut(), criteria.getDateDebutFrom(), criteria.getDateDebutTo());
        addPredicate("dateFin", criteria.getDateFin(), criteria.getDateFinFrom(), criteria.getDateFinTo());
        addPredicate("description", criteria.getDescription(),criteria.getDescriptionLike());
        addPredicateInt("nbrJour", criteria.getNbrJour(), criteria.getNbrJourMin(), criteria.getNbrJourMax());
        addPredicateFk("consultatuin","id", criteria.getConsultatuin()==null?null:criteria.getConsultatuin().getId());
        addPredicateFk("consultatuin","id", criteria.getConsultatuins());
        addPredicateFk("consultatuin","ref", criteria.getConsultatuin()==null?null:criteria.getConsultatuin().getRef());
    }

    public CertificatSpecification(CertificatCriteria criteria) {
        super(criteria);
    }

    public CertificatSpecification(CertificatCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
