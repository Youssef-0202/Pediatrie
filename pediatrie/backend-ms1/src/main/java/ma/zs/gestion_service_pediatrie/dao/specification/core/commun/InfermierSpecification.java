package  ma.zs.gestion_service_pediatrie.dao.specification.core.commun;

import ma.zs.gestion_service_pediatrie.dao.criteria.core.commun.InfermierCriteria;
import ma.zs.gestion_service_pediatrie.bean.core.commun.Infermier;
import ma.zs.gestion_service_pediatrie.zynerator.specification.AbstractSpecification;


public class InfermierSpecification extends  AbstractSpecification<InfermierCriteria, Infermier>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("ref", criteria.getRef(),criteria.getRefLike());
        addPredicate("email", criteria.getEmail(),criteria.getEmailLike());
        addPredicate("nom", criteria.getNom(),criteria.getNomLike());
        addPredicate("prenom", criteria.getPrenom(),criteria.getPrenomLike());
        addPredicate("dateNaissance", criteria.getDateNaissance(), criteria.getDateNaissanceFrom(), criteria.getDateNaissanceTo());
        addPredicate("telephone", criteria.getTelephone(),criteria.getTelephoneLike());
        addPredicate("photoProfil", criteria.getPhotoProfil(),criteria.getPhotoProfilLike());
        addPredicateBool("credentialsNonExpired", criteria.getCredentialsNonExpired());
        addPredicateBool("enabled", criteria.getEnabled());
        addPredicateBool("accountNonExpired", criteria.getAccountNonExpired());
        addPredicateBool("accountNonLocked", criteria.getAccountNonLocked());
        addPredicateBool("passwordChanged", criteria.getPasswordChanged());
        addPredicate("username", criteria.getUsername(),criteria.getUsernameLike());
        addPredicate("password", criteria.getPassword(),criteria.getPasswordLike());
        addPredicateFk("sexe","id", criteria.getSexe()==null?null:criteria.getSexe().getId());
        addPredicateFk("sexe","id", criteria.getSexes());
        addPredicateFk("sexe","ref", criteria.getSexe()==null?null:criteria.getSexe().getRef());
    }

    public InfermierSpecification(InfermierCriteria criteria) {
        super(criteria);
    }

    public InfermierSpecification(InfermierCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
