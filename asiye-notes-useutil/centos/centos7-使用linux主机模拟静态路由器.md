> 准备工作 : 使用 route 操控路由

- 输出信息的解析

|     字段      | 释义                                                                                                                                                                                                                                                                                                                                                                                                                                               |
| :-----------: | :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `Destination` | 目标网络或目标主机                                                                                                                                                                                                                                                                                                                                                                                                                                 |
|   `Gateway`   | 网关地址或'\*'(如未设)也就是说,该网络是通过这个 IP 连接出去的;若果显示'0.0.0.0',则表示该路由是直接从本机传送出去的;如果有 IP 显示,则表示本条路由必须经过这个 IP 的转接才能出去.                                                                                                                                                                                                                                                                    |
|   `Genmask`   | 目标网络的子网掩码,'255.255.255.255'为主机,'0.0.0.0'为缺省路由.也就是 netmask;destination 和 genmask 组合成一个完整的网络.                                                                                                                                                                                                                                                                                                                         |
|    `Flags`    | 可能出现的表示有 : <br>U(route is up)表示该路由是正常启动的<br>H(target is host)目标路由是一部主机,而非网络<br>G(use gateway)表示需要通过外部的主机来转接,转递数据<br>R(reinstate route for dynamic routing)使用动态路由时,恢复路由信息标识<br>D(dynamically installed bu daemon or redirect)已经由服务设置为动态路由<br>M(modified from routing daemon orredirect)表示路由已经修改了<br>!(reject route)这个路由将不会被接受(用来抵挡不安全的网络) |
|   `Metric`    | 需要经过几个网络节点{hop}才能到达路由的目标网络地址.                                                                                                                                                                                                                                                                                                                                                                                               |
|     `Ref`     | 参考到此路由规则的数目                                                                                                                                                                                                                                                                                                                                                                                                                             |
|     `Use`     | 有几个转送数据包参考到此路由规则                                                                                                                                                                                                                                                                                                                                                                                                                   |
|    `Iface`    | 路由对应的网络接口设备                                                                                                                                                                                                                                                                                                                                                                                                                             |
|     `MSS`     | 经此路由的 TCP 连接的默认的最大报文长度                                                                                                                                                                                                                                                                                                                                                                                                            |
|   `Window`    | 经此路由的 TCP 连接的默认窗口大小                                                                                                                                                                                                                                                                                                                                                                                                                  |
|    `irtt`     | 初始 RTT 的往返时间                                                                                                                                                                                                                                                                                                                                                                                                                                |

- 指令的常见用法

常用的指令格式 :

```
查看路由表 :
route [n|ee]
添加或删除一条路由 :
route add|del [-net] [IP/CIDR] [gw|dev] [网关或网卡]
route add|del [-net|-host] [网络或主机] netmask [mask] [gw|dev] [网关或网卡]
```

常用的指令选项 :

| `route`选项 | 效用                                              |
| :---------: | :------------------------------------------------ |
|     `n`     | 不使用通信协议或主机名称,直接使用 ip 地址输出     |
|    `ee`     | 显示更加详细的路由信息                            |
|    `add`    | 添加路由信息                                      |
|    `del`    | 删除路由信息                                      |
|   `-net`    | 添加一个网络,后面需紧跟一个网络号地址             |
|   `-host`   | 添加一个主机                                      |
|  `netmask`  | 设置子网,后面需紧跟子网掩码地址                   |
|    `gw`     | 网关信息,后面紧跟网关地址                         |
|    `dev`    | 设置经由哪个网络设备联机出去,后面紧跟网络设备名称 |

> 准备工作 : 如何开启和关闭 IP 代理转发功能

```
[root@centos6 ~]#
[root@centos6 ~]# cat /etc/sysctl.conf | grep ip_forward
net.ipv4.ip_forward = 0
[root@centos6 ~]#
```

&#160;&#160;将配置文件中的`net.ipv4.ip_forward`值更改为 1,然后执行`sysctl -p`,即可开启 IP 代理转发功能;反之为 0 则是关闭,同样需要执行命令或者重启主机.

> 准备工作 : 网关 GATEWAY

网关(gateway),也可以称作网间连接器,或者是协议转换器.  
在 OSI 模型中,属于传输层,主要作用是为了让两个高层协议的不同网段互连.  
网关有不同的分类标准,网关也有很多种,其中`TCP/IP`协议里的网关是最常用的.  
`实质`: 一个网络通信到另一个网络的 ip 地址.  
`地址的选取`:网段内可用的 ip 地址,一般为第一个或最后一个,如 192.168.0.1 或者是 192.168.0.254.  
说白了,两个不同网段的主机要进行通信,必须要经过两个网段的网关才能实现.  
一般的路由器都有默认的网关地址,通常称作`默认网关`.

