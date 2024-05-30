package ma.zs.gestion_service_pediatrie.service.impl.medecin.patient;


import ma.zs.gestion_service_pediatrie.zynerator.exception.EntityNotFoundException;
import ma.zs.gestion_service_pediatrie.bean.core.patient.Relation;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.patient.RelationCriteria;
import ma.zs.gestion_service_pediatrie.dao.facade.core.patient.RelationDao;
import ma.zs.gestion_service_pediatrie.dao.specification.core.patient.RelationSpecification;
import ma.zs.gestion_service_pediatrie.service.facade.medecin.patient.RelationMedecinService;
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
public class RelationMedecinServiceImpl implements RelationMedecinService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Relation update(Relation t) {
        Relation loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Relation.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Relation findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Relation findOrSave(Relation t) {
        if (t != null) {
            Relation result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Relation> importData(List<Relation> items) {
        List<Relation> list = new ArrayList<>();
        for (Relation t : items) {
            Relation founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Relation> findAll() {
        return dao.findAll();
    }

    public List<Relation> findByCriteria(RelationCriteria criteria) {
        List<Relation> content = null;
        if (criteria != null) {
            RelationSpecification mySpecification = constructSpecification(criteria);
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


    private RelationSpecification constructSpecification(RelationCriteria criteria) {
        RelationSpecification mySpecification =  (RelationSpecification) RefelexivityUtil.constructObjectUsingOneParam(RelationSpecification.class, criteria);
        return mySpecification;
    }

    public List<Relation> findPaginatedByCriteria(RelationCriteria criteria, int page, int pageSize, String order, String sortField) {
        RelationSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(RelationCriteria criteria) {
        RelationSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(Relation t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Relation> delete(List<Relation> list) {
		List<Relation> result = new ArrayList();
        if (list != null) {
            for (Relation t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Relation create(Relation t) {
        Relation loaded = findByReferenceEntity(t);
        Relation saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Relation> create(List<Relation> ts) {
        List<Relation> result = new ArrayList<>();
        if (ts != null) {
            for (Relation t : ts) {
				Relation created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Relation findWithAssociatedLists(Long id){
        Relation result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Relation> update(List<Relation> ts, boolean createIfNotExist) {
        List<Relation> result = new ArrayList<>();
        if (ts != null) {
            for (Relation t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Relation loadedItem = dao.findById(t.getId()).orElse(null);
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





    public Relation findByReferenceEntity(Relation t){
        return t==null? null : dao.findByRef(t.getRef());
    }



    public List<Relation> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Relation>> getToBeSavedAndToBeDeleted(List<Relation> oldList, List<Relation> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Relation> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired RelationDao dao;


}
