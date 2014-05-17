package com.liferay.timesheet.bean.view;

import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;
import com.liferay.portal.model.OrganizationConstants;
import com.liferay.portal.model.Team;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.TeamLocalServiceUtil;
import com.liferay.portal.service.permission.TeamPermissionUtil;
import com.liferay.timesheet.primefaces.UserTreeNode;
import com.liferay.timesheet.primefaces.util.TreeNodeUtil;
import com.liferay.timesheet.util.DateTimeUtil;
import com.liferay.timesheet.util.TimeSheetConstants;
import com.liferay.timesheet.util.TimeSheetUtil;
import com.liferay.timesheet.util.UserUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class UserStatisticsViewBean extends AbstractStatisticsBean {

	public UserStatisticsViewBean() {
		super();
	}

	public void init() {
		try {
			setCurrentDate(DateTimeUtil.getTodayWithoutTime());
			setCurrentUser(TimeSheetUtil.getCurrentUser());

			setEndDate(new Date());
			setStartDate(new Date(0));
			setDateNumber(TimeSheetConstants.DATE_DAY);
			setUserList(TimeSheetConstants.USER_LIST_GROUPPED);

			setCurrentLevel(1);
			setUserStatistics(true);

			initUserTree();

			List<Team> teams = TeamLocalServiceUtil.getUserTeams(
				getCurrentUser().getUserId(), TimeSheetUtil.getSiteGroupId());

			currentUserTeams = new ArrayList<Team>();

			for (Team team: teams) {
				if (TeamPermissionUtil.contains(
						TimeSheetUtil.getPermissionChecker(), team.getTeamId(),
						ActionKeys.VIEW)) {

					currentUserTeams.add(team);
				}
			}
		} catch (Exception e) {
			logger.error("Error", e);
		}
	}

	public List<User> getOrganizationUsers() throws Exception {
		return UserUtil.getOrganizationUsers(
			TimeSheetUtil.getCompanyId(),
			OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID);
 	}

	private void initUserTree() throws Exception {
		setRoot(new UserTreeNode());

		TreeNodeUtil.generateUserTreeNodes(getRoot());
	}

	public List<Team> getCurrentUserTeams() {
		return currentUserTeams;
	}

	public void setCurrentUserTeams(List<Team> currentUserTeams) {
		this.currentUserTeams = currentUserTeams;
	}

	private List<Team> currentUserTeams = null;

	private static final long serialVersionUID = 7731185950130246209L;
	private static final Logger logger =
		LoggerFactory.getLogger(UserStatisticsViewBean.class);

}