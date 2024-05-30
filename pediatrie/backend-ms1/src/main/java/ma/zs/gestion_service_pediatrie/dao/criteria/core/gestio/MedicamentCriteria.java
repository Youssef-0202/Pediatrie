package  ma.zs.gestion_service_pediatrie.dao.criteria.core.gestio;



import ma.zs.gestion_service_pediatrie.zynerator.criteria.BaseCriteria;
import java.util.List;

public class MedicamentCriteria extends  BaseCriteria  {

    private String ref;
    private String refLike;
    private String sointProduit;
    private String sointProduitLike;
    private String duree;
    private String dureeLike;
    private String consigne;
    private String consigneLike;

    private TraitementCriteria traitement ;
    private List<TraitementCriteria> traitements ;


    public MedicamentCriteria(){}

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

    public String getSointProduit(){
        return this.sointProduit;
    }
    public void setSointProduit(String sointProduit){
        this.sointProduit = sointProduit;
    }
    public String getSointProduitLike(){
        return this.sointProduitLike;
    }
    public void setSointProduitLike(String sointProduitLike){
        this.sointProduitLike = sointProduitLike;
    }

    public String getDuree(){
        return this.duree;
    }
    public void setDuree(String duree){
        this.duree = duree;
    }
    public String getDureeLike(){
        return this.dureeLike;
    }
    public void setDureeLike(String dureeLike){
        this.dureeLike = dureeLike;
    }

    public String getConsigne(){
        return this.consigne;
    }
    public void setConsigne(String consigne){
        this.consigne = consigne;
    }
    public String getConsigneLike(){
        return this.consigneLike;
    }
    public void setConsigneLike(String consigneLike){
        this.consigneLike = consigneLike;
    }


    public TraitementCriteria getTraitement(){
        return this.traitement;
    }

    public void setTraitement(TraitementCriteria traitement){
        this.traitement = traitement;
    }
    public List<TraitementCriteria> getTraitements(){
        return this.traitements;
    }

    public void setTraitements(List<TraitementCriteria> traitements){
        this.traitements = traitements;
    }
}
