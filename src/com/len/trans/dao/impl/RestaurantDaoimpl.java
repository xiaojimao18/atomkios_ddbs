package com.len.trans.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.len.trans.dao.RestaurantDao;
import com.len.trans.pojo.Restaurant;
import com.len.trans.service.impl.DDBSDaoUtil;

@Repository("restaurantDao")
public class RestaurantDaoimpl implements RestaurantDao {
	@Autowired
	@Qualifier("ddbsDaoUtil")
	private DDBSDaoUtil ddbsDaoUtil;
	
	@Override
	public List<Restaurant> getRestaurantList(String location) {
		List<Restaurant> resList = new ArrayList<Restaurant>();
		
		String tableName = "restaurant";
		String[] fields = {"Location"};
		Object[] params = {location};
		List<JdbcTemplate> jdbcTemplateList = ddbsDaoUtil.getQueryJdbcTemplateList(tableName, fields, params);

		String sql = "select * from " + tableName + " where Location = ?";
		for(JdbcTemplate j : jdbcTemplateList){
			resList.addAll(j.query(sql, params, new RestaurantWrapper()));
			if(!resList.isEmpty()) {
				break;
			}
		}
		
		return resList;
	}

}
