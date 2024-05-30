package ma.zs.gestion_service_pediatrie.bean.core.dossie;

import java.util.Objects;

import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zs.gestion_service_pediatrie.bean.core.consultatio.Consultation;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.gestion_service_pediatrie.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "analyse_medicale")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="analyse_medicale_seq",sequenceName="analyse_medicale_seq",allocationSize=1, initialValue = 1)
public class AnalyseMedicale  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String ref;
    private LocalDateTime dateAnalyseMedicale ;
    private BigDecimal valeur = BigDecimal.ZERO;
    @Column(length = 500)
    private String valeurRang;

    private Epreuve epreuve ;
    private Consultation consultation ;


    public AnalyseMedicale(){
        super();
    }

    public AnalyseMedicale(Long id,String ref){
        this.id = id;
        this.ref = ref ;
    }
    public AnalyseMedicale(String ref){
        this.ref = ref ;
    }




    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="analyse_medicale_seq")
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
    public LocalDateTime getDateAnalyseMedicale(){
        return this.dateAnalyseMedicale;
    }
    public void setDateAnalyseMedicale(LocalDateTime dateAnalyseMedicale){
        this.dateAnalyseMedicale = dateAnalyseMedicale;
    }
    public BigDecimal getValeur(){
        return this.valeur;
    }
    public void setValeur(BigDecimal valeur){
        this.valeur = valeur;
    }
    public String getValeurRang(){
        return this.valeurRang;
    }
    public void setValeurRang(String valeurRang){
        this.valeurRang = valeurRang;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "epreuve")
    public Epreuve getEpreuve(){
        return this.epreuve;
    }
    public void setEpreuve(Epreuve epreuve){
        this.epreuve = epreuve;
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
        AnalyseMedicale analyseMedicale = (AnalyseMedicale) o;
        return id != null && id.equals(analyseMedicale.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

