package com.liferay.timesheet.bean.portlet;

import com.liferay.faces.portal.context.LiferayFacesContext;
import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;
import com.liferay.portal.service.ServiceContext;
import com.liferay.timesheet.NoCurrentTaskSessionException;
import com.liferay.timesheet.NoSelectedTaskException;
import com.liferay.timesheet.TaskSessionCloseException;
import com.liferay.timesheet.TaskSessionCreationException;
import com.liferay.timesheet.TaskSessionUpdateException;
import com.liferay.timesheet.bean.model.TaskModelBean;
import com.liferay.timesheet.bean.model.TaskSessionModelBean;
import com.liferay.timesheet.bean.view.TaskViewBean;
import com.liferay.timesheet.model.Project;
import com.liferay.timesheet.model.Task;
import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.util.ProjectTreeNode;
import com.liferay.timesheet.util.TimesheetUtil;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.TreeNode;

/**
* @author Adorjan Nagy
* @author Tibor Jandi
* @author Istvan Sajtos
* @author Zsolt Szabo
*/

@ManagedBean(name = "taskManagedBean")
@RequestScoped
public class TaskManagedBean implements Serializable {

	public String createTaskAction() {
		long userId = TimesheetUtil.getCurrentUserId();

		TreeNode selectedProjectNode = null;
		Project selectedProject = null;

		LiferayFacesContext liferayFacesContext =
			LiferayFacesContext.getInstance();

		ServiceContext serviceContext =
			TimesheetUtil.createServiceContext();

		try {
			selectedProjectNode = taskViewBean.getSelectedProjectNode();

			selectedProject =
				((ProjectTreeNode)selectedProjectNode).getProject();
		} catch (Exception e) {
			logger.error(e);

			liferayFacesContext.addGlobalErrorMessage(
				"Project node is not selected!");

			return "";
		}

		Task task = null;

		try {
			task = taskModelBean.createTask(
				userId, selectedProject.getProjectId(), serviceContext);

			if (logger.isDebugEnabled()) {
				logger.debug("New Task: " + task.getTaskName());
			}

			TaskSession taskSession = taskSessionModelBean.createTaskSession(
				task.getTaskId());

			taskViewBean.setCurrentTaskSession(taskSession);
		} catch (Exception e) {
			logger.error(e);

			liferayFacesContext.addGlobalErrorMessage("Adding task is failed!");

			return "";
		}

		clear();

		taskViewBean.init();

		return "/views/task/view.xhtml";
	}

	public String createTaskSessionAction() {
		LiferayFacesContext liferayFacesContext =
			LiferayFacesContext.getInstance();

		try {
			Date startTime =
				taskSessionModelBean.getStartTimes().get(getSelectedTaskId());

			taskSessionModelBean.setStartTime(startTime);

			TaskSession taskSession = taskSessionModelBean.createTaskSession(
				selectedTaskId);

			taskViewBean.setCurrentTaskSession(taskSession);

			clear();

			if (logger.isDebugEnabled()) {
				logger.debug(
					"Started taskSession: " + taskSession.getTaskSessionId());
			}
		} catch (NoSelectedTaskException e) {
			logger.error("Unable to select task!");

			liferayFacesContext.addGlobalErrorMessage("Unable to select task!");
		} catch (TaskSessionCloseException e) {
			logger.error("Closing current task session is failed!");

			liferayFacesContext.addGlobalErrorMessage(
				"Closing current task session is failed!");
		} catch (TaskSessionCreationException e) {
			logger.error(
				"Unable to add task session for task: " + selectedTaskId);

			liferayFacesContext.addGlobalErrorMessage(
				"Unable to add task session for task: " + selectedTaskId);
		}

		return "/views/task/view.xhtml";
	}

	public String finishTaskSessionAction() {
		LiferayFacesContext liferayFacesContext =
			LiferayFacesContext.getInstance();

		try {
			taskSessionModelBean.finishTaskSession();
	
			taskViewBean.setCurrentTaskSession(null);
		} catch (NoCurrentTaskSessionException e) {
			logger.error("No current task session!");

			liferayFacesContext.addGlobalErrorMessage(
				"No current task session!");
		} catch (TaskSessionUpdateException e) {
			logger.error("Unable to update task session!");

			liferayFacesContext.addGlobalErrorMessage(
				"Unable to update task session!");
		} catch (Exception e) {
			logger.equals(e);
		}

		return "/views/task/view.xhtml";
	}

	protected void clear() {
		taskModelBean.setTaskName(null);
	}

	public TaskModelBean getTaskModelBean() {
		return taskModelBean;
	}

	public void setTaskModelBean(TaskModelBean taskModelBean) {
		this.taskModelBean = taskModelBean;
	}

	public TaskSessionModelBean getTaskSessionModelBean() {
		return taskSessionModelBean;
	}

	public void setTaskSessionModelBean(
		TaskSessionModelBean taskSessionModelBean) {

		this.taskSessionModelBean = taskSessionModelBean;
	}
	
	public TaskViewBean getTaskViewBean() {
		return taskViewBean;
	}

	public void setTaskViewBean(TaskViewBean taskViewBean) {
		this.taskViewBean = taskViewBean;
	}

	public long getSelectedTaskId() {
		return selectedTaskId;
	}

	public void setSelectedTaskId(long selectedTaskId) {
		this.selectedTaskId = selectedTaskId;
	}

	@ManagedProperty(value="#{param.selectedTaskId}")
	private long selectedTaskId = 0;
	@ManagedProperty(name = "taskViewBean",
		value = "#{taskViewBean}")
	private TaskViewBean taskViewBean;
	@ManagedProperty(name = "taskModelBean",
		value = "#{taskModelBean}")
	private TaskModelBean taskModelBean;
	@ManagedProperty(name = "taskSessionModelBean",
		value = "#{taskSessionModelBean}")
	private TaskSessionModelBean taskSessionModelBean;

	private static final long serialVersionUID = -8412810082872360906L;
	private static Logger logger = LoggerFactory.getLogger(
		TaskManagedBean.class);

}