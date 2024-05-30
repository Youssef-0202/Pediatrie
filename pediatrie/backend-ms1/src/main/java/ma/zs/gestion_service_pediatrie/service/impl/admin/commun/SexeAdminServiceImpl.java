package ma.zs.gestion_service_pediatrie.service.impl.admin.commun;


import ma.zs.gestion_service_pediatrie.zynerator.exception.EntityNotFoundException;
import ma.zs.gestion_service_pediatrie.bean.core.commun.Sexe;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.commun.SexeCriteria;
import ma.zs.gestion_service_pediatrie.dao.facade.core.commun.SexeDao;
import ma.zs.gestion_service_pediatrie.dao.specification.core.commun.SexeSpecification;
import ma.zs.gestion_service_pediatrie.service.facade.admin.commun.SexeAdminService;
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
public class SexeAdminServiceImpl implements SexeAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Sexe update(Sexe t) {
        Sexe loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Sexe.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Sexe findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Sexe findOrSave(Sexe t) {
        if (t != null) {
            Sexe result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Sexe> importData(List<Sexe> items) {
        List<Sexe> list = new ArrayList<>();
        for (Sexe t : items) {
            Sexe founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Sexe> findAll() {
        return dao.findAll();
    }

    public List<Sexe> findByCriteria(SexeCriteria criteria) {
        List<Sexe> content = null;
        if (criteria != null) {
            SexeSpecification mySpecification = constructSpecification(criteria);
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


    private SexeSpecification constructSpecification(SexeCriteria criteria) {
        SexeSpecification mySpecification =  (SexeSpecification) RefelexivityUtil.constructObjectUsingOneParam(SexeSpecification.class, criteria);
        return mySpecification;
    }

    public List<Sexe> findPaginatedByCriteria(SexeCriteria criteria, int page, int pageSize, String order, String sortField) {
        SexeSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(SexeCriteria criteria) {
        SexeSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(Sexe t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Sexe> delete(List<Sexe> list) {
		List<Sexe> result = new ArrayList();
        if (list != null) {
            for (Sexe t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Sexe create(Sexe t) {
        Sexe loaded = findByReferenceEntity(t);
        Sexe saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Sexe> create(List<Sexe> ts) {
        List<Sexe> result = new ArrayList<>();
        if (ts != null) {
            for (Sexe t : ts) {
				Sexe created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Sexe findWithAssociatedLists(Long id){
        Sexe result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Sexe> update(List<Sexe> ts, boolean createIfNotExist) {
        List<Sexe> result = new ArrayList<>();
        if (ts != null) {
            for (Sexe t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Sexe loadedItem = dao.findById(t.getId()).orElse(null);
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





    public Sexe findByReferenceEntity(Sexe t){
        return t==null? null : dao.findByRef(t.getRef());
    }



    public List<Sexe> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Sexe>> getToBeSavedAndToBeDeleted(List<Sexe> oldList, List<Sexe> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Sexe> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired SexeDao dao;


}
