#### 网卡驱动的安装

> 准备工作

- 查找网卡的型号

&#160;&#160;查看网卡的型号有两种途径,一个是直接查看物理网卡硬件上面的厂商标识,此种方式一般需要对桌面机或者服务器进行拆机操作才能查看;另一个是从计算机厂商网站上查看对应型号的硬件列表,以此来确定物理网卡的确切型号.
&#160;&#160;还有一种方式,使用 lspci(列出连接到 PCI 总线上的所有设备) 指令进行筛选.

```sh
[root@centos7 ~]#
[root@centos7 ~]# lspci | grep -i ethernet
02:00.0 Ethernet controller: Intel Corporation 82545EM Gigabit Ethernet Controller (Copper) (rev 01)
[root@centos7 ~]#
```

- 查找系统文件(/lib)中是否包含支持的驱动文件

&#160;&#160;`/lib/modules/release/kernel/drivers`目录中查找所支持的所有驱动程序,其中的`release`是 unix 的版本型号,使用`uname -a`进行核对.该目录下的`/net`目录存储的是支持的所有网络设备列表;其内的`/ethernet`目录存储的是支持的`以太网卡`设备.  
&#160;&#160;总言之,`/drivers`目录里的驱动文件,罗列了通用的支持列表,若是自己的设备没有对应的文件,就需要去手动下载驱动文件了.

```sh
[root@centos7 ~]#
[root@centos7 ~]# uname -a
Linux centos7 3.10.0-862.14.4.el7.x86_64 #1 SMP Wed Sep 26 15:12:11 UTC 2018 x86_64 x86_64 x86_64 GNU/Linux
[root@centos7 ~]# ls /lib/modules/3.10.0-862.14.4.el7.x86_64/kernel/drivers/
acpi        bcma       char     dca       firmware  hv     infiniband  leds      message  mtd     nvme     pinctrl   pps  scsi     thermal  uwb    virtio
ata         block      cpufreq  dma       gpio      hwmon  input       md        mfd      net     parport  platform  ptp  ssb      tty      vfio   watchdog
auxdisplay  bluetooth  crypto   edac      gpu       i2c    iommu       media     misc     ntb     pci      power     pwm  staging  uio      vhost  xen
base        cdrom      dax      firewire  hid       idle   isdn        memstick  mmc      nvdimm  pcmcia   powercap  rtc  target   usb      video
[root@centos7 ~]#
```

- 查找测试虚机中是否包含对应的驱动文件

```sh
[root@centos7 ethernet]#
[root@centos7 ethernet]# pwd
/lib/modules/3.10.0-862.14.4.el7.x86_64/kernel/drivers/net/ethernet
[root@centos7 ethernet]# ls
amazon  aquantia  broadcom  cadence  cavium   cisco  dnet.ko.xz  ethoc.ko.xz  intel      marvell   myricom    oki-semi  realtek  sfc   ti
amd     atheros   brocade   calxeda  chelsio  dec    emulex      icplus       jme.ko.xz  mellanox  netronome  qlogic    rocker   smsc
[root@centos7 ethernet]# lspci | grep -i ethernet
02:00.0 Ethernet controller: Intel Corporation 82545EM Gigabit Ethernet Controller (Copper) (rev 01)
[root@centos7 ethernet]# ls intel/
e1000  e1000e  fm10k  i40e  i40evf  igb  igbvf  ixgbe  ixgbevf
[root@centos7 ethernet]#
```

利用网络进行查看对比 :  
&#160;&#160;(1)在搜索引擎中输入自己的网卡厂商和型号 Intel Corporation 82545EM Gigabit Ethernet Controller,进入其官网的下载页面,下载对应 OS 的驱动文件;注意筛选关键字`drivers`和`linux*`,然后选择稳定的版本进行下载.  
&#160;&#160;(2)测试虚机下载的驱动名称是 : e1000-8.0.30.tar.gz ;进行对比`/intel`中的 e1000 目录里的驱动文件,可确定测试虚机的系统文件中有支持的网卡驱动文件.

- 查找是否加载了该网卡驱动模块

&#160;&#160;参考上一步的操作,确定了驱动文件的名称是`e1000`,然后就可以使用`lsmod`指令查看内核是否已经加载了该模块.  
&#160;&#160;输出内容的第一列表示`网卡的名称`,第二列表示`网卡的大小`,第三列表示`网卡的调用数`;第四列表示`调用驱动的程序`.

```sh
[root@centos7 ~]#
[root@centos7 ~]# lsmod | grep e1000
e1000                 137574  0
[root@centos7 ~]#
```

- 如何操作驱动模块文件(modprobe)

`modprobe` : 智能的向 unix 的内核加载或删除指定的模块.modprobe 可以加载单个模块,也可以加载一组依赖关系的模块组.主要是借助`depmod`指令产生的依赖关系,来决定加载哪个模块;若是在加载的过程中发生错误,modprobe 会卸载出错的整组模块.

