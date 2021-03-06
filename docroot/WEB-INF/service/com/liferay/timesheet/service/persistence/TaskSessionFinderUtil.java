/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.timesheet.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Istvan Sajtos, Zsolt Szabo
 */
public class TaskSessionFinderUtil {
	public static java.util.List<com.liferay.timesheet.model.TaskSession> findByC_U_I(
		long companyId, long userId, java.util.Date date1, java.util.Date date2) {
		return getFinder().findByC_U_I(companyId, userId, date1, date2);
	}

	public static java.util.List<com.liferay.timesheet.model.TaskSession> findByU_T_I(
		long userId, long taskId, java.util.Date date1, java.util.Date date2) {
		return getFinder().findByU_T_I(userId, taskId, date1, date2);
	}

	public static TaskSessionFinder getFinder() {
		if (_finder == null) {
			_finder = (TaskSessionFinder)PortletBeanLocatorUtil.locate(com.liferay.timesheet.service.ClpSerializer.getServletContextName(),
					TaskSessionFinder.class.getName());

			ReferenceRegistry.registerReference(TaskSessionFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(TaskSessionFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(TaskSessionFinderUtil.class,
			"_finder");
	}

	private static TaskSessionFinder _finder;
}