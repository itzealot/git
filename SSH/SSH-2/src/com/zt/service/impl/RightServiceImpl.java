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
	 * ������߸���Ȩ��
	 */
	public void saveOrUpdateRight(Right right) {
		// TODO Auto-generated method stub
		// Insert
		// Ȩ�޷��飬һ��60��Ȩ�ޣ�ʹ��λ���������λ������
		// ��Ŵ�0��ʼ
		// Ȩ����
		int pos = 0;
		// Ȩ��λ
		long code = 1L;
		// save����
		if (right.getId() == null) {
			/**
			 * ���ø÷�ʽ���и���Ȩ��λ��Ȩ���룬�˷������Ӹ�Ч
			 */
			String hql = "SELECT MAX(r.rightPos), MAX(r.rightCode) FROM Right r WHERE r.rightPos = (SELECT MAX(rr.rightPos) FROM Right rr)";

			Object[] arr = (Object[]) this.uniqueResult(hql);
			Integer topPos = (Integer) arr[0];
			Long topCode = (Long) arr[1];

			// û��Ȩ��
			if (topPos == null) {
				pos = 0;
				code = 1L;
			} else {
				// Ȩ�����Ƿ񵽴����ֵ
				if (topCode >= (1L << 60)) {
					// ���ݿ��в�ѯ����Ȩ��λ�Ѿ���ĳ������һ����Ӧ�øı�Ȩ���뼴��1��Ȩ��λ��ʼ��Ϊ1L
					pos = topPos + 1;
					code = 1L;
				} else {
					// Ȩ���벻��
					pos = topPos;
					// Ȩ��λ����һλ
					code = topCode << 1;
				}
			}
			right.setRightPos(pos);
			right.setRightCode(code);
		}
		// ִ�б�����߸��²���
		this.saveOrUpdateEntity(right);
	}

	/**
	 * ����url����Ȩ��
	 */
	public void appendRightByURL(String url) {
		// TODO Auto-generated method stub
		String hql = "SELECT count(*) FROM Right r WHERE r.rightUrl = ?";
		Long count = (Long) this.uniqueResult(hql, url);
		// δ�ҵ�
		if (count == 0) {
			Right right = new Right();
			right.setRightUrl(url);
			this.saveOrUpdateRight(right);
		}
	}

	/**
	 * ��������Ȩ��
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

		// ��������Ȩ�޵�keyword
		this.updateRightsKeyword(allRights);
	}

	/**
	 * ��ѯָ����Χ�ڵ�Ȩ��
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
	 * ��ѯ����ָ����Χ�ڵ�Ȩ��
	 */
	@Override
	public List<Right> findRightsNotInRange(Set<Right> rights) {
		// TODO Auto-generated method stub
		if (!ValidateUtils.isValid(rights)) {// û������Ȩ�ޣ���ѯ����Ȩ��
			return this.findAllEntities();
		}
		// ��ѯ���еĲ��ڷ�Χ�ڵ�Ȩ��
		String hql = "FROM Right r WHERE r.id not in("
				+ DataUtils.extractEntityIds(rights) + ")";
		return this.findEntityByHQL(hql);
	}

	/**
	 * ����Right�������Ȩ�޵�keyword�ֶ���Ϣ
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
	 * ����Ȩ���б��keyword�ֶ���Ϣ
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
	 * ��������Ȩ�޵�keyword�ֶ���Ϣ
	 */
	@Override
	public void updateRightsKeyword() {
		List<Right> rights = findAllEntities();
		updateRightsKeyword(rights);
	}
}

/**
 * �˷�ʽ��Ҫȫ��ɨ�裬Ч�ʱȽϵͣ���Ҫʹ��max��������ѯ
 */
/**
 * // ��Ȩ�����Ȩ��λ�������У��Դ��ҵ�����Ȩ�����Ȩ��λ����Ϊ���Ȩ�޵Ļ�׼ String hql =
 * "FROM Right r ORDER BY r.rightPos desc, r.rightCode desc"; List<Right> rights
 * = this.findEntityByHQL(hql); // ��ʼ����Ϊ�գ�����ʼû��Ȩ�� if
 * (!ValidateUtils.isValid(rights)) { // init pos = 0; code = 1L; } else { //
 * �õ�����Ȩ�����Ȩ��λ���Ӧ��Ȩ�� Right top = rights.get(0); // �ֱ𱣴���Ӧ��Ȩ�����Ȩ��λ int topPos =
 * top.getRightPos(); long topCode = top.getRightCode(); //
 * ���ݿ��в�ѯ����Ȩ��λ�Ѿ���ĳ������һ����Ӧ�øı�Ȩ���뼴��1��Ȩ��λ��ʼ��Ϊ1 if (topCode >= (1L << 60)) { pos =
 * topPos + 1; code = 1; } else { // Ȩ���벻�� pos = topPos; // Ȩ��λ����һλ code =
 * topCode << 1; } }
 */
