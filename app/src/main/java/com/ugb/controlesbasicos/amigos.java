package com.ugb.controlesbasicos;

public class amigos {
    String idproductos;
    String codigo;
    String descripcion;
    String marca;
    String presentacion;
    String precio;
    String urlFotoProdu;

    public amigos(String idproductos, String codigo, String descripcion, String marca, String presentacion, String precio, String urlFoto) {
        this.idproductos = idproductos;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.marca = marca;
        this.presentacion = presentacion;
        this.precio = precio;
        this.urlFotoProdu = urlFoto;
    }

    public String getUrlFotoProdu() {
        return urlFotoProdu;
    }

    public void setUrlFotoProdu(String urlFotoProdu) {
        this.urlFotoProdu = urlFotoProdu;
    }

    public String getIdproductos() {
        return idproductos;
    }

    public void setIdproductos(String idproductos) {
        this.idproductos = idproductos;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}