package  ma.zs.gestion_service_pediatrie.ws.facade.medecin.patient;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ma.zs.gestion_service_pediatrie.ws.dto.consultatio.ConsultationDto;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.gestion_service_pediatrie.bean.core.patient.PatientContact;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.patient.PatientContactCriteria;
import ma.zs.gestion_service_pediatrie.service.facade.medecin.patient.PatientContactMedecinService;
import ma.zs.gestion_service_pediatrie.ws.converter.patient.PatientContactConverter;
import ma.zs.gestion_service_pediatrie.ws.dto.patient.PatientContactDto;
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
@RequestMapping("/api/medecin/patientContact/")
public class PatientContactRestMedecin {




    @Operation(summary = "Finds a list of all patientContacts")
    @GetMapping("")
    public ResponseEntity<List<PatientContactDto>> findAll() throws Exception {
        ResponseEntity<List<PatientContactDto>> res = null;
        List<PatientContact> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<PatientContactDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all patientContacts")
    @GetMapping("optimized")
    public ResponseEntity<List<PatientContactDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<PatientContactDto>> res = null;
        List<PatientContact> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<PatientContactDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Finds a patientContact by num dossier")
    @GetMapping("dossier/num/{num}")
    public List<PatientContactDto> findByPatientNumDossier(@PathVariable String num) {
        return  findDtos(service.findByPatientNumDossier(num));
    }

    @Operation(summary = "Finds a patientContact by id")
    @GetMapping("id/{id}")
    public ResponseEntity<PatientContactDto> findById(@PathVariable Long id) {
        PatientContact t = service.findById(id);
        if (t != null) {
            converter.init(true);
            PatientContactDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a patientContact by email")
    @GetMapping("email/{email}")
    public ResponseEntity<PatientContactDto> findByEmail(@PathVariable String email) {
	    PatientContact t = service.findByReferenceEntity(new PatientContact(email));
        if (t != null) {
            converter.init(true);
            PatientContactDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  patientContact")
    @PostMapping("")
    public ResponseEntity<PatientContactDto> save(@RequestBody PatientContactDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            PatientContact myT = converter.toItem(dto);
            PatientContact t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                PatientContactDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  patientContact")
    @PutMapping("")
    public ResponseEntity<PatientContactDto> update(@RequestBody PatientContactDto dto) throws Exception {
        ResponseEntity<PatientContactDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            PatientContact t = service.findById(dto.getId());
            converter.copy(dto,t);
            PatientContact updated = service.update(t);
            PatientContactDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of patientContact")
    @PostMapping("multiple")
    public ResponseEntity<List<PatientContactDto>> delete(@RequestBody List<PatientContactDto> dtos) throws Exception {
        ResponseEntity<List<PatientContactDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<PatientContact> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified patientContact")
    @DeleteMapping("")
    public ResponseEntity<PatientContactDto> delete(@RequestBody PatientContactDto dto) throws Exception {
		ResponseEntity<PatientContactDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            PatientContact t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified patientContact")
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
    @Operation(summary = "Delete multiple patientContacts by ids")
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



    @Operation(summary = "Finds a patientContact and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<PatientContactDto> findWithAssociatedLists(@PathVariable Long id) {
        PatientContact loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        PatientContactDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds patientContacts by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<PatientContactDto>> findByCriteria(@RequestBody PatientContactCriteria criteria) throws Exception {
        ResponseEntity<List<PatientContactDto>> res = null;
        List<PatientContact> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<PatientContactDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated patientContacts by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody PatientContactCriteria criteria) throws Exception {
        List<PatientContact> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<PatientContactDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets patientContact data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody PatientContactCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<PatientContactDto> findDtos(List<PatientContact> list){
        converter.initObject(true);
        List<PatientContactDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<PatientContactDto> getDtoResponseEntity(PatientContactDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private PatientContactMedecinService service;
    @Autowired private PatientContactConverter converter;





}
