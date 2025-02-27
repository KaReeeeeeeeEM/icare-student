package org.openmrs.module.icare.store.models;

// Generated Oct 7, 2020 12:48:40 PM by Hibernate Tools 5.2.10.Final

import org.openmrs.BaseOpenmrsData;
import org.openmrs.Location;
import org.openmrs.module.icare.core.Item;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * StLedgerType generated by hbm2java
 */
@Entity
@Table(name = "st_ledger_type")
public class LedgerType extends BaseOpenmrsData implements java.io.Serializable {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ledger_type_id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "name", length = 32)
	private String name;
	
	@Column(name = "operation", length = 3)
	private String operation;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ledgerType")
	private List<Ledger> stLedgers = new ArrayList<Ledger>(0);
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getOperation() {
		return this.operation;
	}
	
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public List<Ledger> getStLedgers() {
		return stLedgers;
	}
	
	public void setStLedgers(List<Ledger> stLedgers) {
		this.stLedgers = stLedgers;
	}
	
	public static LedgerType fromMap(Map<String, Object> ledgerMap) {
		
		LedgerType ledgerType = new LedgerType();
		
		ledgerType.setName(ledgerMap.get("name").toString());
		ledgerType.setOperation(ledgerMap.get("operation").toString());
		
		return ledgerType;
		
	}
	
	public Map<String, Object> toMap() {
		
		Map<String, Object> ledgerTypeObject = new HashMap<String, Object>();
		
		ledgerTypeObject.put("name", this.getName());
		ledgerTypeObject.put("uuid", this.getUuid());
		ledgerTypeObject.put("operation", this.getOperation());
		
		Map<String, Object> creatorObject = new HashMap<String, Object>();
		if (this.getCreator() != null) {
			creatorObject.put("uuid", this.getCreator().getUuid());
			creatorObject.put("display", this.getCreator().getDisplayString());
			creatorObject.put("name", this.getCreator().getName());
			creatorObject.put("email", this.getCreator().getEmail());
		}
		ledgerTypeObject.put("creator", creatorObject);
		
		return ledgerTypeObject;
		
	}
}