> 环境搭建 : 宿主机和虚机的配置

1. 在`VMware Workstation`中自定义两个`host-only`(仅主机模式)的适配器.软件选择的路径是: `编辑`--`虚拟网络映射器`--`添加网络`.测试环境选择的是自定义的`VMnet9`和`VMnet10`,需要注意的是,仅主机模式的适配器环境,在宿主机上配置的适配器 IP,就是虚机的网关地址`GATEWAY`.以下是两个测试适配器的参数 : `192.168.88.0/24`和`192.168.99.0/24`.

VMnet9 :

```
以太网适配器 VMware Network Adapter VMnet9:

   连接特定的 DNS 后缀 . . . . . . . :
   描述. . . . . . . . . . . . . . . : VMware Virtual Ethernet Adapter for VMnet9
   物理地址. . . . . . . . . . . . . : 00-50-56-C0-00-09
   DHCP 已启用 . . . . . . . . . . . : 否
   自动配置已启用. . . . . . . . . . : 是
   本地链接 IPv6 地址. . . . . . . . : fe80::193c:41ce:fdc7:253d%43(首选)
   IPv4 地址 . . . . . . . . . . . . : 192.168.88.254(首选)
   子网掩码  . . . . . . . . . . . . : 255.255.255.0
   默认网关. . . . . . . . . . . . . :
   DHCPv6 IAID . . . . . . . . . . . : 721440854
   DHCPv6 客户端 DUID  . . . . . . . : 00-01-00-01-23-FF-C4-A7-C8-5B-76-1A-BC-9B
   DNS 服务器  . . . . . . . . . . . : fec0:0:0:ffff::1%1
                                       fec0:0:0:ffff::2%1
                                       fec0:0:0:ffff::3%1
   TCPIP 上的 NetBIOS  . . . . . . . : 已启用
```

VMnet10 :

```
以太网适配器 VMware Network Adapter VMnet10:

   连接特定的 DNS 后缀 . . . . . . . :
   描述. . . . . . . . . . . . . . . : VMware Virtual Ethernet Adapter for VMnet10
   物理地址. . . . . . . . . . . . . : 00-50-56-C0-00-0A
   DHCP 已启用 . . . . . . . . . . . : 否
   自动配置已启用. . . . . . . . . . : 是
   本地链接 IPv6 地址. . . . . . . . : fe80::9416:a31d:2f36:db8e%44(首选)
   IPv4 地址 . . . . . . . . . . . . : 192.168.99.254(首选)
   子网掩码  . . . . . . . . . . . . : 255.255.255.0
   默认网关. . . . . . . . . . . . . :
   DHCPv6 IAID . . . . . . . . . . . : 738218070
   DHCPv6 客户端 DUID  . . . . . . . : 00-01-00-01-23-FF-C4-A7-C8-5B-76-1A-BC-9B
   DNS 服务器  . . . . . . . . . . . : fec0:0:0:ffff::1%1
                                       fec0:0:0:ffff::2%1
                                       fec0:0:0:ffff::3%1
   TCPIP 上的 NetBIOS  . . . . . . . : 已启用
```

2. 克隆两台虚机作为不同网段的主机,进行测试,注意不要配置网关.

主机 A `88/24-net`的配置,网段:`192.168.88.0/24`,IP:`192.168.88.140`.查看路由确认是否可通网关,并尝试 ping 99 网段的地址.

```
[root@88/24-net ~]#
[root@88/24-net ~]# cat /etc/centos-release
CentOS release 6.3 (Final)
[root@88/24-net ~]# cat /etc/sysconfig/network-scripts/ifcfg-eth0
ONBOOT=yes
IPADDR=192.168.88.140
NETMASK=255.255.255.0
NETWORK=192.168.88.0
BROADCAST=192.168.88.255
HWADDR=00:0C:29:9C:82:80
NAME=eth0
[root@88/24-net ~]#
[root@88/24-net ~]# route -n
Kernel IP routing table
Destination     Gateway         Genmask         Flags Metric Ref    Use Iface
192.168.88.0    0.0.0.0         255.255.255.0   U     0      0        0 eth0
169.254.0.0     0.0.0.0         255.255.0.0     U     1002   0        0 eth0
[root@88/24-net ~]# ping 192.168.88.254
PING 192.168.88.254 (192.168.88.254) 56(84) bytes of data.
64 bytes from 192.168.88.254: icmp_seq=1 ttl=64 time=0.478 ms
64 bytes from 192.168.88.254: icmp_seq=2 ttl=64 time=0.458 ms
64 bytes from 192.168.88.254: icmp_seq=3 ttl=64 time=1.09 ms
64 bytes from 192.168.88.254: icmp_seq=4 ttl=64 time=0.398 ms
^C
--- 192.168.88.254 ping statistics ---
4 packets transmitted, 4 received, 0% packet loss, time 3265ms
rtt min/avg/max/mdev = 0.398/0.606/1.092/0.282 ms
[root@88/24-net ~]#
[root@88/24-net ~]# ping 192.168.99.254
connect: Network is unreachable
[root@88/24-net ~]#
```

