package startup;

import gui.frame.MainFrame;
import gui.panel.MainPanel;
import gui.panel.SpendPanel;
import util.GUIUtil;

import javax.swing.*;

/**
 * Created by OovEver on 2017/8/29.
 */
public class BootStrap {
    public static void main(String[] args) throws Exception{
        GUIUtil.useLNF();
//        后面的程序和这个参数的线程对象可以并行，异步地执行
        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                MainFrame.instance.setVisible(true);
                MainPanel.instance.workingPanel.show(SpendPanel.instance);
            }
        });
    }
}
