package  ma.zs.gestion_service_pediatrie.dao.specification.core.commun;

import ma.zs.gestion_service_pediatrie.dao.criteria.core.commun.MedecinCriteria;
import ma.zs.gestion_service_pediatrie.bean.core.commun.Medecin;
import ma.zs.gestion_service_pediatrie.zynerator.specification.AbstractSpecification;


public class MedecinSpecification extends  AbstractSpecification<MedecinCriteria, Medecin>  {

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
        addPredicate("speciality", criteria.getSpeciality(),criteria.getSpecialityLike());
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

    public MedecinSpecification(MedecinCriteria criteria) {
        super(criteria);
    }

    public MedecinSpecification(MedecinCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
