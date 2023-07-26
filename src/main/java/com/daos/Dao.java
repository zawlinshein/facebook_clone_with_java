package com.daos;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Dao<O, P, I> {
	
	void insert(O o, P p) throws SQLException;
	O selectById(I i) throws SQLException;
	ArrayList<O> selectAll() throws SQLException;
	void delete(I i) throws SQLException;
	void upDate(O o) throws SQLException;
}