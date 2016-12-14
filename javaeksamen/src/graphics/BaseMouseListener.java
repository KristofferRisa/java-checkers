package graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class BaseMouseListener implements ActionListener{

    private static boolean active = true;
    public static void setActive(boolean active){
        BaseMouseListener.active = active;
    }

    protected abstract void doPerformAction(ActionEvent e);

    @Override
    public final void actionPerformed(ActionEvent e){
        if(active){
            doPerformAction(e);
        }
    }
}