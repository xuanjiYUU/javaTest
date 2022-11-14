package GUI;

import GUI.Course.CourceAddFrame;
import GUI.Course.CourceManager;
import GUI.Grade.GradeAddFrame;
import GUI.Grade.GradeManager;
import GUI.Student.StudentManager;
import control.Control;
import control.GraControl;
import execl.Student_Grade_ToExecl;
import tools.mError;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.SQLException;

//
//import Class.ClassAddFrame;
//import Class.ClassManager;
//import Cource.CourceAddFrame;
//import Cource.CourceManager;
//import Depart.DepartAddFrame;
//import Depart.DepartManager;
//import Score.ScoreAddFrame;
//import Score.ScoreManager;
//import Student.StudentInfoAddFrame;
//import Student.StudentManager;
//import User.UserAddFrame;
//import User.UserDelete;
//import User.UserPasswordFrame;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.net.URL;
//
///**
// *
// * Title: 主界面
// * Description: 主界面模块，有对学生、班级、院系、课程、成绩、用户的录入与管理的菜单
// *
// */
//
public class MainFrame extends JFrame {
    JPanel contentPane;
    //	URL url = getClass().getResource("/img/bg.jpg");
//	ImageIcon mainBg = new ImageIcon(url);
//
    JLabel jLabel_welcomeTitle = new JLabel();
    JLabel jLabel_welcomeTitle2 = new JLabel();
    JLabel jLabel_help1 = new JLabel();
    JLabel jLabel_help2 = new JLabel();
    JLabel jLabel_help3 = new JLabel();
    //
    JMenuBar jMenuBar = new JMenuBar();
    //
    JMenu jMenu_stuManager = new JMenu();
    JMenuItem jMenuItem_stuInfoSignUp = new JMenuItem();
    JMenuItem jMenuItem_stuInfoInquire = new JMenuItem();

    JMenu jMenu_classManager = new JMenu();
    JMenuItem jMenuItem_classInput = new JMenuItem();
    JMenuItem jMenuItem_classManager = new JMenuItem();

    JMenu jMenu_departManager = new JMenu();
    JMenuItem jMenuItem_departInput = new JMenuItem();
    JMenuItem jMenuItem_departManager = new JMenuItem();

    JMenu jMenu_courceManager = new JMenu();
    JMenuItem jMenuItem_courceInput = new JMenuItem();
    JMenuItem jMenuItem_courceManager = new JMenuItem();

    JMenu jMenu_scoreManager = new JMenu();
    JMenuItem jMenuItem_scoreInput = new JMenuItem();
    JMenuItem jMenuItem_scoreManager = new JMenuItem();

    JMenu jMenu_ReportManager = new JMenu();
    JMenuItem jMenuItem_jMenu_ReportManager_1 = new JMenuItem();
    JMenuItem jMenuItem_jMenu_ReportManager_2 = new JMenuItem();
    JMenuItem jMenuItem_jMenu_ReportManager_3 = new JMenuItem();

    JMenu jMenu_about = new JMenu();
    JMenuItem jMenuItem_about = new JMenuItem();

    JMenu jMenu_quit = new JMenu();
    JMenuItem jMenuItem_quit = new JMenuItem();
    JMenuItem jMenuItem_reboot = new JMenuItem();

    JPanel jPane_box = new JPanel();
    JLabel jLabe_mainBgBox = new JLabel();
    BorderLayout borderLayout = new BorderLayout();
    JOptionPane jOptionPane_LoginFeedback = new JOptionPane();
    Control control = GraControl.getInstance();
    public MainFrame() throws PropertyVetoException, SQLException {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        jbInit();
    }

    private void jbInit() {
        contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);
        this.setJMenuBar(jMenuBar);
        setSize(new Dimension(911, 698));
        setTitle("欢迎登录学生管理系统");

        jLabel_welcomeTitle.setText("欢 迎 使 用 学 生 信 息 管 理 系 统");
        jLabel_welcomeTitle.setBounds(new Rectangle(180, 130, 600, 33));
        jLabel_welcomeTitle.setFont(new Font("Dialog", Font.BOLD, 30));
        jLabel_welcomeTitle.setForeground(Color.lightGray);

        jLabel_welcomeTitle2.setFont(new Font("Dialog", Font.BOLD, 30));
        jLabel_welcomeTitle2.setText("欢 迎 使 用 学 生 信 息 管 理 系 统");
        jLabel_welcomeTitle2.setBounds(new Rectangle(181, 131, 608, 33));

        jLabel_help1.setFont(new Font("Dialog", Font.BOLD, 20));
        jLabel_help1.setText("该系统可以对：");
        jLabel_help1.setBounds(new Rectangle(500, 340, 400, 30));

