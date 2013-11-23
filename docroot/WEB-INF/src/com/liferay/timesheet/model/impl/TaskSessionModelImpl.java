/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.timesheet.model.impl;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.model.TaskSessionModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the TaskSession service. Represents a row in the &quot;timesheet_TaskSession&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.liferay.timesheet.model.TaskSessionModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link TaskSessionImpl}.
 * </p>
 *
 * @author Istvan Sajtos
 * @see TaskSessionImpl
 * @see com.liferay.timesheet.model.TaskSession
 * @see com.liferay.timesheet.model.TaskSessionModel
 * @generated
 */
public class TaskSessionModelImpl extends BaseModelImpl<TaskSession>
	implements TaskSessionModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a task session model instance should use the {@link com.liferay.timesheet.model.TaskSession} interface instead.
	 */
	public static final String TABLE_NAME = "timesheet_TaskSession";
	public static final Object[][] TABLE_COLUMNS = {
			{ "taskSessionId", Types.BIGINT },
			{ "taskId", Types.BIGINT },
			{ "startTime", Types.TIMESTAMP },
			{ "endTime", Types.TIMESTAMP }
		};
	public static final String TABLE_SQL_CREATE = "create table timesheet_TaskSession (taskSessionId LONG not null primary key,taskId LONG,startTime DATE null,endTime DATE null)";
	public static final String TABLE_SQL_DROP = "drop table timesheet_TaskSession";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.timesheet.model.TaskSession"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.timesheet.model.TaskSession"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = false;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.timesheet.model.TaskSession"));

	public TaskSessionModelImpl() {
	}

	public long getPrimaryKey() {
		return _taskSessionId;
	}

	public void setPrimaryKey(long primaryKey) {
		setTaskSessionId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_taskSessionId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public Class<?> getModelClass() {
		return TaskSession.class;
	}

	public String getModelClassName() {
		return TaskSession.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("taskSessionId", getTaskSessionId());
		attributes.put("taskId", getTaskId());
		attributes.put("startTime", getStartTime());
		attributes.put("endTime", getEndTime());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long taskSessionId = (Long)attributes.get("taskSessionId");

		if (taskSessionId != null) {
			setTaskSessionId(taskSessionId);
		}

		Long taskId = (Long)attributes.get("taskId");

		if (taskId != null) {
			setTaskId(taskId);
		}

		Date startTime = (Date)attributes.get("startTime");

		if (startTime != null) {
			setStartTime(startTime);
		}

		Date endTime = (Date)attributes.get("endTime");

		if (endTime != null) {
			setEndTime(endTime);
		}
	}

	public long getTaskSessionId() {
		return _taskSessionId;
	}

	public void setTaskSessionId(long taskSessionId) {
		_taskSessionId = taskSessionId;
	}

	public long getTaskId() {
		return _taskId;
	}

	public void setTaskId(long taskId) {
		_taskId = taskId;
	}

	public Date getStartTime() {
		return _startTime;
	}

	public void setStartTime(Date startTime) {
		_startTime = startTime;
	}

	public Date getEndTime() {
		return _endTime;
	}

	public void setEndTime(Date endTime) {
		_endTime = endTime;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			TaskSession.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public TaskSession toEscapedModel() {
		if (_escapedModelProxy == null) {
			_escapedModelProxy = (TaskSession)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelProxyInterfaces,
					new AutoEscapeBeanHandler(this));
		}

		return _escapedModelProxy;
	}

	@Override
	public Object clone() {
		TaskSessionImpl taskSessionImpl = new TaskSessionImpl();

		taskSessionImpl.setTaskSessionId(getTaskSessionId());
		taskSessionImpl.setTaskId(getTaskId());
		taskSessionImpl.setStartTime(getStartTime());
		taskSessionImpl.setEndTime(getEndTime());

		taskSessionImpl.resetOriginalValues();

		return taskSessionImpl;
	}

	public int compareTo(TaskSession taskSession) {
		long primaryKey = taskSession.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		TaskSession taskSession = null;

		try {
			taskSession = (TaskSession)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = taskSession.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public void resetOriginalValues() {
	}

	@Override
	public CacheModel<TaskSession> toCacheModel() {
		TaskSessionCacheModel taskSessionCacheModel = new TaskSessionCacheModel();

		taskSessionCacheModel.taskSessionId = getTaskSessionId();

		taskSessionCacheModel.taskId = getTaskId();

		Date startTime = getStartTime();

		if (startTime != null) {
			taskSessionCacheModel.startTime = startTime.getTime();
		}
		else {
			taskSessionCacheModel.startTime = Long.MIN_VALUE;
		}

		Date endTime = getEndTime();

		if (endTime != null) {
			taskSessionCacheModel.endTime = endTime.getTime();
		}
		else {
			taskSessionCacheModel.endTime = Long.MIN_VALUE;
		}

		return taskSessionCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{taskSessionId=");
		sb.append(getTaskSessionId());
		sb.append(", taskId=");
		sb.append(getTaskId());
		sb.append(", startTime=");
		sb.append(getStartTime());
		sb.append(", endTime=");
		sb.append(getEndTime());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(16);

		sb.append("<model><model-name>");
		sb.append("com.liferay.timesheet.model.TaskSession");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>taskSessionId</column-name><column-value><![CDATA[");
		sb.append(getTaskSessionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>taskId</column-name><column-value><![CDATA[");
		sb.append(getTaskId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>startTime</column-name><column-value><![CDATA[");
		sb.append(getStartTime());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>endTime</column-name><column-value><![CDATA[");
		sb.append(getEndTime());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = TaskSession.class.getClassLoader();
	private static Class<?>[] _escapedModelProxyInterfaces = new Class[] {
			TaskSession.class
		};
	private long _taskSessionId;
	private long _taskId;
	private Date _startTime;
	private Date _endTime;
	private TaskSession _escapedModelProxy;
}