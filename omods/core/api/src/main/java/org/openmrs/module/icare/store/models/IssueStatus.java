package org.openmrs.module.icare.store.models;

// Generated Oct 7, 2020 12:48:40 PM by Hibernate Tools 5.2.10.Final

import org.openmrs.BaseOpenmrsData;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * StIssueStatus generated by hbm2java
 */
@Entity
@Table(name = "st_issue_status")
public class IssueStatus extends BaseOpenmrsData implements java.io.Serializable {
	
	@Id
	@GeneratedValue()
	@Column(name = "issue_status_id", unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "issue_id")
	private Issue issue;
	
	@Column(name = "status", length = 32)
	private IssueStatusCode status;
	
	@Column(name = "remarks", length = 65535)
	private String remarks;
	
	public IssueStatusCode getStatus() {
		return this.status;
	}
	
	public void setStatus(IssueStatusCode status) {
		this.status = status;
	}
	
	public String getRemarks() {
		return this.remarks;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public Issue getIssue() {
		return issue;
	}
	
	public void setIssue(Issue issue) {
		this.issue = issue;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public static enum IssueStatusCode {
		ISSUED, CANCELLED, REJECTED, RECEIVED;
		
		private IssueStatusCode() {
		}
	}
	
	public Map<String, Object> toMap() {
		
		Map<String, Object> issueStatusObject = new HashMap<String, Object>();
		
		issueStatusObject.put("uuid", this.getUuid());
		Date date = this.getDateCreated();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		issueStatusObject.put("created", dateFormat.format(date));
		issueStatusObject.put("remarks", this.getRemarks());
		issueStatusObject.put("status", this.getStatus());
		
		Map<String, Object> creatorObject = new HashMap<String, Object>();
		if (this.getCreator() != null) {
			creatorObject.put("uuid", this.getCreator().getUuid());
			creatorObject.put("display", this.getCreator().getDisplayString());
		}
		issueStatusObject.put("creator", creatorObject);
		
		issueStatusObject.put("issue", this.getIssue().getUuid());
		
		return issueStatusObject;
	}
	
	public static IssueStatus fromMap(Map<String, Object> issueStatusMap) {
		
		IssueStatus issueStatus = new IssueStatus();
		issueStatus.setRemarks(issueStatusMap.get("remarks").toString());
		issueStatus.setStatus(IssueStatusCode.valueOf(issueStatusMap.get("status").toString()));
		
		Issue issue = new Issue();
		issue.setUuid(((Map) issueStatusMap.get("issue")).get("uuid").toString());
		issueStatus.setIssue(issue);
		
		return issueStatus;
	}
	
}
