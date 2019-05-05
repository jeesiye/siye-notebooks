&nbsp;&nbsp;在 linux 中,开启 ip 代理转发功能的方式有两种,一种是临时开启,一种是永久性的开启.

- 临时开启 ip 代理转发的功能

&nbsp;&nbsp;只需要修改`/proc/sys/net/ipv4/ip_forward`文件,就可临时开启`ip代理转发`的功能,此文件只有一个数字,0 或者 1.其中`0`表示关闭 ip 代理转发功能,`1`表示开启 ip 代理转发功能.  
&nbsp;&nbsp;这种方式是临时性的,当系统关机或者重启后就会失效.另外,此种方式的修改,只要配置文件保存后就会立即生效,无需做其他的额外操作.

```
[root@centos7 ~]#
[root@centos7 ~]# ll /proc/sys/net/ipv4/ip_forward
-rw-r--r-- 1 root root 0 Mar 25 15:37 /proc/sys/net/ipv4/ip_forward
[root@centos7 ~]# cat /proc/sys/net/ipv4/ip_forward
0
[root@centos7 ~]# echo 1 > /proc/sys/net/ipv4/ip_forward
[root@centos7 ~]# cat /proc/sys/net/ipv4/ip_forward
1
[root@centos7 ~]#
```

- 永久的开启 ip 代理转发功能

&nbsp;&nbsp;在`/etc/sysctl.conf`的配置文件中,修改变量`net.ipv4.ip_forward`的值为 1;若此变量没有,那就添加.修改后,可以重启系统`sync; init 6`,也可以运行`sysctl -p`让其立即生效.

```
[root@centos7 ~]#
[root@centos7 ~]# cat /etc/sysctl.conf
# sysctl settings are defined through files in
# /usr/lib/sysctl.d/, /run/sysctl.d/, and /etc/sysctl.d/.
#
# Vendors settings live in /usr/lib/sysctl.d/.
# To override a whole file, create a new file with the same in
# /etc/sysctl.d/ and put new settings there. To override
# only specific settings, add a file with a lexically later
# name in /etc/sysctl.d/ and put new settings there.
#
# For more information, see sysctl.conf(5) and sysctl.d(5).
[root@centos7 ~]# echo net.ipv4.ip_forward=1 >> /etc/sysctl.conf
[root@centos7 ~]# cat /etc/sysctl.conf
# sysctl settings are defined through files in
# /usr/lib/sysctl.d/, /run/sysctl.d/, and /etc/sysctl.d/.
#
# Vendors settings live in /usr/lib/sysctl.d/.
# To override a whole file, create a new file with the same in
# /etc/sysctl.d/ and put new settings there. To override
# only specific settings, add a file with a lexically later
# name in /etc/sysctl.d/ and put new settings there.
#
# For more information, see sysctl.conf(5) and sysctl.d(5).
net.ipv4.ip_forward=1
[root@centos7 ~]# sysctl -p
net.ipv4.ip_forward = 1
[root@centos7 ~]#
```
