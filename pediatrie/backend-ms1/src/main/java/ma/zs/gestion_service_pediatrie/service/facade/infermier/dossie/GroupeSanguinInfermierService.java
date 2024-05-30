package ma.zs.gestion_service_pediatrie.service.facade.infermier.dossie;

import java.util.List;
import ma.zs.gestion_service_pediatrie.bean.core.dossie.GroupeSanguin;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.dossie.GroupeSanguinCriteria;
import ma.zs.gestion_service_pediatrie.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface GroupeSanguinInfermierService {







	GroupeSanguin create(GroupeSanguin t);

    GroupeSanguin update(GroupeSanguin t);

    List<GroupeSanguin> update(List<GroupeSanguin> ts,boolean createIfNotExist);

    GroupeSanguin findById(Long id);

    GroupeSanguin findOrSave(GroupeSanguin t);

    GroupeSanguin findByReferenceEntity(GroupeSanguin t);

    GroupeSanguin findWithAssociatedLists(Long id);

    List<GroupeSanguin> findAllOptimized();

    List<GroupeSanguin> findAll();

    List<GroupeSanguin> findByCriteria(GroupeSanguinCriteria criteria);

    List<GroupeSanguin> findPaginatedByCriteria(GroupeSanguinCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(GroupeSanguinCriteria criteria);

    List<GroupeSanguin> delete(List<GroupeSanguin> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<GroupeSanguin>> getToBeSavedAndToBeDeleted(List<GroupeSanguin> oldList, List<GroupeSanguin> newList);

    List<GroupeSanguin> importData(List<GroupeSanguin> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<GroupeSanguin> importExcel(MultipartFile file);

}
