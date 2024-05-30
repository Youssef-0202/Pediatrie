package  ma.zs.gestion_service_pediatrie.ws.facade.infermier.patient;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.gestion_service_pediatrie.bean.core.patient.Patient;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.patient.PatientCriteria;
import ma.zs.gestion_service_pediatrie.service.facade.infermier.patient.PatientInfermierService;
import ma.zs.gestion_service_pediatrie.ws.converter.patient.PatientConverter;
import ma.zs.gestion_service_pediatrie.ws.dto.patient.PatientDto;
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
@RequestMapping("/api/infermier/patient/")
public class PatientRestInfermier {




    @Operation(summary = "Finds a list of all patients")
    @GetMapping("")
    public ResponseEntity<List<PatientDto>> findAll() throws Exception {
        ResponseEntity<List<PatientDto>> res = null;
        List<Patient> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
            converter.initObject(true);
        List<PatientDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all patients")
    @GetMapping("optimized")
    public ResponseEntity<List<PatientDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<PatientDto>> res = null;
        List<Patient> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<PatientDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a patient by id")
    @GetMapping("id/{id}")
    public ResponseEntity<PatientDto> findById(@PathVariable Long id) {
        Patient t = service.findById(id);
        if (t != null) {
            converter.init(true);
            PatientDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a patient by numDossier")
    @GetMapping("numDossier/{numDossier}")
    public ResponseEntity<PatientDto> findByNumDossier(@PathVariable String numDossier) {
	    Patient t = service.findByReferenceEntity(new Patient(numDossier));
        if (t != null) {
            converter.init(true);
            PatientDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  patient")
    @PostMapping("")
    public ResponseEntity<PatientDto> save(@RequestBody PatientDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Patient myT = converter.toItem(dto);
            Patient t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                PatientDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  patient")
    @PutMapping("")
    public ResponseEntity<PatientDto> update(@RequestBody PatientDto dto) throws Exception {
        ResponseEntity<PatientDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Patient t = service.findById(dto.getId());
            converter.copy(dto,t);
            Patient updated = service.update(t);
            PatientDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of patient")
    @PostMapping("multiple")
    public ResponseEntity<List<PatientDto>> delete(@RequestBody List<PatientDto> dtos) throws Exception {
        ResponseEntity<List<PatientDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Patient> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified patient")
    @DeleteMapping("")
    public ResponseEntity<PatientDto> delete(@RequestBody PatientDto dto) throws Exception {
		ResponseEntity<PatientDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            Patient t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified patient")
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
    @Operation(summary = "Delete multiple patients by ids")
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


    @Operation(summary = "find by sexe id")
    @GetMapping("sexe/id/{id}")
    public List<PatientDto> findBySexeId(@PathVariable Long id){
        return findDtos(service.findBySexeId(id));
    }
    @Operation(summary = "delete by sexe id")
    @DeleteMapping("sexe/id/{id}")
    public int deleteBySexeId(@PathVariable Long id){
        return service.deleteBySexeId(id);
    }

    @Operation(summary = "Finds a patient and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<PatientDto> findWithAssociatedLists(@PathVariable Long id) {
        Patient loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        PatientDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds patients by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<PatientDto>> findByCriteria(@RequestBody PatientCriteria criteria) throws Exception {
        ResponseEntity<List<PatientDto>> res = null;
        List<Patient> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<PatientDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated patients by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody PatientCriteria criteria) throws Exception {
        List<Patient> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initList(false);
        converter.initObject(true);
        List<PatientDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets patient data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody PatientCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<PatientDto> findDtos(List<Patient> list){
        converter.initList(false);
        converter.initObject(true);
        List<PatientDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<PatientDto> getDtoResponseEntity(PatientDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private PatientInfermierService service;
    @Autowired private PatientConverter converter;





}
