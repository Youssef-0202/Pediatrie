package  ma.zs.gestion_service_pediatrie.dao.criteria.core.rappor;


import ma.zs.gestion_service_pediatrie.dao.criteria.core.consultatio.ConsultationCriteria;

import ma.zs.gestion_service_pediatrie.zynerator.criteria.BaseCriteria;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class DiagnosticCriteria extends  BaseCriteria  {

    private String ref;
    private String refLike;
    private LocalDateTime dateDiagnostic;
    private LocalDateTime dateDiagnosticFrom;
    private LocalDateTime dateDiagnosticTo;
    private String description;
    private String descriptionLike;

    private ConsultationCriteria consultation ;
    private List<ConsultationCriteria> consultations ;


    public DiagnosticCriteria(){}

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

    public LocalDateTime getDateDiagnostic(){
        return this.dateDiagnostic;
    }
    public void setDateDiagnostic(LocalDateTime dateDiagnostic){
        this.dateDiagnostic = dateDiagnostic;
    }
    public LocalDateTime getDateDiagnosticFrom(){
        return this.dateDiagnosticFrom;
    }
    public void setDateDiagnosticFrom(LocalDateTime dateDiagnosticFrom){
        this.dateDiagnosticFrom = dateDiagnosticFrom;
    }
    public LocalDateTime getDateDiagnosticTo(){
        return this.dateDiagnosticTo;
    }
    public void setDateDiagnosticTo(LocalDateTime dateDiagnosticTo){
        this.dateDiagnosticTo = dateDiagnosticTo;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescriptionLike(){
        return this.descriptionLike;
    }
    public void setDescriptionLike(String descriptionLike){
        this.descriptionLike = descriptionLike;
    }


    public ConsultationCriteria getConsultation(){
        return this.consultation;
    }

    public void setConsultation(ConsultationCriteria consultation){
        this.consultation = consultation;
    }
    public List<ConsultationCriteria> getConsultations(){
        return this.consultations;
    }

    public void setConsultations(List<ConsultationCriteria> consultations){
        this.consultations = consultations;
    }
}
