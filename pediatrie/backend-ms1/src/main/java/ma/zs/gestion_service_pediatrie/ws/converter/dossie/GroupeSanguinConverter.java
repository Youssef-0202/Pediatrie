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
import ma.zs.gestion_service_pediatrie.bean.core.dossie.GroupeSanguin;
import ma.zs.gestion_service_pediatrie.ws.dto.dossie.GroupeSanguinDto;

@Component
public class GroupeSanguinConverter {


    public  GroupeSanguinConverter() {
    }


    public GroupeSanguin toItem(GroupeSanguinDto dto) {
        if (dto == null) {
            return null;
        } else {
        GroupeSanguin item = new GroupeSanguin();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getRef()))
                item.setRef(dto.getRef());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public GroupeSanguinDto toDto(GroupeSanguin item) {
        if (item == null) {
            return null;
        } else {
            GroupeSanguinDto dto = new GroupeSanguinDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getRef()))
                dto.setRef(item.getRef());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<GroupeSanguin> toItem(List<GroupeSanguinDto> dtos) {
        List<GroupeSanguin> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (GroupeSanguinDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<GroupeSanguinDto> toDto(List<GroupeSanguin> items) {
        List<GroupeSanguinDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (GroupeSanguin item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(GroupeSanguinDto dto, GroupeSanguin t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<GroupeSanguin> copy(List<GroupeSanguinDto> dtos) {
        List<GroupeSanguin> result = new ArrayList<>();
        if (dtos != null) {
            for (GroupeSanguinDto dto : dtos) {
                GroupeSanguin instance = new GroupeSanguin();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
