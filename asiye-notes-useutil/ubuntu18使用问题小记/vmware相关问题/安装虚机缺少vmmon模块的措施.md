依次执行以下指令  
```sh
cd /tmp
tar -xvf /usr/lib/vmware/modules/source/vmmon.tar
cd vmmon-only/
make
cp vmmon.ko /lib/modules/`uname -r`/misc/vmmon.ko
modprobe vmmon
```  
在执行`modprobe`指令加载模块的时候,有时候会失败.  
有一种原因,是因为windows的自我保护机制,会禁止物理机上的系统共存.这时候只要将bios中的选项`secure boot`给禁用调用,然后重新执行`modprobe`指令加载模块.  
