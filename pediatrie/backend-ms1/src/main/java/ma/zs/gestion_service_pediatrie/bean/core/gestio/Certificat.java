package ma.zs.gestion_service_pediatrie.bean.core.gestio;

import java.util.Objects;

import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zs.gestion_service_pediatrie.bean.core.consultatio.Consultation;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.gestion_service_pediatrie.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "certificat")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="certificat_seq",sequenceName="certificat_seq",allocationSize=1, initialValue = 1)
public class Certificat  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String ref;
    private LocalDateTime dateDebut ;
    private LocalDateTime dateFin ;
    @Column(length = 500)
    private String description;
    private Integer nbrJour = 0;

    private Consultation consultatuin ;


    public Certificat(){
        super();
    }

    public Certificat(Long id,String ref){
        this.id = id;
        this.ref = ref ;
    }
    public Certificat(String ref){
        this.ref = ref ;
    }




    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="certificat_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getRef(){
        return this.ref;
    }
    public void setRef(String ref){
        this.ref = ref;
    }
    public LocalDateTime getDateDebut(){
        return this.dateDebut;
    }
    public void setDateDebut(LocalDateTime dateDebut){
        this.dateDebut = dateDebut;
    }
    public LocalDateTime getDateFin(){
        return this.dateFin;
    }
    public void setDateFin(LocalDateTime dateFin){
        this.dateFin = dateFin;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public Integer getNbrJour(){
        return this.nbrJour;
    }
    public void setNbrJour(Integer nbrJour){
        this.nbrJour = nbrJour;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consultatuin")
    public Consultation getConsultatuin(){
        return this.consultatuin;
    }
    public void setConsultatuin(Consultation consultatuin){
        this.consultatuin = consultatuin;
    }

    @Transient
    public String getLabel() {
        label = ref;
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Certificat certificat = (Certificat) o;
        return id != null && id.equals(certificat.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

