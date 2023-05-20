package cl.usach.mingeso.proyectomingeso1.Services;

import cl.usach.mingeso.proyectomingeso1.Entities.AcopioEntity;
import cl.usach.mingeso.proyectomingeso1.Entities.LaboratorioEntity;
import cl.usach.mingeso.proyectomingeso1.Entities.ProveedorEntity;
import cl.usach.mingeso.proyectomingeso1.Entities.QuincenaEntity;
import cl.usach.mingeso.proyectomingeso1.Repositories.AcopioRepository;
import cl.usach.mingeso.proyectomingeso1.Repositories.LaboratorioRepository;
import cl.usach.mingeso.proyectomingeso1.Repositories.ProveedorRepository;
import cl.usach.mingeso.proyectomingeso1.Repositories.QuincenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class QuincenaService {

    @Autowired
    QuincenaRepository quincenaRepository;

    @Autowired
    AcopioRepository acopioRepository;

    @Autowired
    LaboratorioRepository laboratorioRepository;

    @Autowired
    ProveedorService proveedorService;
    @Autowired
    private ProveedorRepository proveedorRepository;

    public List<QuincenaEntity> obtenerQuincenas(){ return quincenaRepository.findAll(); }

    //a traves del codigo del proveedor, devuelve su objeto ProveedorEntity
    public ProveedorEntity obtenerProveedorPorCodigo(String codigo){
        return proveedorRepository.findByCodigo(codigo);
    }

    //a traves del codigo de un proveedor obtiene los kilos leche de sus acopios
    public List<String> obtenerKilosLechePorProveedor(String codigoProveedor) {
        return acopioRepository.findKlsLecheByProveedor(codigoProveedor);
    }

    //a traves del codigo de un proveedor obtiene el turno de un acopio (M, MT, T)
    public List<String> obtenerTurnoAcopioPorProveedor(String codigoProveedor){
        return acopioRepository.findTurnoByProveedor(codigoProveedor);
    }

    //metodo para convertir un string a date
    public Date convertStringToDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return dateFormat.parse(dateString);
    }

    public List<Date> obtenerFechasAcopio(String codigo) {
        List<AcopioEntity> acopios = acopioRepository.findAllByProveedor(codigo);
        List<Date> fechas = new ArrayList<>();

        for (AcopioEntity acopio : acopios) {
            String fechaString = acopio.getFecha();
            Date fecha = null;

            try {
                fecha = convertStringToDate(fechaString);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            fechas.add(fecha);
        }

        return fechas;
    }

    //metodo que retorna 1 si la fecha es la primera quincena y 2 si es la segunda quincena
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

    //metodo que retorna una lista de enteros con el numero de quincena de cada fecha
    public List<Integer> obtenerNroListaQuincenas(List<Date> fechas) {
        List<Integer> nroQuincenas = new ArrayList<>();

        for (Date fecha : fechas) {
            int numeroQuincena = obtenerNumeroQuincena(fecha);
            nroQuincenas.add(numeroQuincena);
        }

        return nroQuincenas;
    }


    //a traves del codigo de un proveedor obtiene el porcentaje de grasa de un laboratorio
    public List<Double> obtenerPorcentajeGrasaLaboratorio(String codigoProveedor) {
        return laboratorioRepository.findPorcGrasaByProveedor(codigoProveedor);
    }

    //a traves del codigo de un proveedor obtiene el porcentaje de solidos totales de un laboratorio
    public List<Double> obtenerPorcentajeSolidosTotalesLaboratorio(String codigoProveedor) {
        return laboratorioRepository.findPorcSolidosTotalesByProveedor(codigoProveedor);
    }


    //Como obtener la variacion porcentual de los kilos de un proveedor de la quincena actual respecto
    //a la quincena anterior
    public double obtenerVariacionKilos(ProveedorEntity proveedor){
        List<QuincenaEntity> quincenas = quincenaRepository.findQuincenaEntityByProveedorOrderByFechaDesc(proveedor);
        if(quincenas.size() > 1){
            double kilosActual = quincenas.get(0).getKilos();
            double kilosAnterior = quincenas.get(1).getKilos();
            double variacionKilos = (kilosActual - kilosAnterior)/kilosAnterior;
            return variacionKilos;
        }
        else{
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

}
