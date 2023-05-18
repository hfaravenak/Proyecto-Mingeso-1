package cl.usach.mingeso.proyectomingeso1.Controllers;

import cl.usach.mingeso.proyectomingeso1.Entities.ProveedorEntity;
import cl.usach.mingeso.proyectomingeso1.Entities.QuincenaEntity;
import cl.usach.mingeso.proyectomingeso1.Services.ProveedorService;
import cl.usach.mingeso.proyectomingeso1.Services.QuincenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quincena")
public class QuincenaController {

    @Autowired
    QuincenaService quincenaService;
    @Autowired
    ProveedorService proveedorService;

    @GetMapping("/listar_quincena")
    public List<QuincenaEntity> obtenerQuincenas() {
        return quincenaService.obtenerQuincenas();
    }

    @GetMapping("/variacion-kilos/{codigo}")
    public double obtenerVariacionKilos(@PathVariable("codigo") String codigo) {
        ProveedorEntity proveedor = proveedorService.findByCodigo(codigo);
        return quincenaService.obtenerVariacionKilos(proveedor);
    }
}
