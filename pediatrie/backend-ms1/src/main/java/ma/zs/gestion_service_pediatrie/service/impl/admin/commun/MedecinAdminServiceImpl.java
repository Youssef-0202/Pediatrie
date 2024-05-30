package ma.zs.gestion_service_pediatrie.service.impl.admin.commun;


import ma.zs.gestion_service_pediatrie.zynerator.exception.EntityNotFoundException;
import ma.zs.gestion_service_pediatrie.bean.core.commun.Medecin;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.commun.MedecinCriteria;
import ma.zs.gestion_service_pediatrie.dao.facade.core.commun.MedecinDao;
import ma.zs.gestion_service_pediatrie.dao.specification.core.commun.MedecinSpecification;
import ma.zs.gestion_service_pediatrie.service.facade.admin.commun.MedecinAdminService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;
import ma.zs.gestion_service_pediatrie.zynerator.util.RefelexivityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ma.zs.gestion_service_pediatrie.service.facade.admin.commun.SexeAdminService ;
import ma.zs.gestion_service_pediatrie.bean.core.commun.Sexe ;
import ma.zs.gestion_service_pediatrie.zynerator.security.service.facade.UserService;
import ma.zs.gestion_service_pediatrie.zynerator.security.service.facade.RoleService;
import ma.zs.gestion_service_pediatrie.zynerator.security.service.facade.RoleUserService;
import ma.zs.gestion_service_pediatrie.zynerator.security.bean.Role;
import ma.zs.gestion_service_pediatrie.zynerator.security.bean.RoleUser;
import ma.zs.gestion_service_pediatrie.zynerator.security.common.AuthoritiesConstants;
import ma.zs.gestion_service_pediatrie.zynerator.security.service.facade.ModelPermissionUserService;
import javax.xml.crypto.Data;
import java.util.List;

@Service
public class MedecinAdminServiceImpl implements MedecinAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Medecin update(Medecin t) {
        Medecin loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("ce compte medecin n'exeste pas dans la base de donnees", new String[]{Medecin.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Medecin findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Medecin findOrSave(Medecin t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Medecin result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Medecin> importData(List<Medecin> items) {
        List<Medecin> list = new ArrayList<>();
        for (Medecin t : items) {
            Medecin founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Medecin> findAll() {
        return dao.findAll();
    }

    public List<Medecin> findByCriteria(MedecinCriteria criteria) {
        List<Medecin> content = null;
        if (criteria != null) {
            MedecinSpecification mySpecification = constructSpecification(criteria);
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


    private MedecinSpecification constructSpecification(MedecinCriteria criteria) {
        MedecinSpecification mySpecification =  (MedecinSpecification) RefelexivityUtil.constructObjectUsingOneParam(MedecinSpecification.class, criteria);
        return mySpecification;
    }

    public List<Medecin> findPaginatedByCriteria(MedecinCriteria criteria, int page, int pageSize, String order, String sortField) {
        MedecinSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(MedecinCriteria criteria) {
        MedecinSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Medecin> findBySexeId(Long id){
        return dao.findBySexeId(id);
    }
    public int deleteBySexeId(Long id){
        return dao.deleteBySexeId(id);
    }
    public long countBySexeRef(String ref){
        return dao.countBySexeRef(ref);
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
    public int delete(Medecin t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Medecin> delete(List<Medecin> list) {
		List<Medecin> result = new ArrayList();
        if (list != null) {
            for (Medecin t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }


	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Medecin> create(List<Medecin> ts) {
        List<Medecin> result = new ArrayList<>();
        if (ts != null) {
            for (Medecin t : ts) {
				Medecin created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Medecin findWithAssociatedLists(Long id){
        Medecin result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Medecin> update(List<Medecin> ts, boolean createIfNotExist) {
        List<Medecin> result = new ArrayList<>();
        if (ts != null) {
            for (Medecin t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Medecin loadedItem = dao.findById(t.getId()).orElse(null);
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




    public Medecin findByReferenceEntity(Medecin t){
        return t==null? null : dao.findByRef(t.getRef());
    }

    public void findOrSaveAssociatedObject(Medecin t){
        if( t != null) {
            t.setSexe(sexeService.findOrSave(t.getSexe()));
        }
    }



    public List<Medecin> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Medecin>> getToBeSavedAndToBeDeleted(List<Medecin> oldList, List<Medecin> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Medecin> importExcel(MultipartFile file) {
        return null;
    }


    @Override
    public Medecin create(Medecin t) {
        if (findByUsername(t.getUsername()) != null || t.getPassword() == null) return null;

               t.setPassword(userService.cryptPassword(t.getPassword()));
               t.setUsername(t.getUsername());
               t.setEnabled(true);
               t.setAccountNonExpired(true);
               t.setAccountNonLocked(true);
               t.setCredentialsNonExpired(true);
               t.setPasswordChanged(false);
               t.setCreatedAt(LocalDateTime.now());
               t.setEmail(t.getEmail());

               Role role = roleService.findByAuthority(AuthoritiesConstants.MEDECIN);
               RoleUser roleUser = new RoleUser();
               roleUser.setRole(role);
               if (t.getRoleUsers() == null)
                 t.setRoleUsers(new ArrayList<>());

               t.getRoleUsers().add(roleUser);
               if (t.getModelPermissionUsers() == null)
                 t.setModelPermissionUsers(new ArrayList<>());

               t.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

               Medecin mySaved = dao.save(t);

               if (t.getModelPermissionUsers() != null) {
                   t.getModelPermissionUsers().forEach(e -> {
                   e.setUser(mySaved);
                   modelPermissionUserService.create(e);
               });
        }
               if (t.getRoleUsers() != null) {
                   t.getRoleUsers().forEach(element-> {
                   element.setUser(mySaved);
                   roleUserService.create(element);
               });
        }

        return mySaved;
     }

    public Medecin findByUsername(String username){
        return dao.findByUsername(username);
    }

    public boolean changePassword(String username, String newPassword) {
        return userService.changePassword(username, newPassword);
    }





    private @Autowired UserService userService;
    private @Autowired RoleService roleService;
    private @Autowired ModelPermissionUserService modelPermissionUserService;
    private @Autowired RoleUserService roleUserService;

    @Autowired
    private SexeAdminService sexeService ;

    private @Autowired MedecinDao dao;


}
