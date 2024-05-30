package  ma.zs.gestion_service_pediatrie.dao.criteria.core.gestio;


import ma.zs.gestion_service_pediatrie.dao.criteria.core.consultatio.ConsultationCriteria;

import ma.zs.gestion_service_pediatrie.zynerator.criteria.BaseCriteria;
import java.util.List;

public class TraitementCriteria extends  BaseCriteria  {

    private String ref;
    private String refLike;
    private String imageOrdonnance;
    private String imageOrdonnanceLike;

    private OrdonnanceCriteria ordonnance ;
    private List<OrdonnanceCriteria> ordonnances ;
    private ConsultationCriteria consultatuin ;
    private List<ConsultationCriteria> consultatuins ;


    public TraitementCriteria(){}

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

    public String getImageOrdonnance(){
        return this.imageOrdonnance;
    }
    public void setImageOrdonnance(String imageOrdonnance){
        this.imageOrdonnance = imageOrdonnance;
    }
    public String getImageOrdonnanceLike(){
        return this.imageOrdonnanceLike;
    }
    public void setImageOrdonnanceLike(String imageOrdonnanceLike){
        this.imageOrdonnanceLike = imageOrdonnanceLike;
    }


    public OrdonnanceCriteria getOrdonnance(){
        return this.ordonnance;
    }

    public void setOrdonnance(OrdonnanceCriteria ordonnance){
        this.ordonnance = ordonnance;
    }
    public List<OrdonnanceCriteria> getOrdonnances(){
        return this.ordonnances;
    }

    public void setOrdonnances(List<OrdonnanceCriteria> ordonnances){
        this.ordonnances = ordonnances;
    }
    public ConsultationCriteria getConsultatuin(){
        return this.consultatuin;
    }

    public void setConsultatuin(ConsultationCriteria consultatuin){
        this.consultatuin = consultatuin;
    }
    public List<ConsultationCriteria> getConsultatuins(){
        return this.consultatuins;
    }

    public void setConsultatuins(List<ConsultationCriteria> consultatuins){
        this.consultatuins = consultatuins;
    }
}
