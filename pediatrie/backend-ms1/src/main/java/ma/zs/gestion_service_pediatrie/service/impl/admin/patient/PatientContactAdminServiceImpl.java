package ma.zs.gestion_service_pediatrie.service.impl.admin.patient;


import ma.zs.gestion_service_pediatrie.zynerator.exception.EntityNotFoundException;
import ma.zs.gestion_service_pediatrie.bean.core.patient.PatientContact;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.patient.PatientContactCriteria;
import ma.zs.gestion_service_pediatrie.dao.facade.core.patient.PatientContactDao;
import ma.zs.gestion_service_pediatrie.dao.specification.core.patient.PatientContactSpecification;
import ma.zs.gestion_service_pediatrie.service.facade.admin.patient.PatientContactAdminService;
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

import ma.zs.gestion_service_pediatrie.service.facade.admin.patient.RelationAdminService ;
import ma.zs.gestion_service_pediatrie.bean.core.patient.Relation ;
import ma.zs.gestion_service_pediatrie.service.facade.admin.patient.PatientAdminService ;
import ma.zs.gestion_service_pediatrie.bean.core.patient.Patient ;

import java.util.List;
@Service
public class PatientContactAdminServiceImpl implements PatientContactAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public PatientContact update(PatientContact t) {
        PatientContact loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{PatientContact.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public PatientContact findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public PatientContact findOrSave(PatientContact t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            PatientContact result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<PatientContact> importData(List<PatientContact> items) {
        List<PatientContact> list = new ArrayList<>();
        for (PatientContact t : items) {
            PatientContact founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<PatientContact> findAll() {
        return dao.findAll();
    }

    public List<PatientContact> findByCriteria(PatientContactCriteria criteria) {
        List<PatientContact> content = null;
        if (criteria != null) {
            PatientContactSpecification mySpecification = constructSpecification(criteria);
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


    private PatientContactSpecification constructSpecification(PatientContactCriteria criteria) {
        PatientContactSpecification mySpecification =  (PatientContactSpecification) RefelexivityUtil.constructObjectUsingOneParam(PatientContactSpecification.class, criteria);
        return mySpecification;
    }

    public List<PatientContact> findPaginatedByCriteria(PatientContactCriteria criteria, int page, int pageSize, String order, String sortField) {
        PatientContactSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(PatientContactCriteria criteria) {
        PatientContactSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<PatientContact> findByRelationId(Long id){
        return dao.findByRelationId(id);
    }
    public int deleteByRelationId(Long id){
        return dao.deleteByRelationId(id);
    }
    public long countByRelationRef(String ref){
        return dao.countByRelationRef(ref);
    }
    public List<PatientContact> findByPatientId(Long id){
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
    public int delete(PatientContact t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<PatientContact> delete(List<PatientContact> list) {
		List<PatientContact> result = new ArrayList();
        if (list != null) {
            for (PatientContact t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public PatientContact create(PatientContact t) {
        PatientContact loaded = findByReferenceEntity(t);
        PatientContact saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<PatientContact> create(List<PatientContact> ts) {
        List<PatientContact> result = new ArrayList<>();
        if (ts != null) {
            for (PatientContact t : ts) {
				PatientContact created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public PatientContact findWithAssociatedLists(Long id){
        PatientContact result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<PatientContact> update(List<PatientContact> ts, boolean createIfNotExist) {
        List<PatientContact> result = new ArrayList<>();
        if (ts != null) {
            for (PatientContact t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    PatientContact loadedItem = dao.findById(t.getId()).orElse(null);
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





    public PatientContact findByReferenceEntity(PatientContact t){
        return t==null? null : dao.findByCin(t.getCin());
    }
    public void findOrSaveAssociatedObject(PatientContact t){
        if( t != null) {
            t.setRelation(relationService.findOrSave(t.getRelation()));
            t.setPatient(patientService.findOrSave(t.getPatient()));
        }
    }



    public List<PatientContact> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<PatientContact>> getToBeSavedAndToBeDeleted(List<PatientContact> oldList, List<PatientContact> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<PatientContact> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private RelationAdminService relationService ;
    @Autowired
    private PatientAdminService patientService ;

    private @Autowired PatientContactDao dao;


}
