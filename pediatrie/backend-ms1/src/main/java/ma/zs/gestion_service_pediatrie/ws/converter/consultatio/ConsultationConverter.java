package  ma.zs.gestion_service_pediatrie.ws.converter.consultatio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.gestion_service_pediatrie.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zs.gestion_service_pediatrie.zynerator.util.ListUtil;

import ma.zs.gestion_service_pediatrie.ws.converter.dossie.FichePatientConverter;
import ma.zs.gestion_service_pediatrie.ws.converter.dossie.RadiologieConverter;
import ma.zs.gestion_service_pediatrie.ws.converter.commun.InfermierConverter;
import ma.zs.gestion_service_pediatrie.ws.converter.commun.MedecinConverter;
import ma.zs.gestion_service_pediatrie.ws.converter.dossie.EpreuveConverter;
import ma.zs.gestion_service_pediatrie.ws.converter.rappor.SyntheseMedicaleConverter;
import ma.zs.gestion_service_pediatrie.ws.converter.patient.PatientConverter;
import ma.zs.gestion_service_pediatrie.ws.converter.rappor.DiagnosticConverter;
import ma.zs.gestion_service_pediatrie.ws.converter.dossie.AnalyseMedicaleConverter;
import ma.zs.gestion_service_pediatrie.ws.converter.dossie.TypeImageConverter;
import ma.zs.gestion_service_pediatrie.ws.converter.dossie.AntecedentConverter;

import ma.zs.gestion_service_pediatrie.bean.core.patient.Patient;


import ma.zs.gestion_service_pediatrie.zynerator.util.StringUtil;
import ma.zs.gestion_service_pediatrie.zynerator.converter.AbstractConverter;
import ma.zs.gestion_service_pediatrie.zynerator.util.DateUtil;
import ma.zs.gestion_service_pediatrie.bean.core.consultatio.Consultation;
import ma.zs.gestion_service_pediatrie.ws.dto.consultatio.ConsultationDto;

@Component
public class ConsultationConverter {

    @Autowired
    private FichePatientConverter fichePatientConverter ;
    @Autowired
    private RadiologieConverter radiologieConverter ;
    @Autowired
    private InfermierConverter infermierConverter ;
    @Autowired
    private MedecinConverter medecinConverter ;
    @Autowired
    private EpreuveConverter epreuveConverter ;
    @Autowired
    private SyntheseMedicaleConverter syntheseMedicaleConverter ;
    @Autowired
    private PatientConverter patientConverter ;
    @Autowired
    private DiagnosticConverter diagnosticConverter ;
    @Autowired
    private AnalyseMedicaleConverter analyseMedicaleConverter ;
    @Autowired
    private TypeImageConverter typeImageConverter ;
    @Autowired
    private AntecedentConverter antecedentConverter ;
    private boolean medecin = true;
    private boolean infermier;
    private boolean patient;
    private boolean analyseMedicale;
    private boolean fichePatient;
    private boolean radiologie;
    private boolean diagnostic;
    private boolean syntheseMedicale;

    public  ConsultationConverter() {
        init(true);
    }


    public Consultation toItem(ConsultationDto dto) {
        if (dto == null) {
            return null;
        } else {
        Consultation item = new Consultation();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getRef()))
                item.setRef(dto.getRef());
            if(StringUtil.isNotEmpty(dto.getDateConsultation()))
                item.setDateConsultation(DateUtil.stringEnToDate(dto.getDateConsultation()));
            if(StringUtil.isNotEmpty(dto.getHeureConsultation()))
                item.setHeureConsultation(DateUtil.stringEnToDate(dto.getHeureConsultation()));
            if(StringUtil.isNotEmpty(dto.getTypeConsultation()))
                item.setTypeConsultation(dto.getTypeConsultation());
            if(this.medecin && dto.getMedecin()!=null){
                item.setMedecin(medecinConverter.toItem(dto.getMedecin())) ;
            }


            if(this.infermier && dto.getInfermier()!=null)
                item.setInfermier(infermierConverter.toItem(dto.getInfermier())) ;

            if(dto.getPatient() != null && dto.getPatient().getId() != null){
                item.setPatient(new Patient());
                item.getPatient().setId(dto.getPatient().getId());
                item.getPatient().setNumDossier(dto.getPatient().getNumDossier());
            }


