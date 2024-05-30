package  ma.zs.gestion_service_pediatrie.dao.criteria.core.dossie;


import ma.zs.gestion_service_pediatrie.dao.criteria.core.consultatio.ConsultationCriteria;

import ma.zs.gestion_service_pediatrie.zynerator.criteria.BaseCriteria;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class RadiologieCriteria extends  BaseCriteria  {

    private String ref;
    private String refLike;
    private LocalDateTime dateRadiologie;
    private LocalDateTime dateRadiologieFrom;
    private LocalDateTime dateRadiologieTo;

    public ConsultationCriteria getConsultation() {
        return consultation;
    }

    public void setConsultation(ConsultationCriteria consultation) {
        this.consultation = consultation;
    }

    public List<ConsultationCriteria> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<ConsultationCriteria> consultations) {
        this.consultations = consultations;
    }

    private String commentaire;
    private String commentaireLike;
    private String imageScann;
    private String imageScannLike;

    private ConsultationCriteria consultation ;
    private List<ConsultationCriteria> consultations ;
    private TypeImageCriteria typeImage ;
    private List<TypeImageCriteria> typeImages ;


    public RadiologieCriteria(){}

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

    public LocalDateTime getDateRadiologie(){
        return this.dateRadiologie;
    }
    public void setDateRadiologie(LocalDateTime dateRadiologie){
        this.dateRadiologie = dateRadiologie;
    }
    public LocalDateTime getDateRadiologieFrom(){
        return this.dateRadiologieFrom;
    }
    public void setDateRadiologieFrom(LocalDateTime dateRadiologieFrom){
        this.dateRadiologieFrom = dateRadiologieFrom;
    }
    public LocalDateTime getDateRadiologieTo(){
        return this.dateRadiologieTo;
    }
    public void setDateRadiologieTo(LocalDateTime dateRadiologieTo){
        this.dateRadiologieTo = dateRadiologieTo;
    }
    public String getCommentaire(){
        return this.commentaire;
    }
    public void setCommentaire(String commentaire){
        this.commentaire = commentaire;
    }
    public String getCommentaireLike(){
        return this.commentaireLike;
    }
    public void setCommentaireLike(String commentaireLike){
        this.commentaireLike = commentaireLike;
    }

    public String getImageScann(){
        return this.imageScann;
    }
    public void setImageScann(String imageScann){
        this.imageScann = imageScann;
    }
    public String getImageScannLike(){
        return this.imageScannLike;
    }
    public void setImageScannLike(String imageScannLike){
        this.imageScannLike = imageScannLike;
    }


    public TypeImageCriteria getTypeImage(){
        return this.typeImage;
    }

    public void setTypeImage(TypeImageCriteria typeImage){
        this.typeImage = typeImage;
    }
    public List<TypeImageCriteria> getTypeImages(){
        return this.typeImages;
    }

    public void setTypeImages(List<TypeImageCriteria> typeImages){
        this.typeImages = typeImages;
    }
}
