package  ma.zs.gestion_service_pediatrie.ws.facade.admin.dossie;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.gestion_service_pediatrie.bean.core.dossie.AnalyseMedicale;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.dossie.AnalyseMedicaleCriteria;
import ma.zs.gestion_service_pediatrie.service.facade.admin.dossie.AnalyseMedicaleAdminService;
import ma.zs.gestion_service_pediatrie.ws.converter.dossie.AnalyseMedicaleConverter;
import ma.zs.gestion_service_pediatrie.ws.dto.dossie.AnalyseMedicaleDto;
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
@RequestMapping("/api/admin/analyseMedicale/")
public class AnalyseMedicaleRestAdmin {




    @Operation(summary = "Finds a list of all analyseMedicales")
    @GetMapping("")
    public ResponseEntity<List<AnalyseMedicaleDto>> findAll() throws Exception {
        ResponseEntity<List<AnalyseMedicaleDto>> res = null;
        List<AnalyseMedicale> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<AnalyseMedicaleDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all analyseMedicales")
    @GetMapping("optimized")
    public ResponseEntity<List<AnalyseMedicaleDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<AnalyseMedicaleDto>> res = null;
        List<AnalyseMedicale> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<AnalyseMedicaleDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a analyseMedicale by id")
    @GetMapping("id/{id}")
    public ResponseEntity<AnalyseMedicaleDto> findById(@PathVariable Long id) {
        AnalyseMedicale t = service.findById(id);
        if (t != null) {
            converter.init(true);
            AnalyseMedicaleDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a analyseMedicale by ref")
    @GetMapping("ref/{ref}")
    public ResponseEntity<AnalyseMedicaleDto> findByRef(@PathVariable String ref) {
	    AnalyseMedicale t = service.findByReferenceEntity(new AnalyseMedicale(ref));
        if (t != null) {
            converter.init(true);
            AnalyseMedicaleDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  analyseMedicale")
    @PostMapping("")
    public ResponseEntity<AnalyseMedicaleDto> save(@RequestBody AnalyseMedicaleDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            AnalyseMedicale myT = converter.toItem(dto);
            AnalyseMedicale t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                AnalyseMedicaleDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  analyseMedicale")
    @PutMapping("")
    public ResponseEntity<AnalyseMedicaleDto> update(@RequestBody AnalyseMedicaleDto dto) throws Exception {
        ResponseEntity<AnalyseMedicaleDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            AnalyseMedicale t = service.findById(dto.getId());
            converter.copy(dto,t);
            AnalyseMedicale updated = service.update(t);
            AnalyseMedicaleDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of analyseMedicale")
    @PostMapping("multiple")
    public ResponseEntity<List<AnalyseMedicaleDto>> delete(@RequestBody List<AnalyseMedicaleDto> dtos) throws Exception {
        ResponseEntity<List<AnalyseMedicaleDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<AnalyseMedicale> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified analyseMedicale")
    @DeleteMapping("")
    public ResponseEntity<AnalyseMedicaleDto> delete(@RequestBody AnalyseMedicaleDto dto) throws Exception {
		ResponseEntity<AnalyseMedicaleDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            AnalyseMedicale t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified analyseMedicale")
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
    @Operation(summary = "Delete multiple analyseMedicales by ids")
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


    @Operation(summary = "find by epreuve id")
    @GetMapping("epreuve/id/{id}")
    public List<AnalyseMedicaleDto> findByEpreuveId(@PathVariable Long id){
        return findDtos(service.findByEpreuveId(id));
    }
    @Operation(summary = "delete by epreuve id")
    @DeleteMapping("epreuve/id/{id}")
    public int deleteByEpreuveId(@PathVariable Long id){
        return service.deleteByEpreuveId(id);
    }
    @Operation(summary = "find by consultation id")
    @GetMapping("consultation/id/{id}")
    public List<AnalyseMedicaleDto> findByConsultationId(@PathVariable Long id){
        return findDtos(service.findByConsultationId(id));
    }
    @Operation(summary = "delete by consultation id")
    @DeleteMapping("consultation/id/{id}")
    public int deleteByConsultationId(@PathVariable Long id){
        return service.deleteByConsultationId(id);
    }

    @Operation(summary = "Finds a analyseMedicale and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<AnalyseMedicaleDto> findWithAssociatedLists(@PathVariable Long id) {
        AnalyseMedicale loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        AnalyseMedicaleDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds analyseMedicales by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<AnalyseMedicaleDto>> findByCriteria(@RequestBody AnalyseMedicaleCriteria criteria) throws Exception {
        ResponseEntity<List<AnalyseMedicaleDto>> res = null;
        List<AnalyseMedicale> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<AnalyseMedicaleDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated analyseMedicales by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody AnalyseMedicaleCriteria criteria) throws Exception {
        List<AnalyseMedicale> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<AnalyseMedicaleDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets analyseMedicale data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody AnalyseMedicaleCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<AnalyseMedicaleDto> findDtos(List<AnalyseMedicale> list){
        converter.initObject(true);
        List<AnalyseMedicaleDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<AnalyseMedicaleDto> getDtoResponseEntity(AnalyseMedicaleDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private AnalyseMedicaleAdminService service;
    @Autowired private AnalyseMedicaleConverter converter;





}
