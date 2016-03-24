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
 * ��־��¼��
 * 
 * @author zengtao
 *
 */
public class Logger {

	@Resource
	private LogService logService;

	/**
	 * ��¼
	 * 
	 * @param point
	 * @return
	 */
	public Object record(ProceedingJoinPoint point) {
		// �½���־���󣬻����½�ϵͳʱ��
		Log log = new Log();

		// ��Ҫ������try-catch���У������쳣���Իع�
		try {
			ActionContext context = ActionContext.getContext();
			// ���ò�����
			if (context != null) {
				// �õ�session����
				Map<String, Object> session = context.getSession();
				if (session != null) {
					// �õ�����Ķ���
					Staff staff = (Staff) session.get("staff");
					if (staff != null) {
						// id:name��ʽ
						log.setOperator("" + staff.getId() + ":"
								+ staff.getName());
					}
				}
			}

			// ���ò������Ƽ���������
			String operName = point.getSignature().getName();
			log.setOperName(operName);

			// ��ò�������
			Object[] params = point.getArgs();
			log.setOperParams(StringUtils.arr2Str(params));

			// ����Ŀ�����ķ���
			Object result = point.proceed();

			// ���ò������
			log.setOperResult("success");

			// ���ý����Ϣ
			if (result != null) {
				log.setOperResult(result.toString());
			}
			return result;
		} catch (Throwable e) {
			// TODO: handle exception
			log.setOperResult("failure");
			// ��ȡʧ����Ϣ
			log.setResultMsg(e.getMessage());
		} finally {
			logService.saveEntity(log);
		}
		return null;
	}
}
