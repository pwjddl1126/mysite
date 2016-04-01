package com.estsoft.web.action.guestbook;

import com.estsoft.web.action.Action;
import com.estsoft.web.action.ActionFactory;

public class GuestbookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if( "insert".equals( actionName )  ) {
			action = new InsertAction();
		} else if( "deleteform".equals( actionName )  ) {
			action = new DeleteFormAction();
		} else if( "delete".equals( actionName )  ) {
			action = new DeleteAction();
		} else {
			action = new ListAction();
		}
		
		return action;
	}

}

