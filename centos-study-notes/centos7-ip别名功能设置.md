#### IP 别名功能的配置

- ip 别名功能的介绍

&#160;&#160;在 linux 的环境下,一个网络设备接口是可以虚拟出多个接口.配置文件名的格式如`ensx:x`或者是`ethx:x`,这样的效果就是让一块网卡设备绑定多个 ip 地址.一块网卡设备绑定多个 ip 地址就称作`ip的别名功能`.  
&nbsp;&nbsp;实现的方式很简单,就是在原有的配置文件目录中,复制需要实现 ip 别名功能的网卡配置文件,修改添加的 ip 地址的配置参数后,加载该配置文件即可.  
&nbsp;&nbsp;以 centos7.x 为例,在`/etc/sysconfig/network-scripts/`目录中,复制`ifcfg-ens32`文件,将其命名为`ifcfg-ens32:0`,修改复制后的网络配置参数,然后重启加载启动网卡配置服务,就可实现 ip 别名的功能.若是配置多个别名,继续复制多个配置文件即可,但保证文件名称不重复.譬如`ifcfg-ens32:1`,`ifcfg-ens32:2`,`ifcfg-ens32:3`.

---

> 实现步骤

- 在网络配置文件的目录下,复制指定网卡的配置文件,文件名设置为 ifcfg-ens32:0

```
[root@centos7 ~]#
[root@centos7 ~]# cd /etc/sysconfig/network-scripts/
[root@centos7 network-scripts]# cp ifcfg-ens32 ifcfg-ens32:0
[root@centos7 network-scripts]# ll | grep ifcfg
-rw-r--r--  1 root root   197 Mar 25 13:45 ifcfg-ens32
-rw-r--r--  1 root root   197 Mar 25 15:32 ifcfg-ens32:0
-rw-r--r--. 1 root root   254 May  3  2017 ifcfg-lo
[root@centos7 network-scripts]#
```

- 修改额外绑定的网络接口的配置文件

&nbsp;&nbsp;只是简单的测试,绑定同网段闲置的 ip 地址即可,注意不要造成 ip 地址冲突.

```
[root@centos7 network-scripts]#
[root@centos7 network-scripts]# diff ifcfg-ens32 ifcfg-ens32:0
2c2
< IPADDR=192.168.44.133
---
> IPADDR=192.168.44.134
[root@centos7 network-scripts]#
```

- 重启网络服务,使用 service 或 systemctl 都可.

```
[root@centos7 network-scripts]#
[root@centos7 network-scripts]# cd
[root@centos7 ~]# service network restart
Restarting network (via systemctl):  [  OK  ]
[root@centos7 ~]#
```

- 查看是否正确配置

```
[root@centos7 ~]#
[root@centos7 ~]# ip addr show
1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
    inet 127.0.0.1/8 scope host lo
       valid_lft forever preferred_lft forever
    inet6 ::1/128 scope host
       valid_lft forever preferred_lft forever
2: ens32: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc pfifo_fast state UP qlen 1000
    link/ether 00:0c:29:d3:24:1b brd ff:ff:ff:ff:ff:ff
    inet 192.168.44.133/24 brd 192.168.44.255 scope global ens32
       valid_lft forever preferred_lft forever
    inet 192.168.44.134/24 brd 192.168.44.255 scope global secondary ens32:0
       valid_lft forever preferred_lft forever
    inet6 fe80::20c:29ff:fed3:241b/64 scope link
       valid_lft forever preferred_lft forever
[root@centos7 ~]# ping 192.168.44.134
PING 192.168.44.134 (192.168.44.134) 56(84) bytes of data.
64 bytes from 192.168.44.134: icmp_seq=1 ttl=64 time=0.028 ms
64 bytes from 192.168.44.134: icmp_seq=2 ttl=64 time=0.052 ms
^C
--- 192.168.44.134 ping statistics ---
2 packets transmitted, 2 received, 0% packet loss, time 1000ms
rtt min/avg/max/mdev = 0.028/0.040/0.052/0.012 ms
[root@centos7 ~]#
```

- 在宿主机上验证是否可正常通信

```
C:\Users\admin>
C:\Users\admin>ping 192.168.44.134

正在 Ping 192.168.44.134 具有 32 字节的数据:
来自 192.168.44.134 的回复: 字节=32 时间<1ms TTL=64
来自 192.168.44.134 的回复: 字节=32 时间<1ms TTL=64
来自 192.168.44.134 的回复: 字节=32 时间<1ms TTL=64
来自 192.168.44.134 的回复: 字节=32 时间<1ms TTL=64

192.168.44.134 的 Ping 统计信息:
    数据包: 已发送 = 4，已接收 = 4，丢失 = 0 (0% 丢失)，
往返行程的估计时间(以毫秒为单位):
    最短 = 0ms，最长 = 0ms，平均 = 0ms

C:\Users\admin>
```
