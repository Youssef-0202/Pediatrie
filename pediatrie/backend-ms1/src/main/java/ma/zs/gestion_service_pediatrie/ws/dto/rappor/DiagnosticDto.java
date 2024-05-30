package  ma.zs.gestion_service_pediatrie.ws.dto.rappor;

import ma.zs.gestion_service_pediatrie.zynerator.audit.Log;
import ma.zs.gestion_service_pediatrie.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zs.gestion_service_pediatrie.ws.dto.consultatio.ConsultationDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiagnosticDto  extends AuditBaseDto {

    private String ref  ;
    private String dateDiagnostic ;
    private String description  ;

    private ConsultationDto consultation ;



    public DiagnosticDto(){
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
    public String getDateDiagnostic(){
        return this.dateDiagnostic;
    }
    public void setDateDiagnostic(String dateDiagnostic){
        this.dateDiagnostic = dateDiagnostic;
    }

    @Log
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }


    public ConsultationDto getConsultation(){
        return this.consultation;
    }

    public void setConsultation(ConsultationDto consultation){
        this.consultation = consultation;
    }






}
