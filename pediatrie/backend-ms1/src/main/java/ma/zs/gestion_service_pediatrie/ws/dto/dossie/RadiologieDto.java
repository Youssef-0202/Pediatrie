package  ma.zs.gestion_service_pediatrie.ws.dto.dossie;

import ma.zs.gestion_service_pediatrie.zynerator.audit.Log;
import ma.zs.gestion_service_pediatrie.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zs.gestion_service_pediatrie.ws.dto.consultatio.ConsultationDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class RadiologieDto  extends AuditBaseDto {

    private String ref  ;
    private String dateRadiologie ;
    private String commentaire  ;
    private String imageScann  ;

    private ConsultationDto consultationDto;
    private TypeImageDto typeImage ;



    public RadiologieDto(){
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
    public String getDateRadiologie(){
        return this.dateRadiologie;
    }
    public void setDateRadiologie(String dateRadiologie){
        this.dateRadiologie = dateRadiologie;
    }

    @Log
    public String getCommentaire(){
        return this.commentaire;
    }
    public void setCommentaire(String commentaire){
        this.commentaire = commentaire;
    }

    @Log
    public String getImageScann(){
        return this.imageScann;
    }
    public void setImageScann(String imageScann){
        this.imageScann = imageScann;
    }


    public ConsultationDto getConsultationDto(){
        return this.consultationDto;
    }

    public void setConsultationDto(ConsultationDto consultationDto){
        this.consultationDto = consultationDto;
    }
    public TypeImageDto getTypeImage(){
        return this.typeImage;
    }

    public void setTypeImage(TypeImageDto typeImage){
        this.typeImage = typeImage;
    }






}
