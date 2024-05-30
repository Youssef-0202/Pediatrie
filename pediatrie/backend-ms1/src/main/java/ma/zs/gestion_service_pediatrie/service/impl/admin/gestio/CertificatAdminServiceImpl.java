package ma.zs.gestion_service_pediatrie.service.impl.admin.gestio;


import ma.zs.gestion_service_pediatrie.zynerator.exception.EntityNotFoundException;
import ma.zs.gestion_service_pediatrie.bean.core.gestio.Certificat;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.gestio.CertificatCriteria;
import ma.zs.gestion_service_pediatrie.dao.facade.core.gestio.CertificatDao;
import ma.zs.gestion_service_pediatrie.dao.specification.core.gestio.CertificatSpecification;
import ma.zs.gestion_service_pediatrie.service.facade.admin.gestio.CertificatAdminService;
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

import ma.zs.gestion_service_pediatrie.service.facade.admin.consultatio.ConsultationAdminService ;
import ma.zs.gestion_service_pediatrie.bean.core.consultatio.Consultation ;

import java.util.List;
@Service
public class CertificatAdminServiceImpl implements CertificatAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Certificat update(Certificat t) {
        Certificat loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Certificat.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Certificat findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Certificat findOrSave(Certificat t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Certificat result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Certificat> importData(List<Certificat> items) {
        List<Certificat> list = new ArrayList<>();
        for (Certificat t : items) {
            Certificat founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Certificat> findAll() {
        return dao.findAll();
    }

    public List<Certificat> findByCriteria(CertificatCriteria criteria) {
        List<Certificat> content = null;
        if (criteria != null) {
            CertificatSpecification mySpecification = constructSpecification(criteria);
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


    private CertificatSpecification constructSpecification(CertificatCriteria criteria) {
        CertificatSpecification mySpecification =  (CertificatSpecification) RefelexivityUtil.constructObjectUsingOneParam(CertificatSpecification.class, criteria);
        return mySpecification;
    }

    public List<Certificat> findPaginatedByCriteria(CertificatCriteria criteria, int page, int pageSize, String order, String sortField) {
        CertificatSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(CertificatCriteria criteria) {
        CertificatSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Certificat> findByConsultatuinId(Long id){
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
    public int delete(Certificat t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Certificat> delete(List<Certificat> list) {
		List<Certificat> result = new ArrayList();
        if (list != null) {
            for (Certificat t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Certificat create(Certificat t) {
        Certificat loaded = findByReferenceEntity(t);
        Certificat saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Certificat> create(List<Certificat> ts) {
        List<Certificat> result = new ArrayList<>();
        if (ts != null) {
            for (Certificat t : ts) {
				Certificat created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Certificat findWithAssociatedLists(Long id){
        Certificat result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Certificat> update(List<Certificat> ts, boolean createIfNotExist) {
        List<Certificat> result = new ArrayList<>();
        if (ts != null) {
            for (Certificat t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Certificat loadedItem = dao.findById(t.getId()).orElse(null);
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





    public Certificat findByReferenceEntity(Certificat t){
        return t==null? null : dao.findByRef(t.getRef());
    }
    public void findOrSaveAssociatedObject(Certificat t){
        if( t != null) {
            t.setConsultatuin(consultationService.findOrSave(t.getConsultatuin()));
        }
    }



    public List<Certificat> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Certificat>> getToBeSavedAndToBeDeleted(List<Certificat> oldList, List<Certificat> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Certificat> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private ConsultationAdminService consultationService ;

    private @Autowired CertificatDao dao;


}
