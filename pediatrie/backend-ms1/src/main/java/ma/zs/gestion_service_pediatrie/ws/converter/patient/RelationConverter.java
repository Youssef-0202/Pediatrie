package  ma.zs.gestion_service_pediatrie.ws.converter.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.gestion_service_pediatrie.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zs.gestion_service_pediatrie.zynerator.util.StringUtil;
import ma.zs.gestion_service_pediatrie.zynerator.converter.AbstractConverter;
import ma.zs.gestion_service_pediatrie.zynerator.util.DateUtil;
import ma.zs.gestion_service_pediatrie.bean.core.patient.Relation;
import ma.zs.gestion_service_pediatrie.ws.dto.patient.RelationDto;

@Component
public class RelationConverter {


    public  RelationConverter() {
    }


    public Relation toItem(RelationDto dto) {
        if (dto == null) {
            return null;
        } else {
        Relation item = new Relation();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getRef()))
                item.setRef(dto.getRef());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public RelationDto toDto(Relation item) {
        if (item == null) {
            return null;
        } else {
            RelationDto dto = new RelationDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getRef()))
                dto.setRef(item.getRef());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<Relation> toItem(List<RelationDto> dtos) {
        List<Relation> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (RelationDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<RelationDto> toDto(List<Relation> items) {
        List<RelationDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Relation item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(RelationDto dto, Relation t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Relation> copy(List<RelationDto> dtos) {
        List<Relation> result = new ArrayList<>();
        if (dtos != null) {
            for (RelationDto dto : dtos) {
                Relation instance = new Relation();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
