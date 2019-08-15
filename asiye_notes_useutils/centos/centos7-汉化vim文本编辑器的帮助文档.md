- `http://vimcdoc.sourceforge.net/`官网下载`vimcdoc-<version>.tar.gz`;
- 使用`lrzsz`工具包的`rz`指令,将下载包上传到`unix`主机上;
- 参考安装手册;

```bash
[root@centos7 ~]#
[root@centos7 ~]# pwd
/root
[root@centos7 ~]# ll | grep vimcdoc
drwxr-xr-x.  3 97206  544     210 Mar 11 15:52 vimcdoc-1.8.0
-rw-r--r--.  1 root  root 1844817 Mar  7 13:16 vimcdoc-1.8.0.tar.gz
[root@centos7 ~]# cat -n vimcdoc-1.8.0/INSTALL | head -33 | tail -12
    22  +-----------+
    23  |  INSTALL  |
    24  +-----------+
    25  Linux：
    26  下载的 tar.gz 包括所有翻译过的 vim 文档 (.cnx 文件)。
    27  先将其解压缩：
    28      tar zxvf vimcdoc-<version>.tar.gz
    29  然后进入 vimcdoc-<version> 目录并执行
    30      ./vimcdoc.sh -i
    31  就可以了。该安装程序会自动识别 Vim 的安装路径，将中文的文档拷贝
    32  到相应的地方。原有的英文文档不受影响。
    33
[root@centos7 ~]#
```

centos 的安装效果图如下 :  
![](assets/centos7-汉化vim文本编辑器的帮助文档-47c0e617.png)  
ubuntu 的安装效果图如下 :  
![](assets/centos7-汉化vim文本编辑器的帮助文档-66cb7814.png)
