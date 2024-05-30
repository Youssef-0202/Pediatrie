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

import ma.zs.gestion_service_pediatrie.bean.core.gestio.Certificat;
import ma.zs.gestion_service_pediatrie.dao.criteria.core.gestio.CertificatCriteria;
import ma.zs.gestion_service_pediatrie.service.facade.infermier.gestio.CertificatInfermierService;
import ma.zs.gestion_service_pediatrie.ws.converter.gestio.CertificatConverter;
import ma.zs.gestion_service_pediatrie.ws.dto.gestio.CertificatDto;
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
@RequestMapping("/api/infermier/certificat/")
public class CertificatRestInfermier {




    @Operation(summary = "Finds a list of all certificats")
    @GetMapping("")
    public ResponseEntity<List<CertificatDto>> findAll() throws Exception {
        ResponseEntity<List<CertificatDto>> res = null;
        List<Certificat> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<CertificatDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all certificats")
    @GetMapping("optimized")
    public ResponseEntity<List<CertificatDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<CertificatDto>> res = null;
        List<Certificat> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<CertificatDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a certificat by id")
    @GetMapping("id/{id}")
    public ResponseEntity<CertificatDto> findById(@PathVariable Long id) {
        Certificat t = service.findById(id);
        if (t != null) {
            converter.init(true);
            CertificatDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a certificat by ref")
    @GetMapping("ref/{ref}")
    public ResponseEntity<CertificatDto> findByRef(@PathVariable String ref) {
	    Certificat t = service.findByReferenceEntity(new Certificat(ref));
        if (t != null) {
            converter.init(true);
            CertificatDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  certificat")
    @PostMapping("")
    public ResponseEntity<CertificatDto> save(@RequestBody CertificatDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Certificat myT = converter.toItem(dto);
            Certificat t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                CertificatDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  certificat")
    @PutMapping("")
    public ResponseEntity<CertificatDto> update(@RequestBody CertificatDto dto) throws Exception {
        ResponseEntity<CertificatDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Certificat t = service.findById(dto.getId());
            converter.copy(dto,t);
            Certificat updated = service.update(t);
            CertificatDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of certificat")
    @PostMapping("multiple")
    public ResponseEntity<List<CertificatDto>> delete(@RequestBody List<CertificatDto> dtos) throws Exception {
        ResponseEntity<List<CertificatDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Certificat> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified certificat")
    @DeleteMapping("")
    public ResponseEntity<CertificatDto> delete(@RequestBody CertificatDto dto) throws Exception {
		ResponseEntity<CertificatDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            Certificat t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified certificat")
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
    @Operation(summary = "Delete multiple certificats by ids")
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



    @Operation(summary = "Finds a certificat and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<CertificatDto> findWithAssociatedLists(@PathVariable Long id) {
        Certificat loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        CertificatDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds certificats by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<CertificatDto>> findByCriteria(@RequestBody CertificatCriteria criteria) throws Exception {
        ResponseEntity<List<CertificatDto>> res = null;
        List<Certificat> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<CertificatDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated certificats by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody CertificatCriteria criteria) throws Exception {
        List<Certificat> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<CertificatDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets certificat data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody CertificatCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<CertificatDto> findDtos(List<Certificat> list){
        converter.initObject(true);
        List<CertificatDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<CertificatDto> getDtoResponseEntity(CertificatDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private CertificatInfermierService service;
    @Autowired private CertificatConverter converter;





}
