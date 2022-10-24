package hash;

import javax.swing.*; // Buttons etc.
import java.awt.*; // for Layouts
import java.awt.event.*; // for ActionListener
import javax.swing.JOptionPane; // for ShowMessage
import java.awt.Font;
import  java.security.MessageDigest;
import java.math.BigInteger;

public class Hash {
    public static void main(String[] args) {
        DesignGUI designGUI = new DesignGUI();
	designGUI.Make(); 
    }
}

class DesignGUI implements ActionListener{
    Frame MSGframe = new JFrame("");
    JFrame frame_main;
    
    JPanel panel_north;
    JPanel panel_center;
    JPanel panel_south;
    
    JTextArea textArea_hash;
    JScrollPane scroller_textArea_hash;
    
    JTextArea textArea_text;
    JScrollPane scroller_textArea_text;
    ButtonGroup bg; 
    JRadioButton MD2;    
    JRadioButton MD5; 
    JRadioButton SHA_1; 
    JRadioButton SHA_256;
    JRadioButton SHA_384;
    JRadioButton SHA_512;
    
    JPanel panel_buttons;
    JButton button_convert;
    JButton button_clear;
    
    void Make(){
        frame_main = new JFrame("Hash Generator");
	frame_main.setSize(430,300);
	frame_main.setResizable(false);
	//frame_main.setLocationRelativeTo(null);
	frame_main.setLocation(100, 100);
	frame_main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel_north = new JPanel();
	frame_main.getContentPane().add(BorderLayout.NORTH, panel_north);
	panel_north.setBackground(Color.GRAY);

	panel_center = new JPanel();
	frame_main.getContentPane().add(BorderLayout.CENTER, panel_center);
	panel_center.setBackground(Color.WHITE);

	panel_south = new JPanel();
	frame_main.getContentPane().add(BorderLayout.SOUTH, panel_south);
	panel_south.setBackground(Color.GREEN);
    
        textArea_hash = new JTextArea(2, 35);
	panel_north.add(textArea_hash);
	textArea_hash.setBackground(Color.LIGHT_GRAY);
	textArea_hash.setLineWrap(true);
        
	scroller_textArea_hash = new JScrollPane(textArea_hash);
	scroller_textArea_hash.getVerticalScrollBar().setBackground(Color.LIGHT_GRAY);
	scroller_textArea_hash.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	panel_north.add(scroller_textArea_hash);
        
        //	
        textArea_text = new JTextArea(8, 35);
	panel_center.add(textArea_text);
	textArea_text.setBackground(Color.WHITE);
	textArea_text.setLineWrap(true);
        
        scroller_textArea_text = new JScrollPane(textArea_text);
	scroller_textArea_text.getVerticalScrollBar().setBackground(Color.WHITE);
	scroller_textArea_text.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	panel_center.add(scroller_textArea_text);
        
        bg = new ButtonGroup(); 
        MD2 = new JRadioButton("MD2"); bg.add(MD2); panel_center.add(MD2);
        MD5 = new JRadioButton("MD5"); bg.add(MD5); panel_center.add(MD5);
        SHA_1 = new JRadioButton("SHA-1"); bg.add(SHA_1); panel_center.add(SHA_1);
        SHA_256 = new JRadioButton("SHA-256"); bg.add(SHA_256); panel_center.add(SHA_256);
        SHA_384 = new JRadioButton("SHA-384"); bg.add(SHA_384); panel_center.add(SHA_384);
        SHA_512 = new JRadioButton("SHA-512"); bg.add(SHA_512); panel_center.add(SHA_512);
        
        //
        panel_south.setLayout(new FlowLayout());
            
        button_convert= new JButton("CONV");
	panel_south.add(button_convert);
	button_convert.addActionListener(this);
            
        button_clear= new JButton("CLEAR");
	panel_south.add(button_clear);
	button_clear.addActionListener(this);
        
        frame_main.setVisible(true);
        textArea_text.requestFocus();
    }
    
    public void actionPerformed(ActionEvent event) {
        String hash_algo = "";  
	if (event.getSource() == button_convert) {
            HashFnc hashFnc = new HashFnc();
            if (MD2.isSelected()) hash_algo = "MD2";
            if (MD5.isSelected()) hash_algo = "MD5";
            if (SHA_1.isSelected()) hash_algo = "SHA1";
            if (SHA_256.isSelected()) hash_algo = "SHA-256";
            if (SHA_384.isSelected()) hash_algo = "SHA-384";
            if (SHA_512.isSelected()) hash_algo = "SHA-512";
            textArea_hash.setText(hashFnc.Go(textArea_text.getText(), hash_algo));
	}
        if (event.getSource() == button_clear) {
            textArea_hash.setText("");
            textArea_text.setText("");
	}
    }

}

class HashFnc{
    String Go(String input, String hash_algo) {
        String output = "";
        try {
            MessageDigest md = MessageDigest.getInstance(hash_algo);
            md.update(input.getBytes());
            byte[] digest = md.digest();
            output = new BigInteger(1, digest).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
    return output;
    }
}

