package ma.zs.gestion_service_pediatrie.service.impl.infermier.dossie;


import ma.zs.gestion_service_pediatrie.zynerator.exception.EntityNotFoundException;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.Antecedent;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.dossie.AntecedentCriteria;
import ma.zs.gestion_service_pediatrie.dao.facade.core.dossie.AntecedentDao;
import ma.zs.gestion_service_pediatrie.dao.specification.core.dossie.AntecedentSpecification;
import ma.zs.gestion_service_pediatrie.service.facade.infermier.dossie.AntecedentInfermierService;
import org.springframework.data.jpa.repository.Query;
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

import ma.zs.gestion_service_pediatrie.service.facade.infermier.dossie.GroupeSanguinInfermierService ;

@Service
public class AntecedentInfermierServiceImpl implements AntecedentInfermierService {
   @Override
    @Query("SELECT a.antecedent FROM FichePatient a WHERE a.consultation.patient.numDossier = :nom")
    public List<Antecedent> findNumDossier(String nom) {
        return dao.findNumDossier(nom);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Antecedent update(Antecedent t) {
        Antecedent loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Antecedent.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Antecedent findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Antecedent findOrSave(Antecedent t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Antecedent result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Antecedent> importData(List<Antecedent> items) {
        List<Antecedent> list = new ArrayList<>();
        for (Antecedent t : items) {
            Antecedent founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Antecedent> findAll() {
        return dao.findAll();
    }

    public List<Antecedent> findByCriteria(AntecedentCriteria criteria) {
        List<Antecedent> content = null;
        if (criteria != null) {
            AntecedentSpecification mySpecification = constructSpecification(criteria);
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


    private AntecedentSpecification constructSpecification(AntecedentCriteria criteria) {
        AntecedentSpecification mySpecification =  (AntecedentSpecification) RefelexivityUtil.constructObjectUsingOneParam(AntecedentSpecification.class, criteria);
        return mySpecification;
    }

    public List<Antecedent> findPaginatedByCriteria(AntecedentCriteria criteria, int page, int pageSize, String order, String sortField) {
        AntecedentSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(AntecedentCriteria criteria) {
        AntecedentSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Antecedent> findByGroupeSanguinId(Long id){
        return dao.findByGroupeSanguinId(id);
    }
    public int deleteByGroupeSanguinId(Long id){
        return dao.deleteByGroupeSanguinId(id);
    }
    public long countByGroupeSanguinRef(String ref){
        return dao.countByGroupeSanguinRef(ref);
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
    public int delete(Antecedent t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Antecedent> delete(List<Antecedent> list) {
		List<Antecedent> result = new ArrayList();
        if (list != null) {
            for (Antecedent t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Antecedent create(Antecedent t) {
        Antecedent loaded = findByReferenceEntity(t);
        Antecedent saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Antecedent> create(List<Antecedent> ts) {
        List<Antecedent> result = new ArrayList<>();
        if (ts != null) {
            for (Antecedent t : ts) {
				Antecedent created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Antecedent findWithAssociatedLists(Long id){
        Antecedent result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Antecedent> update(List<Antecedent> ts, boolean createIfNotExist) {
        List<Antecedent> result = new ArrayList<>();
        if (ts != null) {
            for (Antecedent t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Antecedent loadedItem = dao.findById(t.getId()).orElse(null);
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





    public Antecedent findByReferenceEntity(Antecedent t){
        return t==null? null : dao.findByRef(t.getRef());
    }
    public void findOrSaveAssociatedObject(Antecedent t){
        if( t != null) {
            t.setGroupeSanguin(groupeSanguinService.findOrSave(t.getGroupeSanguin()));
        }
    }



    public List<Antecedent> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Antecedent>> getToBeSavedAndToBeDeleted(List<Antecedent> oldList, List<Antecedent> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Antecedent> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private GroupeSanguinInfermierService groupeSanguinService ;

    private @Autowired AntecedentDao dao;


}
