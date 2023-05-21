package cl.usach.mingeso.proyectomingeso1.Controllers;

import cl.usach.mingeso.proyectomingeso1.Entities.PlanillaEntity;
import cl.usach.mingeso.proyectomingeso1.Entities.ProveedorEntity;
import cl.usach.mingeso.proyectomingeso1.Services.PlanillaService;
import cl.usach.mingeso.proyectomingeso1.Services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/planilla")
public class PlanillaController {
    @Autowired
    PlanillaService planillaService;
    @Autowired
    ProveedorService proveedorService;

    @GetMapping("/obtener-planilla")
    public List<PlanillaEntity> obtenerPlanilla(){ return planillaService.obtenerPlanillas(); }

    @GetMapping("/obtener-quincena/{codigo}")
    public String obtenerQuincenaPlanilla(@PathVariable("codigo") String codigoProveedor) {
        return planillaService.obtenerFechaMasReciente(codigoProveedor);
    }

    @GetMapping("/obtener-nombreproveedor/{codigo}")
    public String obtenerNombreProveedor(@PathVariable("codigo") String codigoProveedor) {
        return planillaService.obtenerNombreProveedor(codigoProveedor);
    }

    @GetMapping("/obtener-klstotales/{codigo}")
    public Double obtenerKilosTotalesProveedor(@PathVariable("codigo") String codigoProveedor) {
        return planillaService.obtenerSumaKilosPorProveedor(codigoProveedor);
    }

    @GetMapping("/obtener-nroDiasQueEnvioLeche/{codigo}")
    public Integer obtenerNroDiasQueEnvioLeche(@PathVariable("codigo") String codigoProveedor) {
        return planillaService.obtenerNroDiasQueEnvioLeche(codigoProveedor);
    }

    @GetMapping("/obtener-promedioDiarioKlsLeche/{codigo}")
    public Double obtenerPromedioKlsLecheDiario(@PathVariable("codigo") String codigoProveedor) {
        return planillaService.promedioDiarioKilosLeche(codigoProveedor);
    }

    @GetMapping("/variacion-kilos/{codigo}")
    public double obtenerVariacionKilos(@PathVariable("codigo") String codigo) {
        ProveedorEntity proveedor = proveedorService.findByCodigo(codigo);
        return planillaService.obtenerVariacionKilos(proveedor);
    }

    @GetMapping("/variacion-grasa/{codigo}")
    public double obtenerVariacionGrasa(@PathVariable("codigo") String codigo) {
        ProveedorEntity proveedor = proveedorService.findByCodigo(codigo);
        return planillaService.obtenerVariacionGrasa(proveedor);
    }

    @GetMapping("/variacion-solidos/{codigo}")
    public double obtenerVariacionSolidos(@PathVariable("codigo") String codigo) {
        ProveedorEntity proveedor = proveedorService.findByCodigo(codigo);
        return planillaService.obtenerVariacionSolidos(proveedor);
    }

    @GetMapping("/obtener-porcentajeGrasa/{codigo}")
    public Double obtenerPorcentajeGrasa(@PathVariable("codigo") String codigoProveedor) {
        return planillaService.obtenerTotalPorcentajeGrasaLaboratorio(codigoProveedor);
    }

    @GetMapping("/obtener-porcentajeST/{codigo}")
    public Double obtenerPorcentajeST(@PathVariable("codigo") String codigoProveedor) {
        return planillaService.obtenerTotalPorcentajeSolidosTotalesLaboratorio(codigoProveedor);
    }


}
