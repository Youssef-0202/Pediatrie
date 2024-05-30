package ma.zs.gestion_service_pediatrie.service.impl.admin.gestio;


import ma.zs.gestion_service_pediatrie.zynerator.exception.EntityNotFoundException;
import ma.zs.gestion_service_pediatrie.bean.core.gestio.Medicament;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.gestio.MedicamentCriteria;
import ma.zs.gestion_service_pediatrie.dao.facade.core.gestio.MedicamentDao;
import ma.zs.gestion_service_pediatrie.dao.specification.core.gestio.MedicamentSpecification;
import ma.zs.gestion_service_pediatrie.service.facade.admin.gestio.MedicamentAdminService;
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

import java.util.List;
@Service
public class MedicamentAdminServiceImpl implements MedicamentAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Medicament update(Medicament t) {
        Medicament loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Medicament.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Medicament findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Medicament findOrSave(Medicament t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Medicament result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Medicament> importData(List<Medicament> items) {
        List<Medicament> list = new ArrayList<>();
        for (Medicament t : items) {
            Medicament founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Medicament> findAll() {
        return dao.findAll();
    }

    public List<Medicament> findByCriteria(MedicamentCriteria criteria) {
        List<Medicament> content = null;
        if (criteria != null) {
            MedicamentSpecification mySpecification = constructSpecification(criteria);
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


    private MedicamentSpecification constructSpecification(MedicamentCriteria criteria) {
        MedicamentSpecification mySpecification =  (MedicamentSpecification) RefelexivityUtil.constructObjectUsingOneParam(MedicamentSpecification.class, criteria);
        return mySpecification;
    }

    public List<Medicament> findPaginatedByCriteria(MedicamentCriteria criteria, int page, int pageSize, String order, String sortField) {
        MedicamentSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(MedicamentCriteria criteria) {
        MedicamentSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Medicament> findByTraitementId(Long id){
        return dao.findByTraitementId(id);
    }
    public int deleteByTraitementId(Long id){
        return dao.deleteByTraitementId(id);
    }
    public long countByTraitementRef(String ref){
        return dao.countByTraitementRef(ref);
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
    public int delete(Medicament t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Medicament> delete(List<Medicament> list) {
		List<Medicament> result = new ArrayList();
        if (list != null) {
            for (Medicament t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Medicament create(Medicament t) {
        Medicament loaded = findByReferenceEntity(t);
        Medicament saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Medicament> create(List<Medicament> ts) {
        List<Medicament> result = new ArrayList<>();
        if (ts != null) {
            for (Medicament t : ts) {
				Medicament created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Medicament findWithAssociatedLists(Long id){
        Medicament result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Medicament> update(List<Medicament> ts, boolean createIfNotExist) {
        List<Medicament> result = new ArrayList<>();
        if (ts != null) {
            for (Medicament t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Medicament loadedItem = dao.findById(t.getId()).orElse(null);
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





    public Medicament findByReferenceEntity(Medicament t){
        return t==null? null : dao.findByRef(t.getRef());
    }
    public void findOrSaveAssociatedObject(Medicament t){
        if( t != null) {
            t.setTraitement(traitementService.findOrSave(t.getTraitement()));
        }
    }



    public List<Medicament> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Medicament>> getToBeSavedAndToBeDeleted(List<Medicament> oldList, List<Medicament> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Medicament> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private TraitementAdminService traitementService ;

    private @Autowired MedicamentDao dao;


}
