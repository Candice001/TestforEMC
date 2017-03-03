package emc.test3.storage.entity;

public class LunUnit {
	private long id;
	private int size;
	private String mountHost;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getMountHost() {
		return mountHost;
	}
	public void setMountHost(String mountHost) {
		this.mountHost = mountHost;
	}
}
