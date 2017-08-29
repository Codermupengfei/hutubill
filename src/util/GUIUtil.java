package util;

import javafx.scene.layout.TilePane;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Created by OovEver on 2017/8/29.
 */
public class GUIUtil {
    private static String imageFolder = "src/img";
//给按钮设置图标和文本以及提示文字
    public static void setImageIcon(JButton b, String fileName,String tip) {
        ImageIcon imageIcon = new ImageIcon(new File(imageFolder, fileName).getAbsolutePath());
        b.setIcon(imageIcon);
//        设置最好的大小 不一定显示出来
        b.setPreferredSize(new Dimension(61, 81));
//        比如，你设置了 lbl.setToolTipText("This is a tip");后
//        那么当你运行程序，并将鼠标移动到lbl上停顿一下后，屏幕上就会显示This is a tip 。
        b.setToolTipText(tip);
//        可设置文字相对于Icon的相对位置
        b.setVerticalTextPosition(JButton.BOTTOM);
        b.setHorizontalTextPosition(JButton.CENTER);
        b.setText(tip);
    }
//设置组件的前景色
    public static void setColor(Color color, JComponent... cs) {
        for (JComponent c : cs) {
            c.setForeground(color);
        }
    }
    /**
     *
     * @param p
     * @param strechRate 拉伸比例1表示满屏幕
     */
    public static void showPanel(JPanel p, double strechRate) {
        GUIUtil.useLNF();
        JFrame f = new JFrame();
        f.setSize(500, 500);
        f.setLocationRelativeTo(null);
        CenterPanel cp = new CenterPanel(strechRate);
        f.setContentPane(cp);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        cp.show(p);
    }
    /*
    * 检验组件内容是否为数字格式
    * */

    public static boolean checkNumber(JTextField tf, String input) {
        if (!checkEmpty(tf, input)) {
            return false;
        }
        String text = tf.getText().trim();
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e1) {
            JOptionPane.showMessageDialog(null, input + " 需要是整数");
            tf.grabFocus();
            return false;
        }
    }

//    检测组件中的内容是否为0
public static boolean checkZero(JTextField tf, String input) {
    if (!checkNumber(tf, input))
        return false;
    String text = tf.getText().trim();
    if(0==Integer.parseInt(text)){
        JOptionPane.showMessageDialog(null, input + " 不能为零");
        tf.grabFocus();
        return false;
    }
    return true;
}

    /*
    * 校验一个输入框内容是否是空
    * */
    public static boolean checkEmpty(JTextField tf, String input) {
        String text = tf.getText().trim();
        if (text.length() == 0) {
//            显示一个提示框
            JOptionPane.showMessageDialog(null, input + "不能为空");
//          requestFocus()等焦点获取函数，
            tf.grabFocus();
            return false;
        }
        return true;
    }
    public static int getInt(JTextField tf) {
        return Integer.parseInt(tf.getText());
    }
//    使用水晶皮肤。 Java提供很多种皮肤,但是大部分都比较难看，水晶皮肤还算是看得过去的一种
public static void useLNF() {
    try {
        javax.swing.UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

}
    public static void showPanel(JPanel p) {
        showPanel(p,0.85);
    }
}
