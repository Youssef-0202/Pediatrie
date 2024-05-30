package  ma.zs.gestion_service_pediatrie.ws.converter.dossie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.gestion_service_pediatrie.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zs.gestion_service_pediatrie.zynerator.util.StringUtil;
import ma.zs.gestion_service_pediatrie.zynerator.converter.AbstractConverter;
import ma.zs.gestion_service_pediatrie.zynerator.util.DateUtil;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.TypeImage;
import ma.zs.gestion_service_pediatrie.ws.dto.dossie.TypeImageDto;

@Component
public class TypeImageConverter {


    public  TypeImageConverter() {
    }


    public TypeImage toItem(TypeImageDto dto) {
        if (dto == null) {
            return null;
        } else {
        TypeImage item = new TypeImage();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getRef()))
                item.setRef(dto.getRef());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public TypeImageDto toDto(TypeImage item) {
        if (item == null) {
            return null;
        } else {
            TypeImageDto dto = new TypeImageDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getRef()))
                dto.setRef(item.getRef());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<TypeImage> toItem(List<TypeImageDto> dtos) {
        List<TypeImage> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (TypeImageDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<TypeImageDto> toDto(List<TypeImage> items) {
        List<TypeImageDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (TypeImage item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(TypeImageDto dto, TypeImage t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<TypeImage> copy(List<TypeImageDto> dtos) {
        List<TypeImage> result = new ArrayList<>();
        if (dtos != null) {
            for (TypeImageDto dto : dtos) {
                TypeImage instance = new TypeImage();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
