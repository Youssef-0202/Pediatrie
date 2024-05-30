package  ma.zs.gestion_service_pediatrie.ws.converter.dossie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.gestion_service_pediatrie.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.gestion_service_pediatrie.ws.converter.consultatio.ConsultationConverter;
import ma.zs.gestion_service_pediatrie.ws.converter.dossie.AntecedentConverter;

import ma.zs.gestion_service_pediatrie.bean.core.consultatio.Consultation;


import ma.zs.gestion_service_pediatrie.zynerator.util.StringUtil;
import ma.zs.gestion_service_pediatrie.zynerator.converter.AbstractConverter;
import ma.zs.gestion_service_pediatrie.zynerator.util.DateUtil;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.FichePatient;
import ma.zs.gestion_service_pediatrie.ws.dto.dossie.FichePatientDto;

@Component
public class FichePatientConverter {

    @Autowired
    private ConsultationConverter consultationConverter ;
    @Autowired
    private AntecedentConverter antecedentConverter ;
    private boolean antecedent;
    private boolean consultation;

    public  FichePatientConverter() {
        initObject(true);
    }


    public FichePatient toItem(FichePatientDto dto) {
        if (dto == null) {
            return null;
        } else {
        FichePatient item = new FichePatient();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getRef()))
                item.setRef(dto.getRef());
            if(StringUtil.isNotEmpty(dto.getDateFichePatient()))
                item.setDateFichePatient(DateUtil.stringEnToDate(dto.getDateFichePatient()));
            if(StringUtil.isNotEmpty(dto.getAntecedentFamillial()))
                item.setAntecedentFamillial(dto.getAntecedentFamillial());
            if(StringUtil.isNotEmpty(dto.getHistoireMaladie()))
                item.setHistoireMaladie(dto.getHistoireMaladie());
            if(StringUtil.isNotEmpty(dto.getExamenFichePatient()))
                item.setExamenFichePatient(dto.getExamenFichePatient());
            if(StringUtil.isNotEmpty(dto.getConclusionFichePatient()))
                item.setConclusionFichePatient(dto.getConclusionFichePatient());
            if(this.antecedent && dto.getAntecedent()!=null)
                item.setAntecedent(antecedentConverter.toItem(dto.getAntecedent())) ;

            if(dto.getConsultation() != null && dto.getConsultation().getId() != null){
                item.setConsultation(new Consultation());
                item.getConsultation().setId(dto.getConsultation().getId());
                item.getConsultation().setRef(dto.getConsultation().getRef());
            }




        return item;
        }
    }


    public FichePatientDto toDto(FichePatient item) {
        if (item == null) {
            return null;
        } else {
            FichePatientDto dto = new FichePatientDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getRef()))
                dto.setRef(item.getRef());
            if(item.getDateFichePatient()!=null)
                dto.setDateFichePatient(DateUtil.dateTimeToString(item.getDateFichePatient()));
            if(StringUtil.isNotEmpty(item.getAntecedentFamillial()))
                dto.setAntecedentFamillial(item.getAntecedentFamillial());
            if(StringUtil.isNotEmpty(item.getHistoireMaladie()))
                dto.setHistoireMaladie(item.getHistoireMaladie());
            if(StringUtil.isNotEmpty(item.getExamenFichePatient()))
                dto.setExamenFichePatient(item.getExamenFichePatient());
            if(StringUtil.isNotEmpty(item.getConclusionFichePatient()))
                dto.setConclusionFichePatient(item.getConclusionFichePatient());
            if(this.antecedent && item.getAntecedent()!=null) {
                dto.setAntecedent(antecedentConverter.toDto(item.getAntecedent())) ;

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
        this.antecedent = value;
        this.consultation = value;
    }
	
    public List<FichePatient> toItem(List<FichePatientDto> dtos) {
        List<FichePatient> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (FichePatientDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<FichePatientDto> toDto(List<FichePatient> items) {
        List<FichePatientDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (FichePatient item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(FichePatientDto dto, FichePatient t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getAntecedent() != null)
        antecedentConverter.copy(dto.getAntecedent(), t.getAntecedent());
        if (dto.getConsultation() != null)
        consultationConverter.copy(dto.getConsultation(), t.getConsultation());
    }

    public List<FichePatient> copy(List<FichePatientDto> dtos) {
        List<FichePatient> result = new ArrayList<>();
        if (dtos != null) {
            for (FichePatientDto dto : dtos) {
                FichePatient instance = new FichePatient();
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
    public AntecedentConverter getAntecedentConverter(){
        return this.antecedentConverter;
    }
    public void setAntecedentConverter(AntecedentConverter antecedentConverter ){
        this.antecedentConverter = antecedentConverter;
    }
    public boolean  isAntecedent(){
        return this.antecedent;
    }
    public void  setAntecedent(boolean antecedent){
        this.antecedent = antecedent;
    }
    public boolean  isConsultation(){
        return this.consultation;
    }
    public void  setConsultation(boolean consultation){
        this.consultation = consultation;
    }
}
