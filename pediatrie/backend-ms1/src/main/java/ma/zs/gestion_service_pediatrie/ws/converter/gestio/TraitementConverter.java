package  ma.zs.gestion_service_pediatrie.ws.converter.gestio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.gestion_service_pediatrie.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zs.gestion_service_pediatrie.zynerator.util.ListUtil;

import ma.zs.gestion_service_pediatrie.ws.converter.gestio.OrdonnanceConverter;
import ma.zs.gestion_service_pediatrie.ws.converter.consultatio.ConsultationConverter;
import ma.zs.gestion_service_pediatrie.ws.converter.gestio.MedicamentConverter;

import ma.zs.gestion_service_pediatrie.bean.core.consultatio.Consultation;


import ma.zs.gestion_service_pediatrie.zynerator.util.StringUtil;
import ma.zs.gestion_service_pediatrie.zynerator.converter.AbstractConverter;
import ma.zs.gestion_service_pediatrie.zynerator.util.DateUtil;
import ma.zs.gestion_service_pediatrie.bean.core.gestio.Traitement;
import ma.zs.gestion_service_pediatrie.ws.dto.gestio.TraitementDto;

@Component
public class TraitementConverter {

    @Autowired
    private OrdonnanceConverter ordonnanceConverter ;
    @Autowired
    private ConsultationConverter consultationConverter ;
    @Autowired
    private MedicamentConverter medicamentConverter ;
    private boolean ordonnance;
    private boolean consultatuin;
    private boolean medicaments;

    public  TraitementConverter() {
        init(true);
    }


    public Traitement toItem(TraitementDto dto) {
        if (dto == null) {
            return null;
        } else {
        Traitement item = new Traitement();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getRef()))
                item.setRef(dto.getRef());
            if(StringUtil.isNotEmpty(dto.getImageOrdonnance()))
                item.setImageOrdonnance(dto.getImageOrdonnance());
            if(this.ordonnance && dto.getOrdonnance()!=null)
                item.setOrdonnance(ordonnanceConverter.toItem(dto.getOrdonnance())) ;

            if(dto.getConsultatuin() != null && dto.getConsultatuin().getId() != null){
                item.setConsultatuin(new Consultation());
                item.getConsultatuin().setId(dto.getConsultatuin().getId());
                item.getConsultatuin().setRef(dto.getConsultatuin().getRef());
            }


            if(this.medicaments && ListUtil.isNotEmpty(dto.getMedicaments()))
                item.setMedicaments(medicamentConverter.toItem(dto.getMedicaments()));


        return item;
        }
    }


    public TraitementDto toDto(Traitement item) {
        if (item == null) {
            return null;
        } else {
            TraitementDto dto = new TraitementDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getRef()))
                dto.setRef(item.getRef());
            if(StringUtil.isNotEmpty(item.getImageOrdonnance()))
                dto.setImageOrdonnance(item.getImageOrdonnance());
            if(this.ordonnance && item.getOrdonnance()!=null) {
                ordonnanceConverter.setTraitement(false);
                dto.setOrdonnance(ordonnanceConverter.toDto(item.getOrdonnance())) ;
                ordonnanceConverter.setTraitement(true);

            }
            if(this.consultatuin && item.getConsultatuin()!=null) {
                dto.setConsultatuin(consultationConverter.toDto(item.getConsultatuin())) ;

            }
        if(this.medicaments && ListUtil.isNotEmpty(item.getMedicaments())){
            medicamentConverter.init(true);
            medicamentConverter.setTraitement(false);
            dto.setMedicaments(medicamentConverter.toDto(item.getMedicaments()));
            medicamentConverter.setTraitement(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.medicaments = value;
    }
    public void initObject(boolean value) {
        this.ordonnance = value;
        this.consultatuin = value;
    }
	
    public List<Traitement> toItem(List<TraitementDto> dtos) {
        List<Traitement> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (TraitementDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<TraitementDto> toDto(List<Traitement> items) {
        List<TraitementDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Traitement item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    /*public void copy(TraitementDto dto, Traitement t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getOrdonnance() != null)
        ordonnanceConverter.copy(dto.getOrdonnance(), t.getOrdonnance());
        if (dto.getConsultatuin() != null)
        consultationConverter.copy(dto.getConsultatuin(), t.getConsultatuin());
        if (dto.getMedicaments() != null)
            t.setMedicaments(medicamentConverter.copy(dto.getMedicaments()));
    }*/

    public void copy(TraitementDto dto, Traitement t) {
        if (t != null) {
            BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
            if (dto.getOrdonnance() != null) {
                ordonnanceConverter.copy(dto.getOrdonnance(), t.getOrdonnance());
            }
            if (dto.getConsultatuin() != null) {
                consultationConverter.copy(dto.getConsultatuin(), t.getConsultatuin());
            }
            if (dto.getMedicaments() != null) {
                t.setMedicaments(medicamentConverter.copy(dto.getMedicaments()));
            }
        }
    }

    public List<Traitement> copy(List<TraitementDto> dtos) {
        List<Traitement> result = new ArrayList<>();
        if (dtos != null) {
            for (TraitementDto dto : dtos) {
                Traitement instance = new Traitement();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public OrdonnanceConverter getOrdonnanceConverter(){
        return this.ordonnanceConverter;
    }
    public void setOrdonnanceConverter(OrdonnanceConverter ordonnanceConverter ){
        this.ordonnanceConverter = ordonnanceConverter;
    }
    public ConsultationConverter getConsultationConverter(){
        return this.consultationConverter;
    }
    public void setConsultationConverter(ConsultationConverter consultationConverter ){
        this.consultationConverter = consultationConverter;
    }
    public MedicamentConverter getMedicamentConverter(){
        return this.medicamentConverter;
    }
    public void setMedicamentConverter(MedicamentConverter medicamentConverter ){
        this.medicamentConverter = medicamentConverter;
    }
    public boolean  isOrdonnance(){
        return this.ordonnance;
    }
    public void  setOrdonnance(boolean ordonnance){
        this.ordonnance = ordonnance;
    }
    public boolean  isConsultatuin(){
        return this.consultatuin;
    }
    public void  setConsultatuin(boolean consultatuin){
        this.consultatuin = consultatuin;
    }
    public boolean  isMedicaments(){
        return this.medicaments ;
    }
    public void  setMedicaments(boolean medicaments ){
        this.medicaments  = medicaments ;
    }
}
