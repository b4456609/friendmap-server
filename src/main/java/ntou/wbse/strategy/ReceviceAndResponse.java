package ntou.wbse.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ntou.wbse.FriendmapController;

abstract public class ReceviceAndResponse implements Strategy {

	public void execute() {
		action();
		response();
	}

	public abstract void action();

	public abstract void response();
}
