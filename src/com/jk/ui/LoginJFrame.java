package com.jk.ui;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import com.jk.domain.User;
import com.jk.tool.CodeUtil;

public class LoginJFrame extends JFrame implements MouseListener {
    //跟登录相关的代码都写在这个界面

    //创建一个集合存储正确的用户名和密码
    static ArrayList<User> list = new ArrayList<>();
    static {
        list.add(new User("zhangsan","123"));
        list.add(new User("lisi","1234"));
        list.add(new User("jack","5521"));
    }

    //登录和注册按钮
    JButton login = new JButton();
    JButton register = new JButton();

    //用户名和密码和验证码输入框
    JTextField username = new JTextField();
    JTextField password = new JTextField();
    JTextField code = new JTextField();

    //管理验证码
    JLabel rightCode = new JLabel();


    public LoginJFrame() {
        //初始化界面
        initJFrame();

        //在这个界面中添加内容
        initView();

        //让当前界面显示出来
        this.setVisible(true);
    }

    public void initView() {
        //1. 添加用户名文字
        JLabel usernameText = new JLabel(new ImageIcon("image\\login\\用户名.png"));
        usernameText.setBounds(116, 135, 47, 17);
        this.getContentPane().add(usernameText);

        //2.添加用户名输入框
        username.setBounds(195, 134, 200, 30);
        this.getContentPane().add(username);

        //3.添加密码文字
        JLabel passwordText = new JLabel(new ImageIcon("image\\login\\密码.png"));
        passwordText.setBounds(130, 195, 32, 16);
        this.getContentPane().add(passwordText);

        //4.密码输入框
        password.setBounds(195, 195, 200, 30);
        this.getContentPane().add(password);

        //验证码提示
        JLabel codeText = new JLabel(new ImageIcon("image\\login\\验证码.png"));
        codeText.setBounds(133, 256, 50, 30);
        this.getContentPane().add(codeText);

        //验证码的输入框
        code.setBounds(195, 256, 100, 30);
        this.getContentPane().add(code);

        //CodeUtil为自己编写的工具类，通过导包引入
        String codeStr = CodeUtil.getCode();
        //rightCode为管理验证码的地方
        //给其绑定单击事件，单机后更换验证码
        rightCode.addMouseListener(this);
        //设置内容
        rightCode.setText(codeStr);
        //位置和宽高
        rightCode.setBounds(300, 256, 50, 30);
        //添加到界面
        this.getContentPane().add(rightCode);

        //5.添加登录按钮
        login.setBounds(123, 310, 128, 47);
        login.setIcon(new ImageIcon("image\\login\\登录按钮.png"));
        //去除按钮的默认边框
        login.setBorderPainted(false);
        //去除按钮的默认背景
        login.setContentAreaFilled(false);
        this.getContentPane().add(login);
        login.addMouseListener(this);


        //6.添加注册按钮
        register.setBounds(256, 310, 128, 47);
        register.setIcon(new ImageIcon("image\\login\\注册按钮.png"));
        //去除按钮的默认边框
        register.setBorderPainted(false);
        //去除按钮的默认背景
        register.setContentAreaFilled(false);
        this.getContentPane().add(register);
        register.addMouseListener(this);

        //7.添加背景图片
        JLabel background = new JLabel(new ImageIcon("image\\login\\background.png"));
        background.setBounds(0, 0, 470, 390);
        this.getContentPane().add(background);
    }


    public void initJFrame() {
        this.setSize(488, 430);//设置宽高
        this.setTitle("拼图游戏 V1.0登录");//设置标题
        this.setDefaultCloseOperation(3);//设置关闭模式
        this.setLocationRelativeTo(null);//居中
        this.setAlwaysOnTop(true);//置顶
        this.setLayout(null);//取消内部默认布局
    }


    //要展示用户名或密码错误
    //showJDialog(text)  会在弹框中展示提升信息text
    public void showJDialog(String content) {
        //创建一个弹框对象
        JDialog jDialog = new JDialog();
        //给弹框设置大小
        jDialog.setSize(200, 150);
        //让弹框置顶
        jDialog.setAlwaysOnTop(true);
        //让弹框居中
        jDialog.setLocationRelativeTo(null);
        //弹框不关闭永远无法操作下面的界面
        jDialog.setModal(true);

        //创建Jlabel对象管理文字并添加到弹框当中
        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, 200, 150);
        jDialog.getContentPane().add(warning);

        //让弹框展示出来
        jDialog.setVisible(true);
    }

    //点击
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    //按住不松
    @Override
    public void mousePressed(MouseEvent e) {
        Object obj = e.getSource();
        if(obj == login){
            System.out.println("登录");
            login.setIcon(new ImageIcon("image\\login\\登录按下.png"));
            //this.getContentPane().repaint();
        }else if(obj == register){
            System.out.println("注册");
            register.setIcon(new ImageIcon("image\\login\\注册按下.png"));
        }
    }
    //松开
    @Override
    public void mouseReleased(MouseEvent e) {
        Object obj = e.getSource();
        if(obj == login){
            //换回按钮颜色
            login.setIcon(new ImageIcon("image\\login\\登录按钮.png"));

            String username = this.username.getText();
            String password = this.password.getText();
            //创建一个用户对象，看这个对象是否在list里
            User user = new User(username, password);
            //得到用户输入的验证码和新验证码
            String code = this.code.getText();
            String rightCode = this.rightCode.getText();
            //忽略大小写的比较
            if(!code.equalsIgnoreCase(rightCode) ){
                showJDialog("验证码错误");
                return;
            }
            if(username.length() == 0 || password.length() == 0){
                showJDialog("用户名或密码不可为空");
                return;
            }
            if(!contains(user)){
                showJDialog("用户名或密码错误");
                return;
            }
            System.out.println("登录成功");
            //关闭登录界面
            this.setVisible(false);
            //打开游戏界面
            new GameJFrame();
        }else if(obj == register){
            register.setIcon(new ImageIcon("image\\login\\注册按钮.png"));
        }else if(obj == rightCode){
            System.out.println("更换验证码");
            rightCode.setText(CodeUtil.getCode());
        }
    }

    private boolean contains(User user) {
        for (User user1 : list) {
            if(user1.getName().equals(user.getName()) && user1.getPassword().equals(user.getPassword())){
                return true;
            }
        }
        return false;
    }

    //划入
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    //划出
    @Override
    public void mouseExited(MouseEvent e) {

    }
}