            if(this.analyseMedicale && ListUtil.isNotEmpty(dto.getAnalyseMedicale()))
                item.setAnalyseMedicale(analyseMedicaleConverter.toItem(dto.getAnalyseMedicale()));
            if(this.fichePatient && ListUtil.isNotEmpty(dto.getFichePatient()))
                item.setFichePatient(fichePatientConverter.toItem(dto.getFichePatient()));
            if(this.radiologie && ListUtil.isNotEmpty(dto.getRadiologie()))
                item.setRadiologie(radiologieConverter.toItem(dto.getRadiologie()));
            if(this.diagnostic && ListUtil.isNotEmpty(dto.getDiagnostic()))
                item.setDiagnostic(diagnosticConverter.toItem(dto.getDiagnostic()));
            if(this.syntheseMedicale && ListUtil.isNotEmpty(dto.getSyntheseMedicale()))
                item.setSyntheseMedicale(syntheseMedicaleConverter.toItem(dto.getSyntheseMedicale()));


        return item;
        }
    }


    public ConsultationDto toDto(Consultation item) {
        if (item == null) {
            return null;
        } else {
            ConsultationDto dto = new ConsultationDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getRef()))
                dto.setRef(item.getRef());
            if(item.getDateConsultation()!=null)
                dto.setDateConsultation(DateUtil.dateTimeToString(item.getDateConsultation()));
            if(item.getHeureConsultation()!=null)
                dto.setHeureConsultation(DateUtil.dateTimeToString(item.getHeureConsultation()));
            if(StringUtil.isNotEmpty(item.getTypeConsultation()))
                dto.setTypeConsultation(item.getTypeConsultation());
            if(this.medecin && item.getMedecin()!=null) {
                dto.setMedecin(medecinConverter.toDto(item.getMedecin())) ;

            }
            if(this.infermier && item.getInfermier()!=null) {
                dto.setInfermier(infermierConverter.toDto(item.getInfermier())) ;

            }
            if(this.patient && item.getPatient()!=null) {
                dto.setPatient(patientConverter.toDto(item.getPatient())) ;

            }
        if(this.analyseMedicale && ListUtil.isNotEmpty(item.getAnalyseMedicale())){
            analyseMedicaleConverter.init(true);
            analyseMedicaleConverter.setConsultation(false);
            dto.setAnalyseMedicale(analyseMedicaleConverter.toDto(item.getAnalyseMedicale()));
            analyseMedicaleConverter.setConsultation(true);

        }
        if(this.fichePatient && ListUtil.isNotEmpty(item.getFichePatient())){
            fichePatientConverter.init(true);
            fichePatientConverter.setConsultation(false);
            dto.setFichePatient(fichePatientConverter.toDto(item.getFichePatient()));
            fichePatientConverter.setConsultation(true);

        }
        if(this.radiologie && ListUtil.isNotEmpty(item.getRadiologie())){
            radiologieConverter.init(true);
            radiologieConverter.setConsultatuin(false);
            dto.setRadiologie(radiologieConverter.toDto(item.getRadiologie()));
            radiologieConverter.setConsultatuin(true);

        }
        if(this.diagnostic && ListUtil.isNotEmpty(item.getDiagnostic())){
            diagnosticConverter.init(true);
            diagnosticConverter.setConsultation(false);
            dto.setDiagnostic(diagnosticConverter.toDto(item.getDiagnostic()));
            diagnosticConverter.setConsultation(true);

        }
        if(this.syntheseMedicale && ListUtil.isNotEmpty(item.getSyntheseMedicale())){
            syntheseMedicaleConverter.init(true);
            syntheseMedicaleConverter.setConsultation(false);
            dto.setSyntheseMedicale(syntheseMedicaleConverter.toDto(item.getSyntheseMedicale()));
            syntheseMedicaleConverter.setConsultation(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.analyseMedicale = value;
        this.fichePatient = value;
        this.radiologie = value;
        this.diagnostic = value;
        this.syntheseMedicale = value;
    }
    public void initObject(boolean value) {
        this.medecin = value;
        this.infermier = value;
        this.patient = value;
    }
	
    public List<Consultation> toItem(List<ConsultationDto> dtos) {
        List<Consultation> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ConsultationDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ConsultationDto> toDto(List<Consultation> items) {
        List<ConsultationDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Consultation item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ConsultationDto dto, Consultation t) {
        if(t != null){
            BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
            if (dto.getMedecin() != null)
                medecinConverter.copy(dto.getMedecin(), t.getMedecin());
            if (dto.getInfermier() != null)
                infermierConverter.copy(dto.getInfermier(), t.getInfermier());
            if (dto.getPatient() != null)
                patientConverter.copy(dto.getPatient(), t.getPatient());
            if (dto.getAnalyseMedicale() != null)
                t.setAnalyseMedicale(analyseMedicaleConverter.copy(dto.getAnalyseMedicale()));
            if (dto.getFichePatient() != null)
                t.setFichePatient(fichePatientConverter.copy(dto.getFichePatient()));
            if (dto.getRadiologie() != null)
                t.setRadiologie(radiologieConverter.copy(dto.getRadiologie()));
            if (dto.getDiagnostic() != null)
                t.setDiagnostic(diagnosticConverter.copy(dto.getDiagnostic()));
            if (dto.getSyntheseMedicale() != null)
                t.setSyntheseMedicale(syntheseMedicaleConverter.copy(dto.getSyntheseMedicale()));
        }

    }

    public List<Consultation> copy(List<ConsultationDto> dtos) {
        List<Consultation> result = new ArrayList<>();
        if (dtos != null) {
            for (ConsultationDto dto : dtos) {
                Consultation instance = new Consultation();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public FichePatientConverter getFichePatientConverter(){
        return this.fichePatientConverter;
    }
    public void setFichePatientConverter(FichePatientConverter fichePatientConverter ){
        this.fichePatientConverter = fichePatientConverter;
    }
    public RadiologieConverter getRadiologieConverter(){
        return this.radiologieConverter;
    }
    public void setRadiologieConverter(RadiologieConverter radiologieConverter ){
        this.radiologieConverter = radiologieConverter;
    }
    public InfermierConverter getInfermierConverter(){
        return this.infermierConverter;
    }
    public void setInfermierConverter(InfermierConverter infermierConverter ){
        this.infermierConverter = infermierConverter;
    }
    public MedecinConverter getMedecinConverter(){
        return this.medecinConverter;
    }
    public void setMedecinConverter(MedecinConverter medecinConverter ){
        this.medecinConverter = medecinConverter;
    }
    public EpreuveConverter getEpreuveConverter(){
        return this.epreuveConverter;
    }
    public void setEpreuveConverter(EpreuveConverter epreuveConverter ){
        this.epreuveConverter = epreuveConverter;
    }
    public SyntheseMedicaleConverter getSyntheseMedicaleConverter(){
        return this.syntheseMedicaleConverter;
    }
    public void setSyntheseMedicaleConverter(SyntheseMedicaleConverter syntheseMedicaleConverter ){
        this.syntheseMedicaleConverter = syntheseMedicaleConverter;
    }
    public PatientConverter getPatientConverter(){
        return this.patientConverter;
    }
    public void setPatientConverter(PatientConverter patientConverter ){
        this.patientConverter = patientConverter;
    }
    public DiagnosticConverter getDiagnosticConverter(){
        return this.diagnosticConverter;
    }
    public void setDiagnosticConverter(DiagnosticConverter diagnosticConverter ){
        this.diagnosticConverter = diagnosticConverter;
    }
    public AnalyseMedicaleConverter getAnalyseMedicaleConverter(){
        return this.analyseMedicaleConverter;
    }
    public void setAnalyseMedicaleConverter(AnalyseMedicaleConverter analyseMedicaleConverter ){
        this.analyseMedicaleConverter = analyseMedicaleConverter;
    }
    public TypeImageConverter getTypeImageConverter(){
        return this.typeImageConverter;
    }
    public void setTypeImageConverter(TypeImageConverter typeImageConverter ){
        this.typeImageConverter = typeImageConverter;
    }
    public AntecedentConverter getAntecedentConverter(){
        return this.antecedentConverter;
    }
    public void setAntecedentConverter(AntecedentConverter antecedentConverter ){
        this.antecedentConverter = antecedentConverter;
    }
    public boolean  isMedecin(){
        return this.medecin;
    }
    public void  setMedecin(boolean medecin){
        this.medecin = medecin;
    }
    public boolean  isInfermier(){
        return this.infermier;
    }
    public void  setInfermier(boolean infermier){
        this.infermier = infermier;
    }
    public boolean  isPatient(){
        return this.patient;
    }
    public void  setPatient(boolean patient){
        this.patient = patient;
    }
    public boolean  isAnalyseMedicale(){
        return this.analyseMedicale ;
    }
    public void  setAnalyseMedicale(boolean analyseMedicale ){
        this.analyseMedicale  = analyseMedicale ;
    }
    public boolean  isFichePatient(){
        return this.fichePatient ;
    }
    public void  setFichePatient(boolean fichePatient ){
        this.fichePatient  = fichePatient ;
    }
    public boolean  isRadiologie(){
        return this.radiologie ;
    }
    public void  setRadiologie(boolean radiologie ){
        this.radiologie  = radiologie ;
    }
    public boolean  isDiagnostic(){
        return this.diagnostic ;
    }
    public void  setDiagnostic(boolean diagnostic ){
        this.diagnostic  = diagnostic ;
    }
    public boolean  isSyntheseMedicale(){
        return this.syntheseMedicale ;
    }
    public void  setSyntheseMedicale(boolean syntheseMedicale ){
        this.syntheseMedicale  = syntheseMedicale ;
    }
}
