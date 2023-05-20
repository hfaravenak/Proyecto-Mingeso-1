package cl.usach.mingeso.proyectomingeso1.Controllers;

import cl.usach.mingeso.proyectomingeso1.Entities.AcopioEntity;
import cl.usach.mingeso.proyectomingeso1.Entities.ProveedorEntity;
import cl.usach.mingeso.proyectomingeso1.Entities.QuincenaEntity;
import cl.usach.mingeso.proyectomingeso1.Services.ProveedorService;
import cl.usach.mingeso.proyectomingeso1.Services.QuincenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    /*
    @GetMapping("nuevaquincena/{codigo}")
    public QuincenaEntity nuevaQuincena(@PathVariable("codigo") String codigo) {
        return quincenaService.crearQuincena(codigo);
    }
    */


    //Estos son puros testings de metodos
    @GetMapping("/obtener-proveedor/{codigo}")
    public ProveedorEntity obtenerProveedorPorCodigo(@PathVariable("codigo") String codigo) {
          return quincenaService.obtenerProveedorPorCodigo(codigo);
    }

    @GetMapping("obtener-klsleche/{codigo}")
    public List<String> obtenerTodosKlsLeche(@PathVariable("codigo") String codigo) {
        return quincenaService.obtenerKilosLechePorProveedor(codigo);
    }

    @GetMapping("obtener-turno/{codigo}")
    public List<String> obtenerTurnoAcopio(@PathVariable("codigo") String codigo) {
        return quincenaService.obtenerTurnoAcopioPorProveedor(codigo);
    }

    @GetMapping("obtener-fecha/{codigo}")
    public List<Date> obtenerFechaAcopio(@PathVariable("codigo") String codigo) {
        return quincenaService.obtenerFechasAcopio(codigo);
    }

    @GetMapping("obtener-porcentajegrasa/{codigo}")
    public List<Double> obtenerPorcentajeGrasa(@PathVariable("codigo") String codigo) {
        return quincenaService.obtenerPorcentajeGrasaLaboratorio(codigo);
    }

    @GetMapping("obtener-porcentajesolidos/{codigo}")
    public List<Double> obtenerPorcentajeSolidos(@PathVariable("codigo") String codigo) {
        return quincenaService.obtenerPorcentajeSolidosTotalesLaboratorio(codigo);
    }

}
