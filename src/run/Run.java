package run;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import deal.GetText;

public class Run {
	
	public static int id = (int)(Math.random()*1000);
	
	static
	{
		try(InputStream is = new FileInputStream("id.txt"))  //从id.txt获取id
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			if(br.readLine().equals("isRandom = false"))  //如果并不是随机数,是指定id
			{
				String temp_id = "";
				String line = br.readLine();
				char temp[] = line.toCharArray();
				I:
				for(int i=0;i<temp.length;i++)
				{
					if(temp[i]=='=')
					{
						for(int j=i+2;j<temp.length;j++)
						{
							temp_id = temp_id + temp[j];
						}
						break I;
					}
				}
				id = Integer.valueOf(temp_id);
			}
			br.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("id:"+id);
		GetText gettext = new GetText();
		Thread thread_gettext = new Thread(gettext);
		thread_gettext.start();

	}

}
