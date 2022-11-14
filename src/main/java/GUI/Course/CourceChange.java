package GUI.Course;

import java.awt.Font;
import javax.swing.JFrame;

import com.borland.jbcl.layout.XYLayout;

import com.borland.jbcl.layout.*;
import control.Control;
import control.CouControl;
import entity.Cource;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

/**
 * Title: 课程修改
 * Description: 课程修改模块
 *
 * @author 谢孟辉
 */

public class CourceChange extends JFrame {
    XYLayout xYLayout1 = new XYLayout();
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JTextField jTextField1 = new JTextField();
    JButton jButton1 = new JButton();
    JButton jButton3 = new JButton();
    JOptionPane jOptionPane1 = new JOptionPane();
    int csid;
    private Cource old;

    public CourceChange(Cource c) {
        this.old = c;
        try {
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        getContentPane().setLayout(xYLayout1);
        xYLayout1.setWidth(500);
        xYLayout1.setHeight(350);
        jLabel1.setFont(new java.awt.Font("新宋体", Font.BOLD, 20));
        jLabel1.setText("课   程   修   改 ");
        jButton3.addActionListener(new CourceManager_jButton3_actionAdapter(this));
        jButton1.addActionListener(new CourceManager_jButton1_actionAdapter(this));
        jTextField1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jButton3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jButton3.setText("退    出");
        jButton1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jButton1.setText("修    改");
        this.getContentPane().add(jLabel2, new XYConstraints(85, 80, 80, 30));
        this.getContentPane().add(jTextField1, new XYConstraints(200, 80, 140, 30));
        this.getContentPane().add(jLabel1, new XYConstraints(158, 21, -1, 49));
        this.getContentPane().add(jButton1, new XYConstraints(130, 230, 90, 30));
        this.getContentPane().add(jButton3, new XYConstraints(295, 230, 90, 30));
        jLabel2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jLabel2.setText("课程名称：");
        this.setTitle("课程修改与删除");
    }

    // 退出
    public void jButton3_actionPerformed(ActionEvent e) {
        this.dispose();
    }

    /**
     * j button1执行操作
     *
     * @param e e
     **/
    public void jButton1_actionPerformed(ActionEvent e) {
        try {
            Control control = CouControl.getInstance();
            Cource _new = new Cource(jTextField1.getText().trim());
            control.update(old, _new);
            jOptionPane1.showMessageDialog(this, "更新成功！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
            jTextField1.setText("");
        } catch (PropertyVetoException ex) {
            throw new RuntimeException(ex);
        } catch (SQLException ex) {
            jOptionPane1.showMessageDialog(this, "更新失败！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
            throw new RuntimeException(ex);
        }


    }
}

class CourceManager_jButton1_actionAdapter implements ActionListener {
    private CourceChange adaptee;

    CourceManager_jButton1_actionAdapter(CourceChange adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton1_actionPerformed(e);
    }
}

class CourceManager_jButton3_actionAdapter implements ActionListener {
    private CourceChange adaptee;

    CourceManager_jButton3_actionAdapter(CourceChange adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton3_actionPerformed(e);
    }
}
