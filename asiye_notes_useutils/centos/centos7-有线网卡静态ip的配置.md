> 配置文件

- 网络相关的配置文件的位置

```
[root@centos7 ~]#
[root@centos7 ~]# #网络地址的配置文件
[root@centos7 ~]# ll /etc/sysconfig/network-scripts/ifcfg-ens32
-rw-r--r-- 1 root root 197 Mar 25 13:45 /etc/sysconfig/network-scripts/ifcfg-ens32
[root@centos7 ~]# #网络回环地址的配置文件
[root@centos7 ~]# ll /etc/sysconfig/network-scripts/ifcfg-lo
-rw-r--r--. 1 root root 254 May  3  2017 /etc/sysconfig/network-scripts/ifcfg-lo
[root@centos7 ~]# #DNS域名解析地址的配置文件
[root@centos7 ~]# ll /etc/resolv.conf
-rw-r--r-- 1 root root 81 Mar 25 11:21 /etc/resolv.conf
[root@centos7 ~]# #系统主机名hostname的配置文件
[root@centos7 ~]# ll /etc/hosts
-rw-r--r--. 1 root root 158 Jun  7  2013 /etc/hosts
[root@centos7 ~]#
```

- 网络配置文件解析 ifcfg-ens32

| 标识标记 key | 释义                                                 |
| :----------- | :--------------------------------------------------- |
| `NAME`       | 与配置文件名称对应的标签,无实际意义                  |
| `DEVICE`     | 网卡设备的名称                                       |
| `HWADDR`     | 网卡的物理 MAC 地址                                  |
| `ONBOOT`     | 设置此网卡是否开机自启(yes\|no)                      |
| `BOOTPROTO`  | 标记此网卡的地址配置是静态的(static)还是动态的(dhcp) |
| `IPADDR`     | 网络配置的 ip 地址                                   |
| `NETMASK`    | 网络配置的子网掩码地址                               |
| `GATEWAY`    | 网络配置的网关地址                                   |
| `DNS1`       | 首选的 DNS 地址                                      |
| `DNS2`       | 备用的 DNS 地址                                      |
| `BROADCAST`  | 网络配置的广播地址                                   |
| `NETWORK`    | 网络配置的网络地址                                   |
| `TYPE`       | 指定网卡类型,ethernet 表示以太网                     |

- 回环地址配置文件的解析 ifcfg-lo

| 标识标记 key | 释义                                   |
| :----------- | :------------------------------------- |
| `DEVICE`     | 指定网络设备名称为 lo                  |
| `IPADDR`     | 指定回环 lo 接口的 ip 地址             |
| `NETMASK`    | 指定回环 lo 接口的子网掩码             |
| `NETWORK`    | 指定回环 lo 接口的网络号               |
| `BROADCAST`  | 指定回环 lo 接口的广播地址             |
| `ONBOOT`     | 设置开启是否启动回环 lo 接口(yes\|no)  |
| `NAME`       | 将 lo 接口设置为本地(loopback)回环地址 |

- 域名解析配置文件的说明 resolv.conf

&#160;&#160;域名解析的配置文件中,每一行表示一个域名解析服务器;主要由两部分组成,每部分由空格分开.  
&#160;&#160;第一部分是固定不变的,标记`nameserver`,表示注册一个域名解析服务器;第二部分是对应的域名解析服务器的 ip 地址.

- 主机名配置文件解析 hosts

&#160;&#160;unix 中的 hosts 文件,类似于 windows 中的 host 文件;如果设置了主机名和 ip 地址的绑定,当访问该主机是,系统就会从 hosts 文件中进行 ip 地址解析查询,而不是从 resolv.conf 配置文件中设置的 DNS 域名解析服务器上查询 ip 地址的解析.  
&#160;&#160;hosts 文件中每行表示一个主机,由`三部分`表示,每部分由`空格`进行隔开.前两部分是必选的,第三部分是可选的.第一部分表示主机的`ip地址`,第二部分表示`主机名.域名`,第三部分表示`主机名(主机别名).`

- 网络管理工具在版本更替的过渡

