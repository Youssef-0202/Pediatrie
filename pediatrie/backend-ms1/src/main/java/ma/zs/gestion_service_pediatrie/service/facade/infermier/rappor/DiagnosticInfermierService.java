package ma.zs.gestion_service_pediatrie.service.facade.infermier.rappor;

import java.util.List;
import ma.zs.gestion_service_pediatrie.bean.core.rappor.Diagnostic;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.rappor.DiagnosticCriteria;
import ma.zs.gestion_service_pediatrie.zynerator.service.IService;


import org.springframework.data.jpa.repository.Query;
import org.springframework.web.multipart.MultipartFile;

public interface DiagnosticInfermierService {



    List<Diagnostic> findByConsultationId(Long id);
    int deleteByConsultationId(Long id);
    long countByConsultationRef(String ref);




	Diagnostic create(Diagnostic t);

    @Query("SELECT a.diagnostic FROM Consultation a WHERE a.patient.numDossier = :nom")
    List<Diagnostic> findByNumDossier(String nom);

    Diagnostic update(Diagnostic t);

    List<Diagnostic> update(List<Diagnostic> ts,boolean createIfNotExist);

    Diagnostic findById(Long id);

    Diagnostic findOrSave(Diagnostic t);

    Diagnostic findByReferenceEntity(Diagnostic t);

    Diagnostic findWithAssociatedLists(Long id);

    List<Diagnostic> findAllOptimized();

    List<Diagnostic> findAll();

    List<Diagnostic> findByCriteria(DiagnosticCriteria criteria);

    List<Diagnostic> findPaginatedByCriteria(DiagnosticCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(DiagnosticCriteria criteria);

    List<Diagnostic> delete(List<Diagnostic> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Diagnostic>> getToBeSavedAndToBeDeleted(List<Diagnostic> oldList, List<Diagnostic> newList);

    List<Diagnostic> importData(List<Diagnostic> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Diagnostic> importExcel(MultipartFile file);

}
