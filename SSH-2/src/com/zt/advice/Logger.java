package com.zt.advice;

import java.util.Map;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;

import com.opensymphony.xwork2.ActionContext;
import com.zt.entities.Log;
import com.zt.entities.Staff;
import com.zt.service.LogService;
import com.zt.utils.StringUtils;

/**
 * 日志记录仪
 * 
 * @author zengtao
 *
 */
public class Logger {

	@Resource
	private LogService logService;

	/**
	 * 记录
	 * 
	 * @param point
	 * @return
	 */
	public Object record(ProceedingJoinPoint point) {
		// 新建日志对象，会有新建系统时间
		Log log = new Log();

		// 需要包含在try-catch块中，出现异常可以回滚
		try {
			ActionContext context = ActionContext.getContext();
			// 设置操作人
			if (context != null) {
				// 得到session对象
				Map<String, Object> session = context.getSession();
				if (session != null) {
					// 得到登入的对象
					Staff staff = (Staff) session.get("staff");
					if (staff != null) {
						// id:name形式
						log.setOperator("" + staff.getId() + ":"
								+ staff.getName());
					}
				}
			}

			// 设置操作名称即函数名称
			String operName = point.getSignature().getName();
			log.setOperName(operName);

			// 获得操作参数
			Object[] params = point.getArgs();
			log.setOperParams(StringUtils.arr2Str(params));

			// 调用目标对象的方法
			Object result = point.proceed();

			// 设置操作结果
			log.setOperResult("success");

			// 设置结果消息
			if (result != null) {
				log.setOperResult(result.toString());
			}
			return result;
		} catch (Throwable e) {
			// TODO: handle exception
			log.setOperResult("failure");
			// 获取失败信息
			log.setResultMsg(e.getMessage());
		} finally {
			logService.saveEntity(log);
		}
		return null;
	}
}
