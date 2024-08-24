/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registro;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Hp
 */
public class colorearCelda extends JTable{

    public colorearCelda() {
        setSelectionBackground(new Color(0, 64, 122));
        setSelectionForeground(new Color(255, 255, 255));
    }

    //JUBILACIÓN
//FALLECIMIENTO
//RETIRO
    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int columnIndex){
        Component componente = super.prepareRenderer(renderer, rowIndex, columnIndex);
        if(getValueAt(rowIndex, columnIndex).toString().equals("  JUBILACIÓN")){
            //componente.setBackground(new Color(153,255,102));
            componente.setForeground(new Color(0, 0, 255));
            componente.setFont(new Font("Verdana", Font.BOLD, 12));
        }else if(getValueAt(rowIndex, columnIndex).toString().equals("  FALLECIMIENTO")){
            componente.setForeground(new Color(255, 0, 0));
            componente.setFont(new Font("Verdana", Font.BOLD, 12));
        }else if(getValueAt(rowIndex, columnIndex).toString().equals("  RETIRO")){
            componente.setForeground(new Color(0, 0, 0));
            componente.setFont(new Font("Verdana", Font.BOLD, 12));
        }else if(getValueAt(rowIndex, columnIndex).toString().equals("  Todos los Valores")){
            componente.setForeground(new Color(0, 153, 153));
            componente.setFont(new Font("Verdana", Font.BOLD, 12));
        }else{
            //componente.setBackground(new Color(255,255,255));
            componente.setForeground(new Color(0,0,0));
        }
        return componente;
    }
}
