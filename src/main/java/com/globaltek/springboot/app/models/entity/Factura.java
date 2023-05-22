package com.globaltek.springboot.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "facturas")
public class Factura implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "El valor no puede ser nulo")
	private Integer numeroFactura;
	
	@NotNull
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createAt;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "factura_id")
	private List<ItemFactura> items;
	
	public Factura() {
		this.items = new ArrayList<ItemFactura>();
	}

	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}
	
	@NotEmpty
	private String tipoPago;
	
	@NotNull(message = "El valor no puede ser nulo")
	private Integer documentoCliente;

	@NotEmpty
	@NotNull(message = "El descuento no puede ser nulo")
	private String nombreCliente;

	
	@NotNull(message = "El valor no puede ser nulo")
	private Double subTotal;

	
	@NotNull(message = "El descuento no puede ser nulo")
	@Min(value = 0, message = "El descuento no puede ser menor que 0")
	@Max(value = 100, message = "El descuento no puede ser mayor que 100")
	private Double descuento;

	@NotNull(message = "El valor no puede ser nulo")
	private Double iva;

	@NotNull(message = "El valor no puede ser nulo")
	private Double totalDescuento;

	@NotNull(message = "El valor no puede ser nulo")
	private Double totalImpuesto;

	@NotNull(message = "El valor no puede ser nulo")
	private Double total;
	
	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(Integer numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}

	public Integer getDocumentoCliente() {
		return documentoCliente;
	}

	public void setDocumentoCliente(Integer documentoCliente) {
		this.documentoCliente = documentoCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public Double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}

	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	public Double getIva() {
		return iva;
	}

	public void setIva(Double iva) {
		this.iva = iva;
	}

	public Double getTotalDescuento() {
		return totalDescuento;
	}

	public void setTotalDescuento(Double totalDescuento) {
		this.totalDescuento = totalDescuento;
	}

	public Double getTotalImpuesto() {
		return totalImpuesto;
	}

	public void setTotalImpuesto(Double totalImpuesto) {
		this.totalImpuesto = totalImpuesto;
	}

	public List<ItemFactura> getItems() {
		return items;
	}

	public void setItems(List<ItemFactura> items) {
		this.items = items;
	}
	
	public void addItemFactura(ItemFactura item) {
		this.items.add(item);
	}

	public Double getTotal() {
		Double total=0.0;
		
		int size=items.size();
		for(int i=0;i< size;i++) {
			total +=items.get(i).calcularImporte();
		}
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	
	private static final int  PORCENTAJE=100;
	
    public Double calcularTotalDescuento() {
        return getSubTotal() * (getDescuento() / PORCENTAJE);
    }

   
    public Double calcularTotalImpuesto() {
        return getSubTotal() * (getIva() / PORCENTAJE);
    }

    public Double calcularTotal() {
        return getSubTotal() - calcularTotalDescuento() + calcularTotalImpuesto();
    }
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 1L;

}
