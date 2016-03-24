package com.zt.entities;

import java.util.Date;

/**
 * 日志类<br />
 * extends BaseEntity
 * 
 * @author zengtao
 *
 */
public class Log {

	private String id;
	// 操作人
	private String operator;
	// 操作名称，方法名
	private String operName;
	// 操作参数
	private String operParams;
	// 操作结果，成功，失败
	private String operResult;
	// 结果消息
	private String resultMsg;
	// 操作时间
	private Date operTime = new Date();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	public String getOperParams() {
		return operParams;
	}

	public void setOperParams(String operParams) {
		this.operParams = operParams;
	}

	public String getOperResult() {
		return operResult;
	}

	public void setOperResult(String operResult) {
		this.operResult = operResult;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public Date getOperTime() {
		return operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

	@Override
	public String toString() {
		return "Log [id=" + id + ", operator=" + operator + ", operName="
				+ operName + ", operParams=" + operParams + ", operResult="
				+ operResult + ", resultMsg=" + resultMsg + ", operTime="
				+ operTime + "]";
	}

}
