package  ma.zs.gestion_service_pediatrie.ws.dto.dossie;

import ma.zs.gestion_service_pediatrie.zynerator.audit.Log;
import ma.zs.gestion_service_pediatrie.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;


import ma.zs.gestion_service_pediatrie.ws.dto.consultatio.ConsultationDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnalyseMedicaleDto  extends AuditBaseDto {

    private String ref  ;
    private String dateAnalyseMedicale ;
    private BigDecimal valeur  ;
    private String valeurRang  ;

    private EpreuveDto epreuve ;
    private ConsultationDto consultation ;



    public AnalyseMedicaleDto(){
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateAnalyseMedicale(){
        return this.dateAnalyseMedicale;
    }
    public void setDateAnalyseMedicale(String dateAnalyseMedicale){
        this.dateAnalyseMedicale = dateAnalyseMedicale;
    }

    @Log
    public BigDecimal getValeur(){
        return this.valeur;
    }
    public void setValeur(BigDecimal valeur){
        this.valeur = valeur;
    }

    @Log
    public String getValeurRang(){
        return this.valeurRang;
    }
    public void setValeurRang(String valeurRang){
        this.valeurRang = valeurRang;
    }


    public EpreuveDto getEpreuve(){
        return this.epreuve;
    }

    public void setEpreuve(EpreuveDto epreuve){
        this.epreuve = epreuve;
    }
    public ConsultationDto getConsultation(){
        return this.consultation;
    }

    public void setConsultation(ConsultationDto consultation){
        this.consultation = consultation;
    }






}
