package  ma.zs.gestion_service_pediatrie.dao.criteria.core.dossie;



import ma.zs.gestion_service_pediatrie.zynerator.criteria.BaseCriteria;
import java.util.List;

public class AntecedentCriteria extends  BaseCriteria  {

    private String ref;
    private String refLike;
    private String allergie;
    private String allergieLike;
    private String etat_psy;
    private String etat_psyLike;
    private String respiratoire;
    private String respiratoireLike;
    private String alimentation;
    private String alimentationLike;
    private String mouvement;
    private String mouvementLike;
    private String sommeil;
    private String sommeilLike;

    private GroupeSanguinCriteria groupeSanguin ;
    private List<GroupeSanguinCriteria> groupeSanguins ;


    public AntecedentCriteria(){}

    public String getRef(){
        return this.ref;
    }
    public void setRef(String ref){
        this.ref = ref;
    }
    public String getRefLike(){
        return this.refLike;
    }
    public void setRefLike(String refLike){
        this.refLike = refLike;
    }

    public String getAllergie(){
        return this.allergie;
    }
    public void setAllergie(String allergie){
        this.allergie = allergie;
    }
    public String getAllergieLike(){
        return this.allergieLike;
    }
    public void setAllergieLike(String allergieLike){
        this.allergieLike = allergieLike;
    }

    public String getEtat_psy(){
        return this.etat_psy;
    }
    public void setEtat_psy(String etat_psy){
        this.etat_psy = etat_psy;
    }
    public String getEtat_psyLike(){
        return this.etat_psyLike;
    }
    public void setEtat_psyLike(String etat_psyLike){
        this.etat_psyLike = etat_psyLike;
    }

    public String getRespiratoire(){
        return this.respiratoire;
    }
    public void setRespiratoire(String respiratoire){
        this.respiratoire = respiratoire;
    }
    public String getRespiratoireLike(){
        return this.respiratoireLike;
    }
    public void setRespiratoireLike(String respiratoireLike){
        this.respiratoireLike = respiratoireLike;
    }

    public String getAlimentation(){
        return this.alimentation;
    }
    public void setAlimentation(String alimentation){
        this.alimentation = alimentation;
    }
    public String getAlimentationLike(){
        return this.alimentationLike;
    }
    public void setAlimentationLike(String alimentationLike){
        this.alimentationLike = alimentationLike;
    }

    public String getMouvement(){
        return this.mouvement;
    }
    public void setMouvement(String mouvement){
        this.mouvement = mouvement;
    }
    public String getMouvementLike(){
        return this.mouvementLike;
    }
    public void setMouvementLike(String mouvementLike){
        this.mouvementLike = mouvementLike;
    }

    public String getSommeil(){
        return this.sommeil;
    }
    public void setSommeil(String sommeil){
        this.sommeil = sommeil;
    }
    public String getSommeilLike(){
        return this.sommeilLike;
    }
    public void setSommeilLike(String sommeilLike){
        this.sommeilLike = sommeilLike;
    }


    public GroupeSanguinCriteria getGroupeSanguin(){
        return this.groupeSanguin;
    }

    public void setGroupeSanguin(GroupeSanguinCriteria groupeSanguin){
        this.groupeSanguin = groupeSanguin;
    }
    public List<GroupeSanguinCriteria> getGroupeSanguins(){
        return this.groupeSanguins;
    }

    public void setGroupeSanguins(List<GroupeSanguinCriteria> groupeSanguins){
        this.groupeSanguins = groupeSanguins;
    }
}
