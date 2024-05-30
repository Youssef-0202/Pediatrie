package  ma.zs.gestion_service_pediatrie.ws.converter.gestio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.gestion_service_pediatrie.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.gestion_service_pediatrie.ws.converter.gestio.TraitementConverter;
import ma.zs.gestion_service_pediatrie.ws.converter.consultatio.ConsultationConverter;

import ma.zs.gestion_service_pediatrie.bean.core.gestio.Traitement;
import ma.zs.gestion_service_pediatrie.bean.core.consultatio.Consultation;


import ma.zs.gestion_service_pediatrie.zynerator.util.StringUtil;
import ma.zs.gestion_service_pediatrie.zynerator.converter.AbstractConverter;
import ma.zs.gestion_service_pediatrie.zynerator.util.DateUtil;
import ma.zs.gestion_service_pediatrie.bean.core.gestio.Ordonnance;
import ma.zs.gestion_service_pediatrie.ws.dto.gestio.OrdonnanceDto;

@Component
public class OrdonnanceConverter {

    @Autowired
    private TraitementConverter traitementConverter ;
    @Autowired
    private ConsultationConverter consultationConverter ;
    private boolean traitement;
    private boolean consultatuin;

    public  OrdonnanceConverter() {
        initObject(true);
    }


    public Ordonnance toItem(OrdonnanceDto dto) {
        if (dto == null) {
            return null;
        } else {
        Ordonnance item = new Ordonnance();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getRef()))
                item.setRef(dto.getRef());
            if(StringUtil.isNotEmpty(dto.getNomHospital()))
                item.setNomHospital(dto.getNomHospital());
            if(StringUtil.isNotEmpty(dto.getDateOrdonnance()))
                item.setDateOrdonnance(DateUtil.stringEnToDate(dto.getDateOrdonnance()));
            if(StringUtil.isNotEmpty(dto.getAdresseHospitla()))
                item.setAdresseHospitla(dto.getAdresseHospitla());
            if(StringUtil.isNotEmpty(dto.getSignature()))
                item.setSignature(dto.getSignature());
            if(dto.getTraitement() != null && dto.getTraitement().getId() != null){
                item.setTraitement(new Traitement());
                item.getTraitement().setId(dto.getTraitement().getId());
                item.getTraitement().setRef(dto.getTraitement().getRef());
            }

            if(dto.getConsultatuin() != null && dto.getConsultatuin().getId() != null){
                item.setConsultatuin(new Consultation());
                item.getConsultatuin().setId(dto.getConsultatuin().getId());
                item.getConsultatuin().setRef(dto.getConsultatuin().getRef());
            }




        return item;
        }
    }


    public OrdonnanceDto toDto(Ordonnance item) {
        if (item == null) {
            return null;
        } else {
            OrdonnanceDto dto = new OrdonnanceDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getRef()))
                dto.setRef(item.getRef());
            if(StringUtil.isNotEmpty(item.getNomHospital()))
                dto.setNomHospital(item.getNomHospital());
            if(item.getDateOrdonnance()!=null)
                dto.setDateOrdonnance(DateUtil.dateTimeToString(item.getDateOrdonnance()));
            if(StringUtil.isNotEmpty(item.getAdresseHospitla()))
                dto.setAdresseHospitla(item.getAdresseHospitla());
            if(StringUtil.isNotEmpty(item.getSignature()))
                dto.setSignature(item.getSignature());
            if(this.traitement && item.getTraitement()!=null) {
                traitementConverter.setOrdonnance(false);
                dto.setTraitement(traitementConverter.toDto(item.getTraitement())) ;
                traitementConverter.setOrdonnance(true);

            }
            if(this.consultatuin && item.getConsultatuin()!=null) {
                dto.setConsultatuin(consultationConverter.toDto(item.getConsultatuin())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.traitement = value;
        this.consultatuin = value;
    }
	
    public List<Ordonnance> toItem(List<OrdonnanceDto> dtos) {
        List<Ordonnance> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (OrdonnanceDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<OrdonnanceDto> toDto(List<Ordonnance> items) {
        List<OrdonnanceDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Ordonnance item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(OrdonnanceDto dto, Ordonnance t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getTraitement() != null)
        traitementConverter.copy(dto.getTraitement(), t.getTraitement());
        if (dto.getConsultatuin() != null)
        consultationConverter.copy(dto.getConsultatuin(), t.getConsultatuin());
    }

    public List<Ordonnance> copy(List<OrdonnanceDto> dtos) {
        List<Ordonnance> result = new ArrayList<>();
        if (dtos != null) {
            for (OrdonnanceDto dto : dtos) {
                Ordonnance instance = new Ordonnance();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public TraitementConverter getTraitementConverter(){
        return this.traitementConverter;
    }
    public void setTraitementConverter(TraitementConverter traitementConverter ){
        this.traitementConverter = traitementConverter;
    }
    public ConsultationConverter getConsultationConverter(){
        return this.consultationConverter;
    }
    public void setConsultationConverter(ConsultationConverter consultationConverter ){
        this.consultationConverter = consultationConverter;
    }
    public boolean  isTraitement(){
        return this.traitement;
    }
    public void  setTraitement(boolean traitement){
        this.traitement = traitement;
    }
    public boolean  isConsultatuin(){
        return this.consultatuin;
    }
    public void  setConsultatuin(boolean consultatuin){
        this.consultatuin = consultatuin;
    }
}
