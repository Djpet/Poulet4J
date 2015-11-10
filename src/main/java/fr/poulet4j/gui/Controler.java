package fr.poulet4j.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Controler extends JPanel {

    private GuiControler<?> guiControler;

    private TurnImageBuilder<?> turnImageBuilder;

    private JCheckBox showCheckBox;

    public Controler(TurnImageBuilder<?> turnImageBuilder) {
        this.turnImageBuilder = turnImageBuilder;
        setLayout(new BorderLayout(0, 0));
        setBorder(new LineBorder(Color.DARK_GRAY));

        JPanel panel = new JPanel();
        add(panel, BorderLayout.NORTH);
        panel.setLayout(new BorderLayout(0, 0));

        JLabel controlerName = new JLabel(turnImageBuilder.getClass().getSimpleName());
        controlerName.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 11));
        panel.add(controlerName, BorderLayout.CENTER);

        showCheckBox = new JCheckBox("Show");
        showCheckBox.setSelected(true);
        showCheckBox.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 11));
        panel.add(showCheckBox, BorderLayout.EAST);
        showCheckBox.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                guiControler.refreshAfterChange();
            }
        });

        JPanel controlPanel = turnImageBuilder.getControlPanel(this);
        if (controlPanel != null) {
            add(controlPanel, BorderLayout.CENTER);
        }
    }

    public void setGuiControler(GuiControler<?> guiControler) {
        this.guiControler = guiControler;
    }

    public TurnImageBuilder<?> getTurnImageBuilder() {
        return turnImageBuilder;
    }

    public boolean isShow() {
        return showCheckBox.isSelected();
    }

    public void refresh() {
        guiControler.refreshAfterChange();
    }

}
