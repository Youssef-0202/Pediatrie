package  ma.zs.gestion_service_pediatrie.ws.dto.gestio;

import ma.zs.gestion_service_pediatrie.zynerator.audit.Log;
import ma.zs.gestion_service_pediatrie.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zs.gestion_service_pediatrie.ws.dto.consultatio.ConsultationDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class CertificatDto  extends AuditBaseDto {

    private String ref  ;
    private String dateDebut ;
    private String dateFin ;
    private String description  ;
    private Integer nbrJour  = 0 ;

    private ConsultationDto consultatuin ;



    public CertificatDto(){
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
    public String getDateDebut(){
        return this.dateDebut;
    }
    public void setDateDebut(String dateDebut){
        this.dateDebut = dateDebut;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateFin(){
        return this.dateFin;
    }
    public void setDateFin(String dateFin){
        this.dateFin = dateFin;
    }

    @Log
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    @Log
    public Integer getNbrJour(){
        return this.nbrJour;
    }
    public void setNbrJour(Integer nbrJour){
        this.nbrJour = nbrJour;
    }


    public ConsultationDto getConsultatuin(){
        return this.consultatuin;
    }

    public void setConsultatuin(ConsultationDto consultatuin){
        this.consultatuin = consultatuin;
    }






}
