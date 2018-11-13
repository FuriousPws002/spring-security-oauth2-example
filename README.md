# spring-security-oauth2-example
spring-security-oauth2认证服务和资源服务示例

## 说明
该项目是基于spring-security-oauth2实现的demo
包含了认证服务和资源服务示例，由于用户认证采用了jdbc的方式，所以要先导入oauth2.sql文件
主要参考了官方文档得以实现，为了方便自己学习和供他人参考，特记录于此，项目中难免有写得不当之处，若有疑问，可发送邮箱furiouspws002@gmail.com共同交流

## 获取access_token示例
### 授权码模式authorization_code
第一步：
请求http://localhost:8080/oauth/authorize?client_id=testclient&response_type=code&redirect_uri=https://www.baidu.com获取code
第二步：
https://www.baidu.com/?code=Z0HXTU
第三步：
通过code获取access_token
http://localhost:8080/oauth/token?grant_type=authorization_code&code=Z0HXTU&client_id=testclient&client_secret=testsecret&redirect_uri=https://www.baidu.com

### refresh_token模式
http://testclient:testsecret@localhost:8080/oauth/token?grant_type=refresh_token&refresh_token=e9f25584-5c6e-4dc8-9903-bb5657815ea0
需要在AuthorizationServerEndpointsConfigurer中注入UserDetailsService

### 简化模式implicit
http://localhost:8080/oauth/authorize?response_type=token&client_id=testclient&redirect_uri=https://www.baidu.com

### 客户端模式client_credentials
http://testclient:testsecret@localhost:8080/oauth/token?grant_type=client_credentials

### 密码模式password
http://localhost:8080/oauth/token?username=user1&password=123456&grant_type=password&scope=read&client_id=testclient&client_secret=testsecret
需要重写WebSecurityConfigurerAdapter类中authenticationManagerBean方法

## 注意事项
要实现密码模式，须重写WebSecurityConfigurerAdapter类中authenticationManagerBean方法