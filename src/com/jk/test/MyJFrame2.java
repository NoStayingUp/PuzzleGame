package com.jk.test;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyJFrame2 extends JFrame implements KeyListener {
    public MyJFrame2(){
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

        //this：表示这个窗口界面
        //addKeyListener：表示给这个界面绑定键盘监听事件
        //函数参数为KeyListener的实现类
        //因为this实现了KeyListener，所以this就是KeyListener的实现类，可以作为函数参数传入
        this.addKeyListener(this);

        //让界面显示出来，放在最后
        this.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    //如果我们按住一个按键不松开，那么就会一直调用keyPressed方法
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("按下不松");

    }
    //如何区分按键？
    //通过参数KeyEvent e 的getKeyCode方法可以得到各个按键的编号
    //通过编号，就可以得到按下的按键
    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("松开");
        int keyCode = e.getKeyCode();
        if(keyCode == 65){
            System.out.println("按的是a");
        }else if(keyCode == 66){
            System.out.println("按的是b");
        }
    }
}
