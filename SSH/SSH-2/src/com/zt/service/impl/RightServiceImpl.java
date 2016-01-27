package com.zt.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zt.dao.BaseDao;
import com.zt.entities.security.Right;
import com.zt.service.RightService;
import com.zt.utils.DataUtils;
import com.zt.utils.StringUtils;
import com.zt.utils.ValidateUtils;

/**
 * RightServiceImpl, name: rightService
 * 
 * @author zengtao
 *
 */
@Service("rightService")
public class RightServiceImpl extends BaseServiceImpl<Right> implements
		RightService {

	@Resource(name = "rightDao")
	public void setDao(BaseDao<Right> dao) {
		// TODO Auto-generated method stub
		super.setDao(dao);
	}

	/**
	 * 保存或者更新权限
	 */
	public void saveOrUpdateRight(Right right) {
		// TODO Auto-generated method stub
		// Insert
		// 权限分组，一组60个权限，使用位运算进行移位操作；
		// 组号从0开始
		// 权限码
		int pos = 0;
		// 权限位
		long code = 1L;
		// save操作
		if (right.getId() == null) {
			/**
			 * 改用该方式进行更新权限位和权限码，此方法更加高效
			 */
			String hql = "SELECT MAX(r.rightPos), MAX(r.rightCode) FROM Right r WHERE r.rightPos = (SELECT MAX(rr.rightPos) FROM Right rr)";

			Object[] arr = (Object[]) this.uniqueResult(hql);
			Integer topPos = (Integer) arr[0];
			Long topCode = (Long) arr[1];

			// 没有权限
			if (topPos == null) {
				pos = 0;
				code = 1L;
			} else {
				// 权限码是否到达最大值
				if (topCode >= (1L << 60)) {
					// 数据库中查询出的权限位已经是某组的最后一个，应该改变权限码即加1，权限位初始化为1L
					pos = topPos + 1;
					code = 1L;
				} else {
					// 权限码不变
					pos = topPos;
					// 权限位左移一位
					code = topCode << 1;
				}
			}
			right.setRightPos(pos);
			right.setRightCode(code);
		}
		// 执行保存或者更新操作
		this.saveOrUpdateEntity(right);
	}

	/**
	 * 按照url增加权限
	 */
	public void appendRightByURL(String url) {
		// TODO Auto-generated method stub
		String hql = "SELECT count(*) FROM Right r WHERE r.rightUrl = ?";
		Long count = (Long) this.uniqueResult(hql, url);
		// 未找到
		if (count == 0) {
			Right right = new Right();
			right.setRightUrl(url);
			this.saveOrUpdateRight(right);
		}
	}

	/**
	 * 批量更新权限
	 */
	@Override
	public void batchUpdateRights(List<Right> allRights) {
		// TODO Auto-generated method stub
		String hql = "UPDATE Right r SET r.rightName = ?, r.common = ? WHERE r.id = ?";
		if (ValidateUtils.isValid(allRights)) {
			for (Right r : allRights) {
				this.batchEntityByHQL(hql, r.getRightName(), r.isCommon(),
						r.getId());
			}
		}

		// 批量更新权限的keyword
		this.updateRightsKeyword(allRights);
	}

	/**
	 * 查询指定范围内的权限
	 */
	@Override
	public List<Right> findRightsInRange(Integer[] ids) {
		// TODO Auto-generated method stub
		if (ValidateUtils.isValid(ids)) {
			String hql = "FROM Right r WHERE r.id in ("
					+ StringUtils.arr2Str(ids) + ")";
			return this.findEntityByHQL(hql);
		}
		return null;
	}

	/**
	 * 查询不在指定范围内的权限
	 */
	@Override
	public List<Right> findRightsNotInRange(Set<Right> rights) {
		// TODO Auto-generated method stub
		if (!ValidateUtils.isValid(rights)) {// 没有授予权限，查询所有权限
			return this.findAllEntities();
		}
		// 查询所有的不在范围内的权限
		String hql = "FROM Right r WHERE r.id not in("
				+ DataUtils.extractEntityIds(rights) + ")";
		return this.findEntityByHQL(hql);
	}

	/**
	 * 根据Right对象更新权限的keyword字段信息
	 */
	@Override
	public void updateStaffsKeyword(Right right) {
		List<Right> rights = findAllEntities();

		if (!ValidateUtils.isValid(rights)) {
			return;
		}

		// execute update by hql
		String hql = "UPDATE Right r SET r.keyword = ? WHERE r.id = ?";

		String keyword = "" + right.getId() + "," + right.getRightName();
		this.executeUpdateByHql(hql, keyword, right.getId());
	}

	/**
	 * 更新权限列表的keyword字段信息
	 */
	@Override
	public void updateRightsKeyword(List<Right> rights) {
		if (!ValidateUtils.isValid(rights)) {
			return;
		}

		// execute update by hql
		String hql = "UPDATE Right r SET r.keyword = ? WHERE r.id = ?";
		for (Right r : rights) {
			String keyword = "" + r.getId() + "," + r.getRightName();
			this.executeUpdateByHql(hql, keyword, r.getId());
		}
	}

	/**
	 * 更新所有权限的keyword字段信息
	 */
	@Override
	public void updateRightsKeyword() {
		List<Right> rights = findAllEntities();
		updateRightsKeyword(rights);
	}
}

/**
 * 此方式需要全表扫描，效率比较低，需要使用max方法来查询
 */
/**
 * // 按权限码和权限位降序排列，以此找到最大的权限码和权限位，作为添加权限的基准 String hql =
 * "FROM Right r ORDER BY r.rightPos desc, r.rightCode desc"; List<Right> rights
 * = this.findEntityByHQL(hql); // 初始集合为空，即开始没有权限 if
 * (!ValidateUtils.isValid(rights)) { // init pos = 0; code = 1L; } else { //
 * 得到最大的权限码和权限位相对应的权限 Right top = rights.get(0); // 分别保存相应的权限码和权限位 int topPos =
 * top.getRightPos(); long topCode = top.getRightCode(); //
 * 数据库中查询出的权限位已经是某组的最后一个，应该改变权限码即加1，权限位初始化为1 if (topCode >= (1L << 60)) { pos =
 * topPos + 1; code = 1; } else { // 权限码不变 pos = topPos; // 权限位左移一位 code =
 * topCode << 1; } }
 */
