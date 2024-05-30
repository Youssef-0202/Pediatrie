package  ma.zs.gestion_service_pediatrie.ws.dto.dossie;

import ma.zs.gestion_service_pediatrie.zynerator.audit.Log;
import ma.zs.gestion_service_pediatrie.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zs.gestion_service_pediatrie.ws.dto.consultatio.ConsultationDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class FichePatientDto  extends AuditBaseDto {

    private String ref  ;
    private String dateFichePatient ;
    private String antecedentFamillial  ;
    private String histoireMaladie  ;
    private String examenFichePatient  ;
    private String conclusionFichePatient  ;

    private AntecedentDto antecedent ;
    private ConsultationDto consultation ;



    public FichePatientDto(){
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
    public String getDateFichePatient(){
        return this.dateFichePatient;
    }
    public void setDateFichePatient(String dateFichePatient){
        this.dateFichePatient = dateFichePatient;
    }

    @Log
    public String getAntecedentFamillial(){
        return this.antecedentFamillial;
    }
    public void setAntecedentFamillial(String antecedentFamillial){
        this.antecedentFamillial = antecedentFamillial;
    }

    @Log
    public String getHistoireMaladie(){
        return this.histoireMaladie;
    }
    public void setHistoireMaladie(String histoireMaladie){
        this.histoireMaladie = histoireMaladie;
    }

    @Log
    public String getExamenFichePatient(){
        return this.examenFichePatient;
    }
    public void setExamenFichePatient(String examenFichePatient){
        this.examenFichePatient = examenFichePatient;
    }

    @Log
    public String getConclusionFichePatient(){
        return this.conclusionFichePatient;
    }
    public void setConclusionFichePatient(String conclusionFichePatient){
        this.conclusionFichePatient = conclusionFichePatient;
    }


    public AntecedentDto getAntecedent(){
        return this.antecedent;
    }

    public void setAntecedent(AntecedentDto antecedent){
        this.antecedent = antecedent;
    }
    public ConsultationDto getConsultation(){
        return this.consultation;
    }

    public void setConsultation(ConsultationDto consultation){
        this.consultation = consultation;
    }






}
