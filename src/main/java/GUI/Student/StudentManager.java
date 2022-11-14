package GUI.Student;

import GUI.MainFrame;
import com.borland.jbcl.layout.XYConstraints;
import com.borland.jbcl.layout.XYLayout;
import control.Control;
import control.StuControl;
import entity.Student;
import tools.mError;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;


/**
 * 学生管理器
 *还有一个按班级查询没写
 * @author 马文涛
 * @date 2022/11/13
 */
public class StudentManager extends JFrame {
    String sql, zhy;
    JLabel jLabel1 = new JLabel();
    XYLayout xYLayout1 = new XYLayout();
    JLabel jLabel2 = new JLabel();
    JComboBox jComboBox1 = new JComboBox();
    JPanel jPanel1 = new JPanel();
    JLabel jLabel3 = new JLabel();
    JButton jButton1 = new JButton();
    JPanel jPanel2 = new JPanel();
    XYLayout xYLayout2 = new XYLayout();
    JLabel jLabel4 = new JLabel();
    JTextField jTextField2 = new JTextField();
    JButton jButton2 = new JButton();
    ButtonGroup buttonGroup1 = new ButtonGroup();
    JPanel jPanel4 = new JPanel();
    JLabel jLabel6 = new JLabel();
    XYLayout xYLayout4 = new XYLayout();
    JTextField jTextField3 = new JTextField();
    JButton jButton4 = new JButton();
    XYLayout xYLayout5 = new XYLayout();
    JTextField jTextField1 = new JTextField();
    JButton jButton5 = new JButton();
    JButton jButton6 = new JButton();
    JScrollPane jScrollPane1 = new JScrollPane();
    JTable jTable1 = new JTable();
    int intRow;
    String[] arrField = {"学号", "姓名", "性别", "出生日期", "班级"};
    DefaultTableModel model = new DefaultTableModel();
    Object[][] arrData = {};
    static int find;
    JButton jButton7 = new JButton();
    JOptionPane jOptionPane_LoginFeedback = new JOptionPane();
    Control control = StuControl.getInstance();

