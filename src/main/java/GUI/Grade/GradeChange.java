package GUI.Grade;


import control.Control;
import control.GraControl;
import control.StuControl;
import entity.Grade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.SQLException;

/**
 * Title: 学生信息修改
 * Description: 学生信息修改模块
 *
 * @author 谢孟辉
 */

public class GradeChange extends JFrame {
    JPanel contentPane;
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JLabel jLabel4 = new JLabel();
    JTextField jTextField1 = new JTextField();

    JButton jButton1 = new JButton();
    JButton jButton2 = new JButton();
    JLabel jLabel10 = new JLabel();
    JOptionPane jOptionPane1 = new JOptionPane();
    JOptionPane jOptionPane_LoginFeedback = new JOptionPane();
    Control control = GraControl.getInstance();
    private Grade old;

    public GradeChange(Grade old) throws PropertyVetoException, SQLException {
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
        setTitle("成绩信息修改");
        jLabel1.setFont(new Font("Dialog", Font.BOLD, 23));
        jLabel1.setForeground(SystemColor.inactiveCaptionText);
        jLabel1.setText("学  生  成  绩  修  改");
        jLabel1.setBounds(new Rectangle(196, 16, 232, 25));
        jLabel2.setFont(new Font("Dialog", Font.BOLD, 23));
        jLabel2.setText("学  生  成  绩  修  改");
        jLabel2.setBounds(new Rectangle(197, 17, 231, 25));
        jLabel4.setFont(new Font("Dialog", Font.PLAIN, 18));
        jLabel4.setText("得       分:");
        jLabel4.setBounds(new Rectangle(50, 74, 90, 22));
        // 1 姓名 2出生日期 3班級
        jTextField1.setFont(new Font("Dialog", Font.PLAIN, 16));
        jTextField1.setBorder(BorderFactory.createLoweredBevelBorder());
        jTextField1.setBounds(new Rectangle(150, 74, 195, 23));
        jTextField1.setText(old.getScore() + "");
        jButton1.setText("提  交");
        jButton1.setBounds(new Rectangle(147, 274, 90, 27));
        jButton1.setBorder(BorderFactory.createRaisedBevelBorder());
        jButton1.addActionListener(new GradeChange_jButton1_actionAdapter(this));
        jButton1.setFont(new Font("Dialog", Font.PLAIN, 16));
        jButton2.setText("退  出");
        jButton2.setBounds(new Rectangle(375, 274, 90, 27));
        jButton2.addActionListener(new GradeChange_jButton2_actionAdapter(this));
        jButton2.setFont(new Font("Dialog", Font.PLAIN, 16));
        jOptionPane_LoginFeedback.setBounds(new Rectangle(28, 263, 262, 90));
        jOptionPane_LoginFeedback.setLayout(null);
        contentPane.add(jLabel2);
        contentPane.add(jLabel1);
        contentPane.add(jTextField1);
        contentPane.add(jLabel4);
        contentPane.add(jButton1);
        contentPane.add(jButton2);
        contentPane.add(jLabel10);
        contentPane.add(jOptionPane_LoginFeedback);

    }


    public void jButton1_actionPerformed(ActionEvent e) {
        // 异常判断
        if (jTextField1.getText().trim().equals("")) {
            jOptionPane1.showMessageDialog(this, "请输入学生姓名！", "提 示", jOptionPane1.INFORMATION_MESSAGE);
        } else {
            try {
                control.update(old, new Grade(Integer.valueOf(jTextField1.getText().trim())));
                jTextField1.setText("");
                jOptionPane1.showMessageDialog(this, "修改成功！", "提 示", jOptionPane1.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                jOptionPane1.showMessageDialog(this, "修改失敗！", "提 示", jOptionPane1.INFORMATION_MESSAGE);
                throw new RuntimeException(ex);
            }

        }
    }


    // 退出
    public void jButton2_actionPerformed(ActionEvent e) {
        this.dispose();
    }


    class GradeChange_jButton2_actionAdapter implements ActionListener {
        private GradeChange adaptee;

        GradeChange_jButton2_actionAdapter(GradeChange adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            adaptee.jButton2_actionPerformed(e);
        }
    }

    class GradeChange_jButton1_actionAdapter implements ActionListener {
        private GradeChange adaptee;

        GradeChange_jButton1_actionAdapter(GradeChange adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
                adaptee.jButton1_actionPerformed(e);
        }
    }

}



