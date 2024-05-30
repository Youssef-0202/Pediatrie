package  ma.zs.gestion_service_pediatrie.ws.dto.gestio;

import ma.zs.gestion_service_pediatrie.zynerator.audit.Log;
import ma.zs.gestion_service_pediatrie.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zs.gestion_service_pediatrie.ws.dto.consultatio.ConsultationDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrdonnanceDto  extends AuditBaseDto {

    private String ref  ;
    private String nomHospital  ;
    private String dateOrdonnance ;
    private String adresseHospitla  ;
    private String signature  ;

    private TraitementDto traitement ;
    private ConsultationDto consultatuin ;



    public OrdonnanceDto(){
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
    public String getNomHospital(){
        return this.nomHospital;
    }
    public void setNomHospital(String nomHospital){
        this.nomHospital = nomHospital;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateOrdonnance(){
        return this.dateOrdonnance;
    }
    public void setDateOrdonnance(String dateOrdonnance){
        this.dateOrdonnance = dateOrdonnance;
    }

    @Log
    public String getAdresseHospitla(){
        return this.adresseHospitla;
    }
    public void setAdresseHospitla(String adresseHospitla){
        this.adresseHospitla = adresseHospitla;
    }

    @Log
    public String getSignature(){
        return this.signature;
    }
    public void setSignature(String signature){
        this.signature = signature;
    }


    public TraitementDto getTraitement(){
        return this.traitement;
    }

    public void setTraitement(TraitementDto traitement){
        this.traitement = traitement;
    }
    public ConsultationDto getConsultatuin(){
        return this.consultatuin;
    }

    public void setConsultatuin(ConsultationDto consultatuin){
        this.consultatuin = consultatuin;
    }






}
