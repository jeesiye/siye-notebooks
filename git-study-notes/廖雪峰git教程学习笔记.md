git 的安装  
git 的历史  
集中式和分布式的区别认知  
以上三点省略

> git 快速使用的指令

git init : 初始化一个 git 仓库,会生成一个隐藏文件夹`.git`.  
git add 'file-name' : 将修改提交到暂存区 index.  
git commit -m 'message' : 将修改提交到分支.  
git status : 查看工作区的状态  
git diff : 内嵌工具,比较两个文件的不同  
git rm : 删除文件

> 版本的回退

git log --pretty=oneline  
或者是  
git log  
查看历史提交记录的唯一 id

git reset --hard 'commit-id'  
重置指定版本的工作区

git reflog  
查看历史的输入指令

> 远程仓库的配置

ssh-keygen -t rsa : 在用户目录下生成 ssh 密匙文件.  
git config --global user.name 'yourname' : 配置用户  
git config --global user.email 'youremail' : 配置用户邮箱信息  
git config -l : 查看 global 级别的配置信息列表,文件在~/.gitconfig 中

> 分支 branch

git branch : 查看分支  
git branch 'branch-name' : 创建分支  
git checkout 'branch-name' : 切换分支  
git branch -d `branch-name` : 删除分之
