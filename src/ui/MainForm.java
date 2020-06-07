package ui;

import business.ContactBusiness;
import entity.ContactEntity;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainForm extends JFrame{
    private JPanel rootPanel;
    private JButton buttonAdicionar;
    private JButton buttonRemover;
    private JTable tableContatos;
    private JLabel labelContatos;
    private String mnome = "";
    private String mtelefone ="";

    private ContactBusiness mcontactBusiness;

    public MainForm(){
        setContentPane(rootPanel);
        setSize(500,250);
        setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2 );

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        mcontactBusiness = new ContactBusiness();
        setListeners();
        loadContacts();
    }

    private void loadContacts(){
        List<ContactEntity> contactList = mcontactBusiness.getList();

        String[] columnNames = {"Nome", "Telefone"};
        DefaultTableModel model = new DefaultTableModel(new Object[0][0], columnNames);

        for(ContactEntity i: contactList){
            Object[] o = new Object[2];
            o[0] = i.getName();
            o[1] = i.getTelefone();

            model.addRow(o);
        }
        tableContatos.clearSelection();
        tableContatos.setModel(model);

        labelContatos.setText(mcontactBusiness.getContactCountDescription());
    }

    private void setListeners(){
        buttonAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ContactForm();
                dispose();
            }
        });
        tableContatos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(e.getValueIsAdjusting()){
                    if(tableContatos.getSelectedRow() != -1) {
                        mnome = tableContatos.getValueAt(tableContatos.getSelectedRow(), 0).toString();
                        mtelefone = tableContatos.getValueAt(tableContatos.getSelectedRow(), 1).toString();
                    }
                }
            }
        });
        buttonRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mcontactBusiness.delete(mnome, mtelefone);
                    loadContacts();
                    mnome = "";
                    mtelefone = "";
                }
                catch (Exception ex){
                    JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
                }
            }
        });
    }
}
