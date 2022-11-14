package GUI.Grade;

import com.borland.jbcl.layout.XYConstraints;
import com.borland.jbcl.layout.XYLayout;
import control.Control;
import control.GraControl;
import entity.Grade;
import entity.Student;
import tools.mError;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class GradeManager extends JFrame {
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
    JButton jButton4 = new JButton();
    JButton jb_sort = new JButton();
    XYLayout xYLayout5 = new XYLayout();
    JTextField jTextField1 = new JTextField();
    JButton jButton5 = new JButton();
    JButton jButton6 = new JButton();
    JScrollPane jScrollPane1 = new JScrollPane();
    JTable jTable1 = new JTable();
    int intRow;
    String[] arrField = {"班级", "学号", "学生姓名", "课程名称", "得分", "该生总分"};
    DefaultTableModel model = new DefaultTableModel();
    JButton jButton7 = new JButton();
    JOptionPane jOptionPane_LoginFeedback = new JOptionPane();
    Control control = GraControl.getInstance();
    List<Student> result;

    boolean Flag = true; //排序

    public GradeManager() throws PropertyVetoException, SQLException {
        try {
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        read(null);
        getContentPane().setLayout(xYLayout1);
        jLabel1.setFont(new Font("新宋体", Font.BOLD, 27));
        jLabel1.setText("成绩信息管理");
        jLabel2.setFont(new Font("Dialog", Font.PLAIN, 20));
        jLabel2.setText("请选择查询方式：");
        this.setTitle("成绩信息管理");
        jPanel1.setBorder(BorderFactory.createEtchedBorder());
        jPanel1.setLayout(xYLayout5);
        jLabel3.setFont(new Font("Dialog", Font.PLAIN, 20));
        jLabel3.setText("请输入学号：");
        jButton1.setFont(new Font("Dialog", Font.PLAIN, 20));
        jButton1.setText("查询");
        jButton1.addActionListener(new GradeC_jButton1_actionAdapter(this));
        jb_sort.setFont(new Font("Dialog", Font.PLAIN, 12));
        jb_sort.setText("按分数排序");
        jb_sort.addActionListener(new GradeC_jBsort_actionAdapter(this));
        jPanel2.setBorder(BorderFactory.createEtchedBorder());
        jPanel2.setLayout(xYLayout2);
        jLabel4.setFont(new Font("Dialog", Font.PLAIN, 20));
        jLabel4.setText("请输入学生姓名：");
        jButton2.setFont(new Font("Dialog", Font.PLAIN, 20));
        jButton2.setText("查询");
        jButton2.addActionListener(new GradeC_jButton2_actionAdapter(this));
        jButton4.setFont(new Font("Dialog", Font.PLAIN, 20));
        jButton4.setText("查询");
        jButton4.addActionListener(new GradeC_jButton4_actionAdapter(this));
        jComboBox1.addActionListener(new GradeC_jComboBox1_actionAdapter(this));
        jComboBox1.setFont(new Font("Dialog", Font.PLAIN, 20));
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
        jButton6.addActionListener(new GradeC_jButton6_actionAdapter(this));
        jScrollPane1.setBorder(BorderFactory.createEtchedBorder());
        jTable1.setCellSelectionEnabled(true);
        jButton7.setFont(new Font("Dialog", Font.PLAIN, 20));
        jButton7.setText("删除");
        jButton7.addActionListener(new GradeC_jButton7_actionAdapter(this));
        this.getContentPane().add(jPanel2, new XYConstraints(150, 225, 510, 50));
        jPanel2.add(jButton2, new XYConstraints(381, 8, 85, 27));
        ShowPanel();
        jComboBox1.addItem("查询全部");
        jComboBox1.addItem("按学号查询");
        jComboBox1.addItem("按姓名查询");
        jPanel1.add(jTextField1, new XYConstraints(164, 8, 149, 25));
        jPanel1.add(jButton1, new XYConstraints(360, 8, 80, 29));
        jPanel1.add(jLabel3, new XYConstraints(29, 8, 125, 26));
        jPanel2.add(jLabel4, new XYConstraints(21, 8, 164, 31));
        jPanel2.add(jTextField2, new XYConstraints(206, 8, 132, 27));
        this.getContentPane().add(jLabel1, new XYConstraints(321, 12, 180, 38));
        this.getContentPane().add(jComboBox1, new XYConstraints(370, 176, -1, 30));
        this.getContentPane().add(jLabel2, new XYConstraints(169, 174, 161, 32));
        this.getContentPane().add(jPanel1, new XYConstraints(150, 225, 510, 50));
        this.getContentPane().add(jScrollPane1, new XYConstraints(20, 293, 759, 350));
        this.getContentPane().add(jb_sort, new XYConstraints(125, 745, 95, 34));
        this.getContentPane().add(jButton5, new XYConstraints(325, 745, 95, 34));
        this.getContentPane().add(jButton6, new XYConstraints(525, 745, 95, 34));
        this.getContentPane().add(jButton7, new XYConstraints(425, 745, 95, 34));

        UpdateRecord();
    }

    // 更新显示
    public void UpdateRecord() throws SQLException, mError {
        Object[][] arrTmp = {}; // 设定表格的字段
        model = new DefaultTableModel(arrTmp, arrField);
        jTable1 = new JTable(model);
        jScrollPane1.getViewport().add(jTable1, null);
        jScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        for (Student s : result) {
            for (int i = 0; i < s.getGrades().size(); i++) {
                Vector vec = new Vector();
                Grade g = s.getGrades().get(i);
                vec.add(s.getCno());
                vec.add(s.getSno());
                vec.add(s.getName());
                vec.add(g.getCname());
                vec.add(g.getScore());
                if (i + 1 == s.getGrades().size()) {
                    vec.add(s.getSum_score());
                }
                model.addRow(vec);
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
    }

    // 查询方式
    public void ShowPanel() throws SQLException, mError {
        jPanel1.setVisible(false);
        jPanel2.setVisible(false);
        if (jComboBox1.getSelectedIndex() == 1)
            jPanel1.setVisible(true);
        else if (jComboBox1.getSelectedIndex() == 2)
            jPanel2.setVisible(true);
        else if (jComboBox1.getSelectedIndex() == 0)
            read(null);
        UpdateRecord();
    }

    private void read(Object o) {
        System.out.println("更新");
        try {
            result = control.read(o);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (mError e) {
            JOptionPane.showMessageDialog(this, "信息不存在！", "提 示",
                    jOptionPane_LoginFeedback.INFORMATION_MESSAGE);

        }
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

    public void jBsort_actionPerformed(ActionEvent e) {
        Flag = !Flag;
        if (Flag) {
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(jTable1.getModel());
            List<RowSorter.SortKey> list = new ArrayList<>();
            list.add(new RowSorter.SortKey(4,SortOrder.ASCENDING));
            sorter.setSortKeys(list);
            jTable1.setRowSorter(sorter);
            sorter.sort();
        } else {
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(jTable1.getModel());
            List<RowSorter.SortKey> list = new ArrayList<>();
            list.add(new RowSorter.SortKey(4, SortOrder.DESCENDING));
            sorter.setSortKeys(list);
            jTable1.setRowSorter(sorter);
            sorter.sort();

        }
    }

    // 按学号查询
    public void jButton1_actionPerformed(ActionEvent e) throws SQLException, mError {
        if (!jTextField1.getText().trim().equals("")) {
            read(Long.valueOf(jTextField1.getText().trim()));
            UpdateRecord();
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
                read(jTextField2.getText().trim());
                UpdateRecord();
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
            Grade old = new Grade(Long.valueOf(model.getValueAt(intRow, 1).toString()), model.getValueAt(intRow, 3).toString(), Integer.valueOf(model.getValueAt(intRow, 4).toString()));
            System.out.println(old);
            GradeChange siadd = new GradeChange(old);
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
            delstu();
        } else
            jOptionPane_LoginFeedback.showMessageDialog(this, "请选择要删除的信息！", "提 示",
                    jOptionPane_LoginFeedback.INFORMATION_MESSAGE);
    }

    // 删除
    public void delstu() {
        System.out.println("删除");
        //要cname和sno
        Grade delete = new Grade(Long.valueOf(model.getValueAt(intRow, 1).toString()), model.getValueAt(intRow, 3).toString());
        System.out.println(delete);
        try {
            control.delete(delete);
            jOptionPane_LoginFeedback.showMessageDialog(this, "刪除成功！", "提 示",
                    jOptionPane_LoginFeedback.INFORMATION_MESSAGE);
            read(null);
            UpdateRecord();
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (mError e) {
            jOptionPane_LoginFeedback.showMessageDialog(this, e.getMessage(), "提 示",
                    jOptionPane_LoginFeedback.INFORMATION_MESSAGE);
        }

//        try {
//            if (control.delete(new Grade(sno)) == false) {
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

class GradeC_jButton7_actionAdapter implements ActionListener {
    private GradeManager adaptee;

    GradeC_jButton7_actionAdapter(GradeManager adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton7_actionPerformed(e);
    }
}

class GradeC_jButton4_actionAdapter implements ActionListener {
    private GradeManager adaptee;

    GradeC_jButton4_actionAdapter(GradeManager adaptee) {
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

class GradeC_jButton2_actionAdapter implements ActionListener {
    private GradeManager adaptee;

    GradeC_jButton2_actionAdapter(GradeManager adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton2_actionPerformed(e);
    }
}

class GradeC_jButton1_actionAdapter implements ActionListener {
    private GradeManager adaptee;

    GradeC_jButton1_actionAdapter(GradeManager adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            adaptee.jButton1_actionPerformed(e);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (mError ex) {
        }
    }
}

class GradeC_jButton6_actionAdapter implements ActionListener {
    private GradeManager adaptee;

    GradeC_jButton6_actionAdapter(GradeManager adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton6_actionPerformed(e);
    }
}

class GradeC_jBsort_actionAdapter implements ActionListener {
    private GradeManager adaptee;

    GradeC_jBsort_actionAdapter(GradeManager adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jBsort_actionPerformed(e);
    }
}

class GradeC_jComboBox1_actionAdapter implements ActionListener {
    private GradeManager adaptee;

    GradeC_jComboBox1_actionAdapter(GradeManager adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            adaptee.jComboBox1_actionPerformed(e);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (mError ex) {
        }
    }
}
