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

import ma.zs.gestion_service_pediatrie.bean.core.dossie.Epreuve;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.dossie.EpreuveCriteria;
import ma.zs.gestion_service_pediatrie.service.facade.infermier.dossie.EpreuveInfermierService;
import ma.zs.gestion_service_pediatrie.ws.converter.dossie.EpreuveConverter;
import ma.zs.gestion_service_pediatrie.ws.dto.dossie.EpreuveDto;
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
@RequestMapping("/api/infermier/epreuve/")
public class EpreuveRestInfermier {




    @Operation(summary = "Finds a list of all epreuves")
    @GetMapping("")
    public ResponseEntity<List<EpreuveDto>> findAll() throws Exception {
        ResponseEntity<List<EpreuveDto>> res = null;
        List<Epreuve> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<EpreuveDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all epreuves")
    @GetMapping("optimized")
    public ResponseEntity<List<EpreuveDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<EpreuveDto>> res = null;
        List<Epreuve> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<EpreuveDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a epreuve by id")
    @GetMapping("id/{id}")
    public ResponseEntity<EpreuveDto> findById(@PathVariable Long id) {
        Epreuve t = service.findById(id);
        if (t != null) {
            EpreuveDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a epreuve by ref")
    @GetMapping("ref/{ref}")
    public ResponseEntity<EpreuveDto> findByRef(@PathVariable String ref) {
	    Epreuve t = service.findByReferenceEntity(new Epreuve(ref));
        if (t != null) {
            EpreuveDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  epreuve")
    @PostMapping("")
    public ResponseEntity<EpreuveDto> save(@RequestBody EpreuveDto dto) throws Exception {
        if(dto!=null){
            Epreuve myT = converter.toItem(dto);
            Epreuve t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                EpreuveDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  epreuve")
    @PutMapping("")
    public ResponseEntity<EpreuveDto> update(@RequestBody EpreuveDto dto) throws Exception {
        ResponseEntity<EpreuveDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Epreuve t = service.findById(dto.getId());
            converter.copy(dto,t);
            Epreuve updated = service.update(t);
            EpreuveDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of epreuve")
    @PostMapping("multiple")
    public ResponseEntity<List<EpreuveDto>> delete(@RequestBody List<EpreuveDto> dtos) throws Exception {
        ResponseEntity<List<EpreuveDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Epreuve> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified epreuve")
    @DeleteMapping("")
    public ResponseEntity<EpreuveDto> delete(@RequestBody EpreuveDto dto) throws Exception {
		ResponseEntity<EpreuveDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            Epreuve t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified epreuve")
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
    @Operation(summary = "Delete multiple epreuves by ids")
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



    @Operation(summary = "Finds a epreuve and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<EpreuveDto> findWithAssociatedLists(@PathVariable Long id) {
        Epreuve loaded =  service.findWithAssociatedLists(id);
        EpreuveDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds epreuves by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<EpreuveDto>> findByCriteria(@RequestBody EpreuveCriteria criteria) throws Exception {
        ResponseEntity<List<EpreuveDto>> res = null;
        List<Epreuve> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<EpreuveDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated epreuves by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody EpreuveCriteria criteria) throws Exception {
        List<Epreuve> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<EpreuveDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets epreuve data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody EpreuveCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<EpreuveDto> findDtos(List<Epreuve> list){
        List<EpreuveDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<EpreuveDto> getDtoResponseEntity(EpreuveDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private EpreuveInfermierService service;
    @Autowired private EpreuveConverter converter;





}
