package ma.zs.gestion_service_pediatrie.zynerator.security.service.facade;

import ma.zs.gestion_service_pediatrie.zynerator.security.bean.ActionPermission;
import ma.zs.gestion_service_pediatrie.zynerator.security.dao.criteria.core.ActionPermissionCriteria;
import ma.zs.gestion_service_pediatrie.zynerator.service.IService;
import java.util.List;


public interface ActionPermissionService extends  IService<ActionPermission,ActionPermissionCriteria>  {

    List<ActionPermission> findAllOptimized();

}
