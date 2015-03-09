package net.smart4life.springuserplay.service;

import net.smart4life.springuserplay.datamodel.BaseFilter;
import net.smart4life.springuserplay.entity.Idable;

import java.util.List;

/**
 * Created by ILIN02 on 09.03.2015.
 */
public interface DataModelFindAble<T extends Idable, F extends BaseFilter> {

	List<T> findByFilter(F filter, int first, int pageSize);
	Long countByFilter(F filter);
}
