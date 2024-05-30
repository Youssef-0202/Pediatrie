package  ma.zs.gestion_service_pediatrie.ws.converter.dossie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.gestion_service_pediatrie.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.gestion_service_pediatrie.ws.converter.consultatio.ConsultationConverter;
import ma.zs.gestion_service_pediatrie.ws.converter.dossie.EpreuveConverter;

import ma.zs.gestion_service_pediatrie.bean.core.consultatio.Consultation;


import ma.zs.gestion_service_pediatrie.zynerator.util.StringUtil;
import ma.zs.gestion_service_pediatrie.zynerator.converter.AbstractConverter;
import ma.zs.gestion_service_pediatrie.zynerator.util.DateUtil;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.AnalyseMedicale;
import ma.zs.gestion_service_pediatrie.ws.dto.dossie.AnalyseMedicaleDto;

@Component
public class AnalyseMedicaleConverter {

    @Autowired
    private ConsultationConverter consultationConverter ;
    @Autowired
    private EpreuveConverter epreuveConverter ;
    private boolean epreuve;
    private boolean consultation;

    public  AnalyseMedicaleConverter() {
        initObject(true);
    }


    public AnalyseMedicale toItem(AnalyseMedicaleDto dto) {
        if (dto == null) {
            return null;
        } else {
        AnalyseMedicale item = new AnalyseMedicale();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getRef()))
                item.setRef(dto.getRef());
            if(StringUtil.isNotEmpty(dto.getDateAnalyseMedicale()))
                item.setDateAnalyseMedicale(DateUtil.stringEnToDate(dto.getDateAnalyseMedicale()));
            if(StringUtil.isNotEmpty(dto.getValeur()))
                item.setValeur(dto.getValeur());
            if(StringUtil.isNotEmpty(dto.getValeurRang()))
                item.setValeurRang(dto.getValeurRang());
            if(this.epreuve && dto.getEpreuve()!=null)
                item.setEpreuve(epreuveConverter.toItem(dto.getEpreuve())) ;

            if(dto.getConsultation() != null && dto.getConsultation().getId() != null){
                item.setConsultation(new Consultation());
                item.getConsultation().setId(dto.getConsultation().getId());
                item.getConsultation().setRef(dto.getConsultation().getRef());
            }




        return item;
        }
    }


    public AnalyseMedicaleDto toDto(AnalyseMedicale item) {
        if (item == null) {
            return null;
        } else {
            AnalyseMedicaleDto dto = new AnalyseMedicaleDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getRef()))
                dto.setRef(item.getRef());
            if(item.getDateAnalyseMedicale()!=null)
                dto.setDateAnalyseMedicale(DateUtil.dateTimeToString(item.getDateAnalyseMedicale()));
            if(StringUtil.isNotEmpty(item.getValeur()))
                dto.setValeur(item.getValeur());
            if(StringUtil.isNotEmpty(item.getValeurRang()))
                dto.setValeurRang(item.getValeurRang());
            if(this.epreuve && item.getEpreuve()!=null) {
                dto.setEpreuve(epreuveConverter.toDto(item.getEpreuve())) ;

            }
            if(this.consultation && item.getConsultation()!=null) {
                dto.setConsultation(consultationConverter.toDto(item.getConsultation())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.epreuve = value;
        this.consultation = value;
    }
	
    public List<AnalyseMedicale> toItem(List<AnalyseMedicaleDto> dtos) {
        List<AnalyseMedicale> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (AnalyseMedicaleDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<AnalyseMedicaleDto> toDto(List<AnalyseMedicale> items) {
        List<AnalyseMedicaleDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (AnalyseMedicale item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(AnalyseMedicaleDto dto, AnalyseMedicale t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getEpreuve() != null)
        epreuveConverter.copy(dto.getEpreuve(), t.getEpreuve());
        if (dto.getConsultation() != null)
        consultationConverter.copy(dto.getConsultation(), t.getConsultation());
    }

    public List<AnalyseMedicale> copy(List<AnalyseMedicaleDto> dtos) {
        List<AnalyseMedicale> result = new ArrayList<>();
        if (dtos != null) {
            for (AnalyseMedicaleDto dto : dtos) {
                AnalyseMedicale instance = new AnalyseMedicale();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public ConsultationConverter getConsultationConverter(){
        return this.consultationConverter;
    }
    public void setConsultationConverter(ConsultationConverter consultationConverter ){
        this.consultationConverter = consultationConverter;
    }
    public EpreuveConverter getEpreuveConverter(){
        return this.epreuveConverter;
    }
    public void setEpreuveConverter(EpreuveConverter epreuveConverter ){
        this.epreuveConverter = epreuveConverter;
    }
    public boolean  isEpreuve(){
        return this.epreuve;
    }
    public void  setEpreuve(boolean epreuve){
        this.epreuve = epreuve;
    }
    public boolean  isConsultation(){
        return this.consultation;
    }
    public void  setConsultation(boolean consultation){
        this.consultation = consultation;
    }
}
