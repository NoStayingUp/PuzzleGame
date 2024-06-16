import com.jk.ui.GameJFrame;
import com.jk.ui.LoginJFrame;
import com.jk.ui.RegisterJFrame;

import java.io.IOException;

public class APP {
    public static void main(String[] args) throws IOException {
        //表示程序的启动入口

        //如果我们想要开启一个界面，就创建谁的对象就可以了
        new GameJFrame();
        //new LoginJFrame();
//        new RegisterJFrame();
    }
}
