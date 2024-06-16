package com.jk.ui;

import com.jk.domain.GameInfo;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.Properties;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    //JFrame 界面 窗体
    //子类自然也表示界面，窗体
    //规定：GameJFrame这个界面表示的就是游戏的主界面
    //以后跟游戏相关的所有逻辑都写在这里

    //创建一个二维数组
    //目的：管理数据
    //加载图片时，会根据二维数组中的数据进行加载
    int[][] data = new int[4][4];

    //x和y是空白方块在二维数组里的坐标
    int x;
    int y;

    //统计步数
    int step;

    //定义一个变量记录图片路径
    String path = "image\\girl\\girl2\\";

    //创建选项下面的选择条目
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");
    JMenuItem girl = new JMenuItem("美女");
    JMenuItem animal = new JMenuItem("动物");
    JMenuItem sport = new JMenuItem("运动");

    JMenu saveJMenu = new JMenu("存档");
    JMenu loadJMenu = new JMenu("读档");

    JMenuItem saveItem0 = new JMenuItem("存档0(空)");
    JMenuItem saveItem1 = new JMenuItem("存档1(空)");
    JMenuItem saveItem2 = new JMenuItem("存档2(空)");
    JMenuItem saveItem3 = new JMenuItem("存档3(空)");
    JMenuItem saveItem4 = new JMenuItem("存档4(空)");

    JMenuItem loadItem0 = new JMenuItem("读档0(空)");
    JMenuItem loadItem1 = new JMenuItem("读档1(空)");
    JMenuItem loadItem2 = new JMenuItem("读档2(空)");
    JMenuItem loadItem3 = new JMenuItem("读档3(空)");
    JMenuItem loadItem4 = new JMenuItem("读档4(空)");

    JMenuItem accountItem = new JMenuItem("公众号");

        public GameJFrame(){
        //初始化界面
        initJFrame();

        //初始化菜单
        initJMenuBar();

        //初始化数据
        initData();

        //初始化图片
        initImage();

        //让界面显示出来，放在最后
        //如果是false，那么就不会有窗口出现
        this.setVisible(true);
    }

    //初始化数据
    private void initData() {
        //1、定义一个一维数组
        int[] arr = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        //2、打乱数组中数据的顺序
        //遍历数组，得到每一个元素，然后那着每一个元素跟随机生成的索引上的数据进行交换
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            //得到随机索引
            int index = r.nextInt(arr.length);
            int t = arr[i];
            arr[i] = arr[index];
            arr[index] = t;
        }
        //4、给二维数组添加数据
        //解法一：
        for (int i = 0; i < arr.length; i++) {
            data[i / 4][i % 4] = arr[i];
            if(arr[i] == 0){
                x = i / 4;
                y = i % 4;
            }
        }
