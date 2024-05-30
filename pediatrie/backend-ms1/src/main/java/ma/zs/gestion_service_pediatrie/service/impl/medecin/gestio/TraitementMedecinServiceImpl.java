package ma.zs.gestion_service_pediatrie.service.impl.medecin.gestio;


import ma.zs.gestion_service_pediatrie.zynerator.exception.EntityNotFoundException;
import ma.zs.gestion_service_pediatrie.bean.core.gestio.Traitement;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.gestio.TraitementCriteria;
import ma.zs.gestion_service_pediatrie.dao.facade.core.gestio.TraitementDao;
import ma.zs.gestion_service_pediatrie.dao.specification.core.gestio.TraitementSpecification;
import ma.zs.gestion_service_pediatrie.service.facade.medecin.gestio.TraitementMedecinService;
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

import ma.zs.gestion_service_pediatrie.service.facade.medecin.gestio.OrdonnanceMedecinService ;
import ma.zs.gestion_service_pediatrie.bean.core.gestio.Ordonnance ;
import ma.zs.gestion_service_pediatrie.service.facade.medecin.consultatio.ConsultationMedecinService ;
import ma.zs.gestion_service_pediatrie.bean.core.consultatio.Consultation ;
import ma.zs.gestion_service_pediatrie.service.facade.medecin.gestio.MedicamentMedecinService ;
import ma.zs.gestion_service_pediatrie.bean.core.gestio.Medicament ;

import java.util.List;
@Service
public class TraitementMedecinServiceImpl implements TraitementMedecinService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Traitement update(Traitement t) {
        Traitement loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Traitement.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public Traitement findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Traitement findOrSave(Traitement t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Traitement result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Traitement> importData(List<Traitement> items) {
        List<Traitement> list = new ArrayList<>();
        for (Traitement t : items) {
            Traitement founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Traitement> findAll() {
        return dao.findAll();
    }

    public List<Traitement> findByCriteria(TraitementCriteria criteria) {
        List<Traitement> content = null;
        if (criteria != null) {
            TraitementSpecification mySpecification = constructSpecification(criteria);
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


    private TraitementSpecification constructSpecification(TraitementCriteria criteria) {
        TraitementSpecification mySpecification =  (TraitementSpecification) RefelexivityUtil.constructObjectUsingOneParam(TraitementSpecification.class, criteria);
        return mySpecification;
    }

    public List<Traitement> findPaginatedByCriteria(TraitementCriteria criteria, int page, int pageSize, String order, String sortField) {
        TraitementSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(TraitementCriteria criteria) {
        TraitementSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Traitement> findByOrdonnanceId(Long id){
        return dao.findByOrdonnanceId(id);
    }
    public int deleteByOrdonnanceId(Long id){
        return dao.deleteByOrdonnanceId(id);
    }
    public long countByOrdonnanceRef(String ref){
        return dao.countByOrdonnanceRef(ref);
    }
    public List<Traitement> findByConsultatuinId(Long id){
        return dao.findByConsultatuinId(id);
    }

    @Transactional
    public int deleteByConsultatuinId(Long id){
        return dao.deleteByConsultatuinId(id);
    }
    public long countByConsultatuinRef(String ref){
        return dao.countByConsultatuinRef(ref);
    }

	public boolean deleteById(Long id) {
        boolean condition = deleteByIdCheckCondition(id);
        if (condition) {
            deleteAssociatedLists(id);
            dao.deleteById(id);
        }
        return condition;
    }

    public boolean deleteByIdCheckCondition(Long id) {
        return true;
    }

    public void deleteByIdIn(List<Long> ids) {

    }
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public int delete(Traitement t) {
        int result = 0;
        if (t != null) {
            deleteAssociatedLists(t.getId());
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }
    @Transactional
    public void deleteAssociatedLists(Long id) {
        medicamentService.deleteByTraitementId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Traitement> delete(List<Traitement> list) {
		List<Traitement> result = new ArrayList();
        if (list != null) {
            for (Traitement t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Traitement create(Traitement t) {
        Traitement loaded = findByReferenceEntity(t);
        Traitement saved;
        if (loaded == null) {
            saved = dao.save(t);
            if (t.getMedicaments() != null) {
                t.getMedicaments().forEach(element-> {
                    element.setTraitement(saved);
                    medicamentService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Traitement> create(List<Traitement> ts) {
        List<Traitement> result = new ArrayList<>();
        if (ts != null) {
            for (Traitement t : ts) {
				Traitement created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Traitement findWithAssociatedLists(Long id){
        Traitement result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setMedicaments(medicamentService.findByTraitementId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Traitement> update(List<Traitement> ts, boolean createIfNotExist) {
        List<Traitement> result = new ArrayList<>();
        if (ts != null) {
            for (Traitement t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Traitement loadedItem = dao.findById(t.getId()).orElse(null);
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

    public void updateWithAssociatedLists(Traitement traitement){
    if(traitement !=null && traitement.getId() != null){
        List<List<Medicament>> resultMedicaments= medicamentService.getToBeSavedAndToBeDeleted(medicamentService.findByTraitementId(traitement.getId()),traitement.getMedicaments());
            medicamentService.delete(resultMedicaments.get(1));
        ListUtil.emptyIfNull(resultMedicaments.get(0)).forEach(e -> e.setTraitement(traitement));
        medicamentService.update(resultMedicaments.get(0),true);
        }
    }




    public Traitement findByReferenceEntity(Traitement t){
        return t==null? null : dao.findByRef(t.getRef());
    }
    public void findOrSaveAssociatedObject(Traitement t){
        if( t != null) {
            t.setOrdonnance(ordonnanceService.findOrSave(t.getOrdonnance()));
            t.setConsultatuin(consultationService.findOrSave(t.getConsultatuin()));
        }
    }



    public List<Traitement> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Traitement>> getToBeSavedAndToBeDeleted(List<Traitement> oldList, List<Traitement> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Traitement> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private OrdonnanceMedecinService ordonnanceService ;
    @Autowired
    private ConsultationMedecinService consultationService ;
    @Autowired
    private MedicamentMedecinService medicamentService ;

    private @Autowired TraitementDao dao;


}
