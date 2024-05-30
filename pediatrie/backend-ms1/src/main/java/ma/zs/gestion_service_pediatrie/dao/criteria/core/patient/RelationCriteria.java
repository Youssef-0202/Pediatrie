package  ma.zs.gestion_service_pediatrie.dao.criteria.core.patient;



import ma.zs.gestion_service_pediatrie.zynerator.criteria.BaseCriteria;
import java.util.List;

public class RelationCriteria extends  BaseCriteria  {

    private String ref;
    private String refLike;
    private String libelle;
    private String libelleLike;



    public RelationCriteria(){}

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

    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }
    public String getLibelleLike(){
        return this.libelleLike;
    }
    public void setLibelleLike(String libelleLike){
        this.libelleLike = libelleLike;
    }


}
