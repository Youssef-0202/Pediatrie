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
@Table(name = "diagnostic")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="diagnostic_seq",sequenceName="diagnostic_seq",allocationSize=1, initialValue = 1)
public class Diagnostic  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String ref;
    private LocalDateTime dateDiagnostic ;
    @Column(length = 500)
    private String description;

    private Consultation consultation ;


    public Diagnostic(){
        super();
    }

    public Diagnostic(Long id,String ref){
        this.id = id;
        this.ref = ref ;
    }
    public Diagnostic(String ref){
        this.ref = ref ;
    }




    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="diagnostic_seq")
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
    public LocalDateTime getDateDiagnostic(){
        return this.dateDiagnostic;
    }
    public void setDateDiagnostic(LocalDateTime dateDiagnostic){
        this.dateDiagnostic = dateDiagnostic;
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
        Diagnostic diagnostic = (Diagnostic) o;
        return id != null && id.equals(diagnostic.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

