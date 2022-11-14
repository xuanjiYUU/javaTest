package GUI.Course;

import control.Control;
import control.CouControl;
import entity.Cource;
import tools.mError;

import java.awt.Dimension;
import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JTextField;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Title: 课程录入
 * Description: 课程录入模块
 *
 * @author 谢孟辉
 */

public class CourceAddFrame extends JFrame {
    JPanel contentPane;
    JLabel jLabel1 = new JLabel();
    int FS, spid, FC;
    JLabel jLabel3 = new JLabel();
    String IS;
    JTextField jTextField2 = new JTextField();
    JButton jButton1 = new JButton();
    JButton jButton2 = new JButton();
    JOptionPane jOptionPane1 = new JOptionPane();
    Control control = CouControl.getInstance();

    public CourceAddFrame() throws PropertyVetoException, SQLException {
        try {
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);
        setSize(new Dimension(482, 300));
        setTitle("课程录入");
        jLabel1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
        jLabel1.setText("课  程  录  入");
        jLabel1.setBounds(new Rectangle(178, 17, 126, 25));
        jLabel3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jLabel3.setText("课程名称：");
        jLabel3.setBounds(new Rectangle(75, 70, 81, 21));
        jTextField2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jTextField2.setBorder(BorderFactory.createLoweredBevelBorder());
        jTextField2.setText("");
        jTextField2.setBounds(new Rectangle(190, 70, 184, 26));
        jButton1.setBounds(new Rectangle(103, 240, 90, 29));
        jButton1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jButton1.setBorder(BorderFactory.createRaisedBevelBorder());
        jButton1.setText("提    交");
        jButton1.addActionListener(new CourceAddFrame_jButton1_actionAdapter(this));
        jButton2.setBounds(new Rectangle(277, 240, 90, 29));
        jButton2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jButton2.setBorder(BorderFactory.createRaisedBevelBorder());
        jButton2.setText("退    出");
        jButton2.addActionListener(new CourceAddFrame_jButton2_actionAdapter(this));
        jOptionPane1.setBounds(new Rectangle(75, 261, 262, 90));
        jOptionPane1.setLayout(null);
        contentPane.add(jLabel1);
        contentPane.add(jButton1);
        contentPane.add(jButton2);
        contentPane.add(jOptionPane1);
        contentPane.add(jLabel3);
        contentPane.add(jTextField2);

    }

    // 录入
    public void InC() {
        // 异常判断
        if (jTextField2.getText().length() == 0) {
            jOptionPane1.showMessageDialog(this, "课程名称不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
        } else {
            try {
                control.insert(new Cource(jTextField2.getText().trim()));
                jOptionPane1.showMessageDialog(this, "课程添加成功!！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
                jTextField2.setText("");
            } catch (SQLException e) {
                jOptionPane1.showMessageDialog(this, "错误！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
                throw new RuntimeException(e);
            } catch (mError e) {
                jOptionPane1.showMessageDialog(this, e.getMessage(), "提示", JOptionPane.INFORMATION_MESSAGE, null);
                throw new RuntimeException(e);
            }
        }
    }

    public void jButton1_actionPerformed(ActionEvent e) throws SQLException {
        InC();
    }

    public void jButton2_actionPerformed(ActionEvent e) {
        this.dispose();
    }
}

class CourceAddFrame_jButton1_actionAdapter implements ActionListener {
    private CourceAddFrame adaptee;

    CourceAddFrame_jButton1_actionAdapter(CourceAddFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            adaptee.jButton1_actionPerformed(e);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}

class CourceAddFrame_jButton2_actionAdapter implements ActionListener {
    private CourceAddFrame adaptee;

    CourceAddFrame_jButton2_actionAdapter(CourceAddFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton2_actionPerformed(e);
    }
}
