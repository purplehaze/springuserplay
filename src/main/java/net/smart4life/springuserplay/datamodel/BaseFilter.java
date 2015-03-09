package net.smart4life.springuserplay.datamodel;

/**
 * Created by ILIN02 on 09.03.2015.
 */
public class BaseFilter {
	private SortMeta sortMeta;
	private int first;

	public String getSortField(){
		return sortMeta != null ? sortMeta.getSortField() : null;
	}

	public String getSortOrder(){
		return sortMeta != null ? sortMeta.getSortDirection().name() : SortDirection.DESCENDING.name();
	}

	public SortMeta getSortMeta() {
		return sortMeta;
	}

	public void setSortMeta(SortMeta sortMeta) {
		this.sortMeta = sortMeta;
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}
}
