package com.estsoft.web.action.user;

import com.estsoft.web.action.Action;
import com.estsoft.web.action.ActionFactory;

public class UserActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if("joinform".equals(actionName)){
			action = new JoinFormAction();
		}else if("join".equals(actionName)){
			action = new JoinAction();
		}else if("joinsuccess".equals(actionName)){
			action = new JoinSuccessAction();
		}else if("loginform".equals(actionName)){
			action = new LoginFormAction();
		}
		else{
			action = new DefaultAction();
		}
		return action;
	}

}
