package ntou.wbse;

import javax.websocket.server.ServerEndpointConfig.Configurator;

public class FriendmapConfigurator extends Configurator {
	private static Friendmap friendmap = new Friendmap();
	 
    @Override
    public <T> T getEndpointInstance(Class<T> endpointClass) throws InstantiationException {
        return (T)friendmap;
    }
}
