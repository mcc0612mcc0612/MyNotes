package com.itheima.po;
/**
 * �ͻ��־û���
 */
public class Customer {
	private Integer id;       // ����id
	private String username; // �ͻ�����
	private String jobs;      // ְҵ
	private String phone;     // �绰
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getJobs() {
		return jobs;
	}
	public void setJobs(String jobs) {
		this.jobs = jobs;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", username=" + username + 
				       ", jobs=" + jobs + ", phone=" + phone + "]";
	}
}
