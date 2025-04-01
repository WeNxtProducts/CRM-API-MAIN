package com.vi.base.commonUtils;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.vi.base.commonUtils.QUERY_MASTER;

@Repository
public class CommonDaoImpl implements CommonDao {

    @Autowired
    private NamedParameterJdbcTemplate namedTemplate;
    
    @Autowired
	private JdbcTemplate template;
    
    @Override
    public List<Map<String, Object>> getMapQuery(String query, Map<String, Object> queryParams) {
        return namedTemplate.queryForList(query, queryParams);
    }

    @Override
    public QUERY_MASTER getQueryLov(Integer queryId) {
    	try {
			String sql = "SELECT * FROM LJM_QUERY_MASTER WHERE QM_SYS_ID = ?";
			QUERY_MASTER result = template.queryForObject(sql, new Object[] { queryId },
					new BeanPropertyRowMapper<>(QUERY_MASTER.class));
			return result;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
    }
    
}

