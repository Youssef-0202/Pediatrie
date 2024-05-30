package ma.zs.gestion_service_pediatrie.zynerator.process;

import ma.zs.gestion_service_pediatrie.zynerator.audit.AuditBusinessObject;

public interface AbstractProcess<I extends AbstractProcessInput, K extends AbstractProcessOutput, T extends AuditBusinessObject> {
    Result<I, K, T> execute(I input);
}
