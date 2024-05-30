package ma.zs.gestion_service_pediatrie.bean.core.rappor;

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
@Table(name = "synthese_medicale")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="synthese_medicale_seq",sequenceName="synthese_medicale_seq",allocationSize=1, initialValue = 1)
public class SyntheseMedicale  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String ref;
    private LocalDateTime dateSyntheseMedicale ;
    @Column(length = 500)
    private String description;

    private Consultation consultation ;


    public SyntheseMedicale(){
        super();
    }

    public SyntheseMedicale(Long id,String ref){
        this.id = id;
        this.ref = ref ;
    }
    public SyntheseMedicale(String ref){
        this.ref = ref ;
    }




    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="synthese_medicale_seq")
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
    public LocalDateTime getDateSyntheseMedicale(){
        return this.dateSyntheseMedicale;
    }
    public void setDateSyntheseMedicale(LocalDateTime dateSyntheseMedicale){
        this.dateSyntheseMedicale = dateSyntheseMedicale;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consultation")
    public Consultation getConsultation(){
        return this.consultation;
    }
    public void setConsultation(Consultation consultation){
        this.consultation = consultation;
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
        SyntheseMedicale syntheseMedicale = (SyntheseMedicale) o;
        return id != null && id.equals(syntheseMedicale.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

