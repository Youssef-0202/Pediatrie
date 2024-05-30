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

import ma.zs.gestion_service_pediatrie.bean.core.dossie.Radiologie;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.dossie.RadiologieCriteria;
import ma.zs.gestion_service_pediatrie.service.facade.admin.dossie.RadiologieAdminService;
import ma.zs.gestion_service_pediatrie.ws.converter.dossie.RadiologieConverter;
import ma.zs.gestion_service_pediatrie.ws.dto.dossie.RadiologieDto;
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
@RequestMapping("/api/admin/radiologie/")
public class RadiologieRestAdmin {




    @Operation(summary = "Finds a list of all radiologies")
    @GetMapping("")
    public ResponseEntity<List<RadiologieDto>> findAll() throws Exception {
        ResponseEntity<List<RadiologieDto>> res = null;
        List<Radiologie> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<RadiologieDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all radiologies")
    @GetMapping("optimized")
    public ResponseEntity<List<RadiologieDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<RadiologieDto>> res = null;
        List<Radiologie> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<RadiologieDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a radiologie by id")
    @GetMapping("id/{id}")
    public ResponseEntity<RadiologieDto> findById(@PathVariable Long id) {
        Radiologie t = service.findById(id);
        if (t != null) {
            converter.init(true);
            RadiologieDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a radiologie by ref")
    @GetMapping("ref/{ref}")
    public ResponseEntity<RadiologieDto> findByRef(@PathVariable String ref) {
	    Radiologie t = service.findByReferenceEntity(new Radiologie(ref));
        if (t != null) {
            converter.init(true);
            RadiologieDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  radiologie")
    @PostMapping("")
    public ResponseEntity<RadiologieDto> save(@RequestBody RadiologieDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Radiologie myT = converter.toItem(dto);
            Radiologie t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                RadiologieDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  radiologie")
    @PutMapping("")
    public ResponseEntity<RadiologieDto> update(@RequestBody RadiologieDto dto) throws Exception {
        ResponseEntity<RadiologieDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Radiologie t = service.findById(dto.getId());
            converter.copy(dto,t);
            Radiologie updated = service.update(t);
            RadiologieDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of radiologie")
    @PostMapping("multiple")
    public ResponseEntity<List<RadiologieDto>> delete(@RequestBody List<RadiologieDto> dtos) throws Exception {
        ResponseEntity<List<RadiologieDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Radiologie> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified radiologie")
    @DeleteMapping("")
    public ResponseEntity<RadiologieDto> delete(@RequestBody RadiologieDto dto) throws Exception {
		ResponseEntity<RadiologieDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            Radiologie t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified radiologie")
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
    @Operation(summary = "Delete multiple radiologies by ids")
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


    @Operation(summary = "find by consultatuin id")
    @GetMapping("consultatuin/id/{id}")
    public List<RadiologieDto> findByConsultatuinId(@PathVariable Long id){
        return findDtos(service.findByConsultatuinId(id));
    }
    @Operation(summary = "delete by consultatuin id")
    @DeleteMapping("consultatuin/id/{id}")
    public int deleteByConsultatuinId(@PathVariable Long id){
        return service.deleteByConsultatuinId(id);
    }
    @Operation(summary = "find by typeImage id")
    @GetMapping("typeImage/id/{id}")
    public List<RadiologieDto> findByTypeImageId(@PathVariable Long id){
        return findDtos(service.findByTypeImageId(id));
    }
    @Operation(summary = "delete by typeImage id")
    @DeleteMapping("typeImage/id/{id}")
    public int deleteByTypeImageId(@PathVariable Long id){
        return service.deleteByTypeImageId(id);
    }

    @Operation(summary = "Finds a radiologie and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<RadiologieDto> findWithAssociatedLists(@PathVariable Long id) {
        Radiologie loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        RadiologieDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds radiologies by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<RadiologieDto>> findByCriteria(@RequestBody RadiologieCriteria criteria) throws Exception {
        ResponseEntity<List<RadiologieDto>> res = null;
        List<Radiologie> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<RadiologieDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated radiologies by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody RadiologieCriteria criteria) throws Exception {
        List<Radiologie> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<RadiologieDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets radiologie data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody RadiologieCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<RadiologieDto> findDtos(List<Radiologie> list){
        converter.initObject(true);
        List<RadiologieDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<RadiologieDto> getDtoResponseEntity(RadiologieDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private RadiologieAdminService service;
    @Autowired private RadiologieConverter converter;





}