主机 B `99/24-net`的配置,网段:`192.168.99.0/24`,IP:`192.168.99.140`.查看路由确认是否可通网关,并尝试 ping 88 网段的地址.

```
[root@99/24-net ~]#
[root@99/24-net ~]# cat /etc/centos-release
CentOS release 6.3 (Final)
[root@99/24-net ~]# cat /etc/sysconfig/network-scripts/ifcfg-eth0
ONBOOT=yes
IPADDR=192.168.99.140
NETMASK=255.255.255.0
NETWORK=192.168.99.0
BROADCAST=192.168.99.255
HWADDR=00:0C:29:A6:E2:AF
NAME=eth0
[root@99/24-net ~]#
[root@99/24-net ~]# route -n
Kernel IP routing table
Destination     Gateway         Genmask         Flags Metric Ref    Use Iface
192.168.99.0    0.0.0.0         255.255.255.0   U     0      0        0 eth1
169.254.0.0     0.0.0.0         255.255.0.0     U     1002   0        0 eth1
[root@99/24-net ~]# ping 192.168.99.254
PING 192.168.99.254 (192.168.99.254) 56(84) bytes of data.
64 bytes from 192.168.99.254: icmp_seq=1 ttl=64 time=0.194 ms
64 bytes from 192.168.99.254: icmp_seq=2 ttl=64 time=0.357 ms
64 bytes from 192.168.99.254: icmp_seq=3 ttl=64 time=0.389 ms
64 bytes from 192.168.99.254: icmp_seq=4 ttl=64 time=0.386 ms
^C
--- 192.168.99.254 ping statistics ---
4 packets transmitted, 4 received, 0% packet loss, time 3445ms
rtt min/avg/max/mdev = 0.194/0.331/0.389/0.082 ms
[root@99/24-net ~]#
[root@99/24-net ~]# ping 192.168.88.254
connect: Network is unreachable
[root@99/24-net ~]#
```

3. 对作为模拟路由的主机进行配置,需要添加`VMnet9`和`VMnet10`两个网卡,同样的不需要配置网关.网段 192.168.88.0/24 的 IP 是`192.168.88.130`,网段 192.168.99.0/24 的 IP 是`192.168.99.130`.  
   最后测试是否可与同网段的主机进行通信.

虚机路由主机`vm-router`的网络配置 :

```
[root@vm-router network-scripts]#
[root@vm-router network-scripts]# pwd
/etc/sysconfig/network-scripts
[root@vm-router network-scripts]# cat /etc/centos-release
CentOS Linux release 7.4.1708 (Core)
[root@vm-router network-scripts]# cat ifcfg-ens32
ONBOOT=yes
IPADDR=192.168.88.130
NETMASK=255.255.255.0
BROADCAST=192.168.88.255
NETWORK=192.168.88.0
HWADDR=00:0C:29:D3:24:1B
NAME=ens32
[root@vm-router network-scripts]# cat ifcfg-ens34
ONBOOT=yes
IPADDR=192.168.99.130
NETMASK=255.255.255.0
BROADCAST=192.168.99.255
NETWORK=192.168.99.0
HWADDR=00:0C:29:D3:24:25
NAME=ens32
[root@vm-router network-scripts]# cd
[root@vm-router ~]#
[root@vm-router ~]# route -n
Kernel IP routing table
Destination     Gateway         Genmask         Flags Metric Ref    Use Iface
169.254.0.0     0.0.0.0         255.255.0.0     U     1002   0        0 ens32
169.254.0.0     0.0.0.0         255.255.0.0     U     1003   0        0 ens34
192.168.88.0    0.0.0.0         255.255.255.0   U     0      0        0 ens32
192.168.99.0    0.0.0.0         255.255.255.0   U     0      0        0 ens34
[root@vm-router ~]# ping 192.168.88.140
PING 192.168.88.140 (192.168.88.140) 56(84) bytes of data.
64 bytes from 192.168.88.140: icmp_seq=1 ttl=64 time=0.640 ms
64 bytes from 192.168.88.140: icmp_seq=2 ttl=64 time=0.575 ms
^C
--- 192.168.88.140 ping statistics ---
2 packets transmitted, 2 received, 0% packet loss, time 1009ms
rtt min/avg/max/mdev = 0.575/0.607/0.640/0.040 ms
[root@vm-router ~]# ping 192.168.99.140
PING 192.168.99.140 (192.168.99.140) 56(84) bytes of data.
64 bytes from 192.168.99.140: icmp_seq=1 ttl=64 time=1.35 ms
64 bytes from 192.168.99.140: icmp_seq=2 ttl=64 time=0.658 ms
^C
--- 192.168.99.140 ping statistics ---
2 packets transmitted, 2 received, 0% packet loss, time 1002ms
rtt min/avg/max/mdev = 0.658/1.007/1.357/0.350 ms
[root@vm-router ~]#
```

