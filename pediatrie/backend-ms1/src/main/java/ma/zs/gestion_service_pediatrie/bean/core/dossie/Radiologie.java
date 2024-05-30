package ma.zs.gestion_service_pediatrie.bean.core.dossie;

import java.util.Objects;

import java.time.LocalDateTime;


import ma.zs.gestion_service_pediatrie.bean.core.consultatio.Consultation;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.gestion_service_pediatrie.zynerator.bean.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "radiologie")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="radiologie_seq",sequenceName="radiologie_seq",allocationSize=1, initialValue = 1)
public class Radiologie  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String ref;
    private LocalDateTime dateRadiologie ;
    @Column(length = 500)
    private String commentaire;
    @Column(length = 500)
    private String imageScann;

    private Consultation consultation;
    private TypeImage typeImage ;


    public Radiologie(){
        super();
    }

    public Radiologie(Long id,String ref){
        this.id = id;
        this.ref = ref ;
    }
    public Radiologie(String ref){
        this.ref = ref ;
    }




    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="radiologie_seq")
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
    public LocalDateTime getDateRadiologie(){
        return this.dateRadiologie;
    }
    public void setDateRadiologie(LocalDateTime dateRadiologie){
        this.dateRadiologie = dateRadiologie;
    }
    public String getCommentaire(){
        return this.commentaire;
    }
    public void setCommentaire(String commentaire){
        this.commentaire = commentaire;
    }
    public String getImageScann(){
        return this.imageScann;
    }
    public void setImageScann(String imageScann){
        this.imageScann = imageScann;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consultatuin")
    public Consultation getConsultation(){
        return this.consultation;
    }
    public void setConsultation(Consultation consultatuin){
        this.consultation = consultatuin;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_image")
    public TypeImage getTypeImage(){
        return this.typeImage;
    }
    public void setTypeImage(TypeImage typeImage){
        this.typeImage = typeImage;
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
        Radiologie radiologie = (Radiologie) o;
        return id != null && id.equals(radiologie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

