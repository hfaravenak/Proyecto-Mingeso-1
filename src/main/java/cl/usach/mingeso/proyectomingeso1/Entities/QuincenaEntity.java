package cl.usach.mingeso.proyectomingeso1.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "quincena")
@NoArgsConstructor
@AllArgsConstructor
public class QuincenaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private  Integer ID;

    private String quincena;

    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "proveedor", referencedColumnName = "codigo")
    private ProveedorEntity proveedor;

    private Integer kilos;

    private Integer diasAcopioMT;

    private Integer diasAcopioM;

    private Integer diasAcopioT;

    private Integer porcentajeGrasa;

    private Integer porcentajeSolidosTotales;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getQuincena() {
        return quincena;
    }

    public void setQuincena(String quincena) {
        this.quincena = quincena;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ProveedorEntity getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedorEntity proveedor) {
        this.proveedor = proveedor;
    }

    public Integer getKilos() {
        return kilos;
    }

    public void setKilos(Integer kilos) {
        this.kilos = kilos;
    }

    public Integer getDiasAcopioMT() {
        return diasAcopioMT;
    }

    public void setDiasAcopioMT(Integer diasAcopioMT) {
        this.diasAcopioMT = diasAcopioMT;
    }

    public Integer getDiasAcopioM() {
        return diasAcopioM;
    }

    public void setDiasAcopioM(Integer diasAcopioM) {
        this.diasAcopioM = diasAcopioM;
    }

    public Integer getDiasAcopioT() {
        return diasAcopioT;
    }

    public void setDiasAcopioT(Integer diasAcopioT) {
        this.diasAcopioT = diasAcopioT;
    }

    public Integer getPorcentajeGrasa() {
        return porcentajeGrasa;
    }

    public void setPorcentajeGrasa(Integer porcentajeGrasa) {
        this.porcentajeGrasa = porcentajeGrasa;
    }

    public Integer getPorcentajeSolidosTotales() {
        return porcentajeSolidosTotales;
    }

    public void setPorcentajeSolidosTotales(Integer porcentajeSolidosTotales) {
        this.porcentajeSolidosTotales = porcentajeSolidosTotales;
    }
}
