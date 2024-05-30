package ma.zs.gestion_service_pediatrie.service.impl.admin.dossie;


import ma.zs.gestion_service_pediatrie.zynerator.exception.EntityNotFoundException;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.Epreuve;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.dossie.EpreuveCriteria;
import ma.zs.gestion_service_pediatrie.dao.facade.core.dossie.EpreuveDao;
import ma.zs.gestion_service_pediatrie.dao.specification.core.dossie.EpreuveSpecification;
import ma.zs.gestion_service_pediatrie.service.facade.admin.dossie.EpreuveAdminService;
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
public class EpreuveAdminServiceImpl implements EpreuveAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Epreuve update(Epreuve t) {
        Epreuve loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Epreuve.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Epreuve findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Epreuve findOrSave(Epreuve t) {
        if (t != null) {
            Epreuve result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Epreuve> importData(List<Epreuve> items) {
        List<Epreuve> list = new ArrayList<>();
        for (Epreuve t : items) {
            Epreuve founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Epreuve> findAll() {
        return dao.findAll();
    }

    public List<Epreuve> findByCriteria(EpreuveCriteria criteria) {
        List<Epreuve> content = null;
        if (criteria != null) {
            EpreuveSpecification mySpecification = constructSpecification(criteria);
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


    private EpreuveSpecification constructSpecification(EpreuveCriteria criteria) {
        EpreuveSpecification mySpecification =  (EpreuveSpecification) RefelexivityUtil.constructObjectUsingOneParam(EpreuveSpecification.class, criteria);
        return mySpecification;
    }

    public List<Epreuve> findPaginatedByCriteria(EpreuveCriteria criteria, int page, int pageSize, String order, String sortField) {
        EpreuveSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(EpreuveCriteria criteria) {
        EpreuveSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(Epreuve t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Epreuve> delete(List<Epreuve> list) {
		List<Epreuve> result = new ArrayList();
        if (list != null) {
            for (Epreuve t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Epreuve create(Epreuve t) {
        Epreuve loaded = findByReferenceEntity(t);
        Epreuve saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Epreuve> create(List<Epreuve> ts) {
        List<Epreuve> result = new ArrayList<>();
        if (ts != null) {
            for (Epreuve t : ts) {
				Epreuve created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Epreuve findWithAssociatedLists(Long id){
        Epreuve result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Epreuve> update(List<Epreuve> ts, boolean createIfNotExist) {
        List<Epreuve> result = new ArrayList<>();
        if (ts != null) {
            for (Epreuve t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Epreuve loadedItem = dao.findById(t.getId()).orElse(null);
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





    public Epreuve findByReferenceEntity(Epreuve t){
        return t==null? null : dao.findByRef(t.getRef());
    }



    public List<Epreuve> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Epreuve>> getToBeSavedAndToBeDeleted(List<Epreuve> oldList, List<Epreuve> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Epreuve> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired EpreuveDao dao;


}
