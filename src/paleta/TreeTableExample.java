/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paleta;

/**
 *
 * @author vacev
 */
import java.util.ArrayList;
import javax.swing.*;
import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;

public class TreeTableExample extends JFrame {
    
    public TreeTableExample() {
        setTitle("JTreeTable Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // Crear el modelo del árbol
        DefaultMutableTreeTableNode root = new DefaultMutableTreeTableNode("Root");
        DefaultMutableTreeTableNode node1 = new DefaultMutableTreeTableNode("Node 1");
        DefaultMutableTreeTableNode node2 = new DefaultMutableTreeTableNode("Node 2");
        DefaultMutableTreeTableNode node3 = new DefaultMutableTreeTableNode("Node 3");
        root.add(node1);
        root.add(node2);
        root.add(node3);

        // Crear el árbol de modelo de tabla
        DefaultTreeTableModel treeTableModel = new DefaultTreeTableModel(root);
        ArrayList<String> lista = new ArrayList<>();
        lista.add("Colum1");
        lista.add("Colum2");
        treeTableModel.setColumnIdentifiers(lista);

        // Crear el árbol de la tabla
        JXTreeTable treeTable = new JXTreeTable(treeTableModel);

        getContentPane().add(new JScrollPane(treeTable));

        setVisible(true);
    }
}
