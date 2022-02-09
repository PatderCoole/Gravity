import javax.swing.JOptionPane;

public class DebugTools  
{
    
    public DebugTools()
    {
        
    }

    public static void DebugLog(String str)
    {
        JOptionPane.showMessageDialog(null, str, "Debug Message", JOptionPane.INFORMATION_MESSAGE);
    }
}