        jLabel_help2.setFont(new Font("Dialog", Font.BOLD, 20));
        jLabel_help2.setText("学生、课程、成绩");
        jLabel_help2.setBounds(new Rectangle(500, 370, 400, 30));

        jLabel_help3.setFont(new Font("Dialog", Font.BOLD, 20));
        jLabel_help3.setText("进行增加、查询、修改、删除和报表的操作");
        jLabel_help3.setBounds(new Rectangle(500, 400, 400, 30));

        jMenu_stuManager.setText("学生管理");
        jMenuItem_stuInfoSignUp.setText("  增  加");
        jMenuItem_stuInfoSignUp.addActionListener(new MainFrame_jMenuItem_stuInfoSignUp_actionAdapter(this));
        jMenuItem_stuInfoInquire.setText("  管  理");
        jMenuItem_stuInfoInquire.addActionListener(new MainFrame_jMenuItem_stuInfoInquire_actionAdapter(this));
        jMenu_ReportManager.setText("报表");
        jMenuItem_jMenu_ReportManager_1.setText("学生成绩报表");
        jMenuItem_jMenu_ReportManager_1.addActionListener(new MainFrame_jMenuItem_departInput_actionAdapter(this));
//            jMenuItem_departManager.addActionListener(new MainFrame_jMenuItem_departManager_actionAdapter(this));

        jMenu_courceManager.setText("课程管理");
        jMenuItem_courceInput.setText("  增  加");
        jMenuItem_courceInput.addActionListener(new MainFrame_jMenuItem_courceInput_actionAdapter(this));
        jMenuItem_courceManager.setText("  管  理");
        jMenuItem_courceManager.addActionListener(new MainFrame_jMenuItem_courceManager_actionAdapter(this));

        jMenu_scoreManager.setText("成绩管理");
        jMenuItem_scoreInput.setText("  增  加");
        jMenuItem_scoreInput.addActionListener(new MainFrame_jMenuItem_scoreInput_actionAdapter(this));
        jMenuItem_scoreManager.setText("  管  理");
        jMenuItem_scoreManager.addActionListener(new MainFrame_jMenuItem_scoreManager_actionAdapter(this));
        jMenu_quit.setText("退出");
        jMenuItem_quit.setText("  退  出");
        jMenuItem_quit.addActionListener(new MainFrame_jMenuItem_quit_actionAdapter(this));
        jMenuItem_reboot.setText("重新启动");
        jMenuItem_reboot.addActionListener(new MainFrame_jMenuItem_reboot_actionAdapter(this));

        jPane_box.setBounds(new Rectangle(-6, 0, 900, 700));
        jPane_box.setLayout(borderLayout);

        contentPane.add(jLabel_welcomeTitle2);
        contentPane.add(jLabel_welcomeTitle);
        contentPane.add(jLabel_help1);
        contentPane.add(jLabel_help2);
        contentPane.add(jLabel_help3);
        contentPane.add(jPane_box);

        jPane_box.add(jLabe_mainBgBox, BorderLayout.NORTH);

        jMenuBar.add(jMenu_stuManager);
//        jMenuBar.add(jMenu_classManager);
//        jMenuBar.add(jMenu_departManager);
        jMenuBar.add(jMenu_courceManager);
        jMenuBar.add(jMenu_scoreManager);
        jMenuBar.add(jMenu_ReportManager);
//        jMenuBar.add(jMenu_about);
        jMenuBar.add(jMenu_quit);

        jMenu_stuManager.add(jMenuItem_stuInfoSignUp);
        jMenu_stuManager.add(jMenuItem_stuInfoInquire);

        jMenu_classManager.add(jMenuItem_classInput);
        jMenu_classManager.add(jMenuItem_classManager);

        jMenu_departManager.add(jMenuItem_departInput);
        jMenu_departManager.add(jMenuItem_departManager);

        jMenu_courceManager.add(jMenuItem_courceInput);
        jMenu_courceManager.add(jMenuItem_courceManager);

        jMenu_scoreManager.add(jMenuItem_scoreInput);
        jMenu_scoreManager.add(jMenuItem_scoreManager);

        jMenu_ReportManager.add(jMenuItem_jMenu_ReportManager_1);

        jMenu_about.add(jMenuItem_about);

        jMenu_quit.add(jMenuItem_quit);
        jMenu_quit.add(jMenuItem_reboot);
    }

    //
//	// 关于信息
//	public void jMenuItem_about_actionPerformed(ActionEvent e) {
//		jOptionPane_LoginFeedback.showMessageDialog(this, "学生信息管理系统\n版本号：1.0\n作   者：谢孟辉\n邮   箱：huihut@outlook.com\n博   客：blog.huihut.com\n本程序仅供学习使用",
//				"关于", jOptionPane_LoginFeedback.INFORMATION_MESSAGE);
//	}
//
    // 退出
    public void jMenuItem_quit_actionPerformed(ActionEvent e) {
        System.exit(0);
    }

    //
