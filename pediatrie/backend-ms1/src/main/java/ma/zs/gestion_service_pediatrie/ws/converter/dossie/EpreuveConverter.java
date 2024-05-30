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
import ma.zs.gestion_service_pediatrie.bean.core.dossie.Epreuve;
import ma.zs.gestion_service_pediatrie.ws.dto.dossie.EpreuveDto;

@Component
public class EpreuveConverter {


    public  EpreuveConverter() {
    }


    public Epreuve toItem(EpreuveDto dto) {
        if (dto == null) {
            return null;
        } else {
        Epreuve item = new Epreuve();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getRef()))
                item.setRef(dto.getRef());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public EpreuveDto toDto(Epreuve item) {
        if (item == null) {
            return null;
        } else {
            EpreuveDto dto = new EpreuveDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getRef()))
                dto.setRef(item.getRef());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<Epreuve> toItem(List<EpreuveDto> dtos) {
        List<Epreuve> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (EpreuveDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<EpreuveDto> toDto(List<Epreuve> items) {
        List<EpreuveDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Epreuve item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(EpreuveDto dto, Epreuve t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Epreuve> copy(List<EpreuveDto> dtos) {
        List<Epreuve> result = new ArrayList<>();
        if (dtos != null) {
            for (EpreuveDto dto : dtos) {
                Epreuve instance = new Epreuve();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
