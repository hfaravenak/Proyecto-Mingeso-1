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

    @GetMapping("nuevaquincena/{codigo}")
    public QuincenaEntity nuevaQuincena(@PathVariable("codigo") String codigo) {
        return quincenaService.crearQuincena(codigo);
    }

    //Estos son puros testings de metodos
    @GetMapping("/obtener-proveedor/{codigo}")
    public ProveedorEntity obtenerProveedorPorCodigo(@PathVariable("codigo") String codigo) {
          return quincenaService.obtenerProveedorPorCodigo(codigo);
    }

    @GetMapping("obtener-klsleche/{codigo}")
    public Double obtenerTodosKlsLeche(@PathVariable("codigo") String codigo) {
        return quincenaService.obtenerTotalKilosLechePorProveedor(codigo);
    }

    @GetMapping("obtener-fecha/{codigo}")
    public Date obtenerFechaAcopio(@PathVariable("codigo") String codigo) {
        return quincenaService.obtenerFechaPorProveedor(codigo);
    }

    @GetMapping("obtener-porcentajegrasa/{codigo}")
    public Double obtenerPorcentajeGrasa(@PathVariable("codigo") String codigo) {
        return quincenaService.obtenerTotalPorcentajeGrasaLaboratorio(codigo);
    }

    @GetMapping("obtener-porcentajesolidos/{codigo}")
    public Double obtenerPorcentajeSolidos(@PathVariable("codigo") String codigo) {
        return quincenaService.obtenerTotalPorcentajeSolidosTotalesLaboratorio(codigo);
    }

    @GetMapping("obtener-diasacopiom/{codigo}")
    public Integer obtenerDiasAcopioMañana(@PathVariable("codigo") String codigo) {
        return quincenaService.contarDiasAcopiom(codigo);
    }

    @GetMapping("obtener-diasacopiot/{codigo}")
    public Integer obtenerDiasAcopioTarde(@PathVariable("codigo") String codigo) {
        return quincenaService.contarDiasAcopiot(codigo);
    }

    @GetMapping("obtener-diasacopiomt/{codigo}")
    public Integer obtenerDiasAcopioMañanaYtarde(@PathVariable("codigo") String codigo) {
        return quincenaService.contarDiasAcopiomt(codigo);
    }

}
