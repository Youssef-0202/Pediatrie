package  ma.zs.gestion_service_pediatrie.ws.dto.dossie;

import ma.zs.gestion_service_pediatrie.zynerator.audit.Log;
import ma.zs.gestion_service_pediatrie.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;





@JsonInclude(JsonInclude.Include.NON_NULL)
public class AntecedentDto  extends AuditBaseDto {

    private String ref  ;
    private String allergie  ;
    private String etat_psy  ;
    private String respiratoire  ;
    private String alimentation  ;
    private String mouvement  ;
    private String sommeil  ;

    private GroupeSanguinDto groupeSanguin ;



    public AntecedentDto(){
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
    public String getAllergie(){
        return this.allergie;
    }
    public void setAllergie(String allergie){
        this.allergie = allergie;
    }

    @Log
    public String getEtat_psy(){
        return this.etat_psy;
    }
    public void setEtat_psy(String etat_psy){
        this.etat_psy = etat_psy;
    }

    @Log
    public String getRespiratoire(){
        return this.respiratoire;
    }
    public void setRespiratoire(String respiratoire){
        this.respiratoire = respiratoire;
    }

    @Log
    public String getAlimentation(){
        return this.alimentation;
    }
    public void setAlimentation(String alimentation){
        this.alimentation = alimentation;
    }

    @Log
    public String getMouvement(){
        return this.mouvement;
    }
    public void setMouvement(String mouvement){
        this.mouvement = mouvement;
    }

    @Log
    public String getSommeil(){
        return this.sommeil;
    }
    public void setSommeil(String sommeil){
        this.sommeil = sommeil;
    }


    public GroupeSanguinDto getGroupeSanguin(){
        return this.groupeSanguin;
    }

    public void setGroupeSanguin(GroupeSanguinDto groupeSanguin){
        this.groupeSanguin = groupeSanguin;
    }






}