| `modprobe`选项 | 效用                                                  |
| :------------- | :---------------------------------------------------- |
| `a`            | 加载一组匹配的模块                                    |
| `r`            | 删除指定的模块,若为指定模块,则会设置成自动清除的模式. |
| `n`            | 仅显示要执行的操作,而不实际执行                       |
| `v`            | 执行时,显示详细的执行信息                             |

以`vfat`文件扩展系统模块为例,进行测试.  
输出执行信息,但不实际操作 :

```sh
[root@centos7 ~]#
[root@centos7 ~]# modprobe -nv vfat
insmod /lib/modules/3.10.0-862.14.4.el7.x86_64/kernel/fs/fat/fat.ko.xz
insmod /lib/modules/3.10.0-862.14.4.el7.x86_64/kernel/fs/fat/vfat.ko.xz
[root@centos7 ~]#
```

加载一组指定的模块 vfat

```sh
[root@centos7 ~]#
[root@centos7 ~]# modprobe -av vfat
insmod /lib/modules/3.10.0-862.14.4.el7.x86_64/kernel/fs/fat/fat.ko.xz
insmod /lib/modules/3.10.0-862.14.4.el7.x86_64/kernel/fs/fat/vfat.ko.xz
[root@centos7 ~]#
```

查看是否加载成功 vfat

```sh
[root@centos7 ~]#
[root@centos7 ~]# lsmod | grep vfat
vfat                   17461  0
fat                    65950  1 vfat
[root@centos7 ~]#
```

删除指定的模块 vfat

```sh
[root@centos7 ~]#
[root@centos7 ~]# modprobe -rv vfat
rmmod vfat
rmmod fat
[root@centos7 ~]# lsmod | grep vfat
[root@centos7 ~]#
```

> 手动安装网卡驱动(源码包的方式)

以下载的`e1000`源码包为例,直接查看安装文档即可.

```sh
[root@centos7 ~]#
[root@centos7 ~]# ll | grep e100
drwxr-xr-x   3 root  root     110 Jan 13  2011 e1000-8.0.30
-rw-r--r--   1 root  root  215778 Mar 21 11:07 e1000-8.0.30.tar.gz
[root@centos7 ~]# cat -n e1000-8.0.30/README | head -146 | tail -51
    96
    97  1. Move the base driver tar file to the directory of your choice.  For
    98     example, use /home/username/e1000 or /usr/local/src/e1000.
    99
   100  2. Untar/unzip archive:
   101
   102       tar zxf e1000-x.x.x.tar.gz
   103
   104  3. Change to the driver src directory:
   105
   106       cd e1000-x.x.x/src/
   107
   108  4. Compile the driver module:
   109
   110       make install
   111
   112     The binary will be installed as:
   113
   114       /lib/modules/<KERNEL VERSION>/kernel/drivers/net/e1000/e1000.[k]o
   115
   116     The install locations listed above are the default locations.  They
   117     might not be correct for certain Linux distributions.
   118
   119  5. Load the module using either the insmod or modprobe command:
   120
   121       modprobe e1000
   122
   123       insmod e1000
   124
   125     Note that for 2.6 kernels the insmod command can be used if the full
   126     path to the driver module is specified.  For example:
   127
   128       insmod /lib/modules/<KERNEL VERSION>/kernel/drivers/net/e1000/e1000.ko
   129
   130     With 2.6 based kernels also make sure that older e1000 drivers are
   131     removed from the kernel, before loading the new module:
   132
   133       rmmod e1000; modprobe e1000
   134
   135
   136  6. Assign an IP address to the interface by entering the following, where
   137     x is the interface number:
   138
   139       ifconfig ethx <IP_address>
   140
   141  7. Verify that the interface works.  Enter the following, where <IP_address>
   142     is the IP address for another machine on the same subnet as the
   143     interface that is being tested:
   144
   145       ping  <IP_address>
   146
[root@centos7 ~]#
```

另推荐一个静态网卡的配置文件,此文件在 centos6.x 和 centos7.x 中都可适用.注意网卡名称和 mac 地址的更改,其他的路由参数依照实际情况的配置.

```
[root@centos7 ~]#
[root@centos7 ~]# cat -n /etc/sysconfig/network-scripts/ifcfg-ens32
     1  #/etc/sysconfig/network-scripts/ifcfg-ens32
     2  ONBOOT=yes
     3  IPADDR=192.168.44.133
     4  NETMASK=255.255.255.0
     5  GATEWAY=192.168.44.2
     6  BROADCAST=192.168.44.255
     7  NETWORK=192.168.44.0
     8  HWADDR=00:0C:29:D3:24:1B
     9  DNS1=180.76.76.76
    10  DNS2=114.114.114.114
    11  NAME=ens32
[root@centos7 ~]#
```
