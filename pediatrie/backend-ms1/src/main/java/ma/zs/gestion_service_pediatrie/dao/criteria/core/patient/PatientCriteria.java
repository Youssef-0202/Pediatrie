package  ma.zs.gestion_service_pediatrie.dao.criteria.core.patient;


import ma.zs.gestion_service_pediatrie.dao.criteria.core.commun.SexeCriteria;

import ma.zs.gestion_service_pediatrie.zynerator.criteria.BaseCriteria;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class PatientCriteria extends  BaseCriteria  {

    private String numDossier;
    private String numDossierLike;
    private String nom;
    private String nomLike;
    private String prenom;
    private String prenomLike;
    private LocalDateTime dateNaissance;
    private LocalDateTime dateNaissanceFrom;
    private LocalDateTime dateNaissanceTo;
    private String photoProfil;
    private String photoProfilLike;

    private SexeCriteria sexe ;
    private List<SexeCriteria> sexes ;


    public PatientCriteria(){}

    public String getNumDossier(){
        return this.numDossier;
    }
    public void setNumDossier(String numDossier){
        this.numDossier = numDossier;
    }
    public String getNumDossierLike(){
        return this.numDossierLike;
    }
    public void setNumDossierLike(String numDossierLike){
        this.numDossierLike = numDossierLike;
    }

    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }
    public String getNomLike(){
        return this.nomLike;
    }
    public void setNomLike(String nomLike){
        this.nomLike = nomLike;
    }

    public String getPrenom(){
        return this.prenom;
    }
    public void setPrenom(String prenom){
        this.prenom = prenom;
    }
    public String getPrenomLike(){
        return this.prenomLike;
    }
    public void setPrenomLike(String prenomLike){
        this.prenomLike = prenomLike;
    }

    public LocalDateTime getDateNaissance(){
        return this.dateNaissance;
    }
    public void setDateNaissance(LocalDateTime dateNaissance){
        this.dateNaissance = dateNaissance;
    }
    public LocalDateTime getDateNaissanceFrom(){
        return this.dateNaissanceFrom;
    }
    public void setDateNaissanceFrom(LocalDateTime dateNaissanceFrom){
        this.dateNaissanceFrom = dateNaissanceFrom;
    }
    public LocalDateTime getDateNaissanceTo(){
        return this.dateNaissanceTo;
    }
    public void setDateNaissanceTo(LocalDateTime dateNaissanceTo){
        this.dateNaissanceTo = dateNaissanceTo;
    }
    public String getPhotoProfil(){
        return this.photoProfil;
    }
    public void setPhotoProfil(String photoProfil){
        this.photoProfil = photoProfil;
    }
    public String getPhotoProfilLike(){
        return this.photoProfilLike;
    }
    public void setPhotoProfilLike(String photoProfilLike){
        this.photoProfilLike = photoProfilLike;
    }


    public SexeCriteria getSexe(){
        return this.sexe;
    }

    public void setSexe(SexeCriteria sexe){
        this.sexe = sexe;
    }
    public List<SexeCriteria> getSexes(){
        return this.sexes;
    }

    public void setSexes(List<SexeCriteria> sexes){
        this.sexes = sexes;
    }
}
