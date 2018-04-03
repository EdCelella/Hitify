package musicClient;

import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class CustomProgressBarUI extends BasicProgressBarUI{
    
    
    static Color highlight = Color.decode("#5DFDCB");
    
    public CustomProgressBarUI(){
        UIManager.put("ProgressBar.foreground", highlight);
    }
    
  
    
}
