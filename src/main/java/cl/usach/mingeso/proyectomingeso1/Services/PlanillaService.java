package cl.usach.mingeso.proyectomingeso1.Services;

import cl.usach.mingeso.proyectomingeso1.Entities.PlanillaEntity;
import cl.usach.mingeso.proyectomingeso1.Entities.ProveedorEntity;
import cl.usach.mingeso.proyectomingeso1.Entities.QuincenaEntity;
import cl.usach.mingeso.proyectomingeso1.Repositories.*;
import cl.usach.mingeso.proyectomingeso1.Services.QuincenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    public String obtenerFechaMasReciente(String codigoProveedor) {
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

    public Double obtenerSumaKilosPorProveedor(String codigoProveedor) {
        List<QuincenaEntity> quincenas = quincenaRepository.findAllByProveedorOrderByFechaDesc(codigoProveedor);

        double sumaKilos = 0.0;

        for (QuincenaEntity quincena : quincenas) {
            sumaKilos += quincena.getKilos();
        }

        return sumaKilos;
    }

    public int obtenerNroDiasQueEnvioLeche(String codigoProveedor) {
        return acopioRepository.countByProveedor(codigoProveedor);
    }

    public Double promedioDiarioKilosLeche(String codigoProveedor) {
        List<QuincenaEntity> quincenas = quincenaRepository.findAllByProveedorOrderByFechaDesc(codigoProveedor);

        double promedioKlsLecheDiario = 0.0;

        for (QuincenaEntity quincena : quincenas) {
            promedioKlsLecheDiario += quincena.getKilos();
            promedioKlsLecheDiario = promedioKlsLecheDiario / 15;
        }

        return promedioKlsLecheDiario;
    }

    //Como obtener la variacion porcentual de los kilos de un proveedor de la quincena actual respecto
    //a la quincena anterior
    public double obtenerVariacionKilos(ProveedorEntity proveedor) {
        List<QuincenaEntity> quincenas = quincenaRepository.findQuincenaEntityByProveedorOrderByFechaDesc(proveedor);

        if (quincenas.size() > 1) {
            double kilosActual = quincenas.get(0).getKilos();
            double kilosAnterior = quincenas.get(1).getKilos();
            double variacionKilos = (kilosActual - kilosAnterior) / kilosAnterior;
            return variacionKilos;
        } else {
            return 0;
        }
    }

    //Como obtener la variacion porcentual de grasa de un proveedor de la quincena actual respecto
    //a la quincena anterior
    public double obtenerVariacionGrasa(ProveedorEntity proveedor){
        List<QuincenaEntity> quincenas = quincenaRepository.findQuincenaEntityByProveedorOrderByFechaDesc(proveedor);
        if(quincenas.size() > 1){
            double grasaActual = quincenas.get(0).getPorcentajeGrasa();
            double grasaAnterior = quincenas.get(1).getPorcentajeGrasa();
            double variacionGrasa = (grasaActual - grasaAnterior)/grasaAnterior;
            return variacionGrasa;
        }
        else{
            return 0;
        }
    }

    //Como obtener la variacion porcentual de solidos totales de un proveedor de la quincena actual respecto
    //a la quincena anterior
    public double obtenerVariacionSolidos(ProveedorEntity proveedor){
        List<QuincenaEntity> quincenas = quincenaRepository.findQuincenaEntityByProveedorOrderByFechaDesc(proveedor);
        if(quincenas.size() > 1){
            double solidosActual = quincenas.get(0).getPorcentajeSolidosTotales();
            double solidosAnterior = quincenas.get(1).getPorcentajeSolidosTotales();
            double variacionSolidos = (solidosActual - solidosAnterior)/solidosAnterior;
            return variacionSolidos;
        }
        else{
            return 0;
        }
    }

    //a traves del codigo de un proveedor obtiene el porcentaje de grasa de un laboratorio
    public double obtenerTotalPorcentajeGrasaLaboratorio(String codigoProveedor) {
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
    public double obtenerTotalPorcentajeSolidosTotalesLaboratorio(String codigoProveedor) {
        List<Double> porcentajesSolidosTotales = laboratorioRepository.findPorcSolidosTotalesByProveedor(codigoProveedor);
        double totalPorcentajeSolidosTotales = 0.0;

        for (Double porcentaje : porcentajesSolidosTotales) {
            if (porcentaje != null) {
                totalPorcentajeSolidosTotales += porcentaje;
            }
        }

        return totalPorcentajeSolidosTotales;
    }

}
