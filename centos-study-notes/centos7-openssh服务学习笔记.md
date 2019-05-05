> 准备工作

- SSH 的定义(secure shell)

  - 英文全称`Secure Shell`,简称`SSH`,中文释义:安全外壳协议.其是一种加密的网络传输协议,可以在不安全的网络环境中,为网络服务提供安全的传输环境.

  * SSH 通过在网络中建立安全隧道(以非对称加密的方式实现身份验证),来实现 SSH 客户端与服务器之间的连接.
  * 虽然任何网络服务都可以用 SSH 来实现安全传输,但其最常见的用途还是在远程登录系统上,即用 SSH 来传输命令行界面和远程执行命令.此场景使用场合最多的是在 UNIX 系统上,另外在多数 UNIX 版本的系统上 SSH 服务端程序都是默认安装的.
  * 它是 telnet 和非安全的 shell 的替代品.

- openSSH 的定义(OpenBSD Secure Shell)
  - 英文全称`OpenBSD Secure Shell`,本质是`开源的`加密通信的软件.
  - OpenBSD Secure Shell 是使用 SSH 透过计算机网络加密通信的实现.它是商用软件 SSH Communications Security 的开源替代方案.当前是 OpenBSD 的子项目.
  - OpenSSH 不是 OpenSSL,这两个项目是有不同的目的,不同的开发团队,相同的发展目标(`提供开放源码的加密通信软件`)的软件.
  - 程序结构(表格) :

|        子程序         | 说明                                      |
| :-------------------: | :---------------------------------------- |
|         `ssh`         | rlogin 与 telnet 的替代方案               |
|     `scp` `sftp`      | rcp 的替代方案,将文件复制到其他的计算机上 |
|        `sshd`         | SSH 服务器                                |
|     `ssh-keygen`      | 生成 RSA 或 DSA 密匙,用作身份认证         |
| `ssh-agent` `ssh-add` | 帮助用户不需要每次都输入密匙的工具        |
|     `ssh-keyscan`     | 扫描一群机器,并记录器公匙                 |

- 终端软件(Window 平台)

|    软件     | 介绍                                               |
| :---------: | :------------------------------------------------- |
| `secureCRT` | 一款支持 SSH,Telnet 的商用客户端和虚拟终端软件.    |
|  `Xshell`   | 支持 SSH1,SSH2,Telnet 的模拟终端软件,学习使用免费. |
|   `lrzsz`   | unix 上的开源软件包,支持与模拟终端的文件传输       |

> centos 中 openSSH 软件配置

- 服务的管理 sshd

```
[root@centos7 ~]#
[root@centos7 ~]# systemctl status sshd
● sshd.service - OpenSSH server daemon
   Loaded: loaded (/usr/lib/systemd/system/sshd.service; enabled; vendor preset: enabled)
   Active: active (running) since Thu 2019-03-28 09:01:22 CST; 6h ago
     Docs: man:sshd(8)
           man:sshd_config(5)
 Main PID: 1287 (sshd)
   CGroup: /system.slice/sshd.service
           └─1287 /usr/sbin/sshd -D

Mar 28 09:01:22 centos7 systemd[1]: Starting OpenSSH server daemon...
Mar 28 09:01:22 centos7 sshd[1287]: Server listening on 0.0.0.0 port 22.
Mar 28 09:01:22 centos7 sshd[1287]: Server listening on :: port 22.
Mar 28 09:01:22 centos7 systemd[1]: Started OpenSSH server daemon.
Mar 28 09:20:02 centos7 sshd[2267]: Accepted password for root from 192.168.44.1 port 61383 ssh2
[root@centos7 ~]#
```

- 配置文件的查看

```
[root@centos7 ~]#
[root@centos7 ~]# man ssh_config | tail -17 | head -12
FILES
     ~/.ssh/config
             This is the per-user configuration file.  The format of this file is described above.  This file is used by the SSH client.  Because of the potential
             for abuse, this file must have strict permissions: read/write for the user, and not accessible by others.

     /etc/ssh/ssh_config
             Systemwide configuration file.  This file provides defaults for those values that are not specified in the user's configuration file, and for those
             users who do not have a configuration file.  This file must be world-readable.

SEE ALSO
     ssh(1)

[root@centos7 ~]#
```

- 配置文件的部分注释 /etc/ssh/ssh_config(完整参考 man 指令`man ssh_config`)

