package ma.zs.gestion_service_pediatrie.service.impl.admin.dossie;


import ma.zs.gestion_service_pediatrie.zynerator.exception.EntityNotFoundException;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.Radiologie;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.dossie.RadiologieCriteria;
import ma.zs.gestion_service_pediatrie.dao.facade.core.dossie.RadiologieDao;
import ma.zs.gestion_service_pediatrie.dao.specification.core.dossie.RadiologieSpecification;
import ma.zs.gestion_service_pediatrie.service.facade.admin.dossie.RadiologieAdminService;
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
import ma.zs.gestion_service_pediatrie.service.facade.admin.dossie.TypeImageAdminService ;

@Service
public class RadiologieAdminServiceImpl implements RadiologieAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Radiologie update(Radiologie t) {
        Radiologie loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Radiologie.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Radiologie findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Radiologie findOrSave(Radiologie t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Radiologie result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Radiologie> importData(List<Radiologie> items) {
        List<Radiologie> list = new ArrayList<>();
        for (Radiologie t : items) {
            Radiologie founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Radiologie> findAll() {
        return dao.findAll();
    }

    public List<Radiologie> findByCriteria(RadiologieCriteria criteria) {
        List<Radiologie> content = null;
        if (criteria != null) {
            RadiologieSpecification mySpecification = constructSpecification(criteria);
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


    private RadiologieSpecification constructSpecification(RadiologieCriteria criteria) {
        RadiologieSpecification mySpecification =  (RadiologieSpecification) RefelexivityUtil.constructObjectUsingOneParam(RadiologieSpecification.class, criteria);
        return mySpecification;
    }

    public List<Radiologie> findPaginatedByCriteria(RadiologieCriteria criteria, int page, int pageSize, String order, String sortField) {
        RadiologieSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(RadiologieCriteria criteria) {
        RadiologieSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Radiologie> findByConsultatuinId(Long id){
        return dao.findByConsultationId(id);
    }
    public int deleteByConsultatuinId(Long id){
        return dao.deleteByConsultationId(id);
    }
    public long countByConsultatuinRef(String ref){
        return dao.countByConsultationRef(ref);
    }
    public List<Radiologie> findByTypeImageId(Long id){
        return dao.findByTypeImageId(id);
    }
    public int deleteByTypeImageId(Long id){
        return dao.deleteByTypeImageId(id);
    }
    public long countByTypeImageRef(String ref){
        return dao.countByTypeImageRef(ref);
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
    public int delete(Radiologie t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Radiologie> delete(List<Radiologie> list) {
		List<Radiologie> result = new ArrayList();
        if (list != null) {
            for (Radiologie t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Radiologie create(Radiologie t) {
        Radiologie loaded = findByReferenceEntity(t);
        Radiologie saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Radiologie> create(List<Radiologie> ts) {
        List<Radiologie> result = new ArrayList<>();
        if (ts != null) {
            for (Radiologie t : ts) {
				Radiologie created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Radiologie findWithAssociatedLists(Long id){
        Radiologie result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Radiologie> update(List<Radiologie> ts, boolean createIfNotExist) {
        List<Radiologie> result = new ArrayList<>();
        if (ts != null) {
            for (Radiologie t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Radiologie loadedItem = dao.findById(t.getId()).orElse(null);
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





    public Radiologie findByReferenceEntity(Radiologie t){
        return t==null? null : dao.findByRef(t.getRef());
    }
    public void findOrSaveAssociatedObject(Radiologie t){
        if( t != null) {
            t.setConsultation(consultationService.findOrSave(t.getConsultation()));
            t.setTypeImage(typeImageService.findOrSave(t.getTypeImage()));
        }
    }



    public List<Radiologie> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Radiologie>> getToBeSavedAndToBeDeleted(List<Radiologie> oldList, List<Radiologie> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Radiologie> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private ConsultationAdminService consultationService ;
    @Autowired
    private TypeImageAdminService typeImageService ;

    private @Autowired RadiologieDao dao;


}
