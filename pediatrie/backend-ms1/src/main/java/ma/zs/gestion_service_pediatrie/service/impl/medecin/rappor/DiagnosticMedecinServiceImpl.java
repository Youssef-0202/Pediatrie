package ma.zs.gestion_service_pediatrie.service.impl.medecin.rappor;


import ma.zs.gestion_service_pediatrie.zynerator.exception.EntityNotFoundException;
import ma.zs.gestion_service_pediatrie.bean.core.rappor.Diagnostic;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.rappor.DiagnosticCriteria;
import ma.zs.gestion_service_pediatrie.dao.facade.core.rappor.DiagnosticDao;
import ma.zs.gestion_service_pediatrie.dao.specification.core.rappor.DiagnosticSpecification;
import ma.zs.gestion_service_pediatrie.service.facade.medecin.rappor.DiagnosticMedecinService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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

import ma.zs.gestion_service_pediatrie.service.facade.medecin.consultatio.ConsultationMedecinService ;

@Service
public class DiagnosticMedecinServiceImpl implements DiagnosticMedecinService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Diagnostic update(Diagnostic t) {
        Diagnostic loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Diagnostic.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public List<Diagnostic> findByConsultationRef(String ref) {
        return dao.findByConsultationRef(ref);
    }

    public Diagnostic findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Diagnostic findOrSave(Diagnostic t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Diagnostic result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Diagnostic> importData(List<Diagnostic> items) {
        List<Diagnostic> list = new ArrayList<>();
        for (Diagnostic t : items) {
            Diagnostic founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Diagnostic> findAll() {
        return dao.findAll();
    }

    public List<Diagnostic> findByCriteria(DiagnosticCriteria criteria) {
        List<Diagnostic> content = null;
        if (criteria != null) {
            DiagnosticSpecification mySpecification = constructSpecification(criteria);
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


    private DiagnosticSpecification constructSpecification(DiagnosticCriteria criteria) {
        DiagnosticSpecification mySpecification =  (DiagnosticSpecification) RefelexivityUtil.constructObjectUsingOneParam(DiagnosticSpecification.class, criteria);
        return mySpecification;
    }

    public List<Diagnostic> findPaginatedByCriteria(DiagnosticCriteria criteria, int page, int pageSize, String order, String sortField) {
        DiagnosticSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(DiagnosticCriteria criteria) {
        DiagnosticSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Diagnostic> findByConsultationId(Long id){
        return dao.findByConsultationId(id);
    }
    @Transactional
    public int deleteByConsultationId(Long id){
        return dao.deleteByConsultationId(id);
    }
    public long countByConsultationRef(String ref){
        return dao.countByConsultationRef(ref);
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
    public int delete(Diagnostic t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Diagnostic> delete(List<Diagnostic> list) {
		List<Diagnostic> result = new ArrayList();
        if (list != null) {
            for (Diagnostic t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Diagnostic create(Diagnostic t) {
        Diagnostic loaded = findByReferenceEntity(t);
        Diagnostic saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Diagnostic> create(List<Diagnostic> ts) {
        List<Diagnostic> result = new ArrayList<>();
        if (ts != null) {
            for (Diagnostic t : ts) {
				Diagnostic created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Diagnostic findWithAssociatedLists(Long id){
        Diagnostic result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Diagnostic> update(List<Diagnostic> ts, boolean createIfNotExist) {
        List<Diagnostic> result = new ArrayList<>();
        if (ts != null) {
            for (Diagnostic t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Diagnostic loadedItem = dao.findById(t.getId()).orElse(null);
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





    public Diagnostic findByReferenceEntity(Diagnostic t){
        return t==null? null : dao.findByRef(t.getRef());
    }
    public void findOrSaveAssociatedObject(Diagnostic t){
        if( t != null) {
            t.setConsultation(consultationService.findOrSave(t.getConsultation()));
        }
    }
    @Override
    @Query("SELECT a.diagnostic FROM Consultation a WHERE a.patient.numDossier = :nom")
    public List<Diagnostic> findByNumDossier(String nom) {
        return dao.findByNumDossier(nom);
    }

    public List<Diagnostic> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Diagnostic>> getToBeSavedAndToBeDeleted(List<Diagnostic> existingContacts, List<Diagnostic> newContacts) {
        List<Diagnostic> toSave = new ArrayList<>();
        List<Diagnostic> toDelete = new ArrayList<>();

        // Find contacts to save
        for (Diagnostic newContact : newContacts) {
            boolean found = false;
            for (Diagnostic existingContact : existingContacts) {
                if (existingContact.getRef().equals(newContact.getRef())) {
                    // Update existing contact
                    existingContact.setDateDiagnostic(newContact.getDateDiagnostic());
                    existingContact.setDescription(newContact.getDescription());
                    existingContact.setRef(newContact.getRef());
                    toSave.add(existingContact);
                    found = true;
                    break;
                }
            }
            if (!found) {
                // Add new contact
                toSave.add(newContact);
            }
        }

        // Find contacts to delete
        for (Diagnostic existingContact : existingContacts) {
            boolean found = false;
            for (Diagnostic newContact : newContacts) {
                if (existingContact.getRef().equals(newContact.getRef())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                // Delete existing contact
                toDelete.add(existingContact);
            }
        }

        return Arrays.asList(toSave, toDelete);
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Diagnostic> importExcel(MultipartFile file) {
        return null;
    }




    @Autowired
    private ConsultationMedecinService consultationService ;

    private @Autowired DiagnosticDao dao;


}