```
# Port 22
# 用来设置sshd监听的端口,默认值是22

# ListenAdress 0.0.0.0
# 设置sshd服务器绑定的IP地址,不需要特殊的设置,保持默认.

# Protocol 2
# 设置使用的SSH的协议,默认选用的是SSH2,因为SSH1的安全性很低,基本上已经弃用了,保持默认即可.

# HostKey /etc/ssh/ssh_host_rsa_key
# 设置服务器密匙文件的路径,一般保持默认即可.

# KeyRegenerationInterval 1h
# 设置在多少秒后,系统自动生成服务器的密匙.(如果使用密匙)
# 重新生成密匙的目的,是为了防止利用盗用的密匙来解密被截获的信息.

# ServerKeyBits 1024
# 设置服务器密匙的长度,默认为1024位.

# SyslogFacility AUTHPRIV
# 设置在记录来自sshd的消息的时候,是否给出 family code

# LogLevel INFO
# 设置记录sshd日志消息的级别

# LoginGraceTime 2m
# 设置 : 如果用户登录失败,在切断连接前服务器要等待的时间,单位是秒.

PermitRootLogin no
# 设置超级用户 root 能不能用root登录
# root远程登录unix主机是非常危险的,在真实unix服务器的sshd的配置中,此选项建议设置为no.

# StrictMode yes
# 设置SSH在接收登录请求之前,是否检查用户根目录和rhosts文件的权限和所有权.
# 此选项建议设置为 yes

# RSAAuthentication yes
# 设置是否开启RSA密匙验证,若是用户采用RSA密匙登录方式时,开启此选项.

# PubKeyAuthentication yes
# 设置是否开启公共密匙验证,若是采用公共密匙登录的方式,开启此选项.

# AuthorizedKeysFile .ssh/authorized_keys
# 设置公共密匙验证文件的路径,与PubKeyAuthentication配合使用.

# IgnoreRhosts yes
# 设置验证的时候是否使用 ~/.rhosts 和 ~/shosts 文件

# PasswordAuthentication yes
# 设置是否开启密码验证机制,如果使用密码登录系统,设置为yes.

PermitEmptyPasswords no
# 设置是否允许用密码为空的账号登录系统,应当设置为no.

# X11Forwarding yes
# 设置是否允许X11转发

# PrintMotd yes
# 设置sshd是否在用户登录的时候显示/etc/motd中的登录信息.

```

- 测试虚机中的配置文件

