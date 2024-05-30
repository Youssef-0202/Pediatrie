package  ma.zs.gestion_service_pediatrie.dao.criteria.core.consultatio;


import ma.zs.gestion_service_pediatrie.dao.criteria.core.commun.InfermierCriteria;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.commun.MedecinCriteria;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.patient.PatientCriteria;

import ma.zs.gestion_service_pediatrie.zynerator.criteria.BaseCriteria;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class ConsultationCriteria extends  BaseCriteria  {

    private String ref;
    private String refLike;
    private LocalDateTime dateConsultation;
    private LocalDateTime dateConsultationFrom;
    private LocalDateTime dateConsultationTo;
    private LocalDateTime heureConsultation;
    private LocalDateTime heureConsultationFrom;
    private LocalDateTime heureConsultationTo;
    private String typeConsultation;
    private String typeConsultationLike;

    private MedecinCriteria medecin ;
    private List<MedecinCriteria> medecins ;
    private InfermierCriteria infermier ;
    private List<InfermierCriteria> infermiers ;
    private PatientCriteria patient ;
    private List<PatientCriteria> patients ;


    public ConsultationCriteria(){}

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

    public LocalDateTime getDateConsultation(){
        return this.dateConsultation;
    }
    public void setDateConsultation(LocalDateTime dateConsultation){
        this.dateConsultation = dateConsultation;
    }
    public LocalDateTime getDateConsultationFrom(){
        return this.dateConsultationFrom;
    }
    public void setDateConsultationFrom(LocalDateTime dateConsultationFrom){
        this.dateConsultationFrom = dateConsultationFrom;
    }
    public LocalDateTime getDateConsultationTo(){
        return this.dateConsultationTo;
    }
    public void setDateConsultationTo(LocalDateTime dateConsultationTo){
        this.dateConsultationTo = dateConsultationTo;
    }
    public LocalDateTime getHeureConsultation(){
        return this.heureConsultation;
    }
    public void setHeureConsultation(LocalDateTime heureConsultation){
        this.heureConsultation = heureConsultation;
    }
    public LocalDateTime getHeureConsultationFrom(){
        return this.heureConsultationFrom;
    }
    public void setHeureConsultationFrom(LocalDateTime heureConsultationFrom){
        this.heureConsultationFrom = heureConsultationFrom;
    }
    public LocalDateTime getHeureConsultationTo(){
        return this.heureConsultationTo;
    }
    public void setHeureConsultationTo(LocalDateTime heureConsultationTo){
        this.heureConsultationTo = heureConsultationTo;
    }
    public String getTypeConsultation(){
        return this.typeConsultation;
    }
    public void setTypeConsultation(String typeConsultation){
        this.typeConsultation = typeConsultation;
    }
    public String getTypeConsultationLike(){
        return this.typeConsultationLike;
    }
    public void setTypeConsultationLike(String typeConsultationLike){
        this.typeConsultationLike = typeConsultationLike;
    }


    public MedecinCriteria getMedecin(){
        return this.medecin;
    }

    public void setMedecin(MedecinCriteria medecin){
        this.medecin = medecin;
    }
    public List<MedecinCriteria> getMedecins(){
        return this.medecins;
    }

    public void setMedecins(List<MedecinCriteria> medecins){
        this.medecins = medecins;
    }
    public InfermierCriteria getInfermier(){
        return this.infermier;
    }

    public void setInfermier(InfermierCriteria infermier){
        this.infermier = infermier;
    }
    public List<InfermierCriteria> getInfermiers(){
        return this.infermiers;
    }

    public void setInfermiers(List<InfermierCriteria> infermiers){
        this.infermiers = infermiers;
    }
    public PatientCriteria getPatient(){
        return this.patient;
    }

    public void setPatient(PatientCriteria patient){
        this.patient = patient;
    }
    public List<PatientCriteria> getPatients(){
        return this.patients;
    }

    public void setPatients(List<PatientCriteria> patients){
        this.patients = patients;
    }
}
