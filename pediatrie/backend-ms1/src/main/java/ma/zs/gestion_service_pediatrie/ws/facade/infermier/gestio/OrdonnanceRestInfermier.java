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

import ma.zs.gestion_service_pediatrie.bean.core.gestio.Ordonnance;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.gestio.OrdonnanceCriteria;
import ma.zs.gestion_service_pediatrie.service.facade.infermier.gestio.OrdonnanceInfermierService;
import ma.zs.gestion_service_pediatrie.ws.converter.gestio.OrdonnanceConverter;
import ma.zs.gestion_service_pediatrie.ws.dto.gestio.OrdonnanceDto;
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
@RequestMapping("/api/infermier/ordonnance/")
public class OrdonnanceRestInfermier {




    @Operation(summary = "Finds a list of all ordonnances")
    @GetMapping("")
    public ResponseEntity<List<OrdonnanceDto>> findAll() throws Exception {
        ResponseEntity<List<OrdonnanceDto>> res = null;
        List<Ordonnance> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<OrdonnanceDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all ordonnances")
    @GetMapping("optimized")
    public ResponseEntity<List<OrdonnanceDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<OrdonnanceDto>> res = null;
        List<Ordonnance> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<OrdonnanceDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a ordonnance by id")
    @GetMapping("id/{id}")
    public ResponseEntity<OrdonnanceDto> findById(@PathVariable Long id) {
        Ordonnance t = service.findById(id);
        if (t != null) {
            converter.init(true);
            OrdonnanceDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a ordonnance by ref")
    @GetMapping("ref/{ref}")
    public ResponseEntity<OrdonnanceDto> findByRef(@PathVariable String ref) {
	    Ordonnance t = service.findByReferenceEntity(new Ordonnance(ref));
        if (t != null) {
            converter.init(true);
            OrdonnanceDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  ordonnance")
    @PostMapping("")
    public ResponseEntity<OrdonnanceDto> save(@RequestBody OrdonnanceDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Ordonnance myT = converter.toItem(dto);
            Ordonnance t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                OrdonnanceDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  ordonnance")
    @PutMapping("")
    public ResponseEntity<OrdonnanceDto> update(@RequestBody OrdonnanceDto dto) throws Exception {
        ResponseEntity<OrdonnanceDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Ordonnance t = service.findById(dto.getId());
            converter.copy(dto,t);
            Ordonnance updated = service.update(t);
            OrdonnanceDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of ordonnance")
    @PostMapping("multiple")
    public ResponseEntity<List<OrdonnanceDto>> delete(@RequestBody List<OrdonnanceDto> dtos) throws Exception {
        ResponseEntity<List<OrdonnanceDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Ordonnance> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified ordonnance")
    @DeleteMapping("")
    public ResponseEntity<OrdonnanceDto> delete(@RequestBody OrdonnanceDto dto) throws Exception {
		ResponseEntity<OrdonnanceDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            Ordonnance t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified ordonnance")
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
    @Operation(summary = "Delete multiple ordonnances by ids")
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


    @Operation(summary = "find by traitement id")
    @GetMapping("traitement/id/{id}")
    public List<OrdonnanceDto> findByTraitementId(@PathVariable Long id){
        return findDtos(service.findByTraitementId(id));
    }
    @Operation(summary = "delete by traitement id")
    @DeleteMapping("traitement/id/{id}")
    public int deleteByTraitementId(@PathVariable Long id){
        return service.deleteByTraitementId(id);
    }
    @Operation(summary = "find by consultatuin id")
    @GetMapping("consultatuin/id/{id}")
    public List<OrdonnanceDto> findByConsultatuinId(@PathVariable Long id){
        return findDtos(service.findByConsultatuinId(id));
    }
    @Operation(summary = "delete by consultatuin id")
    @DeleteMapping("consultatuin/id/{id}")
    public int deleteByConsultatuinId(@PathVariable Long id){
        return service.deleteByConsultatuinId(id);
    }

    @Operation(summary = "Finds a ordonnance and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<OrdonnanceDto> findWithAssociatedLists(@PathVariable Long id) {
        Ordonnance loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        OrdonnanceDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds ordonnances by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<OrdonnanceDto>> findByCriteria(@RequestBody OrdonnanceCriteria criteria) throws Exception {
        ResponseEntity<List<OrdonnanceDto>> res = null;
        List<Ordonnance> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<OrdonnanceDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated ordonnances by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody OrdonnanceCriteria criteria) throws Exception {
        List<Ordonnance> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<OrdonnanceDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets ordonnance data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody OrdonnanceCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<OrdonnanceDto> findDtos(List<Ordonnance> list){
        converter.initObject(true);
        List<OrdonnanceDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<OrdonnanceDto> getDtoResponseEntity(OrdonnanceDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private OrdonnanceInfermierService service;
    @Autowired private OrdonnanceConverter converter;





}
