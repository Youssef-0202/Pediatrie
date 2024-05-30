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

import ma.zs.gestion_service_pediatrie.bean.core.dossie.Antecedent;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.dossie.AntecedentCriteria;
import ma.zs.gestion_service_pediatrie.service.facade.infermier.dossie.AntecedentInfermierService;
import ma.zs.gestion_service_pediatrie.ws.converter.dossie.AntecedentConverter;
import ma.zs.gestion_service_pediatrie.ws.dto.dossie.AntecedentDto;
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
@RequestMapping("/api/infermier/antecedent/")
public class AntecedentRestInfermier {




    @Operation(summary = "Finds a list of all antecedents")
    @GetMapping("")
    public ResponseEntity<List<AntecedentDto>> findAll() throws Exception {
        ResponseEntity<List<AntecedentDto>> res = null;
        List<Antecedent> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<AntecedentDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all antecedents")
    @GetMapping("optimized")
    public ResponseEntity<List<AntecedentDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<AntecedentDto>> res = null;
        List<Antecedent> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<AntecedentDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a antecedent by id")
    @GetMapping("id/{id}")
    public ResponseEntity<AntecedentDto> findById(@PathVariable Long id) {
        Antecedent t = service.findById(id);
        if (t != null) {
            converter.init(true);
            AntecedentDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a antecedent by ref")
    @GetMapping("ref/{ref}")
    public ResponseEntity<AntecedentDto> findByRef(@PathVariable String ref) {
	    Antecedent t = service.findByReferenceEntity(new Antecedent(ref));
        if (t != null) {
            converter.init(true);
            AntecedentDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  antecedent")
    @PostMapping("")
    public ResponseEntity<AntecedentDto> save(@RequestBody AntecedentDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Antecedent myT = converter.toItem(dto);
            Antecedent t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                AntecedentDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  antecedent")
    @PutMapping("")
    public ResponseEntity<AntecedentDto> update(@RequestBody AntecedentDto dto) throws Exception {
        ResponseEntity<AntecedentDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Antecedent t = service.findById(dto.getId());
            converter.copy(dto,t);
            Antecedent updated = service.update(t);
            AntecedentDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of antecedent")
    @PostMapping("multiple")
    public ResponseEntity<List<AntecedentDto>> delete(@RequestBody List<AntecedentDto> dtos) throws Exception {
        ResponseEntity<List<AntecedentDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Antecedent> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified antecedent")
    @DeleteMapping("")
    public ResponseEntity<AntecedentDto> delete(@RequestBody AntecedentDto dto) throws Exception {
		ResponseEntity<AntecedentDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            Antecedent t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified antecedent")
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
    @Operation(summary = "Delete multiple antecedents by ids")
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



    @Operation(summary = "Finds a antecedent and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<AntecedentDto> findWithAssociatedLists(@PathVariable Long id) {
        Antecedent loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        AntecedentDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds antecedents by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<AntecedentDto>> findByCriteria(@RequestBody AntecedentCriteria criteria) throws Exception {
        ResponseEntity<List<AntecedentDto>> res = null;
        List<Antecedent> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<AntecedentDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated antecedents by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody AntecedentCriteria criteria) throws Exception {
        List<Antecedent> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<AntecedentDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets antecedent data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody AntecedentCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<AntecedentDto> findDtos(List<Antecedent> list){
        converter.initObject(true);
        List<AntecedentDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<AntecedentDto> getDtoResponseEntity(AntecedentDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private AntecedentInfermierService service;
    @Autowired private AntecedentConverter converter;





}
