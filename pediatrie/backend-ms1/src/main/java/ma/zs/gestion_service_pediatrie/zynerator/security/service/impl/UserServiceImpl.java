package ma.zs.gestion_service_pediatrie.zynerator.security.service.impl;


import ma.zs.gestion_service_pediatrie.zynerator.security.bean.ModelPermissionUser;
import ma.zs.gestion_service_pediatrie.zynerator.security.bean.Role;
import ma.zs.gestion_service_pediatrie.zynerator.security.bean.RoleUser;
import ma.zs.gestion_service_pediatrie.zynerator.security.bean.User;
import ma.zs.gestion_service_pediatrie.zynerator.security.common.AuthoritiesConstants;
import ma.zs.gestion_service_pediatrie.zynerator.security.dao.criteria.core.UserCriteria;
import ma.zs.gestion_service_pediatrie.zynerator.security.dao.facade.core.UserDao;
import ma.zs.gestion_service_pediatrie.zynerator.security.dao.specification.core.UserSpecification;
import ma.zs.gestion_service_pediatrie.zynerator.security.service.facade.*;
import ma.zs.gestion_service_pediatrie.zynerator.security.ws.dto.RoleUserDto;
import ma.zs.gestion_service_pediatrie.zynerator.service.AbstractServiceImpl;
import ma.zs.gestion_service_pediatrie.zynerator.util.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl extends AbstractServiceImpl<User, UserCriteria, UserDao> implements UserService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public User create(User t) {
        System.out.println(t.getUsername());
        User foundedUserByUsername = findByUsername(t.getUsername());
        User foundedUserByEmail = dao.findByEmail(t.getEmail());
        if (foundedUserByUsername != null || foundedUserByEmail != null) return null;
        else {
            if (t.getPassword() == null || t.getPassword().isEmpty()) {
                t.setPassword(bCryptPasswordEncoder.encode(t.getUsername()));
            }
            else {
                t.setPassword(bCryptPasswordEncoder.encode(t.getPassword()));
            }
            t.setAccountNonExpired(true);
            t.setAccountNonLocked(true);
            t.setCredentialsNonExpired(true);
            t.setEnabled(true);
            t.setPasswordChanged(false);
            t.setCreatedAt(LocalDateTime.now());
            Role roleFor = roleService.findByAuthority(AuthoritiesConstants.ADMIN);
            super.create(t);
            if (t.getModelPermissionUsers() != null) {
                t.getModelPermissionUsers().forEach(e -> {
                    e.setUser(t);
                    modelPermissionUserService.create(e);
                });
            }
            if (t.getRoleUsers() != null) {
                t.getRoleUsers().forEach(element-> {
                    element.setUser(t);
                    element.setRole(roleFor);
                    roleUserService.create(element);
                });
            }else{
                System.out.println("error");
            }
            return t;
        }
    }



    public User findWithAssociatedLists(Long id){
        User result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setModelPermissionUsers(modelPermissionUserService.findByUserId(id));
            result.setRoleUsers(roleUserService.findByUserId(id));
        }
        return result;
    }
    @Transactional
    public void deleteAssociatedLists(Long id) {
        modelPermissionUserService.deleteByUserId(id);
        roleUserService.deleteByUserId(id);
    }


    public void updateWithAssociatedLists(User user){
    if(user !=null && user.getId() != null){
            List<List<ModelPermissionUser>> resultModelPermissionUsers= modelPermissionUserService.getToBeSavedAndToBeDeleted(modelPermissionUserService.findByUserId(user.getId()),user.getModelPermissionUsers());
            modelPermissionUserService.delete(resultModelPermissionUsers.get(1));
            ListUtil.emptyIfNull(resultModelPermissionUsers.get(0)).forEach(e -> e.setUser(user));
            modelPermissionUserService.update(resultModelPermissionUsers.get(0),true);
            List<List<RoleUser>> resultRoleUsers= roleUserService.getToBeSavedAndToBeDeleted(roleUserService.findByUserId(user.getId()),user.getRoleUsers());
            roleUserService.delete(resultRoleUsers.get(1));
            ListUtil.emptyIfNull(resultRoleUsers.get(0)).forEach(e -> e.setUser(user));
            roleUserService.update(resultRoleUsers.get(0),true);
    }
    }



    public User findByReferenceEntity(User t){
        return  dao.findByEmail(t.getEmail());
    }
    @Override
    public User findByUsername(String username) {
        if (username == null)
            return null;
        return dao.findByUsername(username);
    }

    public List<User> findAllOptimized() {
        return dao.findAllOptimized();
    }


    @Override
    public String cryptPassword(String value) {
        return value == null ? null : bCryptPasswordEncoder.encode(value);
    }

    @Override
    public boolean changePassword(String username, String newPassword) {
        User user = dao.findByUsername(username);
        System.out.println("userName: "+username);
        System.out.println("Password: "+newPassword);
        if (user != null) {
            user.setPassword(cryptPassword(newPassword));
            user.setPasswordChanged(true);
            dao.save(user);
            return true;
        }
        return false;
    }
    @Override
    public User findByUsernameWithRoles(String username) {
        if (username == null)
            return null;
        return dao.findByUsername(username);
    }

    @Override
    @Transactional
    public int deleteByUsername(String username) {
        return dao.deleteByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByUsernameWithRoles(username);
    }

    public void configure() {
        super.configure(User.class, UserSpecification.class);
    }


    @Autowired
    private RoleUserService roleUserService ;
    @Autowired
    private ModelPermissionService modelPermissionService ;
    @Autowired
    private ActionPermissionService actionPermissionService ;
    @Autowired
    private ModelPermissionUserService modelPermissionUserService ;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;



    @Lazy
    @Autowired
    PasswordEncoder bCryptPasswordEncoder;


    public UserServiceImpl(UserDao dao) {
        super(dao);
    }

}
