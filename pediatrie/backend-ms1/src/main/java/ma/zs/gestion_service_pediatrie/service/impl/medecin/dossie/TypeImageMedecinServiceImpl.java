package ma.zs.gestion_service_pediatrie.service.impl.medecin.dossie;


import ma.zs.gestion_service_pediatrie.zynerator.exception.EntityNotFoundException;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.TypeImage;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.dossie.TypeImageCriteria;
import ma.zs.gestion_service_pediatrie.dao.facade.core.dossie.TypeImageDao;
import ma.zs.gestion_service_pediatrie.dao.specification.core.dossie.TypeImageSpecification;
import ma.zs.gestion_service_pediatrie.service.facade.medecin.dossie.TypeImageMedecinService;
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


import java.util.List;
@Service
public class TypeImageMedecinServiceImpl implements TypeImageMedecinService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public TypeImage update(TypeImage t) {
        TypeImage loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{TypeImage.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public TypeImage findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public TypeImage findOrSave(TypeImage t) {
        if (t != null) {
            TypeImage result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<TypeImage> importData(List<TypeImage> items) {
        List<TypeImage> list = new ArrayList<>();
        for (TypeImage t : items) {
            TypeImage founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<TypeImage> findAll() {
        return dao.findAll();
    }

    public List<TypeImage> findByCriteria(TypeImageCriteria criteria) {
        List<TypeImage> content = null;
        if (criteria != null) {
            TypeImageSpecification mySpecification = constructSpecification(criteria);
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


    private TypeImageSpecification constructSpecification(TypeImageCriteria criteria) {
        TypeImageSpecification mySpecification =  (TypeImageSpecification) RefelexivityUtil.constructObjectUsingOneParam(TypeImageSpecification.class, criteria);
        return mySpecification;
    }

    public List<TypeImage> findPaginatedByCriteria(TypeImageCriteria criteria, int page, int pageSize, String order, String sortField) {
        TypeImageSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(TypeImageCriteria criteria) {
        TypeImageSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
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
    public int delete(TypeImage t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TypeImage> delete(List<TypeImage> list) {
		List<TypeImage> result = new ArrayList();
        if (list != null) {
            for (TypeImage t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public TypeImage create(TypeImage t) {
        TypeImage loaded = findByReferenceEntity(t);
        TypeImage saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TypeImage> create(List<TypeImage> ts) {
        List<TypeImage> result = new ArrayList<>();
        if (ts != null) {
            for (TypeImage t : ts) {
				TypeImage created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public TypeImage findWithAssociatedLists(Long id){
        TypeImage result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TypeImage> update(List<TypeImage> ts, boolean createIfNotExist) {
        List<TypeImage> result = new ArrayList<>();
        if (ts != null) {
            for (TypeImage t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    TypeImage loadedItem = dao.findById(t.getId()).orElse(null);
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





    public TypeImage findByReferenceEntity(TypeImage t){
        return t==null? null : dao.findByRef(t.getRef());
    }



    public List<TypeImage> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<TypeImage>> getToBeSavedAndToBeDeleted(List<TypeImage> oldList, List<TypeImage> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<TypeImage> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired TypeImageDao dao;


}
