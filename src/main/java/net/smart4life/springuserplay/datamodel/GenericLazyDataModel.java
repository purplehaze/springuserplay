package net.smart4life.springuserplay.datamodel;

import net.smart4life.springuserplay.entity.Idable;
import net.smart4life.springuserplay.service.DataModelFindAble;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by ILIN02 on 09.03.2015.
 */
public abstract class GenericLazyDataModel<T extends Idable, F extends BaseFilter> extends LazyDataModel<T> {
	private static final long serialVersionUID = 1L;

//	protected final Class<T> entityClass;
//	protected final EntityConverter converter;
	protected final DataModelFindAble service;

	public GenericLazyDataModel(DataModelFindAble service)
	{
		super();
//		this.entityClass = entityClass;
//		this.converter = new EntityConverter();
		this.service = service;

		setPageSize(1);

		setRowCount(-1);
	}

	public abstract F getFilter();

	@Override
	public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		return load(first, pageSize, Arrays.asList(new SortMeta(null, sortField, sortOrder, null)), filters);
	}

	@Override
	public List<T> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
		try
		{
			BaseFilter filter = getFilter();
			filter.setFirst(first);
			if(multiSortMeta != null && multiSortMeta.size() > 0){
				String sField = multiSortMeta.get(0).getSortField();
				SortDirection sDirection = multiSortMeta.get(0).getSortOrder() == null || multiSortMeta.get(0).getSortOrder() == SortOrder.ASCENDING ? SortDirection.ASCENDING : SortDirection.DESCENDING;
				filter.setSortMeta(new net.smart4life.springuserplay.datamodel.SortMeta(sField, sDirection));
			}
			List<T> resultList = service.findByFilter(filter, first, pageSize);

			int rowCnt = service.countByFilter(filter).intValue();
			setRowCount(rowCnt);

			return resultList;
		}
		catch(Exception e)
		{
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@Override
	public Object getRowKey(T entity)
	{
		return entity.getId().toString();
	}

//	@Override
//	public T getRowData(String rowKey)
//	{
//		return (T) converter.getAsObject(null, null, rowKey);
//	}

	@Override
	public T getRowData(String rowKey)
	{
		T ret = null;
		if(rowKey != null && !rowKey.isEmpty())
		{
			List<T> entities = (List<T>)getWrappedData();
			for(T entity : entities)
			{
				if(rowKey.equals(entity.getId().toString()))
				{
					ret = entity;
				}
			}
		}
		return ret;
	}

	@Override
	public void setRowIndex(int rowIndex)
	{
		super.setRowIndex(getPageSize() == 0 ? -1 : rowIndex);
	}
}
