package fr.poulet4j.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import fr.poulet4j.gui.saveturn.BackgroundBuilderSaveTurn;
import fr.poulet4j.gui.saveturn.IaAndItemBuilderSaveTurn;
import fr.poulet4j.gui.saveturn.InfosIaBuilderSaveTurn;
import fr.poulet4j.gui.saveturn.SaveLoader;
import fr.poulet4j.save.SaveTurn;

/**
 * Interface d'analyse.
 */
public class Gui<T> extends JFrame {

    private static final String N_DU_TOUR = "N° du tour : ";

    private static final long serialVersionUID = 1L;

    private JFileChooser fc = new JFileChooser();
    private GuiControler<T> guiControler;

    protected JSlider slider;

    protected JLabel lblNDuTour;

    protected MapUI mapUI;

    protected JTextPane iaInfo1;

    protected JTextPane iaInfo2;
    protected JTextPane pouletInfo;

    protected JPanel controlers;

    public static Gui<SaveTurn> create() {
        Gui<SaveTurn> gui = new Gui<SaveTurn>(new SaveLoader(), new InfosIaBuilderSaveTurn());
        gui.add(new BackgroundBuilderSaveTurn());
        gui.add(new IaAndItemBuilderSaveTurn());
        return gui;
    }

    public Gui(GameReload<T> gameReload, InfosIaBuilder<T> iaBuilder) {
        super();
        guiControler = new GuiControler<T>(this, gameReload, iaBuilder);
        init();
    }

    public void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setPreferredSize(new Dimension(MapUI.MAP_SIZE + 200 + 200 + 10, MapUI.MAP_SIZE + 100));
        getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel bottom = new JPanel();
        getContentPane().add(bottom, BorderLayout.SOUTH);
        bottom.setLayout(new BorderLayout(0, 0));

        slider = new JSlider();
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(final ChangeEvent e) {
                if (lblNDuTour != null && slider != null) {
                    lblNDuTour.setText(N_DU_TOUR + slider.getValue());
                    guiControler.changeTurn(slider.getValue());
                }
            }
        });
        slider.setValue(0);
        bottom.add(slider);

        lblNDuTour = new JLabel(N_DU_TOUR + 0);
        bottom.add(lblNDuTour, BorderLayout.EAST);

        JPanel top = new JPanel();
        getContentPane().add(top, BorderLayout.NORTH);

        JButton btnChoose = new JButton("Choose");
        btnChoose.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                fc.setCurrentDirectory(new File("save/"));
                int returnVal = fc.showOpenDialog(Gui.this);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    guiControler.loadFile(file);
                }
            }
        });
        top.add(btnChoose);

        mapUI = new MapUI();
        getContentPane().add(mapUI, BorderLayout.CENTER);

        JPanel iaInfoPanel = new JPanel();
        iaInfoPanel.setPreferredSize(new Dimension(200, MapUI.MAP_SIZE + 100));
        getContentPane().add(iaInfoPanel, BorderLayout.WEST);
        iaInfoPanel.setLayout(new BoxLayout(iaInfoPanel, BoxLayout.Y_AXIS));

        iaInfo1 = new JTextPane();
        iaInfo1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        iaInfoPanel.add(iaInfo1);

        iaInfo2 = new JTextPane();
        iaInfo2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        iaInfoPanel.add(iaInfo2);

        pouletInfo = new JTextPane();
        pouletInfo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        iaInfoPanel.add(pouletInfo);

        controlers = new JPanel();
        controlers.setMinimumSize(new Dimension(200, 200));
        controlers.setMaximumSize(new Dimension(200, 2000));
        getContentPane().add(controlers, BorderLayout.EAST);
        LayoutManager layout = new BoxLayout(controlers, BoxLayout.Y_AXIS);
        controlers.setLayout(layout);

        setVisible(true);
        pack();
    }

    public Gui<T> add(TurnImageBuilder<T> turnBuilder) {
        guiControler.add(turnBuilder);
        return this;
    }
}
