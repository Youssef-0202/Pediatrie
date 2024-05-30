package  ma.zs.gestion_service_pediatrie.ws.converter.dossie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.gestion_service_pediatrie.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.gestion_service_pediatrie.ws.converter.dossie.GroupeSanguinConverter;



import ma.zs.gestion_service_pediatrie.zynerator.util.StringUtil;
import ma.zs.gestion_service_pediatrie.zynerator.converter.AbstractConverter;
import ma.zs.gestion_service_pediatrie.zynerator.util.DateUtil;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.Antecedent;
import ma.zs.gestion_service_pediatrie.ws.dto.dossie.AntecedentDto;

@Component
public class AntecedentConverter {

    @Autowired
    private GroupeSanguinConverter groupeSanguinConverter ;
    private boolean groupeSanguin;

    public  AntecedentConverter() {
        initObject(true);
    }


    public Antecedent toItem(AntecedentDto dto) {
        if (dto == null) {
            return null;
        } else {
        Antecedent item = new Antecedent();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getRef()))
                item.setRef(dto.getRef());
            if(StringUtil.isNotEmpty(dto.getAllergie()))
                item.setAllergie(dto.getAllergie());
            if(StringUtil.isNotEmpty(dto.getEtat_psy()))
                item.setEtat_psy(dto.getEtat_psy());
            if(StringUtil.isNotEmpty(dto.getRespiratoire()))
                item.setRespiratoire(dto.getRespiratoire());
            if(StringUtil.isNotEmpty(dto.getAlimentation()))
                item.setAlimentation(dto.getAlimentation());
            if(StringUtil.isNotEmpty(dto.getMouvement()))
                item.setMouvement(dto.getMouvement());
            if(StringUtil.isNotEmpty(dto.getSommeil()))
                item.setSommeil(dto.getSommeil());
            if(this.groupeSanguin && dto.getGroupeSanguin()!=null)
                item.setGroupeSanguin(groupeSanguinConverter.toItem(dto.getGroupeSanguin())) ;




        return item;
        }
    }


    public AntecedentDto toDto(Antecedent item) {
        if (item == null) {
            return null;
        } else {
            AntecedentDto dto = new AntecedentDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getRef()))
                dto.setRef(item.getRef());
            if(StringUtil.isNotEmpty(item.getAllergie()))
                dto.setAllergie(item.getAllergie());
            if(StringUtil.isNotEmpty(item.getEtat_psy()))
                dto.setEtat_psy(item.getEtat_psy());
            if(StringUtil.isNotEmpty(item.getRespiratoire()))
                dto.setRespiratoire(item.getRespiratoire());
            if(StringUtil.isNotEmpty(item.getAlimentation()))
                dto.setAlimentation(item.getAlimentation());
            if(StringUtil.isNotEmpty(item.getMouvement()))
                dto.setMouvement(item.getMouvement());
            if(StringUtil.isNotEmpty(item.getSommeil()))
                dto.setSommeil(item.getSommeil());
            if(this.groupeSanguin && item.getGroupeSanguin()!=null) {
                dto.setGroupeSanguin(groupeSanguinConverter.toDto(item.getGroupeSanguin())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.groupeSanguin = value;
    }
	
    public List<Antecedent> toItem(List<AntecedentDto> dtos) {
        List<Antecedent> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (AntecedentDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<AntecedentDto> toDto(List<Antecedent> items) {
        List<AntecedentDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Antecedent item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(AntecedentDto dto, Antecedent t) {
		if(t!=null){
            BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
            if (dto.getGroupeSanguin() != null)
                groupeSanguinConverter.copy(dto.getGroupeSanguin(), t.getGroupeSanguin());
        }
    }

    public List<Antecedent> copy(List<AntecedentDto> dtos) {
        List<Antecedent> result = new ArrayList<>();
        if (dtos != null) {
            for (AntecedentDto dto : dtos) {
                Antecedent instance = new Antecedent();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public GroupeSanguinConverter getGroupeSanguinConverter(){
        return this.groupeSanguinConverter;
    }
    public void setGroupeSanguinConverter(GroupeSanguinConverter groupeSanguinConverter ){
        this.groupeSanguinConverter = groupeSanguinConverter;
    }
    public boolean  isGroupeSanguin(){
        return this.groupeSanguin;
    }
    public void  setGroupeSanguin(boolean groupeSanguin){
        this.groupeSanguin = groupeSanguin;
    }
}
