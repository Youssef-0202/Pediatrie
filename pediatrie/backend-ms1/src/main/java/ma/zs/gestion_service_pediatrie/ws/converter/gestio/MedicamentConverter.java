package  ma.zs.gestion_service_pediatrie.ws.converter.gestio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.gestion_service_pediatrie.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.gestion_service_pediatrie.ws.converter.gestio.TraitementConverter;

import ma.zs.gestion_service_pediatrie.bean.core.gestio.Traitement;


import ma.zs.gestion_service_pediatrie.zynerator.util.StringUtil;
import ma.zs.gestion_service_pediatrie.zynerator.converter.AbstractConverter;
import ma.zs.gestion_service_pediatrie.zynerator.util.DateUtil;
import ma.zs.gestion_service_pediatrie.bean.core.gestio.Medicament;
import ma.zs.gestion_service_pediatrie.ws.dto.gestio.MedicamentDto;

@Component
public class MedicamentConverter {

    @Autowired
    private TraitementConverter traitementConverter ;
    private boolean traitement;

    public  MedicamentConverter() {
        initObject(true);
    }


    public Medicament toItem(MedicamentDto dto) {
        if (dto == null) {
            return null;
        } else {
        Medicament item = new Medicament();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getRef()))
                item.setRef(dto.getRef());
            if(StringUtil.isNotEmpty(dto.getSointProduit()))
                item.setSointProduit(dto.getSointProduit());
            if(StringUtil.isNotEmpty(dto.getDuree()))
                item.setDuree(dto.getDuree());
            if(StringUtil.isNotEmpty(dto.getConsigne()))
                item.setConsigne(dto.getConsigne());
            if(dto.getTraitement() != null && dto.getTraitement().getId() != null){
                item.setTraitement(new Traitement());
                item.getTraitement().setId(dto.getTraitement().getId());
                item.getTraitement().setRef(dto.getTraitement().getRef());
            }




        return item;
        }
    }


    public MedicamentDto toDto(Medicament item) {
        if (item == null) {
            return null;
        } else {
            MedicamentDto dto = new MedicamentDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getRef()))
                dto.setRef(item.getRef());
            if(StringUtil.isNotEmpty(item.getSointProduit()))
                dto.setSointProduit(item.getSointProduit());
            if(StringUtil.isNotEmpty(item.getDuree()))
                dto.setDuree(item.getDuree());
            if(StringUtil.isNotEmpty(item.getConsigne()))
                dto.setConsigne(item.getConsigne());
            if(this.traitement && item.getTraitement()!=null) {
                dto.setTraitement(traitementConverter.toDto(item.getTraitement())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.traitement = value;
    }
	
    public List<Medicament> toItem(List<MedicamentDto> dtos) {
        List<Medicament> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (MedicamentDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<MedicamentDto> toDto(List<Medicament> items) {
        List<MedicamentDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Medicament item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(MedicamentDto dto, Medicament t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getTraitement() != null)
        traitementConverter.copy(dto.getTraitement(), t.getTraitement());
    }

    public List<Medicament> copy(List<MedicamentDto> dtos) {
        List<Medicament> result = new ArrayList<>();
        if (dtos != null) {
            for (MedicamentDto dto : dtos) {
                Medicament instance = new Medicament();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public TraitementConverter getTraitementConverter(){
        return this.traitementConverter;
    }
    public void setTraitementConverter(TraitementConverter traitementConverter ){
        this.traitementConverter = traitementConverter;
    }
    public boolean  isTraitement(){
        return this.traitement;
    }
    public void  setTraitement(boolean traitement){
        this.traitement = traitement;
    }
}
