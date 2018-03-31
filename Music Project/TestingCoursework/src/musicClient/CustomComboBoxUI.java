package musicClient;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.basic.ComboPopup;

public class CustomComboBoxUI extends BasicComboBoxUI{
    
    // Sets colours to be used in design
    Color foreground = Color.decode("#FDFFFC");
    Color background = Color.decode("#2E2F2F");
    Color highlight = Color.decode("#5DFDCB");
    
    @Override
    protected ComboPopup createPopup() {
        return new BasicComboPopup(comboBox) {
            @Override
            protected JScrollPane createScroller() {
                //Creates verticle only scrollpane
                JScrollPane scroller = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                //Styles verticle scroll bar
                scroller.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
                    //Makes up and down buttons white squares
                    @Override
                    protected JButton createDecreaseButton(int orientation) {
                        JButton button = super.createDecreaseButton(orientation);
                        button.setContentAreaFilled(false);
                        button.setBackground(foreground);
                        button.setBorder(new LineBorder(foreground, 1));
                        return button;
                    }

                    @Override
                    protected JButton createIncreaseButton(int orientation) {
                        JButton button = super.createIncreaseButton(orientation);
                        button.setContentAreaFilled(false);
                        button.setBackground(foreground);
                        button.setBorder(new LineBorder(foreground, 1));
                        return button;
                    }

                     // Changes thumb and track colour
                    @Override 
                    protected void configureScrollBarColors(){
                        this.thumbColor = highlight;
                        this.trackColor = background;
                    }

                });
                return scroller;
            }
        };
    }
    
    @Override protected JButton createArrowButton() {
        // Creates arrow down button and styles
        JButton btn = new BasicArrowButton(BasicArrowButton.SOUTH, foreground, foreground, background, foreground);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setBackground(foreground);
        btn.setForeground(foreground);
        btn.setBorder(new LineBorder(foreground, 2));
        return btn; 
    }    
    
}
