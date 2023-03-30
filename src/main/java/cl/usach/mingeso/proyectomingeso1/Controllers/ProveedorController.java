package cl.usach.mingeso.proyectomingeso1.Controllers;

import cl.usach.mingeso.proyectomingeso1.Entities.ProveedorEntity;
import cl.usach.mingeso.proyectomingeso1.Repositories.ProveedorRepository;
import cl.usach.mingeso.proyectomingeso1.Services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("proveedor")
public class ProveedorController {
    @Autowired
    ProveedorService proveedorService;

    @GetMapping("/listar")
    List<ProveedorEntity> listarProveedores() {
        return proveedorService.obtenerProveedores();
    }
    @PostMapping("/agregar")
    private ResponseEntity<ProveedorEntity> createProveedor(@RequestBody ProveedorEntity proveedor){
        try{
            ProveedorEntity user1 = proveedorService.guardarProveedor(proveedor);
            return ResponseEntity.created(new URI("/User"+user1.getCodigo())).body(user1);
        }catch (URISyntaxException e){
            throw new RuntimeException(e);
        }
    }
}
