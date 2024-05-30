package  ma.zs.gestion_service_pediatrie.dao.specification.core.dossie;

import ma.zs.gestion_service_pediatrie.dao.criteria.core.dossie.AntecedentCriteria;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.Antecedent;
import ma.zs.gestion_service_pediatrie.zynerator.specification.AbstractSpecification;


public class AntecedentSpecification extends  AbstractSpecification<AntecedentCriteria, Antecedent>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("ref", criteria.getRef(),criteria.getRefLike());
        addPredicate("allergie", criteria.getAllergie(),criteria.getAllergieLike());
        addPredicate("etat_psy", criteria.getEtat_psy(),criteria.getEtat_psyLike());
        addPredicate("respiratoire", criteria.getRespiratoire(),criteria.getRespiratoireLike());
        addPredicate("alimentation", criteria.getAlimentation(),criteria.getAlimentationLike());
        addPredicate("mouvement", criteria.getMouvement(),criteria.getMouvementLike());
        addPredicate("sommeil", criteria.getSommeil(),criteria.getSommeilLike());
        addPredicateFk("groupeSanguin","id", criteria.getGroupeSanguin()==null?null:criteria.getGroupeSanguin().getId());
        addPredicateFk("groupeSanguin","id", criteria.getGroupeSanguins());
        addPredicateFk("groupeSanguin","ref", criteria.getGroupeSanguin()==null?null:criteria.getGroupeSanguin().getRef());
    }

    public AntecedentSpecification(AntecedentCriteria criteria) {
        super(criteria);
    }

    public AntecedentSpecification(AntecedentCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
