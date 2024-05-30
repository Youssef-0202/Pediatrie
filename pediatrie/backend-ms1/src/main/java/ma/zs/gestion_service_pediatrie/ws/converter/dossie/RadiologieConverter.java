package  ma.zs.gestion_service_pediatrie.ws.converter.dossie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.gestion_service_pediatrie.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.gestion_service_pediatrie.ws.converter.consultatio.ConsultationConverter;

import ma.zs.gestion_service_pediatrie.bean.core.consultatio.Consultation;


import ma.zs.gestion_service_pediatrie.zynerator.util.StringUtil;
import ma.zs.gestion_service_pediatrie.zynerator.util.DateUtil;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.Radiologie;
import ma.zs.gestion_service_pediatrie.ws.dto.dossie.RadiologieDto;

@Component
public class RadiologieConverter {

    @Autowired
    private ConsultationConverter consultationConverter ;
    @Autowired
    private TypeImageConverter typeImageConverter ;
    private boolean consultatuin;
    private boolean typeImage;

    public  RadiologieConverter() {
        initObject(true);
    }


    public Radiologie toItem(RadiologieDto dto) {
        if (dto == null) {
            return null;
        } else {
        Radiologie item = new Radiologie();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getRef()))
                item.setRef(dto.getRef());
            if(StringUtil.isNotEmpty(dto.getDateRadiologie()))
                item.setDateRadiologie(DateUtil.stringEnToDate(dto.getDateRadiologie()));
            if(StringUtil.isNotEmpty(dto.getCommentaire()))
                item.setCommentaire(dto.getCommentaire());
            if(StringUtil.isNotEmpty(dto.getImageScann()))
                item.setImageScann(dto.getImageScann());
            if(dto.getConsultationDto() != null && dto.getConsultationDto().getId() != null){
                item.setConsultation(new Consultation());
                item.getConsultation().setId(dto.getConsultationDto().getId());
                item.getConsultation().setRef(dto.getConsultationDto().getRef());
            }

            if(this.typeImage && dto.getTypeImage()!=null)
                item.setTypeImage(typeImageConverter.toItem(dto.getTypeImage())) ;




        return item;
        }
    }


    public RadiologieDto toDto(Radiologie item) {
        if (item == null) {
            return null;
        } else {
            RadiologieDto dto = new RadiologieDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getRef()))
                dto.setRef(item.getRef());
            if(item.getDateRadiologie()!=null)
                dto.setDateRadiologie(DateUtil.dateTimeToString(item.getDateRadiologie()));
            if(StringUtil.isNotEmpty(item.getCommentaire()))
                dto.setCommentaire(item.getCommentaire());
            if(StringUtil.isNotEmpty(item.getImageScann()))
                dto.setImageScann(item.getImageScann());
            if(this.consultatuin && item.getConsultation()!=null) {
                dto.setConsultationDto(consultationConverter.toDto(item.getConsultation())) ;

            }
            if(this.typeImage && item.getTypeImage()!=null) {
                dto.setTypeImage(typeImageConverter.toDto(item.getTypeImage())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.consultatuin = value;
        this.typeImage = value;
    }
	
    public List<Radiologie> toItem(List<RadiologieDto> dtos) {
        List<Radiologie> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (RadiologieDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<RadiologieDto> toDto(List<Radiologie> items) {
        List<RadiologieDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Radiologie item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(RadiologieDto dto, Radiologie t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getConsultationDto() != null)
        consultationConverter.copy(dto.getConsultationDto(), t.getConsultation());
        if (dto.getTypeImage() != null)
        typeImageConverter.copy(dto.getTypeImage(), t.getTypeImage());
    }

    public List<Radiologie> copy(List<RadiologieDto> dtos) {
        List<Radiologie> result = new ArrayList<>();
        if (dtos != null) {
            for (RadiologieDto dto : dtos) {
                Radiologie instance = new Radiologie();
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
    public TypeImageConverter getTypeImageConverter(){
        return this.typeImageConverter;
    }
    public void setTypeImageConverter(TypeImageConverter typeImageConverter ){
        this.typeImageConverter = typeImageConverter;
    }
    public boolean  isConsultatuin(){
        return this.consultatuin;
    }
    public void  setConsultatuin(boolean consultatuin){
        this.consultatuin = consultatuin;
    }
    public boolean  isTypeImage(){
        return this.typeImage;
    }
    public void  setTypeImage(boolean typeImage){
        this.typeImage = typeImage;
    }
}
