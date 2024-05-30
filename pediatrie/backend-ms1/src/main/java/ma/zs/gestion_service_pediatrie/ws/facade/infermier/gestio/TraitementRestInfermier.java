package  ma.zs.gestion_service_pediatrie.ws.facade.infermier.gestio;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.gestion_service_pediatrie.bean.core.gestio.Traitement;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.gestio.TraitementCriteria;
import ma.zs.gestion_service_pediatrie.service.facade.infermier.gestio.TraitementInfermierService;
import ma.zs.gestion_service_pediatrie.ws.converter.gestio.TraitementConverter;
import ma.zs.gestion_service_pediatrie.ws.dto.gestio.TraitementDto;
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
@RequestMapping("/api/infermier/traitement/")
public class TraitementRestInfermier {




    @Operation(summary = "Finds a list of all traitements")
    @GetMapping("")
    public ResponseEntity<List<TraitementDto>> findAll() throws Exception {
        ResponseEntity<List<TraitementDto>> res = null;
        List<Traitement> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
            converter.initObject(true);
        List<TraitementDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all traitements")
    @GetMapping("optimized")
    public ResponseEntity<List<TraitementDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<TraitementDto>> res = null;
        List<Traitement> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<TraitementDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a traitement by id")
    @GetMapping("id/{id}")
    public ResponseEntity<TraitementDto> findById(@PathVariable Long id) {
        Traitement t = service.findById(id);
        if (t != null) {
            converter.init(true);
            TraitementDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a traitement by ref")
    @GetMapping("ref/{ref}")
    public ResponseEntity<TraitementDto> findByRef(@PathVariable String ref) {
	    Traitement t = service.findByReferenceEntity(new Traitement(ref));
        if (t != null) {
            converter.init(true);
            TraitementDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  traitement")
    @PostMapping("")
    public ResponseEntity<TraitementDto> save(@RequestBody TraitementDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Traitement myT = converter.toItem(dto);
            Traitement t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                TraitementDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  traitement")
    @PutMapping("")
    public ResponseEntity<TraitementDto> update(@RequestBody TraitementDto dto) throws Exception {
        ResponseEntity<TraitementDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Traitement t = service.findById(dto.getId());
            converter.copy(dto,t);
            Traitement updated = service.update(t);
            TraitementDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of traitement")
    @PostMapping("multiple")
    public ResponseEntity<List<TraitementDto>> delete(@RequestBody List<TraitementDto> dtos) throws Exception {
        ResponseEntity<List<TraitementDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Traitement> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified traitement")
    @DeleteMapping("")
    public ResponseEntity<TraitementDto> delete(@RequestBody TraitementDto dto) throws Exception {
		ResponseEntity<TraitementDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            Traitement t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified traitement")
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
    @Operation(summary = "Delete multiple traitements by ids")
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
    public List<TraitementDto> findByConsultatuinId(@PathVariable Long id){
        return findDtos(service.findByConsultatuinId(id));
    }
    @Operation(summary = "delete by consultatuin id")
    @DeleteMapping("consultatuin/id/{id}")
    public int deleteByConsultatuinId(@PathVariable Long id){
        return service.deleteByConsultatuinId(id);
    }

    @Operation(summary = "Finds a traitement and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<TraitementDto> findWithAssociatedLists(@PathVariable Long id) {
        Traitement loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        TraitementDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds traitements by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<TraitementDto>> findByCriteria(@RequestBody TraitementCriteria criteria) throws Exception {
        ResponseEntity<List<TraitementDto>> res = null;
        List<Traitement> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<TraitementDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated traitements by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody TraitementCriteria criteria) throws Exception {
        List<Traitement> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initList(false);
        converter.initObject(true);
        List<TraitementDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets traitement data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody TraitementCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<TraitementDto> findDtos(List<Traitement> list){
        converter.initList(false);
        converter.initObject(true);
        List<TraitementDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<TraitementDto> getDtoResponseEntity(TraitementDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private TraitementInfermierService service;
    @Autowired private TraitementConverter converter;





}
