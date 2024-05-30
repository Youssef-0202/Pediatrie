package  ma.zs.gestion_service_pediatrie.ws.converter.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.gestion_service_pediatrie.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.gestion_service_pediatrie.ws.converter.patient.RelationConverter;
import ma.zs.gestion_service_pediatrie.ws.converter.patient.PatientConverter;

import ma.zs.gestion_service_pediatrie.bean.core.patient.Patient;


import ma.zs.gestion_service_pediatrie.zynerator.util.StringUtil;
import ma.zs.gestion_service_pediatrie.zynerator.converter.AbstractConverter;
import ma.zs.gestion_service_pediatrie.zynerator.util.DateUtil;
import ma.zs.gestion_service_pediatrie.bean.core.patient.PatientContact;
import ma.zs.gestion_service_pediatrie.ws.dto.patient.PatientContactDto;

@Component
public class PatientContactConverter {

    @Autowired
    private RelationConverter relationConverter ;
    @Autowired
    private PatientConverter patientConverter ;
    private boolean relation;
    private boolean patient;

    public  PatientContactConverter() {
        initObject(true);
    }


    public PatientContact toItem(PatientContactDto dto) {
        if (dto == null) {
            return null;
        } else {
        PatientContact item = new PatientContact();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCin()))
                item.setCin(dto.getCin());
            if(StringUtil.isNotEmpty(dto.getEmail()))
                item.setEmail(dto.getEmail());
            if(StringUtil.isNotEmpty(dto.getNom()))
                item.setNom(dto.getNom());
            if(StringUtil.isNotEmpty(dto.getPrenom()))
                item.setPrenom(dto.getPrenom());
            if(StringUtil.isNotEmpty(dto.getTelephone()))
                item.setTelephone(dto.getTelephone());
            if(StringUtil.isNotEmpty(dto.getAdresse()))
                item.setAdresse(dto.getAdresse());
            if(this.relation && dto.getRelation()!=null)
                item.setRelation(relationConverter.toItem(dto.getRelation())) ;

            if(dto.getPatient() != null && dto.getPatient().getId() != null){
                item.setPatient(new Patient());
                item.getPatient().setId(dto.getPatient().getId());
                item.getPatient().setNumDossier(dto.getPatient().getNumDossier());
            }




        return item;
        }
    }


    public PatientContactDto toDto(PatientContact item) {
        if (item == null) {
            return null;
        } else {
            PatientContactDto dto = new PatientContactDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCin()))
                dto.setCin(item.getCin());
            if(StringUtil.isNotEmpty(item.getEmail()))
                dto.setEmail(item.getEmail());
            if(StringUtil.isNotEmpty(item.getNom()))
                dto.setNom(item.getNom());
            if(StringUtil.isNotEmpty(item.getPrenom()))
                dto.setPrenom(item.getPrenom());
            if(StringUtil.isNotEmpty(item.getTelephone()))
                dto.setTelephone(item.getTelephone());
            if(StringUtil.isNotEmpty(item.getAdresse()))
                dto.setAdresse(item.getAdresse());
            if(this.relation && item.getRelation()!=null) {
                dto.setRelation(relationConverter.toDto(item.getRelation())) ;

            }
            if(this.patient && item.getPatient()!=null) {
                dto.setPatient(patientConverter.toDto(item.getPatient())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.relation = value;
        this.patient = value;
    }
	
    public List<PatientContact> toItem(List<PatientContactDto> dtos) {
        List<PatientContact> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (PatientContactDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<PatientContactDto> toDto(List<PatientContact> items) {
        List<PatientContactDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (PatientContact item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(PatientContactDto dto, PatientContact t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getRelation() != null)
        relationConverter.copy(dto.getRelation(), t.getRelation());
        if (dto.getPatient() != null)
        patientConverter.copy(dto.getPatient(), t.getPatient());
    }

    public List<PatientContact> copy(List<PatientContactDto> dtos) {
        List<PatientContact> result = new ArrayList<>();
        if (dtos != null) {
            for (PatientContactDto dto : dtos) {
                PatientContact instance = new PatientContact();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public RelationConverter getRelationConverter(){
        return this.relationConverter;
    }
    public void setRelationConverter(RelationConverter relationConverter ){
        this.relationConverter = relationConverter;
    }
    public PatientConverter getPatientConverter(){
        return this.patientConverter;
    }
    public void setPatientConverter(PatientConverter patientConverter ){
        this.patientConverter = patientConverter;
    }
    public boolean  isRelation(){
        return this.relation;
    }
    public void  setRelation(boolean relation){
        this.relation = relation;
    }
    public boolean  isPatient(){
        return this.patient;
    }
    public void  setPatient(boolean patient){
        this.patient = patient;
    }
}
