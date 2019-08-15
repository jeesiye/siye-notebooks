1)目前个人所知,可下载的wine有两种,一种是`wineHQ`,一种是`deepin-wine`.首先在终端进行卸载.执行如下指令.  
`sudo apt remove wine`,或之`sudo apt remove deepin-wine`.  
2)删除软件的依赖.终端执行如下指令.  
`sudo apt autoremove`.  
3)手动删除相关的文件.  
`/usr/share`目录下的.  
```sh
/usr/share/applications/
/usr/share/app-install/
```  
用户目录下的,`~/`  
```sh
.locale
.cache
.config/menus/applications-merged/
```  
手动删除虚拟的win盘文件.即用户目录下的`.wine`或之`.deepinwine`.  
4)手动删除的文件,搜索关键字`wine`,删除的不是快捷方式,就是wine安装的软件程序.  
