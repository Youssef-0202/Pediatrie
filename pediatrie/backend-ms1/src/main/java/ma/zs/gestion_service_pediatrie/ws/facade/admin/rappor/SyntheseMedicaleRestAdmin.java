package  ma.zs.gestion_service_pediatrie.ws.facade.admin.rappor;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.gestion_service_pediatrie.bean.core.rappor.SyntheseMedicale;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.rappor.SyntheseMedicaleCriteria;
import ma.zs.gestion_service_pediatrie.service.facade.admin.rappor.SyntheseMedicaleAdminService;
import ma.zs.gestion_service_pediatrie.ws.converter.rappor.SyntheseMedicaleConverter;
import ma.zs.gestion_service_pediatrie.ws.dto.rappor.SyntheseMedicaleDto;
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
@RequestMapping("/api/admin/syntheseMedicale/")
public class SyntheseMedicaleRestAdmin {




    @Operation(summary = "Finds a list of all syntheseMedicales")
    @GetMapping("")
    public ResponseEntity<List<SyntheseMedicaleDto>> findAll() throws Exception {
        ResponseEntity<List<SyntheseMedicaleDto>> res = null;
        List<SyntheseMedicale> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<SyntheseMedicaleDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all syntheseMedicales")
    @GetMapping("optimized")
    public ResponseEntity<List<SyntheseMedicaleDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<SyntheseMedicaleDto>> res = null;
        List<SyntheseMedicale> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<SyntheseMedicaleDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a syntheseMedicale by id")
    @GetMapping("id/{id}")
    public ResponseEntity<SyntheseMedicaleDto> findById(@PathVariable Long id) {
        SyntheseMedicale t = service.findById(id);
        if (t != null) {
            converter.init(true);
            SyntheseMedicaleDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a syntheseMedicale by ref")
    @GetMapping("ref/{ref}")
    public ResponseEntity<SyntheseMedicaleDto> findByRef(@PathVariable String ref) {
	    SyntheseMedicale t = service.findByReferenceEntity(new SyntheseMedicale(ref));
        if (t != null) {
            converter.init(true);
            SyntheseMedicaleDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  syntheseMedicale")
    @PostMapping("")
    public ResponseEntity<SyntheseMedicaleDto> save(@RequestBody SyntheseMedicaleDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            SyntheseMedicale myT = converter.toItem(dto);
            SyntheseMedicale t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                SyntheseMedicaleDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  syntheseMedicale")
    @PutMapping("")
    public ResponseEntity<SyntheseMedicaleDto> update(@RequestBody SyntheseMedicaleDto dto) throws Exception {
        ResponseEntity<SyntheseMedicaleDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            SyntheseMedicale t = service.findById(dto.getId());
            converter.copy(dto,t);
            SyntheseMedicale updated = service.update(t);
            SyntheseMedicaleDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of syntheseMedicale")
    @PostMapping("multiple")
    public ResponseEntity<List<SyntheseMedicaleDto>> delete(@RequestBody List<SyntheseMedicaleDto> dtos) throws Exception {
        ResponseEntity<List<SyntheseMedicaleDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<SyntheseMedicale> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified syntheseMedicale")
    @DeleteMapping("")
    public ResponseEntity<SyntheseMedicaleDto> delete(@RequestBody SyntheseMedicaleDto dto) throws Exception {
		ResponseEntity<SyntheseMedicaleDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            SyntheseMedicale t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified syntheseMedicale")
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
    @Operation(summary = "Delete multiple syntheseMedicales by ids")
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
    public List<SyntheseMedicaleDto> findByConsultationId(@PathVariable Long id){
        return findDtos(service.findByConsultationId(id));
    }
    @Operation(summary = "delete by consultation id")
    @DeleteMapping("consultation/id/{id}")
    public int deleteByConsultationId(@PathVariable Long id){
        return service.deleteByConsultationId(id);
    }

    @Operation(summary = "Finds a syntheseMedicale and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<SyntheseMedicaleDto> findWithAssociatedLists(@PathVariable Long id) {
        SyntheseMedicale loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        SyntheseMedicaleDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds syntheseMedicales by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<SyntheseMedicaleDto>> findByCriteria(@RequestBody SyntheseMedicaleCriteria criteria) throws Exception {
        ResponseEntity<List<SyntheseMedicaleDto>> res = null;
        List<SyntheseMedicale> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<SyntheseMedicaleDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated syntheseMedicales by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody SyntheseMedicaleCriteria criteria) throws Exception {
        List<SyntheseMedicale> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<SyntheseMedicaleDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets syntheseMedicale data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody SyntheseMedicaleCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<SyntheseMedicaleDto> findDtos(List<SyntheseMedicale> list){
        converter.initObject(true);
        List<SyntheseMedicaleDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<SyntheseMedicaleDto> getDtoResponseEntity(SyntheseMedicaleDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private SyntheseMedicaleAdminService service;
    @Autowired private SyntheseMedicaleConverter converter;





}
