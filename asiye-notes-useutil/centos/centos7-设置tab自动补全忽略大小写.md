tab 自动补全忽略字母大小写

- 对于哪个登录的用户进行设置,就在用户的目录文件下进行操作.
- 若没有`.inputrc`文件,就手动创建.
- 这种配置的方式,目前进行了 centos7,6,5 或 ubuntu18,16 的版本测试,均可用.
- 添加`set completion-ignore-case on`即可.
- 修改后使用`source`指令或者退出 tty 重新登录才会生效.

```bash
[root@centos7 ~]#
[root@centos7 ~]# pwd
/root
[root@centos7 ~]# ll .inputrc
-rw-r--r--. 1 root root 30 Mar  7 13:21 .inputrc
[root@centos7 ~]# cat .inputrc
set completion-ignore-case on
[root@centos7 ~]#
```
