package musicClient;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class CustomScrollBarUI extends BasicScrollBarUI{
    
    // Sets colours to be used in design
    Color foreground = Color.decode("#FDFFFC");
    Color background = Color.decode("#2E2F2F");
    Color highlight = Color.decode("#5DFDCB");
    Color warning = Color.decode("#FF3366");
   
    
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
        
    
}