//	// 重启
    public void jMenuItem_reboot_actionPerformed(ActionEvent e) {
        this.dispose();
        LoginFrame login = new LoginFrame();
        login.setLocation(400, 200);
        login.setSize(504, 344);
        login.setVisible(true);
        login.setResizable(false);
        login.validate();
    }

    //
    // 学生录入模块
    public void jMenuItem_stuInfoSignUp_actionPerformed(ActionEvent e) throws PropertyVetoException, SQLException {
        GUI.Student.StudentInfoAddFrame siadd = new GUI.Student.StudentInfoAddFrame();
        siadd.setLocation(400, 200);
        siadd.setSize(592, 350);
        siadd.setVisible(true);
        siadd.setResizable(false);
        siadd.validate();
    }

    //
//	// 学生管理模块
    public void jMenuItem_stuInfoInquire_actionPerformed(ActionEvent e) throws PropertyVetoException, SQLException {
        StudentManager siadd = new StudentManager();
        siadd.setLocation(250, 60);
        siadd.setSize(800, 620);
        siadd.setVisible(true);
        siadd.setResizable(false);
        siadd.validate();
    }

    //	// 课程录入模块
    public void jMenuItem_courceInput_actionPerformed(ActionEvent e) throws PropertyVetoException, SQLException {
        CourceAddFrame siadd = new CourceAddFrame();
        siadd.setLocation(400, 200);
        siadd.setSize(482, 320);
        siadd.setVisible(true);
        siadd.setResizable(false);
        siadd.validate();
    }

    public void jMenuItem_departInput_actionPerformed(ActionEvent e) {
        // do报表
        int sl = JOptionPane.showOptionDialog(null, "请选择需要按照总分排序", "报表", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"正序","倒序"}, "正序");
        Student_Grade_ToExecl execl = new Student_Grade_ToExecl();
        try {
            execl.createGrades(control.read(null),sl);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (mError ex) {
            throw new RuntimeException(ex);
        } catch (PropertyVetoException ex) {
            throw new RuntimeException(ex);
        }
    }


    // 课程管理模块
    public void jMenuItem_courceManager_actionPerformed(ActionEvent e) throws PropertyVetoException, SQLException {
        CourceManager siadd = new CourceManager();
        siadd.setLocation(400, 100);
        siadd.setSize(530, 560);
        siadd.setVisible(true);
        siadd.setResizable(false);
        siadd.validate();
    }

    //
//	// 成绩录入模块
    public void jMenuItem_scoreInput_actionPerformed(ActionEvent e) throws PropertyVetoException, SQLException {
        GradeAddFrame siadd = new GradeAddFrame();
        siadd.setLocation(400, 200);
        siadd.setSize(482, 320);
        siadd.setVisible(true);
        siadd.setResizable(false);
        siadd.validate();

    }

    //
//	// 成绩管理模块
    public void jMenuItem_scoreManager_actionPerformed(ActionEvent e) throws PropertyVetoException, SQLException {
        GradeManager siadd = new GradeManager();
        siadd.setLocation(400, 100);
        siadd.setSize(800, 820);
        siadd.setVisible(true);
        siadd.setResizable(false);
        siadd.validate();
    }

    //
