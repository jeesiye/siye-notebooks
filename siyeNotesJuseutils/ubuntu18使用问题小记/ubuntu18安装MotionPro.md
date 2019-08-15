- 关键字搜索`array ssl vpn`,下载所需版本的vpn客户端.  
- 参考官方的安装手册,若是无法安装,进一步设置.  
- 注意,应当给下载的文件分配执行权限.  
- 进入如下的设置  
  ```sh
  printf '%s\n' '#!/bin/bash' 'exit 0' | sudo tee -a /etc/rc.local
  sudo chmod +x /etc/rc.local
  ```  
