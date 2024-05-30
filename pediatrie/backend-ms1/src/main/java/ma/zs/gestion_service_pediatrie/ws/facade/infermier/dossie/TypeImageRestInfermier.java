package  ma.zs.gestion_service_pediatrie.ws.facade.infermier.dossie;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.gestion_service_pediatrie.bean.core.dossie.TypeImage;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.dossie.TypeImageCriteria;
import ma.zs.gestion_service_pediatrie.service.facade.infermier.dossie.TypeImageInfermierService;
import ma.zs.gestion_service_pediatrie.ws.converter.dossie.TypeImageConverter;
import ma.zs.gestion_service_pediatrie.ws.dto.dossie.TypeImageDto;
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
@RequestMapping("/api/infermier/typeImage/")
public class TypeImageRestInfermier {




    @Operation(summary = "Finds a list of all typeImages")
    @GetMapping("")
    public ResponseEntity<List<TypeImageDto>> findAll() throws Exception {
        ResponseEntity<List<TypeImageDto>> res = null;
        List<TypeImage> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeImageDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all typeImages")
    @GetMapping("optimized")
    public ResponseEntity<List<TypeImageDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<TypeImageDto>> res = null;
        List<TypeImage> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeImageDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a typeImage by id")
    @GetMapping("id/{id}")
    public ResponseEntity<TypeImageDto> findById(@PathVariable Long id) {
        TypeImage t = service.findById(id);
        if (t != null) {
            TypeImageDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a typeImage by ref")
    @GetMapping("ref/{ref}")
    public ResponseEntity<TypeImageDto> findByRef(@PathVariable String ref) {
	    TypeImage t = service.findByReferenceEntity(new TypeImage(ref));
        if (t != null) {
            TypeImageDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  typeImage")
    @PostMapping("")
    public ResponseEntity<TypeImageDto> save(@RequestBody TypeImageDto dto) throws Exception {
        if(dto!=null){
            TypeImage myT = converter.toItem(dto);
            TypeImage t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                TypeImageDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  typeImage")
    @PutMapping("")
    public ResponseEntity<TypeImageDto> update(@RequestBody TypeImageDto dto) throws Exception {
        ResponseEntity<TypeImageDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            TypeImage t = service.findById(dto.getId());
            converter.copy(dto,t);
            TypeImage updated = service.update(t);
            TypeImageDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of typeImage")
    @PostMapping("multiple")
    public ResponseEntity<List<TypeImageDto>> delete(@RequestBody List<TypeImageDto> dtos) throws Exception {
        ResponseEntity<List<TypeImageDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<TypeImage> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified typeImage")
    @DeleteMapping("")
    public ResponseEntity<TypeImageDto> delete(@RequestBody TypeImageDto dto) throws Exception {
		ResponseEntity<TypeImageDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            TypeImage t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified typeImage")
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
    @Operation(summary = "Delete multiple typeImages by ids")
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



    @Operation(summary = "Finds a typeImage and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<TypeImageDto> findWithAssociatedLists(@PathVariable Long id) {
        TypeImage loaded =  service.findWithAssociatedLists(id);
        TypeImageDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds typeImages by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<TypeImageDto>> findByCriteria(@RequestBody TypeImageCriteria criteria) throws Exception {
        ResponseEntity<List<TypeImageDto>> res = null;
        List<TypeImage> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeImageDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated typeImages by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody TypeImageCriteria criteria) throws Exception {
        List<TypeImage> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<TypeImageDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets typeImage data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody TypeImageCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<TypeImageDto> findDtos(List<TypeImage> list){
        List<TypeImageDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<TypeImageDto> getDtoResponseEntity(TypeImageDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private TypeImageInfermierService service;
    @Autowired private TypeImageConverter converter;





}
