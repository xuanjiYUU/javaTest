package GUI.Student;


import control.Control;
import control.StuControl;
import entity.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Title: 学生信息修改
 * Description: 学生信息修改模块
 *
 * @author 谢孟辉
 */

public class StudentChange extends JFrame {
    JPanel contentPane;
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JLabel jLabel4 = new JLabel();
    JLabel jLabel6 = new JLabel();
    JLabel jLabel7 = new JLabel();
    JLabel jLabel8 = new JLabel();
    JTextField jTextField1 = new JTextField();
    JTextField jTextField2 = new JTextField();

    JTextField jTextField3 = new JTextField();
    JComboBox jComboBox4 = new JComboBox();
    JButton jButton1 = new JButton();
    JButton jButton2 = new JButton();
    JLabel jLabel10 = new JLabel();
    JOptionPane jOptionPane1 = new JOptionPane();
    JOptionPane jOptionPane_LoginFeedback = new JOptionPane();
    Control control = StuControl.getInstance();
    private Student old;

    public StudentChange(Student old) throws PropertyVetoException, SQLException {
        this.old = old;
        try {
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);
        setSize(new Dimension(592, 500));
        setTitle("学生信息录入");
        jLabel1.setFont(new java.awt.Font("Dialog", Font.BOLD, 23));
        jLabel1.setForeground(SystemColor.inactiveCaptionText);
        jLabel1.setText("学  生  信  息  修  改");
        jLabel1.setBounds(new Rectangle(196, 16, 232, 25));
        jLabel2.setFont(new java.awt.Font("Dialog", Font.BOLD, 23));
        jLabel2.setText("学  生  信  息  修  改");
        jLabel2.setBounds(new Rectangle(197, 17, 231, 25));
        jLabel4.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
        jLabel4.setText("姓       名:");
        jLabel4.setBounds(new Rectangle(50, 74, 90, 22));
        jLabel6.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
        jLabel6.setText("出生日期:");
        jLabel6.setBounds(new Rectangle(50, 124, 90, 22));
        jLabel7.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
        jLabel7.setText("性别:");
        jLabel7.setBounds(new Rectangle(50, 174, 90, 22));
        jLabel8.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
        jLabel8.setText("所属班级:");
        jLabel8.setBounds(new Rectangle(297, 174, 90, 22));
        // 1 姓名 2出生日期 3班級
        jTextField1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jTextField1.setBorder(BorderFactory.createLoweredBevelBorder());
        jTextField1.setBounds(new Rectangle(150, 74, 195, 23));
        jTextField1.setText(old.getName());
        jTextField3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jTextField3.setBorder(BorderFactory.createLoweredBevelBorder());
        jTextField3.setBounds(new Rectangle(390, 174, 125, 23));
        jTextField3.setText(old.getCno() + "");
        jTextField2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jTextField2.setBorder(BorderFactory.createLoweredBevelBorder());
        jTextField2.setText(""); //出生日期
        jTextField2.setText(old.getBrithday());
        jTextField2.setBounds(new Rectangle(150, 124, 195, 23));
        jComboBox4.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jComboBox4.setBorder(BorderFactory.createLoweredBevelBorder());
        jComboBox4.setBounds(new Rectangle(150, 174, 125, 25));
        jComboBox4.addActionListener(new StudentChange.StudentChange_jComboBox4_actionAdapter(this));
        jButton1.setText("提  交");
        jButton1.setBounds(new Rectangle(147, 274, 90, 27));
        jButton1.setBorder(BorderFactory.createRaisedBevelBorder());
        jButton1.addActionListener(new StudentChange.StudentChange_jButton1_actionAdapter(this));
        jButton1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jButton2.setText("退  出");
        jButton2.setBounds(new Rectangle(375, 274, 90, 27));
        jButton2.addActionListener(new StudentChange.StudentChange_jButton2_actionAdapter(this));
        jButton2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jOptionPane_LoginFeedback.setBounds(new Rectangle(28, 263, 262, 90));
        jOptionPane_LoginFeedback.setLayout(null);
        contentPane.add(jLabel2);
        contentPane.add(jLabel1);
        contentPane.add(jLabel8);
        contentPane.add(jLabel7);
        contentPane.add(jComboBox4);
        contentPane.add(jTextField1);
        contentPane.add(jLabel4);
        contentPane.add(jLabel6);
        contentPane.add(jTextField2);
        contentPane.add(jTextField3);
        contentPane.add(jButton1);
        contentPane.add(jButton2);
        contentPane.add(jLabel10);
        contentPane.add(jOptionPane_LoginFeedback);
        this.jComboBox4.addItem("男");
        this.jComboBox4.addItem("女");
//        int selected = old.getSex().equals("男") ? 0 : 1;
        this.jComboBox4.setSelectedItem(old.getSex());

    }


    // 学生信息录入
    public void jButton1_actionPerformed(ActionEvent e) throws SQLException {
        // 异常判断
        if (jTextField1.getText().trim().equals("")) {
            jOptionPane1.showMessageDialog(this, "请输入学生姓名！", "提 示", jOptionPane1.INFORMATION_MESSAGE);
        } else if (jTextField2.getText().trim().equals("")) {
            jOptionPane1.showMessageDialog(this, "请输入学生出生日期！", "提 示", jOptionPane1.INFORMATION_MESSAGE);
        } else if (jTextField3.getText().trim().equals("")) {
            jOptionPane1.showMessageDialog(this, "请输入学生所属班级！", "提 示", jOptionPane1.INFORMATION_MESSAGE);
        } else {
            instu();
        }
    }

    public void jComboBox4_actionPerformed(ActionEvent e) {

    }

    // 退出
    public void jButton2_actionPerformed(ActionEvent e) {
        this.dispose();
    }

    // 学生信息录入委托
    private void instu() throws SQLException {
        Student s = new Student(Long.valueOf(jTextField3.getText().trim()), jTextField1.getText().trim(), jComboBox4.getSelectedItem().toString(), jTextField2.getText().trim());
        System.out.println(s);
        control.update(old, s);
        jOptionPane1.showMessageDialog(this, "录入成功", "提 示", JOptionPane.INFORMATION_MESSAGE);
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
    }

    class StudentChange_jButton2_actionAdapter implements ActionListener {
        private StudentChange adaptee;

        StudentChange_jButton2_actionAdapter(StudentChange adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            adaptee.jButton2_actionPerformed(e);
        }
    }

    class StudentChange_jButton1_actionAdapter implements ActionListener {
        private StudentChange adaptee;

        StudentChange_jButton1_actionAdapter(StudentChange adaptee) {
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

    class StudentChange_jComboBox4_actionAdapter implements ActionListener {
        private StudentChange adaptee;

        StudentChange_jComboBox4_actionAdapter(StudentChange adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            adaptee.jComboBox4_actionPerformed(e);
        }
    }
}



