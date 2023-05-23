package cl.usach.mingeso.proyectomingeso1.Controllers;

import cl.usach.mingeso.proyectomingeso1.Entities.PlanillaEntity;
import cl.usach.mingeso.proyectomingeso1.Entities.ProveedorEntity;
import cl.usach.mingeso.proyectomingeso1.Services.PlanillaService;
import cl.usach.mingeso.proyectomingeso1.Services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/planilla")
public class PlanillaController {
    @Autowired
    PlanillaService planillaService;
    @Autowired
    ProveedorService proveedorService;


    @GetMapping("/ingresar-proveedor")
    public String planilla(){
        return "ingresar-proveedor"; }
    @PostMapping("/ingresar-proveedor")
    public String nuevaPlanilla(@RequestParam("codigo") String codigo){
        planillaService.crearPlanilla(codigo);
        return "redirect:/planilla/listar";
    }

    @GetMapping("/listar")
    public String listar(Model model) {
        List<PlanillaEntity> planillas = planillaService.obtenerPlanillas();
        model.addAttribute("planillas", planillas);
        return "mostrar-planilla";
    }




    /*
    @GetMapping("/obtener-planillaporproveedor/{codigo}")
    public ArrayList<PlanillaEntity> obtenerPlanillaPorProveedor(@PathVariable("codigo") String codigoProveedor) {
        return planillaService.obtenerPlanillaPorCodigoProveedor(codigoProveedor);
    }

    @GetMapping("/obtener-planillas")
    public List<PlanillaEntity> obtenerPlanillas(){ return planillaService.obtenerPlanillas(); }

    @GetMapping("/crear-planilla/{codigo}")
    public PlanillaEntity crearPlanilla(@PathVariable("codigo") String codigoProveedor) {
        return planillaService.crearPlanilla(codigoProveedor);
    }

    @GetMapping("/obtener-quincena/{codigo}")
    public String obtenerQuincenaPlanilla(@PathVariable("codigo") String codigoProveedor) {
        return planillaService.obtenerQuincena(codigoProveedor);
    }

    @GetMapping("/obtener-nombreproveedor/{codigo}")
    public String obtenerNombreProveedor(@PathVariable("codigo") String codigoProveedor) {
        return planillaService.obtenerNombreProveedor(codigoProveedor);
    }

    @GetMapping("/obtener-klstotales/{codigo}")
    public Double obtenerKilosTotalesProveedor(@PathVariable("codigo") String codigoProveedor) {
        return planillaService.obtenerKilosPorProveedor(codigoProveedor);
    }

    @GetMapping("/obtener-nroDiasQueEnvioLeche/{codigo}")
    public Integer obtenerNroDiasQueEnvioLeche(@PathVariable("codigo") String codigoProveedor) {
        return planillaService.obtenerNroDiasQueEnvioLeche(codigoProveedor);
    }

    @GetMapping("/obtener-promedioDiarioKlsLeche/{codigo}")
    public Double obtenerPromedioKlsLecheDiario(@PathVariable("codigo") String codigoProveedor) {
        return planillaService.promedioDiarioKilosLeche(codigoProveedor);
    }

    @GetMapping("/variacion-klsleche/{codigo}")
    public Double obtenerVariacionKlsLeche(@PathVariable("codigo") String codigoProveedor) {
        return planillaService.obtenerVariacionLeche(codigoProveedor);
    }

    @GetMapping("/variacion-grasa/{codigo}")
    public Double obtenerVariacionGrasa(@PathVariable("codigo") String codigoProveedor) {
        return planillaService.obtenerVariacionGrasa(codigoProveedor);
    }

    @GetMapping("/variacion-solidos/{codigo}")
    public Double obtenerVariacionSolidos(@PathVariable("codigo") String codigoProveedor) {
        return planillaService.obtenerVariacionSolidos(codigoProveedor);
    }

    @GetMapping("/obtener-porcentajeGrasa/{codigo}")
    public Double obtenerPorcentajeGrasa(@PathVariable("codigo") String codigoProveedor) {
        return planillaService.obtenerPorcentajeGrasaLaboratorio(codigoProveedor);
    }

    @GetMapping("/obtener-porcentajeST/{codigo}")
    public Double obtenerPorcentajeST(@PathVariable("codigo") String codigoProveedor) {
        return planillaService.obtenerPorcentajeSolidosTotalesLaboratorio(codigoProveedor);
    }

    @GetMapping("/obtener-categoriaProveedor/{codigo}")
    public String obtenerCategoria(@PathVariable("codigo") String codigoProveedor) {
        return planillaService.obtenerCategoriaProveedor(codigoProveedor);
    }

    @GetMapping("/calcular-pagoLecheProveedor/{codigo}")
    public Double calcularPagoLeche(@PathVariable("codigo") String codigoProveedor) {
        return planillaService.calcularPagoLecheProveedor(codigoProveedor);
    }


    @GetMapping("/calcular-pagograsa/{codigo}")
    public Double calcularPagoGrasa(@PathVariable("codigo") String codigoProveedor) {
        return planillaService.calcularPagoPorPorcentajeGrasa(codigoProveedor);
    }

    @GetMapping("/calcular-pagosolidostotales/{codigo}")
    public Double calcularPagoSolidosTotales(@PathVariable("codigo") String codigoProveedor) {
        return planillaService.calcularPagoPorPorcentajeSolidosTotales(codigoProveedor);
    }

    @GetMapping("/calcular-pagosoKlsLeche/{codigo}")
    public Double calcularPagoKLSLeche(@PathVariable("codigo") String codigoProveedor) {
        return planillaService.generarPagoAcopioLecheSinBonificacion(codigoProveedor);
    }

    @GetMapping("/obtener-diasAcopioM/{codigo}")
    public Integer obtenerDiasAcopioM(@PathVariable("codigo") String codigoProveedor) {
        return planillaService.obtenerDiasAcopioM(codigoProveedor);
    }

    @GetMapping("/obtener-diasAcopioT/{codigo}")
    public Integer obtenerDiasAcopioT(@PathVariable("codigo") String codigoProveedor) {
        return planillaService.obtenerDiasAcopioT(codigoProveedor);
    }

    @GetMapping("/obtener-diasAcopioMT/{codigo}")
    public Integer obtenerDiasAcopioMT(@PathVariable("codigo") String codigoProveedor) {
        return planillaService.obtenerDiasAcopioMT(codigoProveedor);
    }

    @GetMapping("/obtener-bonificacionfrecuencia/{codigo}")
    public Double obtenerBonificacionFrecuencia(@PathVariable("codigo") String codigoProveedor) {
        return planillaService.bonificacionXfrecuencia(codigoProveedor);
    }

    @GetMapping("/obtener-pagoAcopioLeche/{codigo}")
    public Double pagoAcopioLeche(@PathVariable("codigo") String codigoProveedor) {
        return planillaService.pagoAcopioLeche(codigoProveedor);
    }

    @GetMapping("/descuento-variacionLeche/{codigo}")
    public Double descuentoVariacionLeche(@PathVariable("codigo") String codigoProveedor) {
        return planillaService.descuentoVariacionNegativaKlsLeche(codigoProveedor);
    }

    @GetMapping("/descuento-variacionGrasa/{codigo}")
    public Double descuentoVariacionGrasa(@PathVariable("codigo") String codigoProveedor) {
        return planillaService.descuentoVariacionNegativaGrasa(codigoProveedor);
    }

    @GetMapping("/descuento-variacionSolidos/{codigo}")
    public Double descuentoVariacionSolidos(@PathVariable("codigo") String codigoProveedor) {
        return planillaService.descuentoVariacionSolidosTotales(codigoProveedor);
    }

    @GetMapping("/obtener-descuentos/{codigo}")
    public Double descuentoVariaciones(@PathVariable("codigo") String codigoProveedor) {
        return planillaService.obtenerDescuentos(codigoProveedor);
    }

    @GetMapping("/obtener-stringDeretencion/{codigo}")
    public String obtenerRetencion(@PathVariable("codigo") String codigoProveedor) {
        return planillaService.obtenerRetencionString(codigoProveedor);
    }

    @GetMapping("/obtener-pagoTotal/{codigo}")
    public Double obtenerPagoTotal(@PathVariable("codigo") String codigoProveedor) {
        return planillaService.obtenerPagoTotal(codigoProveedor);
    }

    @GetMapping("/obtener-montoRetencion/{codigo}")
    public Double obtenerMontoRetencion(@PathVariable("codigo") String codigoProveedor) {
        return planillaService.obtenerMontoRetencion(codigoProveedor);
    }

    @GetMapping("/obtener-pagoFinal/{codigo}")
    public Double obtenerPagoFinal(@PathVariable("codigo") String codigoProveedor) {
        return planillaService.pagoFinal(codigoProveedor);
    }
    */


}
