import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;





public class MainFrame extends JFrame {
    final private Font mainFont = new Font("Segoe print", Font.BOLD, 14);
    JTextField tfTipoDoc, tfSaf, tfAno, tfCaja, tfUsuario, tfLastName, tfF_Ini,tfF_Fin, tfCan_im, tfCan_car;
    public void initialize(){

        JLabel lbTipoDoc = new JLabel("Tipo Doc");
        lbTipoDoc.setFont(mainFont);

        tfTipoDoc = new JTextField();
        tfTipoDoc.setFont(mainFont);

        JLabel lbSaf = new JLabel("SAF");
        lbSaf.setFont(mainFont);

        tfSaf = new JTextField();
        tfSaf.setFont(mainFont);

        JLabel lbAno = new JLabel("Año");
        lbAno.setFont(mainFont);

        tfAno = new JTextField();
        tfAno.setFont(mainFont);

        JLabel lbCaja = new JLabel("N° de Caja");
        lbCaja.setFont(mainFont);

        tfCaja = new JTextField();
        tfCaja.setFont(mainFont);

        JLabel lbUsuario = new JLabel("Usuario");
        lbUsuario.setFont(mainFont);

        tfUsuario = new JTextField();
        tfUsuario.setFont(mainFont);

        JLabel lbLastName = new JLabel("Last Name");
        lbLastName.setFont(mainFont);

        tfLastName = new JTextField();
        tfLastName.setFont(mainFont);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(0, 4, 5, 5));
        formPanel.add(lbUsuario);
        formPanel.add(tfUsuario);
        formPanel.add(lbTipoDoc);
        formPanel.add(tfTipoDoc);
        formPanel.add(lbSaf);
        formPanel.add(tfSaf);
        formPanel.add(lbLastName);
        formPanel.add(tfLastName);
        formPanel.add(lbAno);
        formPanel.add(tfAno);
        formPanel.add(lbCaja);
        formPanel.add(tfCaja);
       
        

        JLabel lbWelcome = new JLabel();
        lbWelcome.setFont(mainFont);

        JButton btnOK = new JButton("OK");
        btnOK.setFont(mainFont);
        btnOK.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String firstName = tfUsuario.getText();
                String lastName = tfLastName.getText();
                lbWelcome.setText("Hello " + firstName + " " + lastName);
            }
            
        });

        JButton btnClear = new JButton("Clear");
        btnClear.setFont(mainFont);
        btnClear.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                tfUsuario.setText("");
                tfLastName.setText("");
                lbWelcome.setText("");
            }                 
        });

        JButton btnPDF = new JButton("PDF");
        btnPDF.setFont(mainFont);
        btnPDF.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                try{
                    PDDocument miPDF = new PDDocument();
                    PDPage pagina = new PDPage(PDRectangle.A4 );
                    miPDF.addPage(pagina);

                    PDImageXObject miImg = PDImageXObject.createFromFile("\\Users\\Usuario\\Documents\\JavaProjects\\Visor lib27\\imagenes\\PG.jpg", miPDF);
                                        
                    PDPageContentStream contenido = new PDPageContentStream(miPDF, pagina);
                    String tipDoc = tfTipoDoc.getText();
                    String saf = tfSaf.getText();
                    String ano = tfAno.getText();
                    String usuario = tfUsuario.getText();
                    
                    contenido.drawImage(miImg, 0,0,565,792);
                                                     
                    contenido.beginText();
                    contenido.setFont(PDType1Font.COURIER_BOLD, 14);
                    contenido.newLineAtOffset(170,pagina.getMediaBox().getHeight()-171);
                    contenido.showText(tipDoc);
                    contenido.endText();

                    contenido.beginText();
                    contenido.setFont(PDType1Font.COURIER_BOLD, 14);    
                    contenido.newLineAtOffset(270,670);
                    contenido.showText(saf);
                    contenido.endText();

                    contenido.beginText();
                    contenido.setFont(PDType1Font.COURIER_BOLD, 14);
                    contenido.newLineAtOffset(350, 670);
                    contenido.showText(ano);
                    contenido.endText();

                    contenido.beginText();
                    contenido.setFont(PDType1Font.COURIER_BOLD, 14);
                    contenido.newLineAtOffset(165,645); 
                    contenido.showText(usuario);                                
                    contenido.endText();
                                                  
                    contenido.close();
                               
                    
                    File file = new File(tfUsuario.getText()+".pdf");
                    System.out.println("Sample file saved at : " + file.getAbsolutePath());
      

                    miPDF.save(file);

                }
                catch(Exception x){
                    System.out.println("Error: "+x.getMessage().toString());
                }

                
            }
            
        });

       
        

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 3, 5, 5));
        buttonsPanel.add(btnOK);
        buttonsPanel.add(btnClear);
        buttonsPanel.add(btnPDF);
   

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(128, 128, 255));
        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(lbWelcome, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        add(mainPanel);
        

        /****seguir video minuto 6:52 *********/

        setTitle("Bienvenido");
        setSize(500, 600);
        setMinimumSize(new Dimension(300, 400));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        MainFrame myFrame = new MainFrame();
        myFrame.initialize();
    }
    
}