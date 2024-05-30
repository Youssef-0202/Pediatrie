package  ma.zs.gestion_service_pediatrie.ws.converter.commun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.gestion_service_pediatrie.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zs.gestion_service_pediatrie.zynerator.util.StringUtil;
import ma.zs.gestion_service_pediatrie.zynerator.converter.AbstractConverter;
import ma.zs.gestion_service_pediatrie.zynerator.util.DateUtil;
import ma.zs.gestion_service_pediatrie.bean.core.commun.Sexe;
import ma.zs.gestion_service_pediatrie.ws.dto.commun.SexeDto;

@Component
public class SexeConverter {


    public  SexeConverter() {
    }


    public Sexe toItem(SexeDto dto) {
        if (dto == null) {
            return null;
        } else {
        Sexe item = new Sexe();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getRef()))
                item.setRef(dto.getRef());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public SexeDto toDto(Sexe item) {
        if (item == null) {
            return null;
        } else {
            SexeDto dto = new SexeDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getRef()))
                dto.setRef(item.getRef());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<Sexe> toItem(List<SexeDto> dtos) {
        List<Sexe> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (SexeDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<SexeDto> toDto(List<Sexe> items) {
        List<SexeDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Sexe item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(SexeDto dto, Sexe t) {
        if(t!=null){
            BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        }
    }

    public List<Sexe> copy(List<SexeDto> dtos) {
        List<Sexe> result = new ArrayList<>();
        if (dtos != null) {
            for (SexeDto dto : dtos) {
                Sexe instance = new Sexe();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
