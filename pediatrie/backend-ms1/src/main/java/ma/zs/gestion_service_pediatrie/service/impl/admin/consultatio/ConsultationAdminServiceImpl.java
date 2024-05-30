package ma.zs.gestion_service_pediatrie.service.impl.admin.consultatio;


import ma.zs.gestion_service_pediatrie.zynerator.exception.EntityNotFoundException;
import ma.zs.gestion_service_pediatrie.bean.core.consultatio.Consultation;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.consultatio.ConsultationCriteria;
import ma.zs.gestion_service_pediatrie.dao.facade.core.consultatio.ConsultationDao;
import ma.zs.gestion_service_pediatrie.dao.specification.core.consultatio.ConsultationSpecification;
import ma.zs.gestion_service_pediatrie.service.facade.admin.consultatio.ConsultationAdminService;
import ma.zs.gestion_service_pediatrie.zynerator.util.ListUtil;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import ma.zs.gestion_service_pediatrie.zynerator.util.RefelexivityUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ma.zs.gestion_service_pediatrie.service.facade.admin.dossie.FichePatientAdminService ;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.FichePatient ;
import ma.zs.gestion_service_pediatrie.service.facade.admin.dossie.RadiologieAdminService ;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.Radiologie ;
import ma.zs.gestion_service_pediatrie.service.facade.admin.commun.InfermierAdminService ;
import ma.zs.gestion_service_pediatrie.service.facade.admin.commun.MedecinAdminService ;
import ma.zs.gestion_service_pediatrie.service.facade.admin.rappor.SyntheseMedicaleAdminService ;
import ma.zs.gestion_service_pediatrie.bean.core.rappor.SyntheseMedicale ;
import ma.zs.gestion_service_pediatrie.service.facade.admin.patient.PatientAdminService ;
import ma.zs.gestion_service_pediatrie.service.facade.admin.rappor.DiagnosticAdminService ;
import ma.zs.gestion_service_pediatrie.bean.core.rappor.Diagnostic ;
import ma.zs.gestion_service_pediatrie.service.facade.admin.dossie.AnalyseMedicaleAdminService ;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.AnalyseMedicale ;

@Service
public class ConsultationAdminServiceImpl implements ConsultationAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Consultation update(Consultation t) {
        Consultation loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Consultation.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public List<Consultation> findByPatientNumDossier(String num) {
        return dao.findByPatientNumDossier(num);
    }

