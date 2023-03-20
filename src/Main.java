import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Main extends JFrame {
    public Main() {
        initComponents();
    }
    public void initComponents() {
        this.setTitle("przeszukiawcz");

        this.setBounds(300,300,300,200);

        panelSzukania.add(znajdz);
        panelSzukania.add(szukany);
        panelSzukania.add(etykietaI);
        panelSzukania.add(zamien);
        panelSzukania.add(etykietaNa);
        panelSzukania.add(nowyTekst);


//        obszarTekstowy.selectAll();
//        obszarTekstowy.select(0,7);
//        obszarTekstowy.replaceSelection("siema");
//        obszarTekstowy.replaceRange("siema", 0, 5);
//        obszarTekstowy.insert("siema ", 0);
//        obszarTekstowy.append(" Siema");
//        obszarTekstowy.select(obszarTekstowy.getText().indexOf("tekst"),
//        obszarTekstowy.getText().indexOf("tekst")+"tekst".length());

        znajdz.addActionListener(new znajdowanieHandler());
        zamien.addActionListener(new zamienianieHandler());
        this.getContentPane().add(obszarPrzewijania, BorderLayout.NORTH);
        this.getContentPane().add(panelSzukania, BorderLayout.CENTER);

        this.setDefaultCloseOperation(3);
    }
    private class znajdowanieHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            poczatekSzukanego = obszarTekstowy.getText().indexOf(szukany.getText(),
                    poczatekSzukanego + szukany.getText().length());
            System.out.println(poczatekSzukanego);
            if (poczatekSzukanego == -1){
                poczatekSzukanego = obszarTekstowy.getText().indexOf(szukany.getText());
            }
            if (poczatekSzukanego >= 0 && szukany.getText().length() > 0) {
                obszarTekstowy.requestFocus();
                obszarTekstowy.select(poczatekSzukanego, poczatekSzukanego + szukany.getText().length());
            }
        }
        private int poczatekSzukanego = 0;
    }

    private class zamienianieHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (obszarTekstowy.getSelectionStart() != obszarTekstowy.getSelectionEnd()){
                zamienTekst();
            } else {
                znajdz.doClick();
                if (obszarTekstowy.getSelectionStart() != obszarTekstowy.getSelectionEnd())
                      zamienTekst();
            }
        }
        private void zamienTekst() {
            obszarTekstowy.requestFocus();
            int opcja = JOptionPane.showConfirmDialog(rootPane, "Czy chcesz zamienić\""
                    + szukany.getText()+"\"na + \""+nowyTekst.getText()+"\"",
                    "Uwaga, nastąpi zmiana!", JOptionPane.YES_NO_OPTION);
            if (opcja == 1){
            obszarTekstowy.replaceRange(nowyTekst.getText(), obszarTekstowy.getSelectionStart(), obszarTekstowy.getSelectionEnd());
            }
        }
    }

    private JTextArea obszarTekstowy = new JTextArea("testowy tekst o testowym charakterze :)",7,10);
    private JScrollPane obszarPrzewijania = new JScrollPane(obszarTekstowy);

    private JPanel panelSzukania = new JPanel();
    private JButton znajdz = new JButton("znajdź");
    private JLabel etykietaI = new JLabel("i");
    private JButton zamien = new JButton("zamień");
    private JLabel etykietaNa = new JLabel("na");
    private JTextField szukany = new JTextField(4);
    private JTextField nowyTekst = new JTextField(4);



    public static void main(String[] args) {
        new Main().setVisible(true);
    }
}