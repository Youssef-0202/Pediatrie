package  ma.zs.gestion_service_pediatrie.ws.facade.infermier.rappor;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.gestion_service_pediatrie.bean.core.rappor.Diagnostic;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.rappor.DiagnosticCriteria;
import ma.zs.gestion_service_pediatrie.service.facade.infermier.rappor.DiagnosticInfermierService;
import ma.zs.gestion_service_pediatrie.ws.converter.rappor.DiagnosticConverter;
import ma.zs.gestion_service_pediatrie.ws.dto.rappor.DiagnosticDto;
import ma.zs.gestion_service_pediatrie.zynerator.controller.AbstractController;
import ma.zs.gestion_service_pediatrie.zynerator.dto.AuditEntityDto;
import ma.zs.gestion_service_pediatrie.zynerator.util.PaginatedList;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zs.gestion_service_pediatrie.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zs.gestion_service_pediatrie.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/infermier/diagnostic/")
public class DiagnosticRestInfermier {




    @Operation(summary = "Finds a list of all diagnostics")
    @GetMapping("")
    public ResponseEntity<List<DiagnosticDto>> findAll() throws Exception {
        ResponseEntity<List<DiagnosticDto>> res = null;
        List<Diagnostic> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<DiagnosticDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "find by num dossier")
    @GetMapping("dossier/num/{num}")
    public List<DiagnosticDto> findByDossierNum(@PathVariable String num){
        return findDtos(service.findByNumDossier(num));
    }

    @Operation(summary = "Finds an optimized list of all diagnostics")
    @GetMapping("optimized")
    public ResponseEntity<List<DiagnosticDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<DiagnosticDto>> res = null;
        List<Diagnostic> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<DiagnosticDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a diagnostic by id")
    @GetMapping("id/{id}")
    public ResponseEntity<DiagnosticDto> findById(@PathVariable Long id) {
        Diagnostic t = service.findById(id);
        if (t != null) {
            converter.init(true);
            DiagnosticDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a diagnostic by ref")
    @GetMapping("ref/{ref}")
    public ResponseEntity<DiagnosticDto> findByRef(@PathVariable String ref) {
	    Diagnostic t = service.findByReferenceEntity(new Diagnostic(ref));
        if (t != null) {
            converter.init(true);
            DiagnosticDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  diagnostic")
    @PostMapping("")
    public ResponseEntity<DiagnosticDto> save(@RequestBody DiagnosticDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Diagnostic myT = converter.toItem(dto);
            Diagnostic t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                DiagnosticDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  diagnostic")
    @PutMapping("")
    public ResponseEntity<DiagnosticDto> update(@RequestBody DiagnosticDto dto) throws Exception {
        ResponseEntity<DiagnosticDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Diagnostic t = service.findById(dto.getId());
            converter.copy(dto,t);
            Diagnostic updated = service.update(t);
            DiagnosticDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of diagnostic")
    @PostMapping("multiple")
    public ResponseEntity<List<DiagnosticDto>> delete(@RequestBody List<DiagnosticDto> dtos) throws Exception {
        ResponseEntity<List<DiagnosticDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Diagnostic> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified diagnostic")
    @DeleteMapping("")
    public ResponseEntity<DiagnosticDto> delete(@RequestBody DiagnosticDto dto) throws Exception {
		ResponseEntity<DiagnosticDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            Diagnostic t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified diagnostic")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        ResponseEntity<Long> res;
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;
        if (id != null) {
            boolean resultDelete = service.deleteById(id);
            if (resultDelete) {
                status = HttpStatus.OK;
            }
        }
        res = new ResponseEntity<>(id, status);
        return res;
    }
    @Operation(summary = "Delete multiple diagnostics by ids")
    @DeleteMapping("multiple/id")
    public ResponseEntity<List<Long>> deleteByIdIn(@RequestBody List<Long> ids) throws Exception {
        ResponseEntity<List<Long>> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (ids != null) {
            service.deleteByIdIn(ids);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(ids, status);
        return res;
     }


    @Operation(summary = "find by consultation id")
    @GetMapping("consultation/id/{id}")
    public List<DiagnosticDto> findByConsultationId(@PathVariable Long id){
        return findDtos(service.findByConsultationId(id));
    }
    @Operation(summary = "delete by consultation id")
    @DeleteMapping("consultation/id/{id}")
    public int deleteByConsultationId(@PathVariable Long id){
        return service.deleteByConsultationId(id);
    }

    @Operation(summary = "Finds a diagnostic and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<DiagnosticDto> findWithAssociatedLists(@PathVariable Long id) {
        Diagnostic loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        DiagnosticDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds diagnostics by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<DiagnosticDto>> findByCriteria(@RequestBody DiagnosticCriteria criteria) throws Exception {
        ResponseEntity<List<DiagnosticDto>> res = null;
        List<Diagnostic> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<DiagnosticDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated diagnostics by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody DiagnosticCriteria criteria) throws Exception {
        List<Diagnostic> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<DiagnosticDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets diagnostic data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody DiagnosticCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<DiagnosticDto> findDtos(List<Diagnostic> list){
        converter.initObject(true);
        List<DiagnosticDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<DiagnosticDto> getDtoResponseEntity(DiagnosticDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private DiagnosticInfermierService service;
    @Autowired private DiagnosticConverter converter;





}
