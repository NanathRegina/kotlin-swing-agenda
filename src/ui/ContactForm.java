package ui;

import business.ContactBusiness;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactForm extends JFrame {


    private JPanel rootPanel;
    private JLabel lblNome;
    private JTextField txtNome;
    private JLabel lblTelefone;
    private JTextField txtTelefone;
    private JButton buttonSalvar;
    private JButton buttonCancelar;

    private ContactBusiness mcontactBusiness;

    public ContactForm(){
        setContentPane(rootPanel);
        setSize(500,250);
        setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2 );

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        mcontactBusiness = new ContactBusiness();
        setListeners();
    }

    private void setListeners() {
        buttonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainForm();
                dispose();
            }
        });

        buttonSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String name = txtNome.getText();
                    String telefone = txtTelefone.getText();

                    mcontactBusiness.save(name, telefone);

                    new MainForm();
                    dispose();
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
                }

            }
        });
    }
}
