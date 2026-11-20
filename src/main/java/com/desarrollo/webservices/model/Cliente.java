package com.desarrollo.webservices.model;

public class Cliente {

    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String fechaNacimiento;
    private String pais;
    private String ciudad;
    private String direccion;
    private double ingresoAnual;
    private int puntajeCredito;
    private boolean tieneDeuda;
    private double montoDeuda;
    private String estado;

    public Cliente(int id, String nombre, String apellido, String email, String telefono,
                   String fechaNacimiento, String pais, String ciudad, String direccion,
                   double ingresoAnual, int puntajeCredito, boolean tieneDeuda,
                   double montoDeuda, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.pais = pais;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.ingresoAnual = ingresoAnual;
        this.puntajeCredito = puntajeCredito;
        this.tieneDeuda = tieneDeuda;
        this.montoDeuda = montoDeuda;
        this.estado = estado;
    }


    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }


    public String getDeudaTexto() {
        return tieneDeuda ? "Sí" : "No";
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public double getIngresoAnual() { return ingresoAnual; }
    public void setIngresoAnual(double ingresoAnual) { this.ingresoAnual = ingresoAnual; }

    public int getPuntajeCredito() { return puntajeCredito; }
    public void setPuntajeCredito(int puntajeCredito) { this.puntajeCredito = puntajeCredito; }

    public boolean isTieneDeuda() { return tieneDeuda; }
    public void setTieneDeuda(boolean tieneDeuda) { this.tieneDeuda = tieneDeuda; }

    public double getMontoDeuda() { return montoDeuda; }
    public void setMontoDeuda(double montoDeuda) { this.montoDeuda = montoDeuda; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}