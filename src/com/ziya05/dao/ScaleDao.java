package com.ziya05.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ziya05.entities.Factor;
import com.ziya05.entities.Group;
import com.ziya05.entities.InfoItem;
import com.ziya05.entities.Level;
import com.ziya05.entities.Option;
import com.ziya05.entities.PersonalInfo;
import com.ziya05.entities.Question;
import com.ziya05.entities.Relation;
import com.ziya05.entities.Scale;

public class ScaleDao implements IScaleDao {

	@Override
	public List<Scale> getAllScales() throws ClassNotFoundException, SQLException {

		Connection conn = getConn();
		Statement stmt = conn.createStatement();
		
		List<Scale> lst = new ArrayList<Scale>();
		String sql = "select id, name, description from scale where isdelete = 0";
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			Scale scale = new Scale();
			scale.setId(rs.getInt("id"));
			scale.setName(rs.getString("name"));
			scale.setDescription(rs.getString("description"));
			lst.add(scale);
		}
		
		stmt.close();
		conn.close();
		
		return lst;
	}

	@Override
	public PersonalInfo getPersonalInfoByScaleId(int scaleId) throws ClassNotFoundException, SQLException {
		Connection conn = getConn();
		Statement stmt = conn.createStatement();
		
		PersonalInfo info = new PersonalInfo();
		List<InfoItem> items = new ArrayList<InfoItem>();
		info.setItems(items);
		String sql = String.format("select name, title from scale_personal_config where scaleId = %d", scaleId);
		
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			InfoItem item = new InfoItem();
			item.setName(rs.getString("name"));
			item.setTitle(rs.getString("title"));
			items.add(item);
		}
		
		stmt.close();
		conn.close();
		
		return info;
	}

	@Override
	public Scale getScaleByScaleId(int scaleId) throws ClassNotFoundException, SQLException {

		Connection conn = getConn();
		Statement stmt = conn.createStatement();
		
		String sql = String.format("select id, name, description from scale where isdelete = 0 and id = %d", scaleId);
		ResultSet rs = stmt.executeQuery(sql);
		rs.next();

		Scale scale = new Scale();
		scale.setId(rs.getInt("id"));
		scale.setName(rs.getString("name"));
		scale.setDescription(rs.getString("description"));
		
		rs.close();

		List<Question> questionLst = new ArrayList<Question>();
		scale.setItems(questionLst);
		sql = String.format("select questionId, title from question where scaleId = %d", scaleId);
		rs = stmt.executeQuery(sql);
		while(rs.next()) {
			Question question = new Question();
			question.setId(rs.getInt("questionId"));
			question.setTitle(rs.getString("title"));
			question.setItems(new ArrayList<Option>());
			questionLst.add(question);
			
		}
		
		rs.close();
		
		List<Option> optionLst = new ArrayList<Option>();
		sql = String.format("select questionId, optionId, content, score, next from `option` where scaleId = %d", scaleId);
		rs = stmt.executeQuery(sql);
		while(rs.next()) {
			Option option = new Option();
			option.setQuestionId(rs.getInt("questionId"));
			option.setId(rs.getInt("optionId"));
			option.setContent(rs.getString("content"));
			option.setScore(rs.getInt("score"));
			option.setNext(rs.getInt("next"));
			optionLst.add(option);
		}
		
		rs.close();
		conn.close();
		
		for(Question question : questionLst) {
			int len = optionLst.size();
			for (int i = len -1; i >= 0; i--) {
				Option option = optionLst.get(i);
				if (option.getQuestionId() == question.getId()) {
					question.getItems().add(option);
					optionLst.remove(option);
				}
			}
		}
		
		return scale;
	}

	@Override
	public List<Factor> getFactorListByScale(int scaleId) throws ClassNotFoundException, SQLException {
		
//		Connection conn = getConn();
//		Statement stmt = conn.createStatement();
//		
//		List<Factor> factorLst = new ArrayList<Factor>();
//		String sql = String.format("select factorId, name, formula from factor where scaleId = %d", id);
//		ResultSet rs = stmt.executeQuery(sql);
//		while(rs.next()) {
//			Factor factor = new Factor();
//			factor.setFactorId(rs.getInt("factorId"));
//			factor.setName(rs.getString("name"));
//			factor.setFormula(rs.getString("formula"));
//			factorLst.add(factor);
//		}
//		
//		rs.close();
//		
//		List<Level> levelLst = new ArrayList<Level>();
//		sql = String.format("select factorId, minValue, `maxValue`, name, description from `level` where scaleId = %d", id);
//		rs = stmt.executeQuery(sql);
//		while(rs.next()) {
//			Level level = new Level();
//			level.setFactorId(rs.getInt("factorId"));
//			level.setMinValue(rs.getDouble("minValue"));
//			level.setMaxValue(rs.getDouble("maxValue"));
//			level.setName(rs.getString("name"));
//			level.setDescription(rs.getString("description"));
//			levelLst.add(level);
//		}
//		
//		rs.close();
//		
//		for (Factor factor : factorLst) {
//			List<Level> lst = new ArrayList<Level>();
//			factor.setLevelList(lst);
//			int len = levelLst.size();
//			for(int i = len - 1; i >= 0; i--) {
//				Level level = levelLst.get(i);
//				if (factor.getFactorId() == level.getFactorId()) {
//					lst.add(level);
//					levelLst.remove(level);
//				}
//			}
//		}
//		
//		return factorLst;
		return null;
	}
	
	@Override
	public List<Group> getGroupListByScale(int scaleId) {
		
		return null;
	}
	
	@Override
	public List<Relation> getRelationByScale(int scaleId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Connection getConn() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/scale?characterEncoding=utf8&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		Connection conn = DriverManager.getConnection(url, "scale", "scale-01");
		
		return conn;
	}

}
