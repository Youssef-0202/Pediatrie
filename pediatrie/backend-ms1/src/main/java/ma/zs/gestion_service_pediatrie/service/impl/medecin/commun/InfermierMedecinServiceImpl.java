package ma.zs.gestion_service_pediatrie.service.impl.medecin.commun;


import ma.zs.gestion_service_pediatrie.zynerator.exception.EntityNotFoundException;
import ma.zs.gestion_service_pediatrie.bean.core.commun.Infermier;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.commun.InfermierCriteria;
import ma.zs.gestion_service_pediatrie.dao.facade.core.commun.InfermierDao;
import ma.zs.gestion_service_pediatrie.dao.specification.core.commun.InfermierSpecification;
import ma.zs.gestion_service_pediatrie.service.facade.medecin.commun.InfermierMedecinService;
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

import ma.zs.gestion_service_pediatrie.service.facade.medecin.commun.SexeMedecinService ;
import ma.zs.gestion_service_pediatrie.bean.core.commun.Sexe ;

import java.time.LocalDateTime;
import ma.zs.gestion_service_pediatrie.zynerator.security.service.facade.UserService;
import ma.zs.gestion_service_pediatrie.zynerator.security.service.facade.RoleService;
import ma.zs.gestion_service_pediatrie.zynerator.security.service.facade.RoleUserService;
import ma.zs.gestion_service_pediatrie.zynerator.security.bean.Role;
import ma.zs.gestion_service_pediatrie.zynerator.security.bean.RoleUser;
import ma.zs.gestion_service_pediatrie.zynerator.security.common.AuthoritiesConstants;
import ma.zs.gestion_service_pediatrie.zynerator.security.service.facade.ModelPermissionUserService;
import java.util.Collection;
import java.util.List;
@Service
public class InfermierMedecinServiceImpl implements InfermierMedecinService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Infermier update(Infermier t) {
        Infermier loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Infermier.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Infermier findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Infermier findOrSave(Infermier t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Infermier result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Infermier> importData(List<Infermier> items) {
        List<Infermier> list = new ArrayList<>();
        for (Infermier t : items) {
            Infermier founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Infermier> findAll() {
        return dao.findAll();
    }

    public List<Infermier> findByCriteria(InfermierCriteria criteria) {
        List<Infermier> content = null;
        if (criteria != null) {
            InfermierSpecification mySpecification = constructSpecification(criteria);
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


    private InfermierSpecification constructSpecification(InfermierCriteria criteria) {
        InfermierSpecification mySpecification =  (InfermierSpecification) RefelexivityUtil.constructObjectUsingOneParam(InfermierSpecification.class, criteria);
        return mySpecification;
    }

    public List<Infermier> findPaginatedByCriteria(InfermierCriteria criteria, int page, int pageSize, String order, String sortField) {
        InfermierSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(InfermierCriteria criteria) {
        InfermierSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Infermier> findBySexeId(Long id){
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
    public int delete(Infermier t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Infermier> delete(List<Infermier> list) {
		List<Infermier> result = new ArrayList();
        if (list != null) {
            for (Infermier t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }


	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Infermier> create(List<Infermier> ts) {
        List<Infermier> result = new ArrayList<>();
        if (ts != null) {
            for (Infermier t : ts) {
				Infermier created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Infermier findWithAssociatedLists(Long id){
        Infermier result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Infermier> update(List<Infermier> ts, boolean createIfNotExist) {
        List<Infermier> result = new ArrayList<>();
        if (ts != null) {
            for (Infermier t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Infermier loadedItem = dao.findById(t.getId()).orElse(null);
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





    public Infermier findByReferenceEntity(Infermier t){
        return t==null? null : dao.findByRef(t.getRef());
    }
    public void findOrSaveAssociatedObject(Infermier t){
        if( t != null) {
            t.setSexe(sexeService.findOrSave(t.getSexe()));
        }
    }



    public List<Infermier> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Infermier>> getToBeSavedAndToBeDeleted(List<Infermier> oldList, List<Infermier> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Infermier> importExcel(MultipartFile file) {
        return null;
    }


    @Override
    public Infermier create(Infermier t) {
        if (findByUsername(t.getUsername()) != null || t.getPassword() == null) return null;
        t.setPassword(userService.cryptPassword(t.getPassword()));
        t.setEnabled(true);
        t.setAccountNonExpired(true);
        t.setAccountNonLocked(true);
        t.setCredentialsNonExpired(true);
        t.setPasswordChanged(false);

        Role role = new Role();
        role.setAuthority(AuthoritiesConstants.MEDECIN);
        role.setCreatedAt(LocalDateTime.now());
        Role myRole = roleService.create(role);
        RoleUser roleUser = new RoleUser();
        roleUser.setRole(myRole);
        if (t.getRoleUsers() == null)
        t.setRoleUsers(new ArrayList<>());

        t.getRoleUsers().add(roleUser);
        if (t.getModelPermissionUsers() == null)
        t.setModelPermissionUsers(new ArrayList<>());

        t.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

        Infermier mySaved = dao.save(t);

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

    public Infermier findByUsername(String username){
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
    private SexeMedecinService sexeService ;

    private @Autowired InfermierDao dao;


}
