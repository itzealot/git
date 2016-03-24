package com.zt.service;

import java.util.List;
import java.util.Set;

import com.zt.entities.security.Right;

/**
 * RightService
 * 
 * @author zengtao
 *
 */
public interface RightService extends BaseService<Right> {

	/**
	 * ����Ȩ��ʱ����������ʱȨ�޲��䣻���������Ȩ��
	 * 
	 * @param right
	 */
	public void saveOrUpdateRight(Right right);

	/**
	 * ����url׷��Ȩ��
	 * 
	 * @param url
	 */
	public void appendRightByURL(String url);

	/**
	 * ��������Ȩ��
	 * 
	 * @param allRights
	 */
	public void batchUpdateRights(List<Right> allRights);

	/**
	 * ��ѯָ����Χ�ڵ�Ȩ��
	 * 
	 * @param rights
	 * @return
	 */
	public List<Right> findRightsInRange(Integer[] ids);

	/**
	 * ��ѯ����ָ����Χ�ڵ�Ȩ��
	 * 
	 * @param rights
	 * @return
	 */
	public List<Right> findRightsNotInRange(Set<Right> rights);

	/**
	 * ��������Ȩ�޵�keyword�ֶ���Ϣ
	 */
	public void updateRightsKeyword();

	/**
	 * ����Right�������Ȩ�޵�keyword�ֶ���Ϣ
	 */
	public void updateStaffsKeyword(Right right);

	/**
	 * ����Ȩ���б��keyword�ֶ���Ϣ
	 */
	public void updateRightsKeyword(List<Right> rights);
}
