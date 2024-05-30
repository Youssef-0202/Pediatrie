package  ma.zs.gestion_service_pediatrie.ws.converter.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.gestion_service_pediatrie.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zs.gestion_service_pediatrie.zynerator.util.ListUtil;

import ma.zs.gestion_service_pediatrie.ws.converter.patient.PatientContactConverter;
import ma.zs.gestion_service_pediatrie.ws.converter.patient.RelationConverter;
import ma.zs.gestion_service_pediatrie.ws.converter.commun.SexeConverter;



import ma.zs.gestion_service_pediatrie.zynerator.util.StringUtil;
import ma.zs.gestion_service_pediatrie.zynerator.converter.AbstractConverter;
import ma.zs.gestion_service_pediatrie.zynerator.util.DateUtil;
import ma.zs.gestion_service_pediatrie.bean.core.patient.Patient;
import ma.zs.gestion_service_pediatrie.ws.dto.patient.PatientDto;

@Component
public class PatientConverter {

    @Autowired
    private PatientContactConverter patientContactConverter ;
    @Autowired
    private RelationConverter relationConverter ;
    @Autowired
    private SexeConverter sexeConverter ;
    private boolean sexe;
    private boolean patientContact;

    public  PatientConverter() {
        init(true);
    }


    public Patient toItem(PatientDto dto) {
        if (dto == null) {
            return null;
        } else {
        Patient item = new Patient();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getNumDossier()))
                item.setNumDossier(dto.getNumDossier());
            if(StringUtil.isNotEmpty(dto.getNom()))
                item.setNom(dto.getNom());
            if(StringUtil.isNotEmpty(dto.getPrenom()))
                item.setPrenom(dto.getPrenom());
            if(StringUtil.isNotEmpty(dto.getDateNaissance()))
                item.setDateNaissance(DateUtil.stringEnToDate(dto.getDateNaissance()));
            if(StringUtil.isNotEmpty(dto.getPhotoProfil()))
                item.setPhotoProfil(dto.getPhotoProfil());
            if(this.sexe && dto.getSexe()!=null)
                item.setSexe(sexeConverter.toItem(dto.getSexe())) ;
            if(dto.getCreatedAt()!=null){
                item.setCreatedAt(dto.getCreatedAt());
            }


            if(this.patientContact && ListUtil.isNotEmpty(dto.getPatientContact()))
                item.setPatientContact(patientContactConverter.toItem(dto.getPatientContact()));


        return item;
        }
    }


    public PatientDto toDto(Patient item) {
        if (item == null) {
            return null;
        } else {
            PatientDto dto = new PatientDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getNumDossier()))
                dto.setNumDossier(item.getNumDossier());
            if(StringUtil.isNotEmpty(item.getNom()))
                dto.setNom(item.getNom());
            if(StringUtil.isNotEmpty(item.getPrenom()))
                dto.setPrenom(item.getPrenom());
            if(item.getDateNaissance()!=null)
                dto.setDateNaissance(DateUtil.dateTimeToString(item.getDateNaissance()));
            if(StringUtil.isNotEmpty(item.getPhotoProfil()))
                dto.setPhotoProfil(item.getPhotoProfil());
            if(this.sexe && item.getSexe()!=null) {
                dto.setSexe(sexeConverter.toDto(item.getSexe())) ;

            }
            if(item.getCreatedAt()!=null){
                dto.setCreatedAt(item.getCreatedAt());
            }
        if(this.patientContact && ListUtil.isNotEmpty(item.getPatientContact())){
            patientContactConverter.init(true);
            patientContactConverter.setPatient(false);
            dto.setPatientContact(patientContactConverter.toDto(item.getPatientContact()));
            patientContactConverter.setPatient(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.patientContact = value;
    }
    public void initObject(boolean value) {
        this.sexe = value;
    }
	
    public List<Patient> toItem(List<PatientDto> dtos) {
        List<Patient> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (PatientDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<PatientDto> toDto(List<Patient> items) {
        List<PatientDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Patient item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(PatientDto dto, Patient t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getSexe() != null)
        sexeConverter.copy(dto.getSexe(), t.getSexe());
        if (dto.getPatientContact() != null)
            t.setPatientContact(patientContactConverter.copy(dto.getPatientContact()));
    }

    public List<Patient> copy(List<PatientDto> dtos) {
        List<Patient> result = new ArrayList<>();
        if (dtos != null) {
            for (PatientDto dto : dtos) {
                Patient instance = new Patient();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public PatientContactConverter getPatientContactConverter(){
        return this.patientContactConverter;
    }
    public void setPatientContactConverter(PatientContactConverter patientContactConverter ){
        this.patientContactConverter = patientContactConverter;
    }
    public RelationConverter getRelationConverter(){
        return this.relationConverter;
    }
    public void setRelationConverter(RelationConverter relationConverter ){
        this.relationConverter = relationConverter;
    }
    public SexeConverter getSexeConverter(){
        return this.sexeConverter;
    }
    public void setSexeConverter(SexeConverter sexeConverter ){
        this.sexeConverter = sexeConverter;
    }
    public boolean  isSexe(){
        return this.sexe;
    }
    public void  setSexe(boolean sexe){
        this.sexe = sexe;
    }
    public boolean  isPatientContact(){
        return this.patientContact ;
    }
    public void  setPatientContact(boolean patientContact ){
        this.patientContact  = patientContact ;
    }
}
