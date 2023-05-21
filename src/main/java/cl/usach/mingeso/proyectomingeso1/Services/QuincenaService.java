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
import java.util.*;

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
    public double obtenerTotalKilosLechePorProveedor(String codigoProveedor) {
        List<String> kilosLeche = acopioRepository.findKlsLecheByProveedor(codigoProveedor);
        double totalKilos = 0.0;

        for (String kilos : kilosLeche) {
            try {
                double kilosDouble = Double.parseDouble(kilos);
                totalKilos += kilosDouble;
            } catch (NumberFormatException e) {
                // Manejar excepción si el valor no se puede convertir a double
            }
        }

        return totalKilos;
    }

    //metodo para convertir un string a date
    public Date convertStringToDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return dateFormat.parse(dateString);
    }

    //a traves del codigo de un proveedor obtiene la fecha de su primer acopio
    public Date obtenerFechaPorProveedor(String codigoProveedor) {
        List<AcopioEntity> acopios = acopioRepository.findAllByProveedor(codigoProveedor);

        if (!acopios.isEmpty()) {
            AcopioEntity acopio = acopios.get(0); // Obtener el primer acopio del proveedor
            String fechaString = acopio.getFecha();

            try {
                return convertStringToDate(fechaString);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        return null; // Si no se encontraron acopios para el proveedor
    }

    //metodo que retorna 1 si la fecha es la primera quincena y 2 si es la segunda quincena de un mes
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

    //a traves del codigo de un proveedor obtiene el porcentaje TOTAL de grasa de un laboratorio
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

    //a traves del codigo de un proveedor obtiene el porcentaje TOTAL de solidos totales de un laboratorio
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

    //obtiene los dias que de mañana un proveeodor envio leche a un acopio
    public int contarDiasAcopiom(String codigoProveedor) {
        List<String> turnos = acopioRepository.findTurnoByProveedor(codigoProveedor);
        int diasAcopiom = 0;

        for (String turno : turnos) {
            if (turno != null && turno.equalsIgnoreCase("M")) {
                diasAcopiom++;
            }
        }

        return diasAcopiom;
    }

    //obtiene los dias que de tarde un proveeodor envio leche a un acopio
    public int contarDiasAcopiot(String codigoProveedor) {
        List<String> turnos = acopioRepository.findTurnoByProveedor(codigoProveedor);
        int diasAcopiot = 0;

        for (String turno : turnos) {
            if (turno != null && turno.equalsIgnoreCase("T")) {
                diasAcopiot++;
            }
        }

        return diasAcopiot;
    }

    //obtiene los dias que de mañana y tarde un proveedor envio leche a un acopio
    public int contarDiasAcopiomt(String codigoProveedor) {
        List<AcopioEntity> acopios = acopioRepository.findAllByProveedor(codigoProveedor);
        int contadorDiasAcopio = 0;

        Map<String, Set<String>> fechaTurnoMap = new HashMap<>();
        for (AcopioEntity acopio : acopios) {
            String fecha = acopio.getFecha();
            String turno = acopio.getTurno();

            if (fecha != null && turno != null && (turno.equalsIgnoreCase("M") || turno.equalsIgnoreCase("T"))) {
                Set<String> turnos = fechaTurnoMap.getOrDefault(fecha, new HashSet<>());
                turnos.add(turno);
                fechaTurnoMap.put(fecha, turnos);
            }
        }

        for (Set<String> turnos : fechaTurnoMap.values()) {
            if (turnos.contains("M") && turnos.contains("T")) {
                contadorDiasAcopio++;
            }
        }

        return contadorDiasAcopio;
    }

    public QuincenaEntity crearQuincena(String codigoProveedor) {
        // Obtener proveedor
        ProveedorEntity proveedor = obtenerProveedorPorCodigo(codigoProveedor);
        if (proveedor == null) {
            throw new IllegalArgumentException("Proveedor no encontrado");
        }

        // Obtener kilos de leche del proveedor
        double totalKilosLeche = obtenerTotalKilosLechePorProveedor(codigoProveedor);

        // Obtener fecha del primer acopio del proveedor
        Date fechaAcopio = obtenerFechaPorProveedor(codigoProveedor);
        if (fechaAcopio == null) {
            throw new IllegalArgumentException("No se encontraron acopios para el proveedor");
        }

        // Obtener número de quincena
        int numeroQuincena = obtenerNumeroQuincena(fechaAcopio);

        // Obtener porcentaje total de grasa del laboratorio del proveedor
        double totalPorcentajeGrasaLaboratorio = obtenerTotalPorcentajeGrasaLaboratorio(codigoProveedor);

        // Obtener porcentaje total de sólidos totales del laboratorio del proveedor
        double totalPorcentajeSolidosTotalesLaboratorio = obtenerTotalPorcentajeSolidosTotalesLaboratorio(codigoProveedor);

        // Contar días de mañana (M) en los acopios del proveedor
        int diasAcopiom = contarDiasAcopiom(codigoProveedor);

        // Contar días de tarde (T) en los acopios del proveedor
        int diasAcopiot = contarDiasAcopiot(codigoProveedor);

        // Contar días de mañana (M) y tarde (T) en los acopios del proveedor
        int diasAcopiomt = contarDiasAcopiomt(codigoProveedor);

        // Crear nueva quincena
        QuincenaEntity nuevaQuincena = new QuincenaEntity();
        nuevaQuincena.setProveedor(proveedor);
        nuevaQuincena.setKilos(totalKilosLeche);
        nuevaQuincena.setFecha(fechaAcopio);
        nuevaQuincena.setQuincena(numeroQuincena);
        nuevaQuincena.setPorcentajeGrasa(totalPorcentajeGrasaLaboratorio);
        nuevaQuincena.setPorcentajeSolidosTotales(totalPorcentajeSolidosTotalesLaboratorio);
        nuevaQuincena.setDiasAcopioM(diasAcopiom);
        nuevaQuincena.setDiasAcopioT(diasAcopiot);
        nuevaQuincena.setDiasAcopioMT(diasAcopiomt);

        return quincenaRepository.save(nuevaQuincena);
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
