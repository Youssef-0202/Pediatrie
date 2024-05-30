package  ma.zs.gestion_service_pediatrie.ws.facade.admin.commun;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.gestion_service_pediatrie.bean.core.commun.Medecin;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.commun.MedecinCriteria;
import ma.zs.gestion_service_pediatrie.service.facade.admin.commun.MedecinAdminService;
import ma.zs.gestion_service_pediatrie.ws.converter.commun.MedecinConverter;
import ma.zs.gestion_service_pediatrie.ws.dto.commun.MedecinDto;
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
@RequestMapping("/api/admin/medecin/")
public class MedecinRestAdmin {

    @Operation(summary = "Finds a list of all medecins")
    @GetMapping("")
    public ResponseEntity<List<MedecinDto>> findAll() throws Exception {
        ResponseEntity<List<MedecinDto>> res = null;
        List<Medecin> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<MedecinDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all medecins")
    @GetMapping("optimized")
    public ResponseEntity<List<MedecinDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<MedecinDto>> res = null;
        List<Medecin> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<MedecinDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a medecin by id")
    @GetMapping("id/{id}")
    public ResponseEntity<MedecinDto> findById(@PathVariable Long id) {
        Medecin t = service.findById(id);
        if (t != null) {
            converter.init(true);
            MedecinDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a medecin by email")
    @GetMapping("email/{email}")
    public ResponseEntity<MedecinDto> findByEmail(@PathVariable String email) {
	    Medecin t = service.findByReferenceEntity(new Medecin(email));
        if (t != null) {
            converter.init(true);
            MedecinDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  medecin")
    @PostMapping("")
    public ResponseEntity<MedecinDto> save(@RequestBody MedecinDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Medecin myT = converter.toItem(dto);
            Medecin t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                MedecinDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  medecin")
    @PutMapping("")
    public ResponseEntity<MedecinDto> update(@RequestBody MedecinDto dto) throws Exception {
        ResponseEntity<MedecinDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Medecin t = service.findById(dto.getId());
            converter.copy(dto,t);
            Medecin updated = service.update(t);
            MedecinDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of medecin")
    @PostMapping("multiple")
    public ResponseEntity<List<MedecinDto>> delete(@RequestBody List<MedecinDto> dtos) throws Exception {
        ResponseEntity<List<MedecinDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Medecin> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified medecin")
    @DeleteMapping("")
    public ResponseEntity<MedecinDto> delete(@RequestBody MedecinDto dto) throws Exception {
		ResponseEntity<MedecinDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            Medecin t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified medecin")
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
    @Operation(summary = "Delete multiple medecins by ids")
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
    public List<MedecinDto> findBySexeId(@PathVariable Long id){
        return findDtos(service.findBySexeId(id));
    }
    @Operation(summary = "delete by sexe id")
    @DeleteMapping("sexe/id/{id}")
    public int deleteBySexeId(@PathVariable Long id){
        return service.deleteBySexeId(id);
    }

    @Operation(summary = "Finds a medecin and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<MedecinDto> findWithAssociatedLists(@PathVariable Long id) {
        Medecin loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        MedecinDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds medecins by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<MedecinDto>> findByCriteria(@RequestBody MedecinCriteria criteria) throws Exception {
        ResponseEntity<List<MedecinDto>> res = null;
        List<Medecin> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<MedecinDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated medecins by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody MedecinCriteria criteria) throws Exception {
        List<Medecin> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<MedecinDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets medecin data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody MedecinCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<MedecinDto> findDtos(List<Medecin> list){
        converter.initObject(true);
        List<MedecinDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<MedecinDto> getDtoResponseEntity(MedecinDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }



    @Operation(summary = "Change password to the specified  utilisateur")
    @PutMapping("changePassword")
    public boolean changePassword(@RequestBody User dto) throws Exception {
        return service.changePassword(dto.getUsername(),dto.getPassword());
    }

    @Autowired private MedecinAdminService service;
    @Autowired private MedecinConverter converter;





}
