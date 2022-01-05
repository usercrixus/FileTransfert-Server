package supra.server.socket;

public interface ConnectionObserver {
	public void updateAfterConnectionToFileServer();
	public void updateAfterConnectionToCommandServer();
}