    public Consultation findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Consultation findOrSave(Consultation t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Consultation result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Consultation> importData(List<Consultation> items) {
        List<Consultation> list = new ArrayList<>();
        for (Consultation t : items) {
            Consultation founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Consultation> findAll() {
        return dao.findAll();
    }

    public List<Consultation> findByCriteria(ConsultationCriteria criteria) {
        List<Consultation> content = null;
        if (criteria != null) {
            ConsultationSpecification mySpecification = constructSpecification(criteria);
            if (criteria.isPeagable()) {
                Pageable pageable = PageRequest.of(0, criteria.getMaxResults());
                content = dao.findAll(mySpecification, pageable).getContent();
            } else {
                content = dao.findAll(mySpecification);
            }
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private ConsultationSpecification constructSpecification(ConsultationCriteria criteria) {
        ConsultationSpecification mySpecification =  (ConsultationSpecification) RefelexivityUtil.constructObjectUsingOneParam(ConsultationSpecification.class, criteria);
        return mySpecification;
    }

    public List<Consultation> findPaginatedByCriteria(ConsultationCriteria criteria, int page, int pageSize, String order, String sortField) {
        ConsultationSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ConsultationCriteria criteria) {
        ConsultationSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Consultation> findByMedecinId(Long id){
        return dao.findByMedecinId(id);
    }
    public int deleteByMedecinId(Long id){
        return dao.deleteByMedecinId(id);
    }
    public long countByMedecinRef(String ref){
        return dao.countByMedecinRef(ref);
    }
    public List<Consultation> findByInfermierId(Long id){
        return dao.findByInfermierId(id);
    }
    public int deleteByInfermierId(Long id){
        return dao.deleteByInfermierId(id);
    }
    public long countByInfermierRef(String ref){
        return dao.countByInfermierRef(ref);
    }
    public List<Consultation> findByPatientId(Long id){
        return dao.findByPatientId(id);
    }
    public int deleteByPatientId(Long id){
        return dao.deleteByPatientId(id);
    }
    public long countByPatientNumDossier(String numDossier){
        return dao.countByPatientNumDossier(numDossier);
    }

	public boolean deleteById(Long id) {
        boolean condition = deleteByIdCheckCondition(id);
        if (condition) {
            deleteAssociatedLists(id);
            dao.deleteById(id);
        }
        return condition;
    }

    public boolean deleteByIdCheckCondition(Long id) {
        return true;
    }

    public void deleteByIdIn(List<Long> ids) {
        //dao.deleteByIdIn(ids);
    }
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public int delete(Consultation t) {
        int result = 0;
        if (t != null) {
            deleteAssociatedLists(t.getId());
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }
    @Transactional
    public void deleteAssociatedLists(Long id) {
        analyseMedicaleService.deleteByConsultationId(id);
        fichePatientService.deleteByConsultationId(id);
        radiologieService.deleteByConsultatuinId(id);
        diagnosticService.deleteByConsultationId(id);
        syntheseMedicaleService.deleteByConsultationId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Consultation> delete(List<Consultation> list) {
		List<Consultation> result = new ArrayList();
        if (list != null) {
            for (Consultation t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Consultation create(Consultation t) {
        Consultation loaded = findByReferenceEntity(t);
        Consultation saved;
        if (loaded == null) {
            saved = dao.save(t);
            if (t.getAnalyseMedicale() != null) {
                t.getAnalyseMedicale().forEach(element-> {
                    element.setConsultation(saved);
                    analyseMedicaleService.create(element);
                });
            }
            if (t.getFichePatient() != null) {
                t.getFichePatient().forEach(element-> {
                    element.setConsultation(saved);
                    fichePatientService.create(element);
                });
            }
            if (t.getRadiologie() != null) {
                t.getRadiologie().forEach(element-> {
                    element.setConsultation(saved);
                    radiologieService.create(element);
                });
            }
            if (t.getDiagnostic() != null) {
                t.getDiagnostic().forEach(element-> {
                    element.setConsultation(saved);
                    diagnosticService.create(element);
                });
            }
            if (t.getSyntheseMedicale() != null) {
                t.getSyntheseMedicale().forEach(element-> {
                    element.setConsultation(saved);
                    syntheseMedicaleService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Consultation> create(List<Consultation> ts) {
        List<Consultation> result = new ArrayList<>();
        if (ts != null) {
            for (Consultation t : ts) {
				Consultation created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Consultation findWithAssociatedLists(Long id){
        Consultation result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setAnalyseMedicale(analyseMedicaleService.findByConsultationId(id));
            result.setFichePatient(fichePatientService.findByConsultationId(id));
            result.setRadiologie(radiologieService.findByConsultatuinId(id));
            result.setDiagnostic(diagnosticService.findByConsultationId(id));
            result.setSyntheseMedicale(syntheseMedicaleService.findByConsultationId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Consultation> update(List<Consultation> ts, boolean createIfNotExist) {
        List<Consultation> result = new ArrayList<>();
        if (ts != null) {
            for (Consultation t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Consultation loadedItem = dao.findById(t.getId()).orElse(null);
                    if (createIfNotExist && (t.getId() == null || loadedItem == null)) {
                        dao.save(t);
                    } else if (t.getId() != null && loadedItem != null) {
                        dao.save(t);
                    } else {
                        result.add(t);
                    }
                }
            }
        }
        return result;
    }

    public void updateWithAssociatedLists(Consultation consultation){
    if(consultation !=null && consultation.getId() != null){
        List<List<AnalyseMedicale>> resultAnalyseMedicale= analyseMedicaleService.getToBeSavedAndToBeDeleted(analyseMedicaleService.findByConsultationId(consultation.getId()),consultation.getAnalyseMedicale());
            analyseMedicaleService.delete(resultAnalyseMedicale.get(1));
        ListUtil.emptyIfNull(resultAnalyseMedicale.get(0)).forEach(e -> e.setConsultation(consultation));
        analyseMedicaleService.update(resultAnalyseMedicale.get(0),true);
        List<List<FichePatient>> resultFichePatient= fichePatientService.getToBeSavedAndToBeDeleted(fichePatientService.findByConsultationId(consultation.getId()),consultation.getFichePatient());
            fichePatientService.delete(resultFichePatient.get(1));
        ListUtil.emptyIfNull(resultFichePatient.get(0)).forEach(e -> e.setConsultation(consultation));
        fichePatientService.update(resultFichePatient.get(0),true);
        List<List<Radiologie>> resultRadiologie= radiologieService.getToBeSavedAndToBeDeleted(radiologieService.findByConsultatuinId(consultation.getId()),consultation.getRadiologie());
            radiologieService.delete(resultRadiologie.get(1));
        ListUtil.emptyIfNull(resultRadiologie.get(0)).forEach(e -> e.setConsultation(consultation));
        radiologieService.update(resultRadiologie.get(0),true);
        List<List<Diagnostic>> resultDiagnostic= diagnosticService.getToBeSavedAndToBeDeleted(diagnosticService.findByConsultationId(consultation.getId()),consultation.getDiagnostic());
            diagnosticService.delete(resultDiagnostic.get(1));
        ListUtil.emptyIfNull(resultDiagnostic.get(0)).forEach(e -> e.setConsultation(consultation));
        diagnosticService.update(resultDiagnostic.get(0),true);
        List<List<SyntheseMedicale>> resultSyntheseMedicale= syntheseMedicaleService.getToBeSavedAndToBeDeleted(syntheseMedicaleService.findByConsultationId(consultation.getId()),consultation.getSyntheseMedicale());
            syntheseMedicaleService.delete(resultSyntheseMedicale.get(1));
        ListUtil.emptyIfNull(resultSyntheseMedicale.get(0)).forEach(e -> e.setConsultation(consultation));
        syntheseMedicaleService.update(resultSyntheseMedicale.get(0),true);
        }
    }




    public Consultation findByReferenceEntity(Consultation t){
        return t==null? null : dao.findByRef(t.getRef());
    }
    public void findOrSaveAssociatedObject(Consultation t){
        if( t != null) {
            t.setMedecin(medecinService.findOrSave(t.getMedecin()));
            t.setInfermier(infermierService.findOrSave(t.getInfermier()));
            t.setPatient(patientService.findOrSave(t.getPatient()));
        }
    }



    public List<Consultation> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Consultation>> getToBeSavedAndToBeDeleted(List<Consultation> oldList, List<Consultation> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Consultation> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private FichePatientAdminService fichePatientService ;
    @Autowired
    private RadiologieAdminService radiologieService ;
    @Autowired
    private InfermierAdminService infermierService ;
    @Autowired
    private MedecinAdminService medecinService ;
    @Autowired
    private SyntheseMedicaleAdminService syntheseMedicaleService ;
    @Autowired
    private PatientAdminService patientService ;
    @Autowired
    private DiagnosticAdminService diagnosticService ;
    @Autowired
    private AnalyseMedicaleAdminService analyseMedicaleService ;

    private @Autowired ConsultationDao dao;


}
