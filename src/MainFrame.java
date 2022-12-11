import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


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
    final private Font mainFont = new Font("Segoe print", Font.BOLD, 14);
    JTextField tfTipoDoc, tfSaf, tfAno, tfCaja, tfUsuario, tfF_Ini,tfF_Fin, tfCan_im, tfCan_car;
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

        JLabel lbf_Ini = new JLabel("Fecha de Inicio");
        lbf_Ini.setFont(mainFont);

        tfF_Ini = new JTextField();
        tfF_Ini.setFont(mainFont);

        JLabel lbf_Fin = new JLabel("Fecha finalización");
        lbf_Fin.setFont(mainFont);

        tfF_Fin = new JTextField();
        tfF_Fin.setFont(mainFont);



        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(0, 4, 5, 5));
        formPanel.add(lbUsuario);
        formPanel.add(tfUsuario);
        formPanel.add(lbTipoDoc);
        formPanel.add(tfTipoDoc);
        formPanel.add(lbSaf);
        formPanel.add(tfSaf);
        formPanel.add(lbAno);
        formPanel.add(tfAno);
        formPanel.add(lbCaja);
        formPanel.add(tfCaja);
        formPanel.add(lbf_Ini);
        formPanel.add(tfF_Ini);
        formPanel.add(lbf_Fin);
        formPanel.add(tfF_Fin);       
        

        JLabel lbWelcome = new JLabel();
        lbWelcome.setFont(mainFont);



        JButton btnClear = new JButton("Clear");
        btnClear.setFont(mainFont);
        btnClear.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                tfUsuario.setText("");
                lbWelcome.setText("");
            }                 
        });

        JButton btnPDF = new JButton("PDF");
        btnPDF.setFont(mainFont);
        btnPDF.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser fs = new JFileChooser(new File("c:\\"));
                fs.setDialogTitle("Guardar planilla en");
                int result = fs.showSaveDialog(null);
                if (result == JFileChooser.APPROVE_OPTION){
                    File file = fs.getSelectedFile();
                    file.getPath();
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
                        String caja = tfCaja.getText();
                        String usuario = tfUsuario.getText();
                        String fecIni = tfF_Ini.getText();
                        String fecFin = tfF_Fin.getText();
                        
                        
                        contenido.drawImage(miImg, 0,0,565,792);
                                                        
                        contenido.beginText();
                        contenido.setFont(PDType1Font.COURIER_BOLD, 14);
                        contenido.newLineAtOffset(170,671);
                        contenido.showText(tipDoc);
                        contenido.endText();

                        contenido.beginText();
                        contenido.setFont(PDType1Font.COURIER_BOLD, 14);    
                        contenido.newLineAtOffset(270,671);
                        contenido.showText(saf);
                        contenido.endText();

                        contenido.beginText();
                        contenido.setFont(PDType1Font.COURIER_BOLD, 14);
                        contenido.newLineAtOffset(350, 671);
                        contenido.showText(ano);
                        contenido.endText();

                        contenido.beginText();
                        contenido.setFont(PDType1Font.COURIER_BOLD, 14);
                        contenido.newLineAtOffset(452, 671);
                        contenido.showText(caja);
                        contenido.endText();

                        contenido.beginText();
                        contenido.setFont(PDType1Font.COURIER_BOLD, 14);
                        contenido.newLineAtOffset(166,645); 
                        contenido.showText(usuario);                                
                        contenido.endText();

                        contenido.beginText();
                        contenido.setFont(PDType1Font.COURIER_BOLD, 11);
                        contenido.newLineAtOffset(294,645); 
                        contenido.showText(fecIni);                                
                        contenido.endText();

                        contenido.beginText();
                        contenido.setFont(PDType1Font.COURIER_BOLD, 11);
                        contenido.newLineAtOffset(435,645); 
                        contenido.showText(fecFin);                                
                        contenido.endText();
                                                   
                        contenido.close();
                        miPDF.save(file + ".pdf");   
                        
                                                    
                                        
                    System.out.println("Sample file saved at : " + file.getAbsolutePath());                                   
                }
                    catch(Exception x){
                    System.out.println("Error: "+x.getMessage().toString());
                }

                
            }
        }
        });

       
        

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 2, 5, 5));
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