package net.smart4life.springuserplay.datamodel;

/**
 * Created by ILIN02 on 09.03.2015.
 */
public class SortMeta {
	private SortDirection sortDirection;
	private String sortField;

	public SortMeta(String sortField, SortDirection sortDirection){
		this.sortField = sortField;
		this.sortDirection = sortDirection;
	}

	public SortMeta(String sortField){
		this(sortField, SortDirection.ASCENDING);
	}

	public SortDirection getSortDirection() {
		return sortDirection;
	}

	public void setSortDirection(SortDirection sortDirection) {
		this.sortDirection = sortDirection;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
}
