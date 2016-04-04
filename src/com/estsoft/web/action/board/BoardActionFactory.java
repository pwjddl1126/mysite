package com.estsoft.web.action.board;

import com.estsoft.web.action.Action;
import com.estsoft.web.action.ActionFactory;
import com.estsoft.web.action.guestbook.DeleteAction;
import com.estsoft.web.action.guestbook.DeleteFormAction;
import com.estsoft.web.action.guestbook.InsertAction;
import com.estsoft.web.action.guestbook.ListAction;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if ("bInsert".equals(actionName)) {
			action = new BoardInsertAction();
		} else if ("bDelete".equals(actionName)) {
			action = new BoardDeleteAction();
		} else if ("bUpdate".equals(actionName)) {
			action = new BoardUpadateAction();
		} else if ("bView".equals(actionName)) {
			action = new BoardViewAction();
		} else if ("bWrite".equals(actionName)) {
			action = new BoardInsertFormAction();
		} else {
			action = new BoardListAction();
		}

		return action;
	}

}
