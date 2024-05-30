package ma.zs.gestion_service_pediatrie.zynerator.security.service.facade;

import ma.zs.gestion_service_pediatrie.zynerator.security.bean.Role;
import ma.zs.gestion_service_pediatrie.zynerator.security.dao.criteria.core.RoleCriteria;
import ma.zs.gestion_service_pediatrie.zynerator.service.IService;



public interface RoleService extends  IService<Role,RoleCriteria>  {
    Role findByAuthority(String authority);
    int deleteByAuthority(String authority);


    



}
