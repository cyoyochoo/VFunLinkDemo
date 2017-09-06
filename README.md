# VFunLinkDemo
demo演示了 **vfun_link_v2.0.aar** 包中以下几个方法的使用：
* 连接：
  ```java
  VFunLink.link(android.app.Activity activity, LinkListener listener)
  ```
* 通知前后台（在Application中注册Activity生命周期回调实现）：
  ```java  
  VFunLink.notifyForeground()、 VFunLink.notifyBackground()
  ```
* 退出通知：
  ```java  
  VFunLink.notifyExit()  
  ```
* 销毁：
  ```java    
  VFunLink.destroy()  
  ```
* LinkListener回调处理：  
  ```java      
  notifyLinkState(boolean isConnected)、notifyExit() 
  ```
具体请参考demo代码
