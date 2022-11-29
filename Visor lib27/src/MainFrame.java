import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JFileChooser;
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
    final private Font mainFont = new Font("Segoe print", Font.BOLD, 18);
    JTextField tfTipoDoc ,tfFirstName, tfLastName;
    public void initialize(){

        JLabel lbTipoDoc = new JLabel("Tipo Doc");
        lbTipoDoc.setFont(mainFont);

        tfTipoDoc = new JTextField();
        tfTipoDoc.setFont(mainFont);

        JLabel lbFirstName = new JLabel("First Name");
        lbFirstName.setFont(mainFont);

        tfFirstName = new JTextField();
        tfFirstName.setFont(mainFont);

        JLabel lbLastName = new JLabel("Last Name");
        lbLastName.setFont(mainFont);

        tfLastName = new JTextField();
        tfLastName.setFont(mainFont);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 1, 5, 5));
        formPanel.add(lbTipoDoc);
        formPanel.add(tfTipoDoc);
        formPanel.add(lbFirstName);
        formPanel.add(tfFirstName);
        formPanel.add(lbLastName);
        formPanel.add(tfLastName);
        

        JLabel lbWelcome = new JLabel();
        lbWelcome.setFont(mainFont);

        JButton btnOK = new JButton("OK");
        btnOK.setFont(mainFont);
        btnOK.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String firstName = tfFirstName.getText();
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
                tfFirstName.setText("");
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
                    String nombre = tfFirstName.getText();
                    
                    contenido.drawImage(miImg, 0,0,565,792);
                                                     
                    contenido.beginText();
                    contenido.setFont(PDType1Font.COURIER_BOLD, 16);
                    contenido.newLineAtOffset(170,pagina.getMediaBox().getHeight()-170);
                    contenido.showText(tipDoc);
                    contenido.newLineAtOffset(0, -30); 
                    contenido.showText(nombre);                
                
                    contenido.endText();
                                                  
                    contenido.close();
                               
                    
                    File file = new File(tfFirstName.getText()+".pdf");
                    System.out.println("Sample file saved at : " + file.getAbsolutePath());
      

                    miPDF.save(file);

                }
                catch(Exception x){
                    System.out.println("Error: "+x.getMessage().toString());
                }

                
            }
            
        });

       
        

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 4, 5, 5));
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