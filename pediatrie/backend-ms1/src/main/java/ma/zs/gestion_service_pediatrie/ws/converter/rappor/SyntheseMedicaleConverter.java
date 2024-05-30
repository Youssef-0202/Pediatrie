package  ma.zs.gestion_service_pediatrie.ws.converter.rappor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.gestion_service_pediatrie.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.gestion_service_pediatrie.ws.converter.consultatio.ConsultationConverter;

import ma.zs.gestion_service_pediatrie.bean.core.consultatio.Consultation;


import ma.zs.gestion_service_pediatrie.zynerator.util.StringUtil;
import ma.zs.gestion_service_pediatrie.zynerator.converter.AbstractConverter;
import ma.zs.gestion_service_pediatrie.zynerator.util.DateUtil;
import ma.zs.gestion_service_pediatrie.bean.core.rappor.SyntheseMedicale;
import ma.zs.gestion_service_pediatrie.ws.dto.rappor.SyntheseMedicaleDto;

@Component
public class SyntheseMedicaleConverter {

    @Autowired
    private ConsultationConverter consultationConverter ;
    private boolean consultation;

    public  SyntheseMedicaleConverter() {
        initObject(true);
    }


    public SyntheseMedicale toItem(SyntheseMedicaleDto dto) {
        if (dto == null) {
            return null;
        } else {
        SyntheseMedicale item = new SyntheseMedicale();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getRef()))
                item.setRef(dto.getRef());
            if(StringUtil.isNotEmpty(dto.getDateSyntheseMedicale()))
                item.setDateSyntheseMedicale(DateUtil.stringEnToDate(dto.getDateSyntheseMedicale()));
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(dto.getConsultation() != null && dto.getConsultation().getId() != null){
                item.setConsultation(new Consultation());
                item.getConsultation().setId(dto.getConsultation().getId());
                item.getConsultation().setRef(dto.getConsultation().getRef());
            }




        return item;
        }
    }


    public SyntheseMedicaleDto toDto(SyntheseMedicale item) {
        if (item == null) {
            return null;
        } else {
            SyntheseMedicaleDto dto = new SyntheseMedicaleDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getRef()))
                dto.setRef(item.getRef());
            if(item.getDateSyntheseMedicale()!=null)
                dto.setDateSyntheseMedicale(DateUtil.dateTimeToString(item.getDateSyntheseMedicale()));
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
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
        this.consultation = value;
    }
	
    public List<SyntheseMedicale> toItem(List<SyntheseMedicaleDto> dtos) {
        List<SyntheseMedicale> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (SyntheseMedicaleDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<SyntheseMedicaleDto> toDto(List<SyntheseMedicale> items) {
        List<SyntheseMedicaleDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (SyntheseMedicale item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(SyntheseMedicaleDto dto, SyntheseMedicale t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getConsultation() != null)
        consultationConverter.copy(dto.getConsultation(), t.getConsultation());
    }

    public List<SyntheseMedicale> copy(List<SyntheseMedicaleDto> dtos) {
        List<SyntheseMedicale> result = new ArrayList<>();
        if (dtos != null) {
            for (SyntheseMedicaleDto dto : dtos) {
                SyntheseMedicale instance = new SyntheseMedicale();
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
    public boolean  isConsultation(){
        return this.consultation;
    }
    public void  setConsultation(boolean consultation){
        this.consultation = consultation;
    }
}
