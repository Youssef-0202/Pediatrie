package ma.zs.gestion_service_pediatrie.service.impl.medecin.patient;


import ma.zs.gestion_service_pediatrie.zynerator.exception.EntityNotFoundException;
import ma.zs.gestion_service_pediatrie.bean.core.patient.Patient;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.patient.PatientCriteria;
import ma.zs.gestion_service_pediatrie.dao.facade.core.patient.PatientDao;
import ma.zs.gestion_service_pediatrie.dao.specification.core.patient.PatientSpecification;
import ma.zs.gestion_service_pediatrie.service.facade.medecin.patient.PatientMedecinService;
import ma.zs.gestion_service_pediatrie.zynerator.service.AbstractServiceImpl;
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

import ma.zs.gestion_service_pediatrie.service.facade.medecin.patient.PatientContactMedecinService ;
import ma.zs.gestion_service_pediatrie.bean.core.patient.PatientContact ;
import ma.zs.gestion_service_pediatrie.service.facade.medecin.commun.SexeMedecinService ;
import ma.zs.gestion_service_pediatrie.bean.core.commun.Sexe ;

import java.util.List;
@Service
public class PatientMedecinServiceImpl implements PatientMedecinService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Patient update(Patient t) {
        Patient loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Patient.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public Patient findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Patient findOrSave(Patient t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Patient result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Patient> importData(List<Patient> items) {
        List<Patient> list = new ArrayList<>();
        for (Patient t : items) {
            Patient founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Patient> findAll() {
        return dao.findAll();
    }

    public List<Patient> findByCriteria(PatientCriteria criteria) {
        List<Patient> content = null;
        if (criteria != null) {
            PatientSpecification mySpecification = constructSpecification(criteria);
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


    private PatientSpecification constructSpecification(PatientCriteria criteria) {
        PatientSpecification mySpecification =  (PatientSpecification) RefelexivityUtil.constructObjectUsingOneParam(PatientSpecification.class, criteria);
        return mySpecification;
    }

    public List<Patient> findPaginatedByCriteria(PatientCriteria criteria, int page, int pageSize, String order, String sortField) {
        PatientSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(PatientCriteria criteria) {
        PatientSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Patient> findBySexeId(Long id){
        return dao.findBySexeId(id);
    }
    public int deleteBySexeId(Long id){
        return dao.deleteBySexeId(id);
    }
    public long countBySexeRef(String ref){
        return dao.countBySexeRef(ref);
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
    public int delete(Patient t) {
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
        patientContactService.deleteByPatientId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Patient> delete(List<Patient> list) {
		List<Patient> result = new ArrayList();
        if (list != null) {
            for (Patient t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Patient create(Patient t) {
        Patient loaded = findByReferenceEntity(t);
        Patient saved;
        if (loaded == null) {
            saved = dao.save(t);
            if (t.getPatientContact() != null) {
                t.getPatientContact().forEach(element-> {
                    element.setPatient(saved);
                    patientContactService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Patient> create(List<Patient> ts) {
        List<Patient> result = new ArrayList<>();
        if (ts != null) {
            for (Patient t : ts) {
				Patient created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Patient findWithAssociatedLists(Long id){
        Patient result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setPatientContact(patientContactService.findByPatientId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Patient> update(List<Patient> ts, boolean createIfNotExist) {
        List<Patient> result = new ArrayList<>();
        if (ts != null) {
            for (Patient t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Patient loadedItem = dao.findById(t.getId()).orElse(null);
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

    public void updateWithAssociatedLists(Patient patient){
    if(patient !=null && patient.getId() != null){
        List<List<PatientContact>> resultPatientContact= patientContactService.getToBeSavedAndToBeDeleted(patientContactService.findByPatientId(patient.getId()),patient.getPatientContact());
            patientContactService.delete(resultPatientContact.get(1));
        ListUtil.emptyIfNull(resultPatientContact.get(0)).forEach(e -> e.setPatient(patient));
        patientContactService.update(resultPatientContact.get(0),true);
        }
    }




    public Patient findByReferenceEntity(Patient t){
        return t==null? null : dao.findByNumDossier(t.getNumDossier());
    }
    public void findOrSaveAssociatedObject(Patient t){
        if( t != null) {
            t.setSexe(sexeService.findOrSave(t.getSexe()));
        }
    }



    public List<Patient> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Patient>> getToBeSavedAndToBeDeleted(List<Patient> oldList, List<Patient> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Patient> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private PatientContactMedecinService patientContactService ;
    @Autowired
    private SexeMedecinService sexeService ;

    private @Autowired PatientDao dao;


}
