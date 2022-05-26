/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpclient;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.net.URL;

/**
 *
 * @author Matheus Oliveira
 */
public class HttpClient extends JFrame {
    // Instantiate elements
    JTextArea textArea = new JTextArea(20, 20);
    GridLayout gridLayout = new GridLayout(1, 2);
    JPanel panel = new JPanel(gridLayout);
    JTextField textField = new JTextField("http://");
    JComboBox<String> comboBox = new JComboBox<>(new String[]{"GET", "POST", "PUT", "DELETE"});
    JButton button = new JButton("Enviar");
    
    HttpClient() {
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Set text area to top 
        textArea.setLineWrap(true);
        add(textArea, BorderLayout.NORTH);
        
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText(acessar(textField.getText(), comboBox.getItemAt(comboBox.getSelectedIndex())));
                setVisible(true);
            }
        });
        
        // Add select box to left of panel
        panel.add(comboBox, BorderLayout.EAST);
        
        // Add text field to center of panel
        panel.add(textField, BorderLayout.CENTER);
        
        // Add button to right of panel
        panel.add(button, BorderLayout.WEST);
        
        // Add panel to bottom
        add(panel, BorderLayout.SOUTH);
        
        // Make visible
        setVisible(true);
    }
    
    public String acessar(String uri, String method) {
       StringBuffer buffer = new StringBuffer();
       
       try {
           URL url = new URL(uri);
           HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
           httpConnection.setRequestMethod(method);
           
           if (method.equals("POST")) {
               httpConnection.setRequestProperty("Accept", "application/xml");
               httpConnection.setRequestProperty("Content-Type", "application/xml");
               httpConnection.setDoOutput(true);
               
               OutputStream outputStream = httpConnection.getOutputStream();
               
               outputStream.write(textArea.getText().trim().getBytes());
               outputStream.close();
               
               BufferedReader bufferReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
               int s;
               
               while ((s = bufferReader.read()) != null) {
                   buffer.append(s);
               }
           }
           
           httpConnection.setReadTimeout(15000);
           httpConnection.connect();
           
           InputStream inputStream = httpConnection.getInputStream();
           int caractere;
           
           while ((caractere = inputStream.read()) != -1) {
               buffer.append((char) caractere);
           }

       } catch (MalformedURLException exception) {
           buffer.append(exception.getMessage());
       } catch (IOException exception) {
           buffer.append(exception.getMessage());
       }
       
       return buffer.toString();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new HttpClient();
    }
}
