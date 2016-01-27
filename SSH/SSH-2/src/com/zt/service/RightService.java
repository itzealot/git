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
	 * 插入权限时自增，更新时权限不变；即保存更新权限
	 * 
	 * @param right
	 */
	public void saveOrUpdateRight(Right right);

	/**
	 * 按照url追加权限
	 * 
	 * @param url
	 */
	public void appendRightByURL(String url);

	/**
	 * 批量更新权限
	 * 
	 * @param allRights
	 */
	public void batchUpdateRights(List<Right> allRights);

	/**
	 * 查询指定范围内的权限
	 * 
	 * @param rights
	 * @return
	 */
	public List<Right> findRightsInRange(Integer[] ids);

	/**
	 * 查询不在指定范围内的权限
	 * 
	 * @param rights
	 * @return
	 */
	public List<Right> findRightsNotInRange(Set<Right> rights);

	/**
	 * 更新所有权限的keyword字段信息
	 */
	public void updateRightsKeyword();

	/**
	 * 根据Right对象更新权限的keyword字段信息
	 */
	public void updateStaffsKeyword(Right right);

	/**
	 * 更新权限列表的keyword字段信息
	 */
	public void updateRightsKeyword(List<Right> rights);
}