4. 开启虚拟路由主机`vm-router`的 IP 地址转发功能,然后在两台主机 A 和主机 B 上添加一条路由规则.

虚拟路由主机`vm-router`上开启 IP 地址转发功能.(保险起见,重启下网卡)

```
[root@vm-router ~]#
[root@vm-router ~]# sysctl -p
[root@vm-router ~]# echo 'net.ipv4.ip_forward = 1' >> /etc/sysctl.conf
[root@vm-router ~]# sysctl -p
net.ipv4.ip_forward = 1
[root@vm-router ~]# systemctl restart network
[root@vm-router ~]#
```

主机 A `88/24-net`添加一条路由规则,用虚机路由主机的同网段 IP 作为网关.

```
[root@88/24-net ~]#
[root@88/24-net ~]# route add -net 192.168.99.0/24 gw 192.168.88.130
[root@88/24-net ~]# route -n
Kernel IP routing table
Destination     Gateway         Genmask         Flags Metric Ref    Use Iface
192.168.99.0    192.168.88.130  255.255.255.0   UG    0      0        0 eth0
192.168.88.0    0.0.0.0         255.255.255.0   U     0      0        0 eth0
169.254.0.0     0.0.0.0         255.255.0.0     U     1002   0        0 eth0
[root@88/24-net ~]#
[root@88/24-net ~]# ping 192.168.99.140
PING 192.168.99.140 (192.168.99.140) 56(84) bytes of data.
64 bytes from 192.168.99.140: icmp_seq=1 ttl=63 time=0.949 ms
64 bytes from 192.168.99.140: icmp_seq=2 ttl=63 time=1.27 ms
64 bytes from 192.168.99.140: icmp_seq=3 ttl=63 time=1.31 ms
64 bytes from 192.168.99.140: icmp_seq=4 ttl=63 time=1.22 ms
^C
--- 192.168.99.140 ping statistics ---
4 packets transmitted, 4 received, 0% packet loss, time 3320ms
rtt min/avg/max/mdev = 0.949/1.191/1.319/0.148 ms
[root@88/24-net ~]#
```

主机 B `99/24-net`同样添加一条路由规则,用虚拟路由主机的同网段 IP 作为网关.

```
[root@99/24-net ~]#
[root@99/24-net ~]# route add -net 192.168.88.0/24 gw 192.168.99.130
[root@99/24-net ~]# route -n
Kernel IP routing table
Destination     Gateway         Genmask         Flags Metric Ref    Use Iface
192.168.99.0    0.0.0.0         255.255.255.0   U     0      0        0 eth1
192.168.88.0    192.168.99.130  255.255.255.0   UG    0      0        0 eth1
169.254.0.0     0.0.0.0         255.255.0.0     U     1002   0        0 eth1
[root@99/24-net ~]#
[root@99/24-net ~]# ping 192.168.88.140
PING 192.168.88.140 (192.168.88.140) 56(84) bytes of data.
64 bytes from 192.168.88.140: icmp_seq=1 ttl=63 time=0.820 ms
64 bytes from 192.168.88.140: icmp_seq=2 ttl=63 time=1.21 ms
64 bytes from 192.168.88.140: icmp_seq=3 ttl=63 time=1.35 ms
64 bytes from 192.168.88.140: icmp_seq=4 ttl=63 time=1.15 ms
^C
--- 192.168.88.140 ping statistics ---
4 packets transmitted, 4 received, 0% packet loss, time 3288ms
rtt min/avg/max/mdev = 0.820/1.135/1.350/0.197 ms
[root@99/24-net ~]#
```

结束 : 此时主机 A 和主机 B 就能互相通信了.