//}
//
    class MainFrame_jMenuItem_courceManager_actionAdapter implements ActionListener {
        private MainFrame adaptee;

        MainFrame_jMenuItem_courceManager_actionAdapter(MainFrame adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            try {
                adaptee.jMenuItem_courceManager_actionPerformed(e);
            } catch (PropertyVetoException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    //
    class MainFrame_jMenuItem_scoreManager_actionAdapter implements ActionListener {
        private MainFrame adaptee;

        MainFrame_jMenuItem_scoreManager_actionAdapter(MainFrame adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            try {
                adaptee.jMenuItem_scoreManager_actionPerformed(e);
            } catch (PropertyVetoException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    //
    class MainFrame_jMenuItem_stuInfoInquire_actionAdapter implements ActionListener {
        private MainFrame adaptee;

        MainFrame_jMenuItem_stuInfoInquire_actionAdapter(MainFrame adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            try {
                adaptee.jMenuItem_stuInfoInquire_actionPerformed(e);
            } catch (PropertyVetoException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    //
//class MainFrame_jMenuItem_classManager_actionAdapter implements ActionListener {
//	private MainFrame adaptee;
//
//	MainFrame_jMenuItem_classManager_actionAdapter(MainFrame adaptee) {
//		this.adaptee = adaptee;
//	}
//
//	public void actionPerformed(ActionEvent e) {
//		adaptee.jMenuItem_classManager_actionPerformed(e);
//	}
//}
//
//class MainFrame_jMenuItem_classInput_actionAdapter implements ActionListener {
//	private MainFrame adaptee;
//
//	MainFrame_jMenuItem_classInput_actionAdapter(MainFrame adaptee) {
//		this.adaptee = adaptee;
//	}
//
//	public void actionPerformed(ActionEvent e) {
//		adaptee.jMenuItem_classInput_actionPerformed(e);
//	}
//}
//
//class MainFrame_jMenuItem_departManager_actionAdapter implements ActionListener {
//	private MainFrame adaptee;
//
//	MainFrame_jMenuItem_departManager_actionAdapter(MainFrame adaptee) {
//		this.adaptee = adaptee;
//	}
//
//	public void actionPerformed(ActionEvent e) {
//		adaptee.jMenuItem_departManager_actionPerformed(e);
//	}
//}
//
    class MainFrame_jMenuItem_departInput_actionAdapter implements ActionListener {
        private MainFrame adaptee;

        MainFrame_jMenuItem_departInput_actionAdapter(MainFrame adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            adaptee.jMenuItem_departInput_actionPerformed(e);
        }
    }

    //
    class MainFrame_jMenuItem_courceInput_actionAdapter implements ActionListener {
        private MainFrame adaptee;

        MainFrame_jMenuItem_courceInput_actionAdapter(MainFrame adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            try {
                adaptee.jMenuItem_courceInput_actionPerformed(e);
            } catch (PropertyVetoException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    //
    class MainFrame_jMenuItem_scoreInput_actionAdapter implements ActionListener {
        private MainFrame adaptee;

        MainFrame_jMenuItem_scoreInput_actionAdapter(MainFrame adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            try {
                adaptee.jMenuItem_scoreInput_actionPerformed(e);
            } catch (PropertyVetoException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    //
//class MainFrame_jMenuItem_userSignUp_actionAdapter implements ActionListener {
//	private MainFrame adaptee;
//
//	MainFrame_jMenuItem_userSignUp_actionAdapter(MainFrame adaptee) {
//		this.adaptee = adaptee;
//	}
//
//	public void actionPerformed(ActionEvent e) {
//
//		adaptee.jMenuItem_userSignUp_actionPerformed(e);
//	}
//}
//
//class MainFrame_jMenuItem_userChangePwd_actionAdapter implements ActionListener {
//	private MainFrame adaptee;
//
//	MainFrame_jMenuItem_userChangePwd_actionAdapter(MainFrame adaptee) {
//		this.adaptee = adaptee;
//	}
//
//	public void actionPerformed(ActionEvent e) {
//		adaptee.jMenuItem_userChangePwd_actionPerformed(e);
//	}
//}
//
//class MainFrame_jMenuItem_userDelete_actionAdapter implements ActionListener {
//	private MainFrame adaptee;
//
//	MainFrame_jMenuItem_userDelete_actionAdapter(MainFrame adaptee) {
//		this.adaptee = adaptee;
//	}
//
//	public void actionPerformed(ActionEvent e) {
//		adaptee.jMenuItem_userDelete_actionPerformed(e);
//	}
//}
//
//class MainFrame_jMenuItem_about_actionAdapter implements ActionListener {
//	private MainFrame adaptee;
//
//	MainFrame_jMenuItem_about_actionAdapter(MainFrame adaptee) {
//		this.adaptee = adaptee;
//	}
//
//	public void actionPerformed(ActionEvent e) {
//		adaptee.jMenuItem_about_actionPerformed(e);
//	}
//}
//
    class MainFrame_jMenuItem_quit_actionAdapter implements ActionListener {
        private MainFrame adaptee;

        MainFrame_jMenuItem_quit_actionAdapter(MainFrame adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            adaptee.jMenuItem_quit_actionPerformed(e);
        }
    }

    //
    class MainFrame_jMenuItem_stuInfoSignUp_actionAdapter implements ActionListener {
        private MainFrame adaptee;

        MainFrame_jMenuItem_stuInfoSignUp_actionAdapter(MainFrame adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            try {
                adaptee.jMenuItem_stuInfoSignUp_actionPerformed(e);
            } catch (PropertyVetoException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    //
    class MainFrame_jMenuItem_reboot_actionAdapter implements ActionListener {
        private MainFrame adaptee;

        MainFrame_jMenuItem_reboot_actionAdapter(MainFrame adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            adaptee.jMenuItem_reboot_actionPerformed(e);
        }
    }
}
