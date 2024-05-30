package ma.zs.gestion_service_pediatrie.zynerator.security.service.facade;

import ma.zs.gestion_service_pediatrie.zynerator.security.bean.ModelPermission;
import ma.zs.gestion_service_pediatrie.zynerator.security.dao.criteria.core.ModelPermissionCriteria;
import ma.zs.gestion_service_pediatrie.zynerator.service.IService;
import java.util.List;



public interface ModelPermissionService extends  IService<ModelPermission,ModelPermissionCriteria>  {
    List<ModelPermission> findAllOptimized();

}
