package  ma.zs.gestion_service_pediatrie.ws.facade.medecin.commun;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.gestion_service_pediatrie.bean.core.commun.Sexe;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.commun.SexeCriteria;
import ma.zs.gestion_service_pediatrie.service.facade.medecin.commun.SexeMedecinService;
import ma.zs.gestion_service_pediatrie.ws.converter.commun.SexeConverter;
import ma.zs.gestion_service_pediatrie.ws.dto.commun.SexeDto;
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
@RequestMapping("/api/medecin/sexe/")
public class SexeRestMedecin {




    @Operation(summary = "Finds a list of all sexes")
    @GetMapping("")
    public ResponseEntity<List<SexeDto>> findAll() throws Exception {
        ResponseEntity<List<SexeDto>> res = null;
        List<Sexe> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<SexeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all sexes")
    @GetMapping("optimized")
    public ResponseEntity<List<SexeDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<SexeDto>> res = null;
        List<Sexe> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<SexeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a sexe by id")
    @GetMapping("id/{id}")
    public ResponseEntity<SexeDto> findById(@PathVariable Long id) {
        Sexe t = service.findById(id);
        if (t != null) {
            SexeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a sexe by ref")
    @GetMapping("ref/{ref}")
    public ResponseEntity<SexeDto> findByRef(@PathVariable String ref) {
	    Sexe t = service.findByReferenceEntity(new Sexe(ref));
        if (t != null) {
            SexeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  sexe")
    @PostMapping("")
    public ResponseEntity<SexeDto> save(@RequestBody SexeDto dto) throws Exception {
        if(dto!=null){
            Sexe myT = converter.toItem(dto);
            Sexe t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                SexeDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  sexe")
    @PutMapping("")
    public ResponseEntity<SexeDto> update(@RequestBody SexeDto dto) throws Exception {
        ResponseEntity<SexeDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Sexe t = service.findById(dto.getId());
            converter.copy(dto,t);
            Sexe updated = service.update(t);
            SexeDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of sexe")
    @PostMapping("multiple")
    public ResponseEntity<List<SexeDto>> delete(@RequestBody List<SexeDto> dtos) throws Exception {
        ResponseEntity<List<SexeDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Sexe> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified sexe")
    @DeleteMapping("")
    public ResponseEntity<SexeDto> delete(@RequestBody SexeDto dto) throws Exception {
		ResponseEntity<SexeDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            Sexe t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified sexe")
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
    @Operation(summary = "Delete multiple sexes by ids")
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



    @Operation(summary = "Finds a sexe and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<SexeDto> findWithAssociatedLists(@PathVariable Long id) {
        Sexe loaded =  service.findWithAssociatedLists(id);
        SexeDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds sexes by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<SexeDto>> findByCriteria(@RequestBody SexeCriteria criteria) throws Exception {
        ResponseEntity<List<SexeDto>> res = null;
        List<Sexe> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<SexeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated sexes by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody SexeCriteria criteria) throws Exception {
        List<Sexe> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<SexeDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets sexe data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody SexeCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<SexeDto> findDtos(List<Sexe> list){
        List<SexeDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<SexeDto> getDtoResponseEntity(SexeDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private SexeMedecinService service;
    @Autowired private SexeConverter converter;





}
