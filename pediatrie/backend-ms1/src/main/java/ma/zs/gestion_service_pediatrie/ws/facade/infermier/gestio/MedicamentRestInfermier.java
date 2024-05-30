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

import ma.zs.gestion_service_pediatrie.bean.core.gestio.Medicament;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.gestio.MedicamentCriteria;
import ma.zs.gestion_service_pediatrie.service.facade.infermier.gestio.MedicamentInfermierService;
import ma.zs.gestion_service_pediatrie.ws.converter.gestio.MedicamentConverter;
import ma.zs.gestion_service_pediatrie.ws.dto.gestio.MedicamentDto;
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
@RequestMapping("/api/infermier/medicament/")
public class MedicamentRestInfermier {




    @Operation(summary = "Finds a list of all medicaments")
    @GetMapping("")
    public ResponseEntity<List<MedicamentDto>> findAll() throws Exception {
        ResponseEntity<List<MedicamentDto>> res = null;
        List<Medicament> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<MedicamentDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all medicaments")
    @GetMapping("optimized")
    public ResponseEntity<List<MedicamentDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<MedicamentDto>> res = null;
        List<Medicament> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<MedicamentDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a medicament by id")
    @GetMapping("id/{id}")
    public ResponseEntity<MedicamentDto> findById(@PathVariable Long id) {
        Medicament t = service.findById(id);
        if (t != null) {
            converter.init(true);
            MedicamentDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a medicament by ref")
    @GetMapping("ref/{ref}")
    public ResponseEntity<MedicamentDto> findByRef(@PathVariable String ref) {
	    Medicament t = service.findByReferenceEntity(new Medicament(ref));
        if (t != null) {
            converter.init(true);
            MedicamentDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  medicament")
    @PostMapping("")
    public ResponseEntity<MedicamentDto> save(@RequestBody MedicamentDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Medicament myT = converter.toItem(dto);
            Medicament t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                MedicamentDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  medicament")
    @PutMapping("")
    public ResponseEntity<MedicamentDto> update(@RequestBody MedicamentDto dto) throws Exception {
        ResponseEntity<MedicamentDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Medicament t = service.findById(dto.getId());
            converter.copy(dto,t);
            Medicament updated = service.update(t);
            MedicamentDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of medicament")
    @PostMapping("multiple")
    public ResponseEntity<List<MedicamentDto>> delete(@RequestBody List<MedicamentDto> dtos) throws Exception {
        ResponseEntity<List<MedicamentDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Medicament> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified medicament")
    @DeleteMapping("")
    public ResponseEntity<MedicamentDto> delete(@RequestBody MedicamentDto dto) throws Exception {
		ResponseEntity<MedicamentDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            Medicament t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified medicament")
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
    @Operation(summary = "Delete multiple medicaments by ids")
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
    public List<MedicamentDto> findByTraitementId(@PathVariable Long id){
        return findDtos(service.findByTraitementId(id));
    }
    @Operation(summary = "delete by traitement id")
    @DeleteMapping("traitement/id/{id}")
    public int deleteByTraitementId(@PathVariable Long id){
        return service.deleteByTraitementId(id);
    }

    @Operation(summary = "Finds a medicament and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<MedicamentDto> findWithAssociatedLists(@PathVariable Long id) {
        Medicament loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        MedicamentDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds medicaments by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<MedicamentDto>> findByCriteria(@RequestBody MedicamentCriteria criteria) throws Exception {
        ResponseEntity<List<MedicamentDto>> res = null;
        List<Medicament> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<MedicamentDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated medicaments by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody MedicamentCriteria criteria) throws Exception {
        List<Medicament> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<MedicamentDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets medicament data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody MedicamentCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<MedicamentDto> findDtos(List<Medicament> list){
        converter.initObject(true);
        List<MedicamentDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<MedicamentDto> getDtoResponseEntity(MedicamentDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private MedicamentInfermierService service;
    @Autowired private MedicamentConverter converter;





}
