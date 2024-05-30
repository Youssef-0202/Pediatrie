package ma.zs.gestion_service_pediatrie.bean.core.gestio;

import java.util.Objects;
import java.util.List;





import ma.zs.gestion_service_pediatrie.bean.core.consultatio.Consultation;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.gestion_service_pediatrie.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "traitement")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="traitement_seq",sequenceName="traitement_seq",allocationSize=1, initialValue = 1)
public class Traitement  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String ref;
    @Column(length = 500)
    private String imageOrdonnance;

    private Ordonnance ordonnance ;
    private Consultation consultatuin ;

    private List<Medicament> medicaments ;

    public Traitement(){
        super();
    }

    public Traitement(Long id,String ref){
        this.id = id;
        this.ref = ref ;
    }
    public Traitement(String ref){
        this.ref = ref ;
    }




    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="traitement_seq")
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
    public String getImageOrdonnance(){
        return this.imageOrdonnance;
    }
    public void setImageOrdonnance(String imageOrdonnance){
        this.imageOrdonnance = imageOrdonnance;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ordonnance")
    public Ordonnance getOrdonnance(){
        return this.ordonnance;
    }
    public void setOrdonnance(Ordonnance ordonnance){
        this.ordonnance = ordonnance;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consultatuin")
    public Consultation getConsultatuin(){
        return this.consultatuin;
    }
    public void setConsultatuin(Consultation consultatuin){
        this.consultatuin = consultatuin;
    }
    @OneToMany(mappedBy = "traitement")

    public List<Medicament> getMedicaments(){
        return this.medicaments;
    }
    public void setMedicaments(List<Medicament> medicaments){
        this.medicaments = medicaments;
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
        Traitement traitement = (Traitement) o;
        return id != null && id.equals(traitement.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