| 6.x 的工具                        | 7.x 的替代工具                         |
| :-------------------------------- | :------------------------------------- |
| `/etc/init.d/network`脚本管理方式 | `NetworkManager`动态的事件驱动管理方式 |
| `ifconfig`ip 的配置               | `ip`                                   |
| `netstat`网络监听利器             | `ss`                                   |
| `setup`图形化管理工具             | `nmtui`                                |

提示:平时测试使用时,建议将`NetworkManager`关掉并禁用,使用`service`,`chkconfig`或`systemctl`.

---

> 维护指令

- 静态地址配置参考

```sh
[root@centos7 ~]#
[root@centos7 ~]# cat /etc/sysconfig/network-scripts/ifcfg-ens32
ONBOOT=yes
IPADDR=192.168.44.133
NETMASK=255.255.255.0
GATEWAY=192.168.44.2
BROADCAST=192.168.44.255
NETWORK=192.168.44.0
HWADDR=00:0C:29:D3:24:1B
DNS1=180.76.76.76
DNS2=114.114.114.114
NAME=ens32
[root@centos7 ~]#
```

- ping 指令的使用检查网络状态

(1) `ping <local_addr>`  
检测本地网络是否正确配置  
(2) `ping <loop_addr>`  
检测主机是否可在网络`network`内进行通信  
(3) `ping <gw_addr>`  
检测路由是否配置正确,且是否正常工作  
(4) `ping <global_addr>`  
检测主机是否可达公网

- 使用 netstat 指令查看路由表

```
[root@centos7 ~]#
[root@centos7 ~]# netstat -rn
Kernel IP routing table
Destination     Gateway         Genmask         Flags   MSS Window  irtt Iface
0.0.0.0         192.168.44.2    0.0.0.0         UG        0 0          0 ens32
169.254.0.0     0.0.0.0         255.255.0.0     U         0 0          0 ens32
192.168.44.0    0.0.0.0         255.255.255.0   U         0 0          0 ens32
[root@centos7 ~]#
```

- 使用 netstat 指令监听系统端口号的状况

| `netstat`选项 | 效用                                       |
| :------------ | :----------------------------------------- |
| `n`           | 输出 ip 地址,而不是域名                    |
| `t`           | 输出 TCP 传输协议的连接状况                |
| `u`           | 输出 UDP 传输协议的连接状况                |
| `a`           | 显示所有连接的 socket                      |
| `p`           | 显示正在使用 socket 的程序识别码和程序名称 |

```
[root@centos7 ~]#
[root@centos7 ~]# netstat -natup
Active Internet connections (servers and established)
Proto Recv-Q Send-Q Local Address           Foreign Address         State       PID/Program name
tcp        0      0 127.0.0.1:25            0.0.0.0:*               LISTEN      1486/master
tcp        0      0 0.0.0.0:111             0.0.0.0:*               LISTEN      884/rpcbind
tcp        0      0 0.0.0.0:22              0.0.0.0:*               LISTEN      1239/sshd
tcp        0      0 127.0.0.1:631           0.0.0.0:*               LISTEN      1242/cupsd
tcp        0      0 192.168.44.133:22       192.168.44.1:55530      ESTABLISHED 1561/sshd: root@pts
tcp6       0      0 ::1:25                  :::*                    LISTEN      1486/master
tcp6       0      0 :::111                  :::*                    LISTEN      884/rpcbind
tcp6       0      0 :::22                   :::*                    LISTEN      1239/sshd
tcp6       0      0 ::1:631                 :::*                    LISTEN      1242/cupsd
udp        0      0 0.0.0.0:5353            0.0.0.0:*                           878/avahi-daemon: r
udp        0      0 127.0.0.1:323           0.0.0.0:*                           935/chronyd
udp        0      0 0.0.0.0:628             0.0.0.0:*                           884/rpcbind
udp        0      0 0.0.0.0:39686           0.0.0.0:*                           878/avahi-daemon: r
udp        0      0 0.0.0.0:111             0.0.0.0:*                           884/rpcbind
udp6       0      0 ::1:323                 :::*                                935/chronyd
udp6       0      0 :::628                  :::*                                884/rpcbind
udp6       0      0 :::111                  :::*                                884/rpcbind
[root@centos7 ~]#
```
