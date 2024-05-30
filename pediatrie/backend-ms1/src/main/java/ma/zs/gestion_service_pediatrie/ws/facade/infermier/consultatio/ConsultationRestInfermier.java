package  ma.zs.gestion_service_pediatrie.ws.facade.infermier.consultatio;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.gestion_service_pediatrie.bean.core.consultatio.Consultation;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.consultatio.ConsultationCriteria;
import ma.zs.gestion_service_pediatrie.service.facade.infermier.consultatio.ConsultationInfermierService;
import ma.zs.gestion_service_pediatrie.ws.converter.consultatio.ConsultationConverter;
import ma.zs.gestion_service_pediatrie.ws.dto.consultatio.ConsultationDto;
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
@RequestMapping("/api/infermier/consultation/")
public class ConsultationRestInfermier {




    @Operation(summary = "Finds a list of all consultations")
    @GetMapping("")
    public ResponseEntity<List<ConsultationDto>> findAll() throws Exception {
        ResponseEntity<List<ConsultationDto>> res = null;
        List<Consultation> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
            converter.initObject(true);
        List<ConsultationDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all consultations")
    @GetMapping("optimized")
    public ResponseEntity<List<ConsultationDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<ConsultationDto>> res = null;
        List<Consultation> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<ConsultationDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a consultation by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ConsultationDto> findById(@PathVariable Long id) {
        Consultation t = service.findById(id);
        if (t != null) {
            converter.init(true);
            ConsultationDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a consultation by ref")
    @GetMapping("ref/{ref}")
    public ResponseEntity<ConsultationDto> findByRef(@PathVariable String ref) {
	    Consultation t = service.findByReferenceEntity(new Consultation(ref));
        if (t != null) {
            converter.init(true);
            ConsultationDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  consultation")
    @PostMapping("")
    public ResponseEntity<ConsultationDto> save(@RequestBody ConsultationDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Consultation myT = converter.toItem(dto);
            Consultation t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ConsultationDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  consultation")
    @PutMapping("")
    public ResponseEntity<ConsultationDto> update(@RequestBody ConsultationDto dto) throws Exception {
        ResponseEntity<ConsultationDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Consultation t = service.findById(dto.getId());
            converter.copy(dto,t);
            Consultation updated = service.update(t);
            ConsultationDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of consultation")
    @PostMapping("multiple")
    public ResponseEntity<List<ConsultationDto>> delete(@RequestBody List<ConsultationDto> dtos) throws Exception {
        ResponseEntity<List<ConsultationDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Consultation> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified consultation")
    @DeleteMapping("")
    public ResponseEntity<ConsultationDto> delete(@RequestBody ConsultationDto dto) throws Exception {
		ResponseEntity<ConsultationDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            Consultation t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified consultation")
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
    @Operation(summary = "Delete multiple consultations by ids")
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


    @Operation(summary = "find by infermier id")
    @GetMapping("infermier/id/{id}")
    public List<ConsultationDto> findByInfermierId(@PathVariable Long id){
        return findDtos(service.findByInfermierId(id));
    }
    @Operation(summary = "delete by infermier id")
    @DeleteMapping("infermier/id/{id}")
    public int deleteByInfermierId(@PathVariable Long id){
        return service.deleteByInfermierId(id);
    }
    @Operation(summary = "find by patient id")
    @GetMapping("patient/id/{id}")
    public List<ConsultationDto> findByPatientId(@PathVariable Long id){
        return findDtos(service.findByPatientId(id));
    }
    @Operation(summary = "delete by patient id")
    @DeleteMapping("patient/id/{id}")
    public int deleteByPatientId(@PathVariable Long id){
        return service.deleteByPatientId(id);
    }

    @Operation(summary = "Finds a consultation and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ConsultationDto> findWithAssociatedLists(@PathVariable Long id) {
        Consultation loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        ConsultationDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds consultations by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ConsultationDto>> findByCriteria(@RequestBody ConsultationCriteria criteria) throws Exception {
        ResponseEntity<List<ConsultationDto>> res = null;
        List<Consultation> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<ConsultationDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated consultations by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ConsultationCriteria criteria) throws Exception {
        List<Consultation> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initList(false);
        converter.initObject(true);
        List<ConsultationDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets consultation data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ConsultationCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ConsultationDto> findDtos(List<Consultation> list){
        converter.initList(false);
        converter.initObject(true);
        List<ConsultationDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ConsultationDto> getDtoResponseEntity(ConsultationDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private ConsultationInfermierService service;
    @Autowired private ConsultationConverter converter;





}
