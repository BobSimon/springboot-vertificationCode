# springboot-vertificationCode
一个基于springboot的前后端分离的登录注册验证码功能

接口说明：

URL	类名	接口	接口描述
/captcha	JcaptchaImageCreater	handleRequest	生成图片验证码
api/v1/user/login	LoginController	doAdd	校验用户密码和验证码是否正确

