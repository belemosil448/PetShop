/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petshop;

/**
 *
 * @author lemos
 */
public class Agendamento {
    private String servicosAgrupados; // Atributo para o agrupamento dos nomes dos serviços
    private int dia;
    private int mes;
    private int hora;

    // Construtor
    public Agendamento(String servicosAgrupados, int dia, int mes, int hora) {
        this.servicosAgrupados = servicosAgrupados;
        this.dia = dia;
        this.mes = mes;
        this.hora = hora;
    }

    // Getters e setters
    public String getServicosAgrupados() {
        return servicosAgrupados;
    }

    public void setServicosAgrupados(String servicosAgrupados) {
        this.servicosAgrupados = servicosAgrupados;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return dia + "/" + mes + " - " + hora + ":00 - " + servicosAgrupados;
    }

}