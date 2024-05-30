package ma.zs.gestion_service_pediatrie.service.impl.admin.gestio;


import ma.zs.gestion_service_pediatrie.zynerator.exception.EntityNotFoundException;
import ma.zs.gestion_service_pediatrie.bean.core.gestio.Ordonnance;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.gestio.OrdonnanceCriteria;
import ma.zs.gestion_service_pediatrie.dao.facade.core.gestio.OrdonnanceDao;
import ma.zs.gestion_service_pediatrie.dao.specification.core.gestio.OrdonnanceSpecification;
import ma.zs.gestion_service_pediatrie.service.facade.admin.gestio.OrdonnanceAdminService;
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

import ma.zs.gestion_service_pediatrie.service.facade.admin.gestio.TraitementAdminService ;
import ma.zs.gestion_service_pediatrie.bean.core.gestio.Traitement ;
import ma.zs.gestion_service_pediatrie.service.facade.admin.consultatio.ConsultationAdminService ;
import ma.zs.gestion_service_pediatrie.bean.core.consultatio.Consultation ;

import java.util.List;
@Service
public class OrdonnanceAdminServiceImpl implements OrdonnanceAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Ordonnance update(Ordonnance t) {
        Ordonnance loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Ordonnance.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Ordonnance findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Ordonnance findOrSave(Ordonnance t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Ordonnance result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Ordonnance> importData(List<Ordonnance> items) {
        List<Ordonnance> list = new ArrayList<>();
        for (Ordonnance t : items) {
            Ordonnance founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Ordonnance> findAll() {
        return dao.findAll();
    }

    public List<Ordonnance> findByCriteria(OrdonnanceCriteria criteria) {
        List<Ordonnance> content = null;
        if (criteria != null) {
            OrdonnanceSpecification mySpecification = constructSpecification(criteria);
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


    private OrdonnanceSpecification constructSpecification(OrdonnanceCriteria criteria) {
        OrdonnanceSpecification mySpecification =  (OrdonnanceSpecification) RefelexivityUtil.constructObjectUsingOneParam(OrdonnanceSpecification.class, criteria);
        return mySpecification;
    }

    public List<Ordonnance> findPaginatedByCriteria(OrdonnanceCriteria criteria, int page, int pageSize, String order, String sortField) {
        OrdonnanceSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(OrdonnanceCriteria criteria) {
        OrdonnanceSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Ordonnance> findByTraitementId(Long id){
        return dao.findByTraitementId(id);
    }
    public int deleteByTraitementId(Long id){
        return dao.deleteByTraitementId(id);
    }
    public long countByTraitementRef(String ref){
        return dao.countByTraitementRef(ref);
    }
    public List<Ordonnance> findByConsultatuinId(Long id){
        return dao.findByConsultatuinId(id);
    }
    public int deleteByConsultatuinId(Long id){
        return dao.deleteByConsultatuinId(id);
    }
    public long countByConsultatuinRef(String ref){
        return dao.countByConsultatuinRef(ref);
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
    public int delete(Ordonnance t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Ordonnance> delete(List<Ordonnance> list) {
		List<Ordonnance> result = new ArrayList();
        if (list != null) {
            for (Ordonnance t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Ordonnance create(Ordonnance t) {
        Ordonnance loaded = findByReferenceEntity(t);
        Ordonnance saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Ordonnance> create(List<Ordonnance> ts) {
        List<Ordonnance> result = new ArrayList<>();
        if (ts != null) {
            for (Ordonnance t : ts) {
				Ordonnance created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Ordonnance findWithAssociatedLists(Long id){
        Ordonnance result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Ordonnance> update(List<Ordonnance> ts, boolean createIfNotExist) {
        List<Ordonnance> result = new ArrayList<>();
        if (ts != null) {
            for (Ordonnance t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Ordonnance loadedItem = dao.findById(t.getId()).orElse(null);
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





    public Ordonnance findByReferenceEntity(Ordonnance t){
        return t==null? null : dao.findByRef(t.getRef());
    }
    public void findOrSaveAssociatedObject(Ordonnance t){
        if( t != null) {
            t.setTraitement(traitementService.findOrSave(t.getTraitement()));
            t.setConsultatuin(consultationService.findOrSave(t.getConsultatuin()));
        }
    }



    public List<Ordonnance> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Ordonnance>> getToBeSavedAndToBeDeleted(List<Ordonnance> oldList, List<Ordonnance> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Ordonnance> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private TraitementAdminService traitementService ;
    @Autowired
    private ConsultationAdminService consultationService ;

    private @Autowired OrdonnanceDao dao;


}
