package ma.zs.gestion_service_pediatrie.service.impl.admin.dossie;


import ma.zs.gestion_service_pediatrie.zynerator.exception.EntityNotFoundException;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.FichePatient;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.dossie.FichePatientCriteria;
import ma.zs.gestion_service_pediatrie.dao.facade.core.dossie.FichePatientDao;
import ma.zs.gestion_service_pediatrie.dao.specification.core.dossie.FichePatientSpecification;
import ma.zs.gestion_service_pediatrie.service.facade.admin.dossie.FichePatientAdminService;
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

import ma.zs.gestion_service_pediatrie.service.facade.admin.consultatio.ConsultationAdminService ;
import ma.zs.gestion_service_pediatrie.bean.core.consultatio.Consultation ;
import ma.zs.gestion_service_pediatrie.service.facade.admin.dossie.AntecedentAdminService ;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.Antecedent ;

import java.util.List;
@Service
public class FichePatientAdminServiceImpl implements FichePatientAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public FichePatient update(FichePatient t) {
        FichePatient loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{FichePatient.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public FichePatient findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public FichePatient findOrSave(FichePatient t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            FichePatient result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<FichePatient> importData(List<FichePatient> items) {
        List<FichePatient> list = new ArrayList<>();
        for (FichePatient t : items) {
            FichePatient founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<FichePatient> findAll() {
        return dao.findAll();
    }

    public List<FichePatient> findByCriteria(FichePatientCriteria criteria) {
        List<FichePatient> content = null;
        if (criteria != null) {
            FichePatientSpecification mySpecification = constructSpecification(criteria);
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


    private FichePatientSpecification constructSpecification(FichePatientCriteria criteria) {
        FichePatientSpecification mySpecification =  (FichePatientSpecification) RefelexivityUtil.constructObjectUsingOneParam(FichePatientSpecification.class, criteria);
        return mySpecification;
    }

    public List<FichePatient> findPaginatedByCriteria(FichePatientCriteria criteria, int page, int pageSize, String order, String sortField) {
        FichePatientSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(FichePatientCriteria criteria) {
        FichePatientSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<FichePatient> findByAntecedentId(Long id){
        return dao.findByAntecedentId(id);
    }
    public int deleteByAntecedentId(Long id){
        return dao.deleteByAntecedentId(id);
    }
    public long countByAntecedentRef(String ref){
        return dao.countByAntecedentRef(ref);
    }
    public List<FichePatient> findByConsultationId(Long id){
        return dao.findByConsultationId(id);
    }
    public int deleteByConsultationId(Long id){
        return dao.deleteByConsultationId(id);
    }
    public long countByConsultationRef(String ref){
        return dao.countByConsultationRef(ref);
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
    public int delete(FichePatient t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<FichePatient> delete(List<FichePatient> list) {
		List<FichePatient> result = new ArrayList();
        if (list != null) {
            for (FichePatient t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public FichePatient create(FichePatient t) {
        FichePatient loaded = findByReferenceEntity(t);
        FichePatient saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<FichePatient> create(List<FichePatient> ts) {
        List<FichePatient> result = new ArrayList<>();
        if (ts != null) {
            for (FichePatient t : ts) {
				FichePatient created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public FichePatient findWithAssociatedLists(Long id){
        FichePatient result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<FichePatient> update(List<FichePatient> ts, boolean createIfNotExist) {
        List<FichePatient> result = new ArrayList<>();
        if (ts != null) {
            for (FichePatient t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    FichePatient loadedItem = dao.findById(t.getId()).orElse(null);
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





    public FichePatient findByReferenceEntity(FichePatient t){
        return t==null? null : dao.findByRef(t.getRef());
    }
    public void findOrSaveAssociatedObject(FichePatient t){
        if( t != null) {
            t.setAntecedent(antecedentService.findOrSave(t.getAntecedent()));
            t.setConsultation(consultationService.findOrSave(t.getConsultation()));
        }
    }



    public List<FichePatient> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<FichePatient>> getToBeSavedAndToBeDeleted(List<FichePatient> oldList, List<FichePatient> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<FichePatient> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private ConsultationAdminService consultationService ;
    @Autowired
    private AntecedentAdminService antecedentService ;

    private @Autowired FichePatientDao dao;


}
