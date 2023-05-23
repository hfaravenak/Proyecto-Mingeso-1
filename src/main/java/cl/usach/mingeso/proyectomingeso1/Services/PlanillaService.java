package cl.usach.mingeso.proyectomingeso1.Services;

import cl.usach.mingeso.proyectomingeso1.Entities.PlanillaEntity;
import cl.usach.mingeso.proyectomingeso1.Entities.ProveedorEntity;
import cl.usach.mingeso.proyectomingeso1.Entities.QuincenaEntity;
import cl.usach.mingeso.proyectomingeso1.Repositories.*;
import cl.usach.mingeso.proyectomingeso1.Services.QuincenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PlanillaService {
    @Autowired
    PlanillaRepository planillaRepository;

    @Autowired
    QuincenaRepository quincenaRepository;

    @Autowired
    QuincenaService quincenaService;

    @Autowired
    ProveedorRepository proveedorRepository;

    @Autowired
    AcopioRepository acopioRepository;

    @Autowired
    LaboratorioRepository laboratorioRepository;

    public List<PlanillaEntity> obtenerPlanillas(){ return planillaRepository.findAll(); }

    public List<PlanillaEntity> obtenerPlanillaPorCodigoProveedor(String codigoProveedor) {
        return planillaRepository.findByCodigoProveedor(codigoProveedor);
    }
    //metodo que retorna 1 si la fecha corresponde a la primera quincena y 2 si es la segunda quincena de un mes
    public int obtenerNumeroQuincena(Date fecha) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);

        int dia = calendar.get(Calendar.DAY_OF_MONTH);

        if (dia <= 15) {
            return 1; // Primera quincena
        } else {
            return 2; // Segunda quincena
        }
    }

    public String obtenerQuincena(String codigoProveedor) {
        List<QuincenaEntity> quincenas = quincenaRepository.findAllByProveedorOrderByFechaDesc(codigoProveedor);

        if (!quincenas.isEmpty()) {
            QuincenaEntity quincena = quincenas.get(0); // Obtener la quincena más reciente
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM");
            int numeroQuincena = obtenerNumeroQuincena(quincena.getFecha()); // Utiliza el método obtenerNumeroQuincena de QuincenaService
            return dateFormat.format(quincena.getFecha()) + "/" + numeroQuincena;
        }

        return ""; // Si no se encontraron quincenas para el proveedor
    }

    //metodo para obtener nombre del proveedor pasandole el codigo
    public String obtenerNombreProveedor(String codigoProveedor) {
        ProveedorEntity proveedor = proveedorRepository.findByCodigo(codigoProveedor);

        if (proveedor != null) {
            return proveedor.getNombre();
        }

        return ""; // Si no se encuentra el proveedor con el código proporcionado
    }

    public Double obtenerKilosPorProveedor(String codigoProveedor) {
        List<QuincenaEntity> quincenas = quincenaRepository.findAllByProveedorOrderByFechaDesc(codigoProveedor);

        if (!quincenas.isEmpty()) {
            QuincenaEntity quincenaSuperior = quincenas.get(0);
            return quincenaSuperior.getKilos();
        }

        return 0.0;
    }

    public int obtenerNroDiasQueEnvioLeche(String codigoProveedor) {
        return acopioRepository.countByProveedor(codigoProveedor);
    }

    public Double promedioDiarioKilosLeche(String codigoProveedor) {
        Double kilos = obtenerKilosPorProveedor(codigoProveedor);
        Double promedioKlsLecheDiario = kilos / 15;

        return promedioKlsLecheDiario;
    }

    //Como obtener la variacion porcentual de los kilos de un proveedor de la quincena actual respecto
    //a la quincena anterior teniendo un codigo de proveedor como entrada
    public double obtenerVariacionLeche(String codigoProveedor) {
        ProveedorEntity proveedor = proveedorRepository.findByCodigo(codigoProveedor);
        if (proveedor != null) {
            List<QuincenaEntity> quincenas = quincenaRepository.findQuincenaEntityByProveedorOrderByFechaDesc(proveedor);

            if (quincenas.size() > 1) {
                double kilosActual = quincenas.get(0).getKilos();
                double kilosAnterior = quincenas.get(1).getKilos();
                double variacionKilos = ((kilosActual - kilosAnterior) / kilosAnterior) * 100;
                return variacionKilos;
            } else {
                return 0.0;
            }
        } else {
            return 0.0;
        }
    }

    //Como obtener la variacion porcentual de grasa de un proveedor de la quincena actual respecto
    //a la quincena anterior teniendo un codigo de proveedor como entrada
    public double obtenerVariacionGrasa(String codigoProveedor) {
        ProveedorEntity proveedor = proveedorRepository.findByCodigo(codigoProveedor);
        if (proveedor != null) {
            List<QuincenaEntity> quincenas = quincenaRepository.findQuincenaEntityByProveedorOrderByFechaDesc(proveedor);

            if (quincenas.size() > 1) {
                double grasaActual = quincenas.get(0).getPorcentajeGrasa();
                double grasaAnterior = quincenas.get(1).getPorcentajeGrasa();
                double variacionGrasa = ((grasaActual - grasaAnterior) / grasaAnterior) * 100;
                return variacionGrasa;
            } else {
                return 0.0;
            }
        } else {
            return 0.0;
        }
    }

    //Como obtener la variacion porcentual de solidos totales de un proveedor de la quincena actual respecto
    //a la quincena anterior teniendo un codigo de proveedor como entrada
    public double obtenerVariacionSolidos(String codigoProveedor) {
        ProveedorEntity proveedor = proveedorRepository.findByCodigo(codigoProveedor);
        if (proveedor != null) {
            List<QuincenaEntity> quincenas = quincenaRepository.findQuincenaEntityByProveedorOrderByFechaDesc(proveedor);

            if (quincenas.size() > 1) {
                double solidosActual = quincenas.get(0).getPorcentajeSolidosTotales();
                double solidosAnterior = quincenas.get(1).getPorcentajeSolidosTotales();
                double variacionsolidos = ((solidosActual - solidosAnterior) / solidosAnterior) * 100;
                return variacionsolidos;
            } else {
                return 0.0;
            }
        } else {
            return 0.0;
        }
    }

    //a traves del codigo de un proveedor obtiene el porcentaje de grasa de un laboratorio
    public double obtenerPorcentajeGrasaLaboratorio(String codigoProveedor) {
        List<Double> porcentajesGrasa = laboratorioRepository.findPorcGrasaByProveedor(codigoProveedor);
        double totalPorcentajeGrasa = 0.0;

        for (Double porcentaje : porcentajesGrasa) {
            if (porcentaje != null) {
                totalPorcentajeGrasa += porcentaje;
            }
        }

        return totalPorcentajeGrasa;
    }

    //a traves del codigo de un proveedor obtiene el porcentaje de solidos totales de un laboratorio
    public double obtenerPorcentajeSolidosTotalesLaboratorio(String codigoProveedor) {
        List<Double> porcentajesSolidosTotales = laboratorioRepository.findPorcSolidosTotalesByProveedor(codigoProveedor);
        double totalPorcentajeSolidosTotales = 0.0;

        for (Double porcentaje : porcentajesSolidosTotales) {
            if (porcentaje != null) {
                totalPorcentajeSolidosTotales += porcentaje;
            }
        }

        return totalPorcentajeSolidosTotales;
    }

    //metodo para calular los pagos por categoria de proveedor
    public Double pagoCategoria(String categoria, Double kilos) {
        return switch (categoria) {
            case "A" -> (700 * kilos);
            case "B" -> (550 * kilos);
            case "C" -> (400 * kilos);
            case "D" -> (250 * kilos);
            default -> 0.0;
        };
    }

    //metodo para obtener la categoria de un proveedor
    public String obtenerCategoriaProveedor(String codigoProveedor) {
        ProveedorEntity proveedor = proveedorRepository.findByCodigo(codigoProveedor);
        if (proveedor != null) {
            return proveedor.getCategoria();
        }
        return ""; // En caso de no encontrar el proveedor
    }

    //metodo para calcular el pago leche de un proveedor
    public Double calcularPagoLecheProveedor(String codigoProveedor) {
        Double kilos = obtenerKilosPorProveedor(codigoProveedor);
        String categoria = obtenerCategoriaProveedor(codigoProveedor);

        if (kilos > 0 && !categoria.isEmpty()) {
            return pagoCategoria(categoria, kilos);
        }

        return 0.0;
    }

    public Double calcularPagoPorPorcentajeGrasa(String codigoProveedor) {
        Double porcentajeGrasa = obtenerPorcentajeGrasaLaboratorio(codigoProveedor);
        Double kilos = obtenerKilosPorProveedor(codigoProveedor);

        if (porcentajeGrasa != null && kilos > 0) {
            if (porcentajeGrasa >= 0 && porcentajeGrasa <= 20) {
                return kilos * 30;
            } else if (porcentajeGrasa >= 21 && porcentajeGrasa <= 45) {
                return kilos * 80;
            } else if (porcentajeGrasa >= 46) {
                return kilos * 120;
            }
        }

        return 0.0;
    }

    public Double calcularPagoPorPorcentajeSolidosTotales(String codigoProveedor) {
        Double porcentajeSolidos = obtenerPorcentajeSolidosTotalesLaboratorio(codigoProveedor);
        Double kilos = obtenerKilosPorProveedor(codigoProveedor);

        if (porcentajeSolidos != null && kilos > 0) {
            if (porcentajeSolidos >= 0 && porcentajeSolidos <= 7) {
                return kilos * -130;
            } else if (porcentajeSolidos >= 8 && porcentajeSolidos <= 18) {
                return kilos * -90;
            } else if (porcentajeSolidos >= 19 && porcentajeSolidos <= 35) {
                return kilos * 95;
            } else if (porcentajeSolidos >= 36) {
                return kilos * 150;
            }
        }

        return 0.0;
    }

    public Double generarPagoAcopioLecheSinBonificacion(String codigoProveedor) {
        Double pagoLeche = calcularPagoLecheProveedor(codigoProveedor);
        Double pagoGrasa = calcularPagoPorPorcentajeGrasa(codigoProveedor);
        Double pagoSolidos = calcularPagoPorPorcentajeSolidosTotales(codigoProveedor);
        Double pagoKilosLeche = pagoLeche + pagoGrasa + pagoSolidos;
        return pagoKilosLeche;

    }

    public Integer obtenerDiasAcopioM(String codigoProveedor) {
        List<QuincenaEntity> quincenas = quincenaRepository.findAllByProveedorOrderByFechaDesc(codigoProveedor);

        if (!quincenas.isEmpty()) {
            QuincenaEntity quincenaSuperior = quincenas.get(0);
            return quincenaSuperior.getDiasAcopioM();
        }
        return 0;
    }

    public Integer obtenerDiasAcopioT(String codigoProveedor) {
        List<QuincenaEntity> quincenas = quincenaRepository.findAllByProveedorOrderByFechaDesc(codigoProveedor);

        if (!quincenas.isEmpty()) {
            QuincenaEntity quincenaSuperior = quincenas.get(0);
            return quincenaSuperior.getDiasAcopioT();
        }
        return 0;
    }

    public Integer obtenerDiasAcopioMT(String codigoProveedor) {
        List<QuincenaEntity> quincenas = quincenaRepository.findAllByProveedorOrderByFechaDesc(codigoProveedor);

        if (!quincenas.isEmpty()) {
            QuincenaEntity quincenaSuperior = quincenas.get(0);
            return quincenaSuperior.getDiasAcopioMT();
        }
        return 0;
    }

    public double bonificacionXfrecuencia(String codigoProveedor) {
        double monto = generarPagoAcopioLecheSinBonificacion(codigoProveedor);
        int diasAcopioMT = obtenerDiasAcopioMT(codigoProveedor);
        int diasAcopioM = obtenerDiasAcopioM(codigoProveedor);
        int diasAcopioT = obtenerDiasAcopioT(codigoProveedor);

        if (diasAcopioMT > 10) {
            return 0.2 * monto;
        } else if (diasAcopioM > 10) {
            return 0.12 * monto;
        } else if (diasAcopioT > 10) {
            return 0.08 * monto;
        } else {
            return 0.0;
        }
    }

    public Double pagoAcopioLeche(String codigoProveedor) {
        Double pago = generarPagoAcopioLecheSinBonificacion(codigoProveedor);
        Double bonificacion = bonificacionXfrecuencia(codigoProveedor);
        Double pagoAcopio = pago + bonificacion;
        return pagoAcopio;

    }

    //metodo que OBTIENE el descuento por la variacion negativa KLS Leche
    public Double descuentoVariacionNegativaKlsLeche(String codigoProveedor) {
        Double variacionKlsLeche = obtenerVariacionLeche(codigoProveedor);
        Double dsctopagoAcopioDeLeche = pagoAcopioLeche(codigoProveedor);

        if(variacionKlsLeche <= -0.46){
            return 0.3 * dsctopagoAcopioDeLeche;
        }else if(variacionKlsLeche <= -0.26){
            return 0.15 * dsctopagoAcopioDeLeche;
        }else if(variacionKlsLeche <= -0.09) {
            return 0.07 * dsctopagoAcopioDeLeche;
        }else{
            return 0.0 * dsctopagoAcopioDeLeche;
        }
    }

    //metodo que OBTIENE el descuento por la variacion negativa de Grasa
    public Double descuentoVariacionNegativaGrasa(String codigoProveedor) {
        Double variacionGrasa = obtenerVariacionGrasa(codigoProveedor);
        Double dsctopagoAcopioDeLeche = pagoAcopioLeche(codigoProveedor);

        if(variacionGrasa <= -0.46){
            return 0.3 * dsctopagoAcopioDeLeche;
        }else if(variacionGrasa <= -0.26){
            return 0.15 * dsctopagoAcopioDeLeche;
        }else if(variacionGrasa <= -0.09) {
            return 0.07 * dsctopagoAcopioDeLeche;
        }else{
            return 0.0 * dsctopagoAcopioDeLeche;
        }
    }

    //metodo que OBTIENE el descuento por la variacion negativa de Solidos Totales
    public Double descuentoVariacionSolidosTotales(String codigoProveedor) {
        Double variacionSolidos = obtenerVariacionSolidos(codigoProveedor);
        Double dsctopagoAcopioDeLeche = pagoAcopioLeche(codigoProveedor);

        if(variacionSolidos <= -0.46){
            return 0.3 * dsctopagoAcopioDeLeche;
        }else if(variacionSolidos <= -0.26){
            return 0.15 * dsctopagoAcopioDeLeche;
        }else if(variacionSolidos <= -0.09) {
            return 0.07 * dsctopagoAcopioDeLeche;
        }else{
            return 0.0 * dsctopagoAcopioDeLeche;
        }
    }

    //descuntos totales por variacion negativa
    public Double obtenerDescuentos(String codigoProveedor) {
        Double descuentoVariacionKlsLeche = descuentoVariacionNegativaKlsLeche(codigoProveedor);
        Double descuentoVariacionGrasa = descuentoVariacionNegativaGrasa(codigoProveedor);
        Double descuentoVariacionSolidos = descuentoVariacionSolidosTotales(codigoProveedor);
        Double descuentos = descuentoVariacionKlsLeche + descuentoVariacionGrasa + descuentoVariacionSolidos;
        return descuentos;
    }

    //obtiene Si o No (tiene retencion) de la tabla proveedor
    public String obtenerRetencionString(String codigoProveedor) {
        ProveedorEntity proveedor = proveedorRepository.findByCodigo(codigoProveedor);
            return proveedor.getRetencion();
    }

    //Pago total = pagoAcopio - descuentos
    public Double obtenerPagoTotal(String codigoProveedor) {
        Double pagoAcopio = pagoAcopioLeche(codigoProveedor);
        Double descuentos = obtenerDescuentos(codigoProveedor);
        Double pagoTotal = pagoAcopio - descuentos;
        return pagoTotal;
    }

    //obtiene el monto de la retencion
    public double obtenerMontoRetencion(String codigoProveedor) {
        String tieneRetencion = obtenerRetencionString(codigoProveedor);
        double montoRetencion = obtenerPagoTotal(codigoProveedor);

        if (tieneRetencion.equals("Si")) {
            if (montoRetencion > 950000) {
                return montoRetencion * 0.13;
            } else {
                return montoRetencion * 0.0;
            }
        } else {
            return montoRetencion * 0.0;
        }
    }

    //obtiene el pago final
    public double pagoFinal(String codigoProveedor){
        double pagoTotal = obtenerPagoTotal(codigoProveedor);
        double retencion = obtenerMontoRetencion(codigoProveedor);
        double pagoFinal = pagoTotal - retencion;
        return pagoFinal;
    }


    public PlanillaEntity crearPlanilla(String codigoProveedor){
        String quincena = obtenerQuincena(codigoProveedor);
        //ahora viene codigo de proveedor
        String nombreProveedor = obtenerNombreProveedor(codigoProveedor);
        Double totalKlsLeche = obtenerKilosPorProveedor(codigoProveedor);
        Integer nroDiasQueEnvioLeche = obtenerNroDiasQueEnvioLeche(codigoProveedor);
        Double promedioDiarioKlsLeche = promedioDiarioKilosLeche(codigoProveedor);
        Double variacionKlsLeche = obtenerVariacionLeche(codigoProveedor);
        Double porcentajeGrasa = obtenerPorcentajeGrasaLaboratorio(codigoProveedor);
        Double variacionGrasa = obtenerVariacionGrasa(codigoProveedor);
        Double porcentajeSolidosTotales = obtenerPorcentajeSolidosTotalesLaboratorio(codigoProveedor);
        Double variacionSolidos = obtenerVariacionSolidos(codigoProveedor);
        Double pagoKlsLeche = calcularPagoLecheProveedor(codigoProveedor);
        Double pagoGrasa = calcularPagoPorPorcentajeGrasa(codigoProveedor);
        Double pagoSolidosTotales = calcularPagoPorPorcentajeSolidosTotales(codigoProveedor);
        Double bonificacionXfrecuencia = bonificacionXfrecuencia(codigoProveedor);
        Double desctoVariacionKlsLeche = descuentoVariacionNegativaKlsLeche(codigoProveedor);
        Double desctoVariacionGrasa = descuentoVariacionNegativaGrasa(codigoProveedor);
        Double desctoVariacionSolidosTotales = descuentoVariacionSolidosTotales(codigoProveedor);
        Double pagoTotal = obtenerPagoTotal(codigoProveedor);
        Double montoRetencion = obtenerMontoRetencion(codigoProveedor);
        Double montoFinal = pagoFinal(codigoProveedor);

        PlanillaEntity nuevaPlanilla = new PlanillaEntity();
        nuevaPlanilla.setQuincena(quincena);
        nuevaPlanilla.setCodigoProveedor(codigoProveedor);
        nuevaPlanilla.setNombreProveedor(nombreProveedor);
        nuevaPlanilla.setTotalKlsLeche(totalKlsLeche);
        nuevaPlanilla.setNroDiasEnvioLeche(nroDiasQueEnvioLeche);
        nuevaPlanilla.setPromedioDiarioKlsLeche(promedioDiarioKlsLeche);
        nuevaPlanilla.setPorcentajeVariacionLeche(variacionKlsLeche);
        nuevaPlanilla.setPorcentajeGrasa(porcentajeGrasa);
        nuevaPlanilla.setPorcentajeVariacionGrasa(variacionGrasa);
        nuevaPlanilla.setPorcentajeSolidosTotales(porcentajeSolidosTotales);
        nuevaPlanilla.setPorcentajeVariacionSolidosTotales(variacionSolidos);
        nuevaPlanilla.setPagoLeche(pagoKlsLeche);
        nuevaPlanilla.setPagoGrasa(pagoGrasa);
        nuevaPlanilla.setPagoSolidosTotales(pagoSolidosTotales);
        nuevaPlanilla.setBonificacionFrecuencia(bonificacionXfrecuencia);
        nuevaPlanilla.setDsctoVariacionLeche(desctoVariacionKlsLeche);
        nuevaPlanilla.setDsctoVariacionGrasa(desctoVariacionGrasa);
        nuevaPlanilla.setDsctoVariacionSolidosTotales(desctoVariacionSolidosTotales);
        nuevaPlanilla.setPagoTotal(pagoTotal);
        nuevaPlanilla.setMontoRetencion(montoRetencion);
        nuevaPlanilla.setMontoFinal(montoFinal);

        return planillaRepository.save(nuevaPlanilla);
    }

}
