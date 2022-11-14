package GUI.Grade;

import control.Control;
import control.CouControl;
import control.GraControl;
import control.StuControl;
import entity.Cource;
import entity.Grade;
import entity.Student;
import tools.mError;

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.List;


public class GradeAddFrame extends JFrame {
    JPanel contentPane;
    JLabel jLabel1 = new JLabel();
    int FS, spid, FC;
    JLabel jLabel3 = new JLabel();
    JLabel jLabel4 = new JLabel();
    String IS;
    JTextField jTextField2 = new JTextField();
    JTextField jTextField3 = new JTextField();
    JButton jButton1 = new JButton();
    JButton jButton2 = new JButton();
    JOptionPane jOptionPane1 = new JOptionPane();
    JLabel jLabel6 = new JLabel();
    JComboBox jComboBox1 = new JComboBox();
    Control gc = GraControl.getInstance();
    Control cc = CouControl.getInstance();
    Control sc = StuControl.getInstance();
    private JLabel jLabel5=  new JLabel();

    public GradeAddFrame() throws PropertyVetoException, SQLException {
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
        setTitle("成绩录入");
        jLabel1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
        jLabel1.setText("成  绩  录  入");
        jLabel1.setBounds(new Rectangle(178, 17, 126, 25));
        jLabel3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jLabel3.setText("学生学号：");
        jLabel3.setBounds(new Rectangle(75, 70, 81, 21));

        jLabel5.setFont(new java.awt.Font("Dialog", Font.PLAIN, 12));
        jLabel5.setText("按回车检索");
        jLabel5.setBounds(new Rectangle(75, 90, 81, 21));
        jLabel4.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jLabel4.setText("成    绩：");
        jLabel4.setBounds(new Rectangle(75, 170, 77, 21));
        jTextField2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jTextField2.setBorder(BorderFactory.createLoweredBevelBorder());
        jTextField2.setText("");
        jTextField2.setBounds(new Rectangle(190, 70, 184, 26));
        jTextField3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
        jTextField3.setBorder(BorderFactory.createLoweredBevelBorder());
        jTextField3.setText("");
        jTextField3.setBounds(new Rectangle(190, 170, 94, 26));
        jButton1.setBounds(new Rectangle(103, 240, 90, 29));
        jButton1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jButton1.setBorder(BorderFactory.createRaisedBevelBorder());
        jButton1.setText("提    交");
        jButton1.addActionListener(new GradeAddFrame_jButton1_actionAdapter(this));
        jButton2.setBounds(new Rectangle(277, 240, 90, 29));
        jButton2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jButton2.setBorder(BorderFactory.createRaisedBevelBorder());
        jButton2.setText("退    出");
        jButton2.addActionListener(new GradeAddFrame_jButton2_actionAdapter(this));
        jOptionPane1.setBounds(new Rectangle(75, 261, 262, 90));
        jOptionPane1.setLayout(null);
        jLabel6.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jLabel6.setText("课       程：");
        jLabel6.setBounds(new Rectangle(75, 120, 82, 21));
        jComboBox1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jComboBox1.setBounds(new Rectangle(190, 120, 160, 25));
        contentPane.add(jLabel1);
        contentPane.add(jButton1);
        contentPane.add(jButton2);
        contentPane.add(jOptionPane1);
        contentPane.add(jTextField3);
        contentPane.add(jLabel4);
        contentPane.add(jLabel3);
        contentPane.add(jLabel5);
        contentPane.add(jTextField2);
        contentPane.add(jComboBox1);
        contentPane.add(jLabel6);
        jComboBox1.addItem("请选择");
        List<Cource> cs = cc.read(null);
        for (Cource c : cs) {
            jComboBox1.addItem(c.getCname());
        }
        jTextField2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        List<Student> s = sc.read(Long.valueOf(jTextField2.getText().trim()));
                        jOptionPane1.showMessageDialog(contentPane,"该生为"+s.get(0).getName()+"！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    } catch (mError ex) {
                        jOptionPane1.showMessageDialog(contentPane,"该生不存在！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }

    // 录入
    public void InC() {
        // 异常判断
        if (jTextField2.getText().length() == 0) {
            jOptionPane1.showMessageDialog(this, "学生学号不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
        } else if (jComboBox1.getSelectedIndex() == 0) {
            jOptionPane1.showMessageDialog(this, "请选择课程！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
        } else if (jTextField3.getText().length() == 0) {
            jOptionPane1.showMessageDialog(this, "成绩不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
        } else {
            try {
                gc.insert(new Grade(Long.valueOf(jTextField2.getText().trim()), jComboBox1.getSelectedItem().toString(), Integer.valueOf(jTextField3.getText().trim())));
                jOptionPane1.showMessageDialog(this, "成绩添加成功！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
                jTextField2.setText("");
                jTextField3.setText("");
                jComboBox1.setSelectedItem("请选择");
            } catch (SQLException e) {
                jOptionPane1.showMessageDialog(this, e.getMessage(), "提示", JOptionPane.INFORMATION_MESSAGE, null);
                throw new RuntimeException(e);
            } catch (mError e) {
                jOptionPane1.showMessageDialog(this, e.getMessage(), "提示", JOptionPane.INFORMATION_MESSAGE, null);
                throw new RuntimeException(e);
            }
        }
    }

    // 录入
    public void jButton1_actionPerformed(ActionEvent e) {
        InC();
    }

    // 退出
    public void jButton2_actionPerformed(ActionEvent e) {
        this.dispose();
    }


}

class GradeAddFrame_jButton1_actionAdapter implements ActionListener {
    private GradeAddFrame adaptee;

    GradeAddFrame_jButton1_actionAdapter(GradeAddFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton1_actionPerformed(e);
    }
}


class GradeAddFrame_jButton2_actionAdapter implements ActionListener {
    private GradeAddFrame adaptee;

    GradeAddFrame_jButton2_actionAdapter(GradeAddFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton2_actionPerformed(e);
    }
}
