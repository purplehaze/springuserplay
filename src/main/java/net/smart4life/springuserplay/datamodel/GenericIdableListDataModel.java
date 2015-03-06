package net.smart4life.springuserplay.datamodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.model.ListDataModel;

import net.smart4life.springuserplay.entity.Idable;
import org.primefaces.model.SelectableDataModel;


/**
 * The Class GenericEntityListDataModel.
 * 
 * @param <T>
 *          the generic type
 */
public class GenericIdableListDataModel<T extends Idable> extends ListDataModel<T> implements
        SelectableDataModel<T>
{

	/**
	 * Map für eine schnellere Suche nach einem selektierten Objekt.
	 */
	private Map<String, T> dataMap;

	/**
	 * Instantiates a new generic entity list data model.
	 */
	public GenericIdableListDataModel()
	{
	}

	/**
	 * Setzt die Daten ins Datamodel und das Map.
	 * 
	 * @param data
	 *          the data
	 */
	public GenericIdableListDataModel(List<T> data)
	{
		super(data);
		dataMap = new HashMap<String, T>();
		for(T t : data)
		{
			dataMap.put("" + t.getId(), t);
		}
	}

	/**
	 * Setzt die Daten ins Datamodel und das Map.
	 * 
	 * @param data
	 *          the data
	 */
	public GenericIdableListDataModel(Set<T> data)
	{
		super(new ArrayList<T>(data));
		dataMap = new HashMap<String, T>();
		for(T t : data)
		{
			dataMap.put("" + t.getId(), t);
		}
	}

	/**
	 * Sucht im map nach einer Entität. Ist die Entität nicht im Map, wird
	 * zusätzlich das Datamodel durchsucht.
	 * 
	 * @param rowKey
	 *          Integer id einer entität
	 * @return ein AuditTrailBase Objekt
	 */
	@Override
	public T getRowData(String rowKey)
	{
		T ret = null;
		if(rowKey != null && !rowKey.isEmpty())
		{
			// Suche zuerst im Map
			ret = dataMap.get(rowKey);
			if(ret == null)
			{
				// Wenn die Entität nicht im Map ist, suche im DataModel.
				@SuppressWarnings("unchecked")
				List<T> entities = (List<T>)getWrappedData();
				for(T entity : entities)
				{
					if(rowKey.equals("" + entity.getId()))
					{
						ret = entity;
					}
				}
			}
		}
		return ret;
	}

	/**
	 * Gibt einen RowKey für die Selektion in einem Primefaces Datatable zurück.
	 * 
	 * @param entity
	 *          (AuditTrailbase)
	 * @return Integer - die Id einer Entität (AuditTrailbase)
	 */
	@Override
	public Object getRowKey(T entity)
	{
		return entity.getId();
	}
}
