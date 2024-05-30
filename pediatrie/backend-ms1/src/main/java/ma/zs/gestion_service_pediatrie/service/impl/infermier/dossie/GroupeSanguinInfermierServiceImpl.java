package ma.zs.gestion_service_pediatrie.service.impl.infermier.dossie;


import ma.zs.gestion_service_pediatrie.zynerator.exception.EntityNotFoundException;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.GroupeSanguin;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.dossie.GroupeSanguinCriteria;
import ma.zs.gestion_service_pediatrie.dao.facade.core.dossie.GroupeSanguinDao;
import ma.zs.gestion_service_pediatrie.dao.specification.core.dossie.GroupeSanguinSpecification;
import ma.zs.gestion_service_pediatrie.service.facade.infermier.dossie.GroupeSanguinInfermierService;
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
public class GroupeSanguinInfermierServiceImpl implements GroupeSanguinInfermierService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public GroupeSanguin update(GroupeSanguin t) {
        GroupeSanguin loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{GroupeSanguin.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public GroupeSanguin findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public GroupeSanguin findOrSave(GroupeSanguin t) {
        if (t != null) {
            GroupeSanguin result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<GroupeSanguin> importData(List<GroupeSanguin> items) {
        List<GroupeSanguin> list = new ArrayList<>();
        for (GroupeSanguin t : items) {
            GroupeSanguin founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<GroupeSanguin> findAll() {
        return dao.findAll();
    }

    public List<GroupeSanguin> findByCriteria(GroupeSanguinCriteria criteria) {
        List<GroupeSanguin> content = null;
        if (criteria != null) {
            GroupeSanguinSpecification mySpecification = constructSpecification(criteria);
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


    private GroupeSanguinSpecification constructSpecification(GroupeSanguinCriteria criteria) {
        GroupeSanguinSpecification mySpecification =  (GroupeSanguinSpecification) RefelexivityUtil.constructObjectUsingOneParam(GroupeSanguinSpecification.class, criteria);
        return mySpecification;
    }

    public List<GroupeSanguin> findPaginatedByCriteria(GroupeSanguinCriteria criteria, int page, int pageSize, String order, String sortField) {
        GroupeSanguinSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(GroupeSanguinCriteria criteria) {
        GroupeSanguinSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(GroupeSanguin t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<GroupeSanguin> delete(List<GroupeSanguin> list) {
		List<GroupeSanguin> result = new ArrayList();
        if (list != null) {
            for (GroupeSanguin t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public GroupeSanguin create(GroupeSanguin t) {
        GroupeSanguin loaded = findByReferenceEntity(t);
        GroupeSanguin saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<GroupeSanguin> create(List<GroupeSanguin> ts) {
        List<GroupeSanguin> result = new ArrayList<>();
        if (ts != null) {
            for (GroupeSanguin t : ts) {
				GroupeSanguin created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public GroupeSanguin findWithAssociatedLists(Long id){
        GroupeSanguin result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<GroupeSanguin> update(List<GroupeSanguin> ts, boolean createIfNotExist) {
        List<GroupeSanguin> result = new ArrayList<>();
        if (ts != null) {
            for (GroupeSanguin t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    GroupeSanguin loadedItem = dao.findById(t.getId()).orElse(null);
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





    public GroupeSanguin findByReferenceEntity(GroupeSanguin t){
        return t==null? null : dao.findByRef(t.getRef());
    }



    public List<GroupeSanguin> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<GroupeSanguin>> getToBeSavedAndToBeDeleted(List<GroupeSanguin> oldList, List<GroupeSanguin> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<GroupeSanguin> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired GroupeSanguinDao dao;


}
