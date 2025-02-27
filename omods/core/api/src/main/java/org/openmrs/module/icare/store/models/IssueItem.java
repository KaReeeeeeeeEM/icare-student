package org.openmrs.module.icare.store.models;

// Generated Oct 7, 2020 12:48:40 PM by Hibernate Tools 5.2.10.Final

import org.openmrs.BaseOpenmrsData;
import org.openmrs.Location;
import org.openmrs.module.icare.core.Item;
import org.openmrs.module.icare.store.util.Stockable;

import javax.persistence.*;
import java.util.*;

/**
 * StIssueItem generated by hbm2java
 */
@Embeddable
class IssueItemId implements java.io.Serializable {
	
	@ManyToOne
	@JoinColumn(name = "issue_id", nullable = false)
	private Issue issue;
	
	@ManyToOne
	@JoinColumn(name = "item_id", nullable = false)
	private Item item;
	
	@Column(name = "batch_no", length = 32)
	private String batchNo;
	
	public Issue getIssue() {
		return issue;
	}
	
	public void setIssue(Issue issue) {
		this.issue = issue;
	}
	
	public Item getItem() {
		return item;
	}
	
	public void setItem(Item item) {
		this.item = item;
	}
	
	public String getBatchNo() {
		return this.batchNo;
	}
	
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	
}

@Entity
@Table(name = "st_issue_item")
public class IssueItem extends BaseOpenmrsData implements java.io.Serializable, Stockable {
	
	@EmbeddedId
	private IssueItemId id;
	
	@Column(name = "quantity")
	private Double quantity;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "expiry_date", length = 10)
	private Date expiryDate;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "issueItem")
	private List<IssueItemStatus> issueItemStatuses = new ArrayList<IssueItemStatus>(0);
	
	//@OneToMany(fetch = FetchType.LAZY, mappedBy = "id")
	//private List<ReceiptItem> receiptItems = new ArrayList<ReceiptItem>(0);
	
	public Double getQuantity() {
		return this.quantity;
	}
	
	@Override
	public Location getLocation() {
		return this.id.getIssue().getIssueingLocation();
	}
	
	@Override
	public Location getSourceLocation() {
		return this.id.getIssue().getIssueingLocation();
	}
	
	@Override
	public Location getDestinationLocation() {
		return this.id.getIssue().getIssuedLocation();
	}
	
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public Item getItem() {
		return this.id.getItem();
	}
	
	public String getBatchNo() {
		return this.id.getBatchNo();
	}
	
	public void setBatchNo(String batchNo) {
		if (id == null) {
			this.id = new IssueItemId();
		}
		
		this.id.setBatchNo(batchNo);
	}
	
	public Date getExpiryDate() {
		return this.expiryDate;
	}
	
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	/*public List<ReceiptItem> getReceiptItems() {
		return receiptItems;
	}
	
	public void setReceiptItems(List<ReceiptItem> receiptItems) {
		this.receiptItems = receiptItems;
	}*/
	
	public void setIssue(Issue issue) {
		if (id == null) {
			this.id = new IssueItemId();
		}
		this.id.setIssue(issue);
	}
	
	public void setItem(Item item) {
		if (id == null) {
			this.id = new IssueItemId();
		}
		this.id.setItem(item);
	}
	
	public List<IssueItemStatus> getIssueItemStatuses() {
		return issueItemStatuses;
	}
	
	public void setIssueItemStatuses(List<IssueItemStatus> issueItemStatuses) {
		this.issueItemStatuses = issueItemStatuses;
	}
	
	public void setId(IssueItemId id) {
		this.id = id;
		
	}
	
	public Map<String, Object> toMap() {
		Map<String, Object> issueItemObject = new HashMap<String, Object>();
		
		issueItemObject.put("quantity", this.getQuantity());
		issueItemObject.put("batch", this.getBatchNo());
		System.out.println("batch no: "+this.getBatchNo());
		issueItemObject.put("expiryDate", this.getExpiryDate());
		issueItemObject.put("uuid",this.getUuid());
		
		Map<String, Object> itemObject = new HashMap<String, Object>();
		itemObject.put("uuid", this.getIssueItemId().getItem().getUuid());
		if (this.getIssueItemId().getItem().getConcept() != null) {
			itemObject.put("display", this.getIssueItemId().getItem().getConcept().getDisplayString());
		} else if (this.getIssueItemId().getItem().getDrug() != null) {
			itemObject.put("display", this.getIssueItemId().getItem().getDrug().getDisplayName().toString());
		}
		issueItemObject.put("item", itemObject);
		
		Map<String, Object> issueObject = new HashMap<String, Object>();
		issueObject.put("uuid", this.getIssueItemId().getIssue().getUuid());
		
		issueItemObject.put("issue", issueObject);

		if(this.getIssueItemStatuses() != null){

			List<Map<String,Object>> issueItemStatusesMapList = new ArrayList<>();
			Map<String,Object> issueItemStatusMap = new HashMap<>();
			for(IssueItemStatus issueItemStatus : this.getIssueItemStatuses()){
				issueItemStatusMap.put("status",issueItemStatus.getStatus());
			}
			issueItemStatusesMapList.add(issueItemStatusMap);
			issueItemObject.put("issueItemStatuses",issueItemStatusesMapList);
		}


		
		return issueItemObject;
	}
	
	public IssueItemId getIssueItemId() {
		return id;
	}
	
	@Override
	public Integer getId() {
		return null;
	}
	
	@Override
	public void setId(Integer integer) {
		
	}
	
}