//        //解法二：
//        //遍历二维数组，给里面的每一个数据赋值
//        for (int i = 0; i < data.length; i++) {
//            for (int j = 0; j < data[i].length; j++) {
//                data[i][j] = arr[i * 4 + j];
//            }
//        }
    }

    //初始化图片
    private void initImage() {

        //在加载新图片前要把原有的图片全部删除
        //不然新的图片会被塞在后面
        this.getContentPane().removeAll();

        if(victory()){
            JLabel win = new JLabel(new ImageIcon("image\\win.png"));
            win.setBounds(203,283,197,73);
            this.getContentPane().add(win);
        }

/*        //创建一个imageIcon的对象
        ImageIcon icon = new ImageIcon("D:\\400-Code\\java\\PuzzleGame\\image\\animal\\animal3\\1.jpg");
        //创建一个JLabel的对象
        JLabel jLabel = new JLabel(icon);
        //指定图片位置
        jLabel.setBounds(0,0,105,105);
        //把管理容器添加到界面中
        //this.add(jLabel);
        //JFrame只是一个大架子
        //getContentPane()得到的JFrame内置的隐藏对象才是管理组件的
        this.getContentPane().add(jLabel);*/

        //把管理容器添加到界面中
        //按二维数组中管理的数据添加图片
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int num = data[i][j];
                //创建一个JLabel的对象
                //使用相对路径
                JLabel jLabel = new JLabel(new ImageIcon( path + num + ".jpg"));
                //指定图片位置
                jLabel.setBounds(105*j + 83,105*i + 134,105,105);
                //给图片设置边框
                //0：凸起来  RAISED
                //1：凹进去  LOWERED
                //为了更清晰直观，Java在BevelBorder中定义了常量代表它们
                jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                //把管理容器添加到界面中
                this.getContentPane().add(jLabel);
            }
        }

        //添加步数
        JLabel stepCount = new JLabel("步数" + step);
        stepCount.setBounds(50,30,100,20);
        this.getContentPane().add(stepCount);

        //添加背景图片
        //细节：先添加的图片在上面，后添加的会塞到后面去
        JLabel background = new JLabel(new ImageIcon("image\\background.png"));
        //设置宽高
        background.setBounds(40, 40, 508, 560);
        //添加到隐藏容器中
        this.getContentPane().add(background);

        //最后刷新一下隐藏容器的界面
        this.getContentPane().repaint();
    }

    private void initJMenuBar() {
        //初始化菜单
        JMenuBar jMenuBar = new JMenuBar();

        //创建菜单上的两个选项（功能  关于我们)
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");

        //创建更换图片
        JMenu changeImage = new JMenu("更换图片");

        //把5个存档，添加到saveJMenu中
        saveJMenu.add(saveItem0);
        saveJMenu.add(saveItem1);
        saveJMenu.add(saveItem2);
        saveJMenu.add(saveItem3);
        saveJMenu.add(saveItem4);

        //把5个读档，添加到loadJMenu中
        loadJMenu.add(loadItem0);
        loadJMenu.add(loadItem1);
        loadJMenu.add(loadItem2);
        loadJMenu.add(loadItem3);
        loadJMenu.add(loadItem4);

        //创建选项下面的选择条目
        //由于在事件监听的方法中要用到这些条目，所以让它们成为成员变量
//        JMenuItem replayItem = new JMenuItem("重新游戏");

        //将每一个选项下的条目添加到选项中
        functionJMenu.add(changeImage);
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);
        functionJMenu.add(saveJMenu);
        functionJMenu.add(loadJMenu);

        aboutJMenu.add(accountItem);

        //把美女，动物，运动添加到更换图片当中
        changeImage.add(girl);
        changeImage.add(animal);
        changeImage.add(sport);


        //给每个条目绑定事件
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);
        girl.addActionListener(this);
        animal.addActionListener(this);
        sport.addActionListener(this);
        saveItem0.addActionListener(this);
        saveItem1.addActionListener(this);
        saveItem2.addActionListener(this);
        saveItem3.addActionListener(this);
        saveItem4.addActionListener(this);
        loadItem0.addActionListener(this);
        loadItem1.addActionListener(this);
        loadItem2.addActionListener(this);
        loadItem3.addActionListener(this);
        loadItem4.addActionListener(this);

        //将两个选项添加到菜单当中
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        //读取存档信息，修改菜单上表示的内容
        getGameInfo();

        //给整个界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    public void getGameInfo(){
        //1、创建一个File对象表示所有存档所在的文件夹
        File file = new File("save");
        //2、进入文件夹，获取里面的存档文件
        File[] files = file.listFiles();
        //3、遍历数组，得到里面每一个存档
        for (File f : files) {
            //f：依次表示每一个存档文件
            //获取每一个存档文件中的步数
            GameInfo gi = null;
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
                gi = (GameInfo) ois.readObject();
                ois.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            //获取步数
            int step = gi.getStep();

            //把存档的步数同步到菜单中
            //save0 --> 0

            //获取存档的文件名save0.data
            String name = f.getName();
            int index = name.charAt(4) - '0';
            //修改菜单上所表示的文字信息
            saveJMenu.getItem(index).setText("存档" + index + "(" + step + "步)");
            loadJMenu.getItem(index).setText("存档" + index + "(" + step + "步)");
        }
    }


    private void initJFrame() {
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

        //与键盘监听事件关联
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //按住不松
    @Override
    public void keyPressed(KeyEvent e) {
        //a 65
        int code = e.getKeyCode();
        if(code == 65){
            this.getContentPane().removeAll();
            //加载完整图片
            JLabel all = new JLabel(new ImageIcon(path + "all.jpg"));
            all.setBounds(83,134,420,420);
            this.getContentPane().add(all);
            //加载背景图片
            //细节：先添加的图片在上面，后添加的会塞到后面去
            JLabel background = new JLabel(new ImageIcon("image\\background.png"));
            //设置宽高
            background.setBounds(40, 40, 508, 560);
            //添加到隐藏容器中
            this.getContentPane().add(background);

            //最后刷新一下隐藏容器的界面
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if(victory()){
            //结束方法
            return;
        }

        int code = e.getKeyCode();
        //对上下左右进行判断
        //左：37 上：38 右：39 下：40
        System.out.println(code);
        if(code == 37){
            if(y == 3){
                //此时到达右边界
                return;
            }
            System.out.println("向左移动");
            //即空白方块（x，y）与其右方方块（x，y+1）交换
            data[x][y] = data[x][y+1];
            data[x][y+1] = 0;
            y++;
            //每走一步，步数加1
            step++;
            //调用初始化图片的方法加载新的图片
            initImage();
        }else if(code == 38){
            if(x == 3){
                //此时到达下边界
                return;
            }
            System.out.println("向上移动");
            //即空白方块（x，y）与其下方方块（x+1，y）交换
            data[x][y] = data[x+1][y];
            data[x+1][y] = 0;
            x++;
            //每走一步，步数加1
            step++;
            //调用初始化图片的方法加载新的图片
            initImage();
        }else if(code == 39){
            if(y == 0){
                //此时到达左边界
                return;
            }
            System.out.println("向右移动");
            //即空白方块（x，y）与其左方方块（x，y-1）交换
            data[x][y] = data[x][y-1];
            data[x][y-1] = 0;
            y--;
            //每走一步，步数加1
            step++;
            //调用初始化图片的方法加载新的图片
            initImage();
        }else if(code == 40){
            if(x == 0){
                //此时到达上边界
                return;
            }
            System.out.println("向下移动");
            //即空白方块（x，y）与其上方方块（x-1，y）交换
            data[x][y] = data[x-1][y];
            data[x-1][y] = 0;
            x--;
            //每走一步，步数加1
            step++;
            //调用初始化图片的方法加载新的图片
            initImage();
        }
        else if(code == 65){
            initImage();
        }else if(code == 87){
            data = new int[][]{
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,0}
            };
            initImage();
        }
    }

    public boolean victory(){
        int[][] win = {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,0},
        };
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if(win[i][j] != data[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj == replayItem){

            //步数清零,一定要在加载图片前
            step = 0;
            //打乱图片
            initData();
            //重新加载图片
            initImage();
        }else if(obj == reLoginItem){
            //关闭当前界面
            this.setVisible(false);
            //打开登录界面
            try {
                new LoginJFrame();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }else if(obj == closeItem){
            //可以直接把虚拟机关掉
            System.exit(0);
        }else if(obj == accountItem){

            //1、创建集合Properties
            Properties prop = new Properties();
            //2、读取数据
            try {
                FileInputStream fis = new FileInputStream("game.properties");
                prop.load(fis);
                fis.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            String path = (String) prop.get("account");

            //弹窗对象JDialog
            JDialog jDialog = new JDialog();
            //创建一个管理图片的容器对象JLabel
            JLabel jLabel = new JLabel(new ImageIcon(path));
            //设置位置和宽高
            jLabel.setBounds(0,0,258,258);
            //把图片添加到弹框中
            jDialog.getContentPane().add(jLabel);
            //给弹框设置大小
            jDialog.setSize(344,344);
            //让弹框置顶
            jDialog.setAlwaysOnTop(true);
            //让弹框居中
            jDialog.setLocationRelativeTo(null);
            //弹框不关闭则无法操作下面的界面
            jDialog.setModal(true);
            //让弹框显示出来
            jDialog.setVisible(true);
        }else if(obj == girl){
            //从13组美女图片中随机选择一组
            Random r = new Random();
            int i = r.nextInt(13) + 1;
            //更新path的值
            path = "image\\girl\\girl" + i + "\\";
            System.out.println(path);
            //重新加载图片,类似重开一把的逻辑
             // 步数清零,一定要在加载图片前
             step = 0;
             //打乱图片
             initData();
             //重新加载图片
             initImage();
        }else if(obj == animal){
            //从8组动物图片中随机选择一组
            Random r = new Random();
            int i = r.nextInt(8) + 1;
            //更新path的值
            path = "image\\animal\\animal" + i + "\\";
            System.out.println(path);
            //重新加载图片,类似重开一把的逻辑
            // 步数清零,一定要在加载图片前
            step = 0;
            //打乱图片
            initData();
            //重新加载图片
            initImage();
        }else if(obj == sport){
            //从10组运动图片中随机选择一组
            Random r = new Random();
            int i = r.nextInt(10) + 1;
            //更新path的值
            path = "image\\sport\\sport" + i + "\\";
            System.out.println(path);
            //重新加载图片,类似重开一把的逻辑
            // 步数清零,一定要在加载图片前
            step = 0;
            //打乱图片
            initData();
            //重新加载图片
            initImage();
        }else if(obj == saveItem0 || obj == saveItem1 || obj == saveItem2 || obj == saveItem3 ||obj == saveItem4){
            //知道是哪个选项被点击了，获取其序号
            JMenuItem item = (JMenuItem) obj;
            String str = item.getText();
            int index = str.charAt(2) - '0';
            //直接把游戏的数据写到本地文件中
            //IO流  对象整体写到文件 且不能更改  序列化
            try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("save\\save" + index + ".data"));
                GameInfo gi = new GameInfo(data,x,y,path,step);
                oos.writeObject(gi);
                oos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            //修改一下存档item上的展示信息
            //存档0(xx步)
            item.setText("存档" + index + "(" + step + "步)");
            //修改一下读档item上的展示信息
            loadJMenu.getItem(index).setText("存档" + index + "(" + step + "步)");
        }else if(obj == loadItem0 || obj == loadItem1 || obj == loadItem2 || obj == loadItem3 ||obj == loadItem4){
            //知道是哪个选项被点击了，获取其序号
            JMenuItem item = (JMenuItem) obj;
            String str = item.getText();
            int index = str.charAt(2) - '0';

            //有序号后可以到本地文件中读取数据
            GameInfo gi = null;
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("save\\save" + index + ".data"));
                gi = (GameInfo)ois.readObject();
                ois.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            data = gi.getData();
            x = gi.getX();
            y = gi.getY();
            step = gi.getStep();
            path = gi.getPath();

            //重新刷新界面加载游戏
            initImage();
        }
    }
}
