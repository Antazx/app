/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz.gestor;

import interfaz.vistas.*;
import javax.swing.JFrame;

/**
 *
 * @author GUillermo
 */
public class GestorInterfaces {
    private static GestorInterfaces gestor;
    private JFrame vistaActual;
    
    public static GestorInterfaces getInstancia() {
        if (gestor==null){
            gestor = new GestorInterfaces();
        }
        return gestor;
    }
    
    public GestorInterfaces(){}
    
    public static void main(String[] args){
        GestorInterfaces.getInstancia().muestraInterfazIdentificarse();
    }

    public void empleadoIdentificado(String rol){
        switch(rol){
            case "Supervisor":
                System.out.println("SUPERVISOR NO IMPLEMENTADO");
                //reemplazarVista(new VistaPrincipalSupervisor());
                break;
            case "Administrativo":
                System.out.println("ADMINISTRATIVO NO IMPLEMENTADO");
                //reemplazarVista(new VistaPrincipalAdministrativo());
                break;
            case "Operario":
                System.out.println("OPERARIO NO IMPLEMENTADO");
                //reemplazarVista(new VistaPrincipalOperario());
                break;
            case "Dependiente":
                System.out.println("DEPENDIENTE NO IMPLEMENTADO");
                //reemplazarVista(new VistaPrincipalDependiente());
                break;
        }
    }
    
    private void reemplazarVista(JFrame nuevaVista){
        vistaActual.dispose();
        vistaActual = nuevaVista;
        vistaActual.setVisible(true);
    }
    
    private void muestraInterfazIdentificarse() {
        vistaActual = new VistaIdentificarse();
        vistaActual.setVisible(true);
    }
    
    private void muestraInterfazConsultarFacturas() {
        vistaActual = new VistaConsultarFacturas();
        vistaActual.setVisible(true);
    }
    
    
}
