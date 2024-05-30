package  ma.zs.gestion_service_pediatrie.ws.converter.commun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.gestion_service_pediatrie.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;


import ma.zs.gestion_service_pediatrie.zynerator.util.StringUtil;
import ma.zs.gestion_service_pediatrie.zynerator.util.DateUtil;
import ma.zs.gestion_service_pediatrie.bean.core.commun.Infermier;
import ma.zs.gestion_service_pediatrie.ws.dto.commun.InfermierDto;

@Component
public class InfermierConverter {

    @Autowired
    private SexeConverter sexeConverter ;
    private boolean sexe;

    public  InfermierConverter() {
        initObject(true);
    }

    public Infermier toItem(InfermierDto dto) {
        if (dto == null) {
            return null;
        } else {
        Infermier item = new Infermier();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getRef()))
                item.setRef(dto.getRef());
            if(StringUtil.isNotEmpty(dto.getEmail()))
                item.setEmail(dto.getEmail());
            if(StringUtil.isNotEmpty(dto.getNom()))
                item.setNom(dto.getNom());
            if(StringUtil.isNotEmpty(dto.getPrenom()))
                item.setPrenom(dto.getPrenom());
            if(StringUtil.isNotEmpty(dto.getDateNaissance()))
                item.setDateNaissance(DateUtil.stringEnToDate(dto.getDateNaissance()));
            if(StringUtil.isNotEmpty(dto.getTelephone()))
                item.setTelephone(dto.getTelephone());
            item.setCredentialsNonExpired(dto.getCredentialsNonExpired());
            item.setEnabled(dto.getEnabled());
            item.setAccountNonExpired(dto.getAccountNonExpired());
            item.setAccountNonLocked(dto.getAccountNonLocked());
            item.setPasswordChanged(dto.getPasswordChanged());
            if(StringUtil.isNotEmpty(dto.getUsername()))
                item.setUsername(dto.getUsername());
            if(StringUtil.isNotEmpty(dto.getPassword()))
                item.setPassword(dto.getPassword());
            if(this.sexe && dto.getSexe()!=null)
                item.setSexe(sexeConverter.toItem(dto.getSexe())) ;
            if(dto.getCreatedAt() != null)
                item.setCreatedAt(dto.getCreatedAt());
            return item;
        }
    }


    public InfermierDto toDto(Infermier item) {
        if (item == null) {
            return null;
        } else {
            InfermierDto dto = new InfermierDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getRef()))
                dto.setRef(item.getRef());
            if(StringUtil.isNotEmpty(item.getEmail()))
                dto.setEmail(item.getEmail());
            if(StringUtil.isNotEmpty(item.getNom()))
                dto.setNom(item.getNom());
            if(StringUtil.isNotEmpty(item.getPrenom()))
                dto.setPrenom(item.getPrenom());
            if(item.getDateNaissance()!=null)
                dto.setDateNaissance(DateUtil.dateTimeToString(item.getDateNaissance()));
            if(StringUtil.isNotEmpty(item.getTelephone()))
                dto.setTelephone(item.getTelephone());
            if(StringUtil.isNotEmpty(item.getCredentialsNonExpired()))
                dto.setCredentialsNonExpired(item.getCredentialsNonExpired());
            if(StringUtil.isNotEmpty(item.getEnabled()))
                dto.setEnabled(item.getEnabled());
            if(StringUtil.isNotEmpty(item.getAccountNonExpired()))
                dto.setAccountNonExpired(item.getAccountNonExpired());
            if(StringUtil.isNotEmpty(item.getAccountNonLocked()))
                dto.setAccountNonLocked(item.getAccountNonLocked());
            if(StringUtil.isNotEmpty(item.getPasswordChanged()))
                dto.setPasswordChanged(item.getPasswordChanged());
            if(StringUtil.isNotEmpty(item.getUsername()))
                dto.setUsername(item.getUsername());
            if(StringUtil.isNotEmpty(item.getPassword()))
                dto.setPassword(item.getPassword());
            if(this.sexe && item.getSexe()!=null) {
                dto.setSexe(sexeConverter.toDto(item.getSexe())) ;

            }
            if(item.getCreatedAt() != null)
                dto.setCreatedAt(item.getCreatedAt());


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.sexe = value;
    }
	
    public List<Infermier> toItem(List<InfermierDto> dtos) {
        List<Infermier> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (InfermierDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<InfermierDto> toDto(List<Infermier> items) {
        List<InfermierDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Infermier item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(InfermierDto dto, Infermier t) {
		if(t != null){
            BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
            if (dto.getSexe() != null)
                sexeConverter.copy(dto.getSexe(), t.getSexe());
        }
    }

    public List<Infermier> copy(List<InfermierDto> dtos) {
        List<Infermier> result = new ArrayList<>();
        if (dtos != null) {
            for (InfermierDto dto : dtos) {
                Infermier instance = new Infermier();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
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
}
