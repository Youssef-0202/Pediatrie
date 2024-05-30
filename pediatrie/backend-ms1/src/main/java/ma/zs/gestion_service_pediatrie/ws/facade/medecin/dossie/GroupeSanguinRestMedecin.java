package  ma.zs.gestion_service_pediatrie.ws.facade.medecin.dossie;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.gestion_service_pediatrie.bean.core.dossie.GroupeSanguin;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.dossie.GroupeSanguinCriteria;
import ma.zs.gestion_service_pediatrie.service.facade.medecin.dossie.GroupeSanguinMedecinService;
import ma.zs.gestion_service_pediatrie.ws.converter.dossie.GroupeSanguinConverter;
import ma.zs.gestion_service_pediatrie.ws.dto.dossie.GroupeSanguinDto;
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
@RequestMapping("/api/medecin/groupeSanguin/")
public class GroupeSanguinRestMedecin {




    @Operation(summary = "Finds a list of all groupeSanguins")
    @GetMapping("")
    public ResponseEntity<List<GroupeSanguinDto>> findAll() throws Exception {
        ResponseEntity<List<GroupeSanguinDto>> res = null;
        List<GroupeSanguin> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<GroupeSanguinDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all groupeSanguins")
    @GetMapping("optimized")
    public ResponseEntity<List<GroupeSanguinDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<GroupeSanguinDto>> res = null;
        List<GroupeSanguin> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<GroupeSanguinDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a groupeSanguin by id")
    @GetMapping("id/{id}")
    public ResponseEntity<GroupeSanguinDto> findById(@PathVariable Long id) {
        GroupeSanguin t = service.findById(id);
        if (t != null) {
            GroupeSanguinDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a groupeSanguin by ref")
    @GetMapping("ref/{ref}")
    public ResponseEntity<GroupeSanguinDto> findByRef(@PathVariable String ref) {
	    GroupeSanguin t = service.findByReferenceEntity(new GroupeSanguin(ref));
        if (t != null) {
            GroupeSanguinDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  groupeSanguin")
    @PostMapping("")
    public ResponseEntity<GroupeSanguinDto> save(@RequestBody GroupeSanguinDto dto) throws Exception {
        if(dto!=null){
            GroupeSanguin myT = converter.toItem(dto);
            GroupeSanguin t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                GroupeSanguinDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  groupeSanguin")
    @PutMapping("")
    public ResponseEntity<GroupeSanguinDto> update(@RequestBody GroupeSanguinDto dto) throws Exception {
        ResponseEntity<GroupeSanguinDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            GroupeSanguin t = service.findById(dto.getId());
            converter.copy(dto,t);
            GroupeSanguin updated = service.update(t);
            GroupeSanguinDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of groupeSanguin")
    @PostMapping("multiple")
    public ResponseEntity<List<GroupeSanguinDto>> delete(@RequestBody List<GroupeSanguinDto> dtos) throws Exception {
        ResponseEntity<List<GroupeSanguinDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<GroupeSanguin> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified groupeSanguin")
    @DeleteMapping("")
    public ResponseEntity<GroupeSanguinDto> delete(@RequestBody GroupeSanguinDto dto) throws Exception {
		ResponseEntity<GroupeSanguinDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            GroupeSanguin t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified groupeSanguin")
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
    @Operation(summary = "Delete multiple groupeSanguins by ids")
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



    @Operation(summary = "Finds a groupeSanguin and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<GroupeSanguinDto> findWithAssociatedLists(@PathVariable Long id) {
        GroupeSanguin loaded =  service.findWithAssociatedLists(id);
        GroupeSanguinDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds groupeSanguins by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<GroupeSanguinDto>> findByCriteria(@RequestBody GroupeSanguinCriteria criteria) throws Exception {
        ResponseEntity<List<GroupeSanguinDto>> res = null;
        List<GroupeSanguin> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<GroupeSanguinDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated groupeSanguins by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody GroupeSanguinCriteria criteria) throws Exception {
        List<GroupeSanguin> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<GroupeSanguinDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets groupeSanguin data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody GroupeSanguinCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<GroupeSanguinDto> findDtos(List<GroupeSanguin> list){
        List<GroupeSanguinDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<GroupeSanguinDto> getDtoResponseEntity(GroupeSanguinDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private GroupeSanguinMedecinService service;
    @Autowired private GroupeSanguinConverter converter;





}
