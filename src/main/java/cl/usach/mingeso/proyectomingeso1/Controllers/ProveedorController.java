package cl.usach.mingeso.proyectomingeso1.Controllers;

import cl.usach.mingeso.proyectomingeso1.Entities.ProveedorEntity;
import cl.usach.mingeso.proyectomingeso1.Services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Controller
@RequestMapping("/proveedor")
public class ProveedorController {
    @Autowired
    ProveedorService proveedorService;

    @GetMapping("/listar")
    public String listar(Model model) {
        List<ProveedorEntity> proveedores = proveedorService.obtenerProveedores();
        model.addAttribute("proveedores", proveedores);
        return "index";
    }


    @GetMapping("/agregar-proveedor")
    public String proveedor(){
        return "agregar-proveedor";
    }
    @PostMapping("/agregar-proveedor")
    public String nuevoProveedor(@RequestParam("codigo") String codigo,
                                 @RequestParam("nombre") String nombre,
                                 @RequestParam("categoria") String categoria,
                                 @RequestParam("retencion") String retencion){
        proveedorService.guardarProveedor(codigo, nombre, categoria, retencion);
        return "redirect:/agregar-proveedor";
    }

}
