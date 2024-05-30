package  ma.zs.gestion_service_pediatrie.ws.dto.gestio;

import ma.zs.gestion_service_pediatrie.zynerator.audit.Log;
import ma.zs.gestion_service_pediatrie.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;


import ma.zs.gestion_service_pediatrie.ws.dto.consultatio.ConsultationDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class TraitementDto  extends AuditBaseDto {

    private String ref  ;
    private String imageOrdonnance  ;

    private OrdonnanceDto ordonnance ;
    private ConsultationDto consultatuin ;

    private List<MedicamentDto> medicaments ;


    public TraitementDto(){
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
    public String getImageOrdonnance(){
        return this.imageOrdonnance;
    }
    public void setImageOrdonnance(String imageOrdonnance){
        this.imageOrdonnance = imageOrdonnance;
    }


    public OrdonnanceDto getOrdonnance(){
        return this.ordonnance;
    }

    public void setOrdonnance(OrdonnanceDto ordonnance){
        this.ordonnance = ordonnance;
    }
    public ConsultationDto getConsultatuin(){
        return this.consultatuin;
    }

    public void setConsultatuin(ConsultationDto consultatuin){
        this.consultatuin = consultatuin;
    }



    public List<MedicamentDto> getMedicaments(){
        return this.medicaments;
    }

    public void setMedicaments(List<MedicamentDto> medicaments){
        this.medicaments = medicaments;
    }



}
