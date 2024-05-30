package  ma.zs.gestion_service_pediatrie.ws.converter.gestio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.gestion_service_pediatrie.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.gestion_service_pediatrie.ws.converter.consultatio.ConsultationConverter;

import ma.zs.gestion_service_pediatrie.bean.core.consultatio.Consultation;


import ma.zs.gestion_service_pediatrie.zynerator.util.StringUtil;
import ma.zs.gestion_service_pediatrie.zynerator.converter.AbstractConverter;
import ma.zs.gestion_service_pediatrie.zynerator.util.DateUtil;
import ma.zs.gestion_service_pediatrie.bean.core.gestio.Certificat;
import ma.zs.gestion_service_pediatrie.ws.dto.gestio.CertificatDto;

@Component
public class CertificatConverter {

    @Autowired
    private ConsultationConverter consultationConverter ;
    private boolean consultatuin;

    public  CertificatConverter() {
        initObject(true);
    }


    public Certificat toItem(CertificatDto dto) {
        if (dto == null) {
            return null;
        } else {
        Certificat item = new Certificat();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getRef()))
                item.setRef(dto.getRef());
            if(StringUtil.isNotEmpty(dto.getDateDebut()))
                item.setDateDebut(DateUtil.stringEnToDate(dto.getDateDebut()));
            if(StringUtil.isNotEmpty(dto.getDateFin()))
                item.setDateFin(DateUtil.stringEnToDate(dto.getDateFin()));
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getNbrJour()))
                item.setNbrJour(dto.getNbrJour());
            if(dto.getConsultatuin() != null && dto.getConsultatuin().getId() != null){
                item.setConsultatuin(new Consultation());
                item.getConsultatuin().setId(dto.getConsultatuin().getId());
                item.getConsultatuin().setRef(dto.getConsultatuin().getRef());
            }




        return item;
        }
    }


    public CertificatDto toDto(Certificat item) {
        if (item == null) {
            return null;
        } else {
            CertificatDto dto = new CertificatDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getRef()))
                dto.setRef(item.getRef());
            if(item.getDateDebut()!=null)
                dto.setDateDebut(DateUtil.dateTimeToString(item.getDateDebut()));
            if(item.getDateFin()!=null)
                dto.setDateFin(DateUtil.dateTimeToString(item.getDateFin()));
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(StringUtil.isNotEmpty(item.getNbrJour()))
                dto.setNbrJour(item.getNbrJour());
            if(this.consultatuin && item.getConsultatuin()!=null) {
                dto.setConsultatuin(consultationConverter.toDto(item.getConsultatuin())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.consultatuin = value;
    }
	
    public List<Certificat> toItem(List<CertificatDto> dtos) {
        List<Certificat> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (CertificatDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<CertificatDto> toDto(List<Certificat> items) {
        List<CertificatDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Certificat item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(CertificatDto dto, Certificat t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getConsultatuin() != null)
        consultationConverter.copy(dto.getConsultatuin(), t.getConsultatuin());
    }

    public List<Certificat> copy(List<CertificatDto> dtos) {
        List<Certificat> result = new ArrayList<>();
        if (dtos != null) {
            for (CertificatDto dto : dtos) {
                Certificat instance = new Certificat();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public ConsultationConverter getConsultationConverter(){
        return this.consultationConverter;
    }
    public void setConsultationConverter(ConsultationConverter consultationConverter ){
        this.consultationConverter = consultationConverter;
    }
    public boolean  isConsultatuin(){
        return this.consultatuin;
    }
    public void  setConsultatuin(boolean consultatuin){
        this.consultatuin = consultatuin;
    }
}
