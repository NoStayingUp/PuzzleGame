package com.jk.test;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyJFrame extends JFrame implements MouseListener {

    //创建一个按钮对象
    JButton jbt = new JButton("点我啊");

    public MyJFrame() {
        //设置界面宽高
        this.setSize(603,680);
        //设置界面的标题
        this.setTitle("拼图游戏jk版 v1.0");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置页面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //取消默认的居中放置，只有取消了才会按xy轴的方式添加组件
        this.setLayout(null);

        //设置位置和宽高
        jbt.setBounds(0,0,100,100);
        //给按钮绑定鼠标事件
        jbt.addMouseListener(this);

        this.getContentPane().add(jbt);

        //让界面显示出来，放在最后
        this.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("单击");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("按住不松");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("松开");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("划入");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("划出");
    }
}
