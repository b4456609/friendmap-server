package ntou.wbse.strategy;

abstract public class ReceviceAndResponse implements Strategy {
	public void execute(){
		action();
		response();
	}
	
	public abstract void action();
	public abstract void response();
}
