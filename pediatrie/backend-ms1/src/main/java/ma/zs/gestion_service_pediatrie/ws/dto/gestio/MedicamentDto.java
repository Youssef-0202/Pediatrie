package  ma.zs.gestion_service_pediatrie.ws.dto.gestio;

import ma.zs.gestion_service_pediatrie.zynerator.audit.Log;
import ma.zs.gestion_service_pediatrie.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;





@JsonInclude(JsonInclude.Include.NON_NULL)
public class MedicamentDto  extends AuditBaseDto {

    private String ref  ;
    private String sointProduit  ;
    private String duree  ;
    private String consigne  ;

    private TraitementDto traitement ;



    public MedicamentDto(){
        super();
    }



    @Log
    public String getRef(){
        return this.ref;
    }
    public void setRef(String ref){
        this.ref = ref;
    }

    @Log
    public String getSointProduit(){
        return this.sointProduit;
    }
    public void setSointProduit(String sointProduit){
        this.sointProduit = sointProduit;
    }

    @Log
    public String getDuree(){
        return this.duree;
    }
    public void setDuree(String duree){
        this.duree = duree;
    }

    @Log
    public String getConsigne(){
        return this.consigne;
    }
    public void setConsigne(String consigne){
        this.consigne = consigne;
    }


    public TraitementDto getTraitement(){
        return this.traitement;
    }

    public void setTraitement(TraitementDto traitement){
        this.traitement = traitement;
    }






}
