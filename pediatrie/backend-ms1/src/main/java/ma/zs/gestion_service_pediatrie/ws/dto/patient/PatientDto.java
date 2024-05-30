package  ma.zs.gestion_service_pediatrie.ws.dto.patient;

import ma.zs.gestion_service_pediatrie.zynerator.audit.Log;
import ma.zs.gestion_service_pediatrie.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zs.gestion_service_pediatrie.ws.dto.commun.SexeDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientDto  extends AuditBaseDto {

    private String numDossier  ;
    private String nom  ;
    private String prenom  ;
    private String dateNaissance ;
    private String photoProfil  ;

    private SexeDto sexe ;

    private List<PatientContactDto> patientContact ;

    private LocalDateTime createdAt;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public PatientDto(){
        super();
    }



    @Log
    public String getNumDossier(){
        return this.numDossier;
    }
    public void setNumDossier(String numDossier){
        this.numDossier = numDossier;
    }

    @Log
    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }

    @Log
    public String getPrenom(){
        return this.prenom;
    }
    public void setPrenom(String prenom){
        this.prenom = prenom;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateNaissance(){
        return this.dateNaissance;
    }
    public void setDateNaissance(String dateNaissance){
        this.dateNaissance = dateNaissance;
    }

    @Log
    public String getPhotoProfil(){
        return this.photoProfil;
    }
    public void setPhotoProfil(String photoProfil){
        this.photoProfil = photoProfil;
    }


    public SexeDto getSexe(){
        return this.sexe;
    }

    public void setSexe(SexeDto sexe){
        this.sexe = sexe;
    }



    public List<PatientContactDto> getPatientContact(){
        return this.patientContact;
    }

    public void setPatientContact(List<PatientContactDto> patientContact){
        this.patientContact = patientContact;
    }



}
