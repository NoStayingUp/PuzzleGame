package com.jk.test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class test1 {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        //设置界面宽高
        jFrame.setSize(603,680);
        //设置界面的标题
        jFrame.setTitle("拼图游戏jk版 v1.0");
        //设置界面置顶
        jFrame.setAlwaysOnTop(true);
        //设置页面居中
        jFrame.setLocationRelativeTo(null);
        //设置关闭模式
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //取消默认的居中放置，只有取消了才会按xy轴的方式添加组件
        jFrame.setLayout(null);

        //创建一个按钮对象
        JButton jtb = new JButton("点我啊");
        //设置位置和宽高
        jtb.setBounds(0,0,100,100);
        //给按钮添加动作监听
        //jtb：组件对象，表示你要给哪个组件添加事件
        //addActionListener：表示我要给哪个组件添加事件监听（动作监听只有鼠标左键点击和空格，是键盘监听和鼠标监听的简化版）
        jtb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("按钮被点击了");
            }
        });
        jFrame.getContentPane().add(jtb);

        jFrame.setVisible(true);

    }
}