```
#	$OpenBSD: ssh_config,v 1.30 2016/02/20 23:06:23 sobrado Exp $

# This is the ssh client system-wide configuration file.  See
# ssh_config(5) for more information.  This file provides defaults for
# users, and the values can be changed in per-user configuration files
# or on the command line.

# Configuration data is parsed as follows:
#  1. command line options
#  2. user-specific file
#  3. system-wide file
# Any configuration value is only changed the first time it is set.
# Thus, host-specific definitions should be at the beginning of the
# configuration file, and defaults at the end.

# Site-wide defaults for some commonly used options.  For a comprehensive
# list of available options, their meanings and defaults, please see the
# ssh_config(5) man page.

# Host *
#   ForwardAgent no
#   规定属于代理的身份验证的用户,是否允许转发到远程主机.
#   候选值是 yes 和 no,默认值是 no.出于密匙安全的角度考虑,此选项应当保持默认,设置为 no.

#   ForwardX11 no
#   是否允许通过安全通道和 Display 设置重定向到 X11 的连接.
#   候选值是 yes 和 no,默认值是 no.出于密匙安全角度的考虑,此选项应当设置为 no.

#   RhostsRSAAuthentication no
#   是否允许基于 rhosts 的 RSA 密匙的身份验证.候选值是 yes 和 no,默认值是 no.这个选项仅使用于 SSH1.

#   RSAAuthentication yes
#   是否使用RSA身份验证
#   候选值是 yes 和 no .默认值是 yes
#   此选项只使用与 SSH1

#   PasswordAuthentication yes
#   是否使用密码验证
#   候选值是 yes 和 no . 默认值是 yes

#   HostbasedAuthentication no
#   是否使用基于 rhosts 的公匙身份验证
#   候选值是 yes 和 no . 默认值是yes

#   GSSAPIAuthentication no
#   是否使用基于 GSSAP 的身份验证.
#   候选值是yes和no,默认值是no.

#   GSSAPIDelegateCredentials no
#   是否允许将凭证转发或委托给服务器.
#   候选值是yes和no,默认值是no.

#   GSSAPIKeyExchange no
#   是否允许使用基于GSSAPI的密匙交换,当使用GSSAPI密匙交换时,服务器不需要注解密匙.
#   候选值是yes和no,默认值是no.

#   GSSAPITrustDNS no
#

#   BatchMode no
#   是否开启密码查询的功能.
#   候选值是yes和no,默认值是no.

#   CheckHostIP yes
#   是否允许检查known_hosts文件中的主机IP地址,以此来检测主机密钥是否因DNS欺骗而更改.
#   候选值是yes和no,默认值是yes.

#   AddressFamily any
#   允许连接时使用的地址类型.
#   候选值是any,inet和inet6,默认值是any
#   any表示允许所有,inet表示仅支持IPv4,Inet6表示仅支持IPv6.

#   ConnectTimeout 0
#   设置连接SSH服务器的超时时间(单位是秒),而不是使用系统默认值的TCP连接超时的时间.
#   此选项的设置不适用于主机拒绝连接时的设置.

#   StrictHostKeyChecking ask
#   默认值是 ask
#   yes : 不会自动将密匙添加到 ~/.ssh/known_hosts 文件中,而且会拒绝变更的密匙验证登录.
#   no  : 会自动的将密匙添加到 ~/.ssh/known_hosts 文件中.
#   ask : 在将密匙添加到 ~/.ssh/known_hosts 文件中的时候,会与用户进行交互式的确认.且变更的密匙验证登录会被拒绝.

#   IdentityFile ~/.ssh/identity
#   IdentityFile ~/.ssh/id_rsa
#   IdentityFile ~/.ssh/id_dsa
#   IdentityFile ~/.ssh/id_ecdsa
#   IdentityFile ~/.ssh/id_ed25519
#   规定DSA, ECDSA, Ed25519 or RSA用户密匙验证读取的文件路径.
#   在SSH1版本中,读取密匙文件的路径是 ~/.ssh/identity
#   在SSH2版本中,读取密匙文件的路径是 ~/.ssh/id_dsa, ~/.ssh/id_ecdsa, ~/.ssh/id_ed25519, ~/.ssh/id_rsa
#   指定密匙身份验证文件的路径.SSH1和SSH2都有默认的读取路径,详细参考man手册.

#   Port 22
#   规定连接的端口,默认值是22

#   Protocol 2
#   规定连接使用的协议,SSH1因为安全性很低,基本已经弃用.

#   Cipher 3des
#   指定SSH1加密会话的密码.候选值blowfish、3des和des
#   默认值是 3des

#   Ciphers aes128-ctr,aes192-ctr,aes256-ctr,arcfour256,arcfour128,aes128-cbc,3des-cbc
#   指定SSH2中加密会话的密码
#   可以指定多个,以逗号分隔

#   MACs hmac-md5,hmac-sha1,umac-64@openssh.com,hmac-ripemd160
#   指定MAC(消息身份验证代码)算法的优先级

#   EscapeChar ~
#   设置转移字符,默认值是 ~

#   Tunnel no
#   设置客户端和服务器之间的转发模式.
#   yes指的是点对点的转发模式
#   默认值是no

#   TunnelDevice any:any

#   PermitLocalCommand no

#   VisualHostKey no

#   ProxyCommand ssh -q -W %h:%p gateway.example.com

#   RekeyLimit 1G 1h
#
# Uncomment this if you want to use .local domain
# Host *.local
#   CheckHostIP no

Host *
	GSSAPIAuthentication yes
# If this option is set to yes then remote X11 clients will have full access
# to the original X11 display. As virtually no X11 client supports the untrusted
# mode correctly we set this to yes.
	ForwardX11Trusted yes
# Send locale-related environment variables
	SendEnv LANG LC_CTYPE LC_NUMERIC LC_TIME LC_COLLATE LC_MONETARY LC_MESSAGES
	SendEnv LC_PAPER LC_NAME LC_ADDRESS LC_TELEPHONE LC_MEASUREMENT
	SendEnv LC_IDENTIFICATION LC_ALL LANGUAGE
	SendEnv XMODIFIERS

```
