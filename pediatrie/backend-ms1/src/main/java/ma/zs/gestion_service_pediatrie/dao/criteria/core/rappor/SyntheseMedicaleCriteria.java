package  ma.zs.gestion_service_pediatrie.dao.criteria.core.rappor;


import ma.zs.gestion_service_pediatrie.dao.criteria.core.consultatio.ConsultationCriteria;

import ma.zs.gestion_service_pediatrie.zynerator.criteria.BaseCriteria;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class SyntheseMedicaleCriteria extends  BaseCriteria  {

    private String ref;
    private String refLike;
    private LocalDateTime dateSyntheseMedicale;
    private LocalDateTime dateSyntheseMedicaleFrom;
    private LocalDateTime dateSyntheseMedicaleTo;
    private String description;
    private String descriptionLike;

    private ConsultationCriteria consultation ;
    private List<ConsultationCriteria> consultations ;


    public SyntheseMedicaleCriteria(){}

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

    public LocalDateTime getDateSyntheseMedicale(){
        return this.dateSyntheseMedicale;
    }
    public void setDateSyntheseMedicale(LocalDateTime dateSyntheseMedicale){
        this.dateSyntheseMedicale = dateSyntheseMedicale;
    }
    public LocalDateTime getDateSyntheseMedicaleFrom(){
        return this.dateSyntheseMedicaleFrom;
    }
    public void setDateSyntheseMedicaleFrom(LocalDateTime dateSyntheseMedicaleFrom){
        this.dateSyntheseMedicaleFrom = dateSyntheseMedicaleFrom;
    }
    public LocalDateTime getDateSyntheseMedicaleTo(){
        return this.dateSyntheseMedicaleTo;
    }
    public void setDateSyntheseMedicaleTo(LocalDateTime dateSyntheseMedicaleTo){
        this.dateSyntheseMedicaleTo = dateSyntheseMedicaleTo;
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
