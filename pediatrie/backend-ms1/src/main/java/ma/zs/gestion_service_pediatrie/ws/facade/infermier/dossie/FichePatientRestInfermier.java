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

import ma.zs.gestion_service_pediatrie.bean.core.dossie.FichePatient;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.dossie.FichePatientCriteria;
import ma.zs.gestion_service_pediatrie.service.facade.infermier.dossie.FichePatientInfermierService;
import ma.zs.gestion_service_pediatrie.ws.converter.dossie.FichePatientConverter;
import ma.zs.gestion_service_pediatrie.ws.dto.dossie.FichePatientDto;
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
@RequestMapping("/api/infermier/fichePatient/")
public class FichePatientRestInfermier {




    @Operation(summary = "Finds a list of all fichePatients")
    @GetMapping("")
    public ResponseEntity<List<FichePatientDto>> findAll() throws Exception {
        ResponseEntity<List<FichePatientDto>> res = null;
        List<FichePatient> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<FichePatientDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "find by num dossier")
    @GetMapping("consultation/num/{ref}")
    public List<FichePatientDto> findByConsultationref(@PathVariable String ref){
        return findDtos(service.findByConsultationRef(ref));
    }

    @Operation(summary = "Finds an optimized list of all fichePatients")
    @GetMapping("optimized")
    public ResponseEntity<List<FichePatientDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<FichePatientDto>> res = null;
        List<FichePatient> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<FichePatientDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a fichePatient by id")
    @GetMapping("id/{id}")
    public ResponseEntity<FichePatientDto> findById(@PathVariable Long id) {
        FichePatient t = service.findById(id);
        if (t != null) {
            converter.init(true);
            FichePatientDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a fichePatient by ref")
    @GetMapping("ref/{ref}")
    public ResponseEntity<FichePatientDto> findByRef(@PathVariable String ref) {
	    FichePatient t = service.findByReferenceEntity(new FichePatient(ref));
        if (t != null) {
            converter.init(true);
            FichePatientDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  fichePatient")
    @PostMapping("")
    public ResponseEntity<FichePatientDto> save(@RequestBody FichePatientDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            FichePatient myT = converter.toItem(dto);
            FichePatient t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                FichePatientDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  fichePatient")
    @PutMapping("")
    public ResponseEntity<FichePatientDto> update(@RequestBody FichePatientDto dto) throws Exception {
        ResponseEntity<FichePatientDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            FichePatient t = service.findById(dto.getId());
            converter.copy(dto,t);
            FichePatient updated = service.update(t);
            FichePatientDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of fichePatient")
    @PostMapping("multiple")
    public ResponseEntity<List<FichePatientDto>> delete(@RequestBody List<FichePatientDto> dtos) throws Exception {
        ResponseEntity<List<FichePatientDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<FichePatient> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified fichePatient")
    @DeleteMapping("")
    public ResponseEntity<FichePatientDto> delete(@RequestBody FichePatientDto dto) throws Exception {
		ResponseEntity<FichePatientDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            FichePatient t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified fichePatient")
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
    @Operation(summary = "Delete multiple fichePatients by ids")
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


    @Operation(summary = "find by antecedent id")
    @GetMapping("antecedent/id/{id}")
    public List<FichePatientDto> findByAntecedentId(@PathVariable Long id){
        return findDtos(service.findByAntecedentId(id));
    }
    @Operation(summary = "delete by antecedent id")
    @DeleteMapping("antecedent/id/{id}")
    public int deleteByAntecedentId(@PathVariable Long id){
        return service.deleteByAntecedentId(id);
    }

    @Operation(summary = "Finds a fichePatient and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<FichePatientDto> findWithAssociatedLists(@PathVariable Long id) {
        FichePatient loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        FichePatientDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds fichePatients by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<FichePatientDto>> findByCriteria(@RequestBody FichePatientCriteria criteria) throws Exception {
        ResponseEntity<List<FichePatientDto>> res = null;
        List<FichePatient> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<FichePatientDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated fichePatients by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody FichePatientCriteria criteria) throws Exception {
        List<FichePatient> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<FichePatientDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets fichePatient data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody FichePatientCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<FichePatientDto> findDtos(List<FichePatient> list){
        converter.initObject(true);
        List<FichePatientDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<FichePatientDto> getDtoResponseEntity(FichePatientDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private FichePatientInfermierService service;
    @Autowired private FichePatientConverter converter;





}
