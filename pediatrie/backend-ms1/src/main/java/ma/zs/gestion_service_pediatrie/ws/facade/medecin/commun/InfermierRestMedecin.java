package  ma.zs.gestion_service_pediatrie.ws.facade.medecin.commun;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.gestion_service_pediatrie.bean.core.commun.Infermier;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.commun.InfermierCriteria;
import ma.zs.gestion_service_pediatrie.service.facade.medecin.commun.InfermierMedecinService;
import ma.zs.gestion_service_pediatrie.ws.converter.commun.InfermierConverter;
import ma.zs.gestion_service_pediatrie.ws.dto.commun.InfermierDto;
import ma.zs.gestion_service_pediatrie.zynerator.controller.AbstractController;
import ma.zs.gestion_service_pediatrie.zynerator.dto.AuditEntityDto;
import ma.zs.gestion_service_pediatrie.zynerator.util.PaginatedList;


import ma.zs.gestion_service_pediatrie.zynerator.security.bean.User;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zs.gestion_service_pediatrie.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zs.gestion_service_pediatrie.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/medecin/infermier/")
public class InfermierRestMedecin {




    @Operation(summary = "Finds a list of all infermiers")
    @GetMapping("")
    public ResponseEntity<List<InfermierDto>> findAll() throws Exception {
        ResponseEntity<List<InfermierDto>> res = null;
        List<Infermier> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<InfermierDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all infermiers")
    @GetMapping("optimized")
    public ResponseEntity<List<InfermierDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<InfermierDto>> res = null;
        List<Infermier> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<InfermierDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a infermier by id")
    @GetMapping("id/{id}")
    public ResponseEntity<InfermierDto> findById(@PathVariable Long id) {
        Infermier t = service.findById(id);
        if (t != null) {
            converter.init(true);
            InfermierDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a infermier by email")
    @GetMapping("email/{email}")
    public ResponseEntity<InfermierDto> findByEmail(@PathVariable String email) {
	    Infermier t = service.findByReferenceEntity(new Infermier(email));
        if (t != null) {
            converter.init(true);
            InfermierDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  infermier")
    @PostMapping("")
    public ResponseEntity<InfermierDto> save(@RequestBody InfermierDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Infermier myT = converter.toItem(dto);
            Infermier t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                InfermierDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  infermier")
    @PutMapping("")
    public ResponseEntity<InfermierDto> update(@RequestBody InfermierDto dto) throws Exception {
        ResponseEntity<InfermierDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Infermier t = service.findById(dto.getId());
            converter.copy(dto,t);
            Infermier updated = service.update(t);
            InfermierDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of infermier")
    @PostMapping("multiple")
    public ResponseEntity<List<InfermierDto>> delete(@RequestBody List<InfermierDto> dtos) throws Exception {
        ResponseEntity<List<InfermierDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Infermier> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified infermier")
    @DeleteMapping("")
    public ResponseEntity<InfermierDto> delete(@RequestBody InfermierDto dto) throws Exception {
		ResponseEntity<InfermierDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            Infermier t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified infermier")
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
    @Operation(summary = "Delete multiple infermiers by ids")
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
    public List<InfermierDto> findBySexeId(@PathVariable Long id){
        return findDtos(service.findBySexeId(id));
    }
    @Operation(summary = "delete by sexe id")
    @DeleteMapping("sexe/id/{id}")
    public int deleteBySexeId(@PathVariable Long id){
        return service.deleteBySexeId(id);
    }

    @Operation(summary = "Finds a infermier and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<InfermierDto> findWithAssociatedLists(@PathVariable Long id) {
        Infermier loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        InfermierDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds infermiers by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<InfermierDto>> findByCriteria(@RequestBody InfermierCriteria criteria) throws Exception {
        ResponseEntity<List<InfermierDto>> res = null;
        List<Infermier> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<InfermierDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated infermiers by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody InfermierCriteria criteria) throws Exception {
        List<Infermier> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<InfermierDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets infermier data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody InfermierCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<InfermierDto> findDtos(List<Infermier> list){
        converter.initObject(true);
        List<InfermierDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<InfermierDto> getDtoResponseEntity(InfermierDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }



    @Operation(summary = "Change password to the specified  utilisateur")
    @PutMapping("changePassword")
    public boolean changePassword(@RequestBody User dto) throws Exception {
        return service.changePassword(dto.getUsername(),dto.getPassword());
    }

    @Autowired private InfermierMedecinService service;
    @Autowired private InfermierConverter converter;





}