    public StudentManager() throws PropertyVetoException, SQLException {
        try {
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void jbInit() throws Exception {

        getContentPane().setLayout(xYLayout1);
        jLabel1.setFont(new Font("新宋体", Font.BOLD, 27));
        jLabel1.setText("学生信息管理");
        jLabel2.setFont(new Font("Dialog", Font.PLAIN, 20));
        jLabel2.setText("请选择查询方式：");
        this.setTitle("学生信息管理");
        jPanel1.setBorder(BorderFactory.createEtchedBorder());
        jPanel1.setLayout(xYLayout5);
        jLabel3.setFont(new Font("Dialog", Font.PLAIN, 20));
        jLabel3.setText("请输入学号：");
        jButton1.setFont(new Font("Dialog", Font.PLAIN, 20));
        jButton1.setText("查询");
        jButton1.addActionListener(new StudentC_jButton1_actionAdapter(this));
        jPanel2.setBorder(BorderFactory.createEtchedBorder());
        jPanel2.setLayout(xYLayout2);
        jLabel4.setFont(new Font("Dialog", Font.PLAIN, 20));
        jLabel4.setText("请输入学生姓名：");
        jButton2.setFont(new Font("Dialog", Font.PLAIN, 20));
        jButton2.setText("查询");
        jButton2.addActionListener(new StudentC_jButton2_actionAdapter(this));
        jPanel4.setBorder(BorderFactory.createEtchedBorder());
        jPanel4.setLayout(xYLayout4);
        jLabel6.setFont(new Font("Dialog", Font.PLAIN, 20));
        jLabel6.setText("请输入要查询的班级：");
        jButton4.setFont(new Font("Dialog", Font.PLAIN, 20));
        jButton4.setText("查询");
        jButton4.addActionListener(new StudentC_jButton4_actionAdapter(this));
        jComboBox1.addActionListener(new StudentC_jComboBox1_actionAdapter(this));
        jComboBox1.setFont(new Font("Dialog", Font.PLAIN, 20));
        jTextField3.setFont(new Font("Dialog", Font.PLAIN, 20));
        jTextField1.setFont(new Font("Dialog", Font.PLAIN, 20));
        jTextField2.setFont(new Font("Dialog", Font.PLAIN, 20));
        jButton5.setFont(new Font("Dialog", Font.PLAIN, 20));
        jButton5.setText("修改");
        jButton5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    jButton5_actionPerformed(e);
                } catch (PropertyVetoException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        jButton6.setFont(new Font("Dialog", Font.PLAIN, 20));
        jButton6.setText("返回");
        jButton6.addActionListener(new StudentC_jButton6_actionAdapter(this));
        jScrollPane1.setBorder(BorderFactory.createEtchedBorder());
        jTable1.setCellSelectionEnabled(true);
        jButton7.setFont(new Font("Dialog", Font.PLAIN, 20));
        jButton7.setText("删除");
        jButton7.addActionListener(new StudentC_jButton7_actionAdapter(this));
        this.getContentPane().add(jPanel2, new XYConstraints(150, 125, 510, 50));
        jPanel2.add(jButton2, new XYConstraints(381, 8, 85, 27));
        this.getContentPane().add(jPanel4, new XYConstraints(150, 125, 510, 50));
        jPanel4.add(jButton4, new XYConstraints(388, 8, 89, 26));
        ShowPanel();
        jComboBox1.addItem("查询全部");
        jComboBox1.addItem("按学号查询");
        jComboBox1.addItem("按姓名查询");
        jComboBox1.addItem("按班级查询");
        jPanel1.add(jTextField1, new XYConstraints(164, 8, 149, 25));
        jPanel1.add(jButton1, new XYConstraints(360, 8, 80, 29));
        jPanel1.add(jLabel3, new XYConstraints(29, 8, 125, 26));
        jPanel2.add(jLabel4, new XYConstraints(21, 8, 164, 31));
        jPanel2.add(jTextField2, new XYConstraints(206, 8, 132, 27));
        jPanel4.add(jLabel6, new XYConstraints(16, 8, 201, 30));
        jPanel4.add(jTextField3, new XYConstraints(212, 8, 152, 28));
        this.getContentPane().add(jLabel1, new XYConstraints(321, 12, 180, 38));
        this.getContentPane().add(jComboBox1, new XYConstraints(370, 76, -1, 30));
        this.getContentPane().add(jLabel2, new XYConstraints(169, 74, 161, 32));
        this.getContentPane().add(jPanel1, new XYConstraints(150, 125, 510, 50));
        jScrollPane1.getViewport().add(jTable1, null);
        this.getContentPane().add(jScrollPane1, new XYConstraints(20, 193, 759, 325));
        this.getContentPane().add(jButton5, new XYConstraints(225, 545, 95, 34));
        this.getContentPane().add(jButton6, new XYConstraints(525, 545, 95, 34));
        this.getContentPane().add(jButton7, new XYConstraints(375, 545, 95, 34));

        // 查找所有学生
//		sql = "select * from tb_student";
        // 更新显示
        UpdateRecord(null);
    }

    // 更新显示
    public void UpdateRecord(Object o) throws SQLException, mError {
        Object[][] arrTmp = {}; // 设定表格的字段
        model = new DefaultTableModel(arrTmp, arrField);
        jTable1 = new JTable(model);
        jScrollPane1.getViewport().add(jTable1, null);
        List<Student> students = control.read(o);
        for (Student s : students) {
            Vector v = new Vector();
            v.add(s.getSno());
            v.add(s.getName());
            v.add(s.getSex());
            v.add(s.getBrithday());
            v.add(s.getCno());
            model.addRow(v);
        }
        jScrollPane1.getHorizontalScrollBar();
        jTable1.setGridColor(Color.blue);
        jTable1.setDragEnabled(true);
        jTable1.setSelectionForeground(Color.red);
        jTable1.setSelectionBackground(Color.green);
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTable1.setRowSelectionAllowed(true);
        jTable1.setShowVerticalLines(true);
    }

    // 查询方式
    public void ShowPanel() throws SQLException, mError {
        jPanel1.setVisible(false);
        jPanel2.setVisible(false);
        jPanel4.setVisible(false);
        if (jComboBox1.getSelectedIndex() == 1)
            jPanel1.setVisible(true);
        else if (jComboBox1.getSelectedIndex() == 2)
            jPanel2.setVisible(true);
        else if (jComboBox1.getSelectedIndex() == 3)
            jPanel4.setVisible(true);
        else if (jComboBox1.getSelectedIndex() == 0)
            UpdateRecord(null);
    }

    // 获取选定的行
    public Long getM() {
        intRow = jTable1.getSelectedRow();
        if (intRow == -1)
            return -1l;
        try {
            //根据第0列返回数据
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Long.valueOf(model.getValueAt(intRow, 0).toString());
    }

    // 退出
    public void jButton6_actionPerformed(ActionEvent e) {
        this.dispose();
    }

    // 按学号查询
    public void jButton1_actionPerformed(ActionEvent e) throws SQLException, mError {
        if (!jTextField1.getText().trim().equals("")) {
            UpdateRecord(Long.valueOf(jTextField1.getText().trim()));
        } else
            JOptionPane.showMessageDialog(this, "请输入要查询的学生学号！", "提 示",
                    jOptionPane_LoginFeedback.INFORMATION_MESSAGE);
    }

    // 选择触发
    public void jComboBox1_actionPerformed(ActionEvent e) throws SQLException, mError {
        ShowPanel();
    }

    // 按姓名查询
    public void jButton2_actionPerformed(ActionEvent e) {
        if (!jTextField2.getText().trim().equals("")) {
            System.out.println(jTextField2.getText().trim());
            try {
                UpdateRecord(jTextField2.getText().trim());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (mError ex) {
                jOptionPane_LoginFeedback.showMessageDialog(this, ex.getMessage(), "提 示",
                        jOptionPane_LoginFeedback.INFORMATION_MESSAGE);
            }
        } else
            jOptionPane_LoginFeedback.showMessageDialog(this, "请输入要查询的学生姓名！", "提 示",
                    jOptionPane_LoginFeedback.INFORMATION_MESSAGE);
    }



    // 修改
    public void jButton5_actionPerformed(ActionEvent e) throws PropertyVetoException, SQLException {
        getM();
        if (intRow != -1) {
            Student old = new Student(Long.valueOf(model.getValueAt(intRow, 0).toString()), Long.valueOf(model.getValueAt(intRow, 4).toString()), model.getValueAt(intRow, 1).toString(),
                    model.getValueAt(intRow, 2).toString(),
                    model.getValueAt(intRow, 3).toString());
            System.out.println(old);
            StudentChange siadd = new StudentChange(old);
            siadd.setLocation(400, 200);
            siadd.setSize(592, 350);
            siadd.setVisible(true);
            siadd.setResizable(false);
            siadd.validate();
            this.dispose();
        } else
            jOptionPane_LoginFeedback.showMessageDialog(this, "请选择要修改的信息！", "提 示",
                    jOptionPane_LoginFeedback.INFORMATION_MESSAGE);
    }

    // 触发删除
    public void jButton7_actionPerformed(ActionEvent e) {

        if (getM() != -1) {
            delstu(getM());
        } else
            jOptionPane_LoginFeedback.showMessageDialog(this, "请选择要删除的信息！", "提 示",
                    jOptionPane_LoginFeedback.INFORMATION_MESSAGE);

    }

    // 删除
    public void delstu(long sno) {

        try {
            if (!control.delete(new Student(sno))) {
                jOptionPane_LoginFeedback.showMessageDialog(this, "学号不存在！", "提 示",
                        jOptionPane_LoginFeedback.INFORMATION_MESSAGE);
            } else {
                jOptionPane_LoginFeedback.showMessageDialog(this, "删除成功！", "提 示",
                        jOptionPane_LoginFeedback.INFORMATION_MESSAGE);
                UpdateRecord(null);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        } catch (mError e) {
            throw new RuntimeException(e);
        }
//        try {
//            if (control.delete(new Student(sno)) == false) {
//                jOptionPane_LoginFeedback.showMessageDialog(this, "学号不存在！", "提 示",
//                        jOptionPane_LoginFeedback.INFORMATION_MESSAGE);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (PropertyVetoException e) {
//            throw new RuntimeException(e);
//        }
    }
}

class StudentC_jButton7_actionAdapter implements ActionListener {
    private StudentManager adaptee;

    StudentC_jButton7_actionAdapter(StudentManager adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton7_actionPerformed(e);
    }
}

class StudentC_jButton4_actionAdapter implements ActionListener {
    private StudentManager adaptee;

    StudentC_jButton4_actionAdapter(StudentManager adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
//        try {
//            adaptee.jButton4_actionPerformed(e);
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        } catch (mError ex) {
//            throw new RuntimeException(ex);
//        }
    }
}

class StudentC_jButton2_actionAdapter implements ActionListener {
    private StudentManager adaptee;

    StudentC_jButton2_actionAdapter(StudentManager adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton2_actionPerformed(e);
    }
}

class StudentC_jButton1_actionAdapter implements ActionListener {
    private StudentManager adaptee;

    StudentC_jButton1_actionAdapter(StudentManager adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            adaptee.jButton1_actionPerformed(e);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (mError ex) {
            throw new RuntimeException(ex);
        }
    }
}

class StudentC_jButton6_actionAdapter implements ActionListener {
    private StudentManager adaptee;

    StudentC_jButton6_actionAdapter(StudentManager adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton6_actionPerformed(e);
    }
}

class StudentC_jComboBox1_actionAdapter implements ActionListener {
    private StudentManager adaptee;

    StudentC_jComboBox1_actionAdapter(StudentManager adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            adaptee.jComboBox1_actionPerformed(e);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (mError ex) {
            throw new RuntimeException(ex);
        }
    }
}
