package  ma.zs.gestion_service_pediatrie.ws.facade.admin.patient;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.gestion_service_pediatrie.bean.core.patient.Relation;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.patient.RelationCriteria;
import ma.zs.gestion_service_pediatrie.service.facade.admin.patient.RelationAdminService;
import ma.zs.gestion_service_pediatrie.ws.converter.patient.RelationConverter;
import ma.zs.gestion_service_pediatrie.ws.dto.patient.RelationDto;
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
@RequestMapping("/api/admin/relation/")
public class RelationRestAdmin {




    @Operation(summary = "Finds a list of all relations")
    @GetMapping("")
    public ResponseEntity<List<RelationDto>> findAll() throws Exception {
        ResponseEntity<List<RelationDto>> res = null;
        List<Relation> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<RelationDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all relations")
    @GetMapping("optimized")
    public ResponseEntity<List<RelationDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<RelationDto>> res = null;
        List<Relation> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<RelationDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a relation by id")
    @GetMapping("id/{id}")
    public ResponseEntity<RelationDto> findById(@PathVariable Long id) {
        Relation t = service.findById(id);
        if (t != null) {
            RelationDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a relation by ref")
    @GetMapping("ref/{ref}")
    public ResponseEntity<RelationDto> findByRef(@PathVariable String ref) {
	    Relation t = service.findByReferenceEntity(new Relation(ref));
        if (t != null) {
            RelationDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  relation")
    @PostMapping("")
    public ResponseEntity<RelationDto> save(@RequestBody RelationDto dto) throws Exception {
        if(dto!=null){
            Relation myT = converter.toItem(dto);
            Relation t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                RelationDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  relation")
    @PutMapping("")
    public ResponseEntity<RelationDto> update(@RequestBody RelationDto dto) throws Exception {
        ResponseEntity<RelationDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Relation t = service.findById(dto.getId());
            converter.copy(dto,t);
            Relation updated = service.update(t);
            RelationDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of relation")
    @PostMapping("multiple")
    public ResponseEntity<List<RelationDto>> delete(@RequestBody List<RelationDto> dtos) throws Exception {
        ResponseEntity<List<RelationDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Relation> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified relation")
    @DeleteMapping("")
    public ResponseEntity<RelationDto> delete(@RequestBody RelationDto dto) throws Exception {
		ResponseEntity<RelationDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            Relation t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified relation")
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
    @Operation(summary = "Delete multiple relations by ids")
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



    @Operation(summary = "Finds a relation and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<RelationDto> findWithAssociatedLists(@PathVariable Long id) {
        Relation loaded =  service.findWithAssociatedLists(id);
        RelationDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds relations by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<RelationDto>> findByCriteria(@RequestBody RelationCriteria criteria) throws Exception {
        ResponseEntity<List<RelationDto>> res = null;
        List<Relation> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<RelationDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated relations by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody RelationCriteria criteria) throws Exception {
        List<Relation> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<RelationDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets relation data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody RelationCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<RelationDto> findDtos(List<Relation> list){
        List<RelationDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<RelationDto> getDtoResponseEntity(RelationDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private RelationAdminService service;
    @Autowired private RelationConverter converter;





}
