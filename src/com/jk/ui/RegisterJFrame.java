package com.jk.ui;

import javax.swing.*;

public class RegisterJFrame extends JFrame {
    //所有跟注册相关的代码都写在这里

    public RegisterJFrame(){
        //设置界面宽高
        this.setSize(488,500);
        //设置界面的标题
        this.setTitle("拼图游戏 注册界面");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置页面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //让界面显示出来，放在最后
        this.setVisible(true);

    }
}
