package com.cinsec.dmc.base.exception.advice;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;

import com.cinsec.dmc.base.exception.DmcSysException;

/**
 * Created by MyElipse 
 * @author huangweijin
 * Date: 2011-3-22 
 * Time: 10:47:54
 * Declare：异常切面类
 */
public class DmcExceptionAdvice implements ThrowsAdvice {

	// 抛DmcSysException切面方法
	public void afterThrowing(Method method, Object[] args, Object obj,
			DmcSysException sysException) {
		
//		this.writeLog(obj, method, sysException);
//		this.sendEmail(sysException);
		
		//sysException.printStackTrace();
	}
	
//	private void sendEmail(DmcSysException sysException){
//		
//		ExceptionCfg exceptionCfg = getEntity(sysException);
//		if(null == exceptionCfg){
//			return;
//		}
//		if(null == exceptionCfg.getIsSendEmail()
//				|| exceptionCfg.getIsSendEmail().intValue() != AppConstant.YES.intValue()){
//			return;
//		}
//		
//		EmailBasicPara emailBasicPara = getEmailBasicPara(exceptionCfg);
//		if(null == emailBasicPara){
//			return;
//		}
//		getEmailService().sendEmail(emailBasicPara);
//	}
//	
//	private void writeLog(Object obj, Method method,
//			DmcSysException sysException) {
//		if(obj instanceof BaseService){
//			BaseService baseService = (BaseService)obj;
//			LogSystem logSystem = this.getEntity(baseService, method, sysException);
//			getLogService().error(logSystem);
//		}
//	}
//	
//	private LogSystem getEntity(BaseService baseService, Method method,
//			DmcSysException sysException) {
//
//		SysInfoBean sysInfo = baseService.getSysInfo();
//		LogSystem logSystem = new LogSystem();
//		if(null != sysInfo.getApplication()){
//			logSystem.setApplicationId(sysInfo.getApplication().getApplicationId());
//		}
//		if(null != sysInfo.getCurModule()){
//			logSystem.setModuleId(sysInfo.getCurModule().getModuleId());
//		}
//		logSystem.setExceptionClass(baseService.getClass().toString());
//		logSystem.setExceptionType(method.getName());
//		logSystem.setLogContent(sysException.getMessage());
//		logSystem.setStackMsg(sysException.getStack().getMessage());
//		if(null != sysInfo.getUserProfile()){
//			logSystem.setEmpNumber(sysInfo.getUserProfile().getEmpNumber());
//			logSystem.setUserLocation(sysInfo.getUserProfile().getLocation());
//		}
//		logSystem.setLogTime(new Date());
//		
//
//		return logSystem;
//	}
	
//	private EmailBasicPara getEmailBasicPara(ExceptionCfg exceptionCfg){
//		
//		EmailBasicPara emailBasicPara = new EmailBasicPara();
//		
//		String[] emailTo = getEmailUsers(exceptionCfg.getEmailUsers());
//		emailBasicPara.setMailTo(emailTo);
//		final String mailSubject = "异常:";
//		emailBasicPara.setMailSubject(mailSubject+DataConverter.valueOf(exceptionCfg.getExceptionCode()));
//		emailBasicPara.setMailContent(exceptionCfg.getExceptionName());
//		
//		return emailBasicPara;
//	}
	
//	private String[] getEmailUsers(String emailUsers){
//		
//		if(DataValidater.isStrEmpty(emailUsers)){
//			return new String[0];
//		}
//		String[] emailUserArr = emailUsers.split(ExceptionConstant.FEN_SIGN);
//		if(DataValidater.isArrEmpty(emailUserArr)){
//			return new String[0];
//		}
//		
//		int len = emailUserArr.length;
//		String[] emailUsersRet = new String[len];
//		for(int i=0; i<len; i++){
//			String emailUser = emailUserArr[i];
//			emailUsersRet[i] = emailUser.split(AppConstant.SPILT_OPER)[1];
//			
//		}
//		return emailUsersRet;
//	}
	
//	private ExceptionCfg getEntity(DmcSysException sysException) {
//
//		ExceptionCfg exceptionCfg = new ExceptionCfg();
//		String code = DataConverter.valueOf(sysException.getCode());
//		exceptionCfg.setExceptionCode(code);
//		exceptionCfg = (ExceptionCfg) getEmailService().querySingle(exceptionCfg);
//		
//		return exceptionCfg;
//	}
	
	// 抛Exception切面方法
//	public void afterThrowing(Method method, Object[] args, Object obj,
//			Exception throwable) {
//		;//throwable.printStackTrace();
//	}

//	public LogService getLogService() {
//		LogService logService = (LogService) ContextFactory
//				.getBean(LogService.class.getSimpleName());
//		return logService;
//	}

//	public EmailService getEmailService() {
//		EmailService emailService = (EmailService) ContextFactory
//				.getBean(EmailService.class.getSimpleName());
//		return emailService;
//	}

}