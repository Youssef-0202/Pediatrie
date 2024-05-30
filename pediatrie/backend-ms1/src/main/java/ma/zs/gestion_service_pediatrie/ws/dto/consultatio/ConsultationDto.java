package  ma.zs.gestion_service_pediatrie.ws.dto.consultatio;

import ma.zs.gestion_service_pediatrie.zynerator.audit.Log;
import ma.zs.gestion_service_pediatrie.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zs.gestion_service_pediatrie.ws.dto.dossie.FichePatientDto;
import ma.zs.gestion_service_pediatrie.ws.dto.dossie.RadiologieDto;
import ma.zs.gestion_service_pediatrie.ws.dto.commun.InfermierDto;
import ma.zs.gestion_service_pediatrie.ws.dto.commun.MedecinDto;
import ma.zs.gestion_service_pediatrie.ws.dto.dossie.EpreuveDto;
import ma.zs.gestion_service_pediatrie.ws.dto.rappor.SyntheseMedicaleDto;
import ma.zs.gestion_service_pediatrie.ws.dto.patient.PatientDto;
import ma.zs.gestion_service_pediatrie.ws.dto.rappor.DiagnosticDto;
import ma.zs.gestion_service_pediatrie.ws.dto.dossie.AnalyseMedicaleDto;
import ma.zs.gestion_service_pediatrie.ws.dto.dossie.TypeImageDto;
import ma.zs.gestion_service_pediatrie.ws.dto.dossie.AntecedentDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConsultationDto  extends AuditBaseDto {

    private String ref  ;
    private String dateConsultation ;
    private String heureConsultation ;
    private String typeConsultation  ;

    private MedecinDto medecin ;
    private InfermierDto infermier ;
    private PatientDto patient ;

    private List<AnalyseMedicaleDto> analyseMedicale ;
    private List<FichePatientDto> fichePatient ;
    private List<RadiologieDto> radiologie ;
    private List<DiagnosticDto> diagnostic ;
    private List<SyntheseMedicaleDto> syntheseMedicale ;


    public ConsultationDto(){
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
    public String getDateConsultation(){
        return this.dateConsultation;
    }
    public void setDateConsultation(String dateConsultation){
        this.dateConsultation = dateConsultation;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getHeureConsultation(){
        return this.heureConsultation;
    }
    public void setHeureConsultation(String heureConsultation){
        this.heureConsultation = heureConsultation;
    }

    @Log
    public String getTypeConsultation(){
        return this.typeConsultation;
    }
    public void setTypeConsultation(String typeConsultation){
        this.typeConsultation = typeConsultation;
    }


    public MedecinDto getMedecin(){
        return this.medecin;
    }

    public void setMedecin(MedecinDto medecin){
        this.medecin = medecin;
    }
    public InfermierDto getInfermier(){
        return this.infermier;
    }

    public void setInfermier(InfermierDto infermier){
        this.infermier = infermier;
    }
    public PatientDto getPatient(){
        return this.patient;
    }

    public void setPatient(PatientDto patient){
        this.patient = patient;
    }



    public List<AnalyseMedicaleDto> getAnalyseMedicale(){
        return this.analyseMedicale;
    }

    public void setAnalyseMedicale(List<AnalyseMedicaleDto> analyseMedicale){
        this.analyseMedicale = analyseMedicale;
    }
    public List<FichePatientDto> getFichePatient(){
        return this.fichePatient;
    }

    public void setFichePatient(List<FichePatientDto> fichePatient){
        this.fichePatient = fichePatient;
    }
    public List<RadiologieDto> getRadiologie(){
        return this.radiologie;
    }

    public void setRadiologie(List<RadiologieDto> radiologie){
        this.radiologie = radiologie;
    }
    public List<DiagnosticDto> getDiagnostic(){
        return this.diagnostic;
    }

    public void setDiagnostic(List<DiagnosticDto> diagnostic){
        this.diagnostic = diagnostic;
    }
    public List<SyntheseMedicaleDto> getSyntheseMedicale(){
        return this.syntheseMedicale;
    }

    public void setSyntheseMedicale(List<SyntheseMedicaleDto> syntheseMedicale){
        this.syntheseMedicale = syntheseMedicale;
    }



}
