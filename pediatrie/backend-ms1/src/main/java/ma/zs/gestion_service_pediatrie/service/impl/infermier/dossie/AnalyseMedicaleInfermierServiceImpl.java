package ma.zs.gestion_service_pediatrie.service.impl.infermier.dossie;


import ma.zs.gestion_service_pediatrie.zynerator.exception.EntityNotFoundException;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.AnalyseMedicale;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.dossie.AnalyseMedicaleCriteria;
import ma.zs.gestion_service_pediatrie.dao.facade.core.dossie.AnalyseMedicaleDao;
import ma.zs.gestion_service_pediatrie.dao.specification.core.dossie.AnalyseMedicaleSpecification;
import ma.zs.gestion_service_pediatrie.service.facade.infermier.dossie.AnalyseMedicaleInfermierService;
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

import ma.zs.gestion_service_pediatrie.service.facade.infermier.consultatio.ConsultationInfermierService ;
import ma.zs.gestion_service_pediatrie.service.facade.infermier.dossie.EpreuveInfermierService ;

@Service
public class AnalyseMedicaleInfermierServiceImpl implements AnalyseMedicaleInfermierService {

    @Override
    public List<AnalyseMedicale> findByConsultationRef(String ref) {
        return dao.findByConsultationRef(ref);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public AnalyseMedicale update(AnalyseMedicale t) {
        AnalyseMedicale loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{AnalyseMedicale.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public AnalyseMedicale findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public AnalyseMedicale findOrSave(AnalyseMedicale t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            AnalyseMedicale result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<AnalyseMedicale> importData(List<AnalyseMedicale> items) {
        List<AnalyseMedicale> list = new ArrayList<>();
        for (AnalyseMedicale t : items) {
            AnalyseMedicale founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<AnalyseMedicale> findAll() {
        return dao.findAll();
    }

    public List<AnalyseMedicale> findByCriteria(AnalyseMedicaleCriteria criteria) {
        List<AnalyseMedicale> content = null;
        if (criteria != null) {
            AnalyseMedicaleSpecification mySpecification = constructSpecification(criteria);
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


    private AnalyseMedicaleSpecification constructSpecification(AnalyseMedicaleCriteria criteria) {
        AnalyseMedicaleSpecification mySpecification =  (AnalyseMedicaleSpecification) RefelexivityUtil.constructObjectUsingOneParam(AnalyseMedicaleSpecification.class, criteria);
        return mySpecification;
    }

    public List<AnalyseMedicale> findPaginatedByCriteria(AnalyseMedicaleCriteria criteria, int page, int pageSize, String order, String sortField) {
        AnalyseMedicaleSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(AnalyseMedicaleCriteria criteria) {
        AnalyseMedicaleSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<AnalyseMedicale> findByEpreuveId(Long id){
        return dao.findByEpreuveId(id);
    }
    public int deleteByEpreuveId(Long id){
        return dao.deleteByEpreuveId(id);
    }
    public long countByEpreuveRef(String ref){
        return dao.countByEpreuveRef(ref);
    }
    public List<AnalyseMedicale> findByConsultationId(Long id){
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
    public int delete(AnalyseMedicale t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<AnalyseMedicale> delete(List<AnalyseMedicale> list) {
		List<AnalyseMedicale> result = new ArrayList();
        if (list != null) {
            for (AnalyseMedicale t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public AnalyseMedicale create(AnalyseMedicale t) {
        AnalyseMedicale loaded = findByReferenceEntity(t);
        AnalyseMedicale saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<AnalyseMedicale> create(List<AnalyseMedicale> ts) {
        List<AnalyseMedicale> result = new ArrayList<>();
        if (ts != null) {
            for (AnalyseMedicale t : ts) {
				AnalyseMedicale created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public AnalyseMedicale findWithAssociatedLists(Long id){
        AnalyseMedicale result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<AnalyseMedicale> update(List<AnalyseMedicale> ts, boolean createIfNotExist) {
        List<AnalyseMedicale> result = new ArrayList<>();
        if (ts != null) {
            for (AnalyseMedicale t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    AnalyseMedicale loadedItem = dao.findById(t.getId()).orElse(null);
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





    public AnalyseMedicale findByReferenceEntity(AnalyseMedicale t){
        return t==null? null : dao.findByRef(t.getRef());
    }
    public void findOrSaveAssociatedObject(AnalyseMedicale t){
        if( t != null) {
            t.setEpreuve(epreuveService.findOrSave(t.getEpreuve()));
            t.setConsultation(consultationService.findOrSave(t.getConsultation()));
        }
    }



    public List<AnalyseMedicale> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<AnalyseMedicale>> getToBeSavedAndToBeDeleted(List<AnalyseMedicale> oldList, List<AnalyseMedicale> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<AnalyseMedicale> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private ConsultationInfermierService consultationService ;
    @Autowired
    private EpreuveInfermierService epreuveService ;

    private @Autowired AnalyseMedicaleDao dao;


}
