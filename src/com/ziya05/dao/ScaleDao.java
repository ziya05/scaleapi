package com.ziya05.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import com.ziya05.entities.Factor;
import com.ziya05.entities.FactorResult;
import com.ziya05.entities.Group;
import com.ziya05.entities.InfoItem;
import com.ziya05.entities.InfoItemOption;
import com.ziya05.entities.Level;
import com.ziya05.entities.Option;
import com.ziya05.entities.OptionSelected;
import com.ziya05.entities.PersonalInfo;
import com.ziya05.entities.Question;
import com.ziya05.entities.Relation;
import com.ziya05.entities.Result;
import com.ziya05.entities.Scale;
import com.ziya05.entities.TesteeData;

public class ScaleDao implements IScaleDao {
	private DataSource ds;
	
	public ScaleDao(DataSource ds) {
		super();
		this.ds = ds;
	}

	@Override
	public List<Scale> getAllScales() throws ClassNotFoundException, SQLException {

		Connection conn = getConn();
		Statement stmt = conn.createStatement();
		
		List<Scale> lst = new ArrayList<Scale>();
		String sql = "select id, name, description from scale where isdelete = 0 order by id";
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			Scale scale = new Scale();
			scale.setId(rs.getInt("id"));
			scale.setName(rs.getString("name"));
			scale.setDescription(rs.getString("description"));
			lst.add(scale);
		}
		
		rs.close();
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
		String sql = String.format("select name, title, `type` from scalepersonalconfig where scaleId = %d", scaleId);
		
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			InfoItem item = new InfoItem();
			item.setName(rs.getString("name"));
			item.setTitle(rs.getString("title"));
			item.setInfoType(rs.getInt("type"));
			items.add(item);
		}
		rs.close();
		
		sql = "select name, `option` from scalepersonaloption";
		rs = stmt.executeQuery(sql);
		List<InfoItemOption> itemOptionLst = new ArrayList<InfoItemOption>();
		while(rs.next()) {
			InfoItemOption itemOption = new InfoItemOption();
			itemOption.setName(rs.getString("name"));
			itemOption.setOption(rs.getString("option"));
			
			itemOptionLst.add(itemOption);
		}
		rs.close();
		
		for (InfoItem item : items) {
			List<InfoItemOption> lst = new ArrayList<InfoItemOption>();
			item.setItemOptions(lst);
			for(InfoItemOption itemOption : itemOptionLst) {
				if (item.getName().equals(itemOption.getName())) {
					lst.add(itemOption);
				}
			}
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
		sql = String.format("select questionId, title, questionType from question where scaleId = %d order by questionId", scaleId);
		rs = stmt.executeQuery(sql);
		while(rs.next()) {
			Question question = new Question();
			question.setId(rs.getInt("questionId"));
			question.setTitle(rs.getString("title"));
			question.setQuestionType(rs.getInt("questionType"));
			questionLst.add(question);
			
		}
		
		rs.close();
		
		List<Option> optionLst = new ArrayList<Option>();
		sql = String.format("select questionId, optionId, content, score, next from `option` where scaleId = %d order by questionId, optionId", scaleId);
		rs = stmt.executeQuery(sql);
		while(rs.next()) {
			Option option = new Option();
			option.setQuestionId(rs.getInt("questionId"));
			option.setOptionId(rs.getString("optionId"));
			option.setContent(rs.getString("content"));
			option.setScore(rs.getInt("score"));
			option.setNext(rs.getInt("next"));
			optionLst.add(option);
		}
		
		rs.close();
		stmt.close();
		conn.close();
		
		for(Question question : questionLst) {
			List<Option> oLst = new ArrayList<Option>();
			question.setItems(oLst);
			int len = optionLst.size();
			for (int i = len -1; i >= 0; i--) {
				Option option = optionLst.get(i);
				if (option.getQuestionId() == question.getId()) {
					oLst.add(option);
					optionLst.remove(option);
				}
			}
			
			Collections.sort(oLst);
		}
		
		return scale;
	}

	@Override
	public List<Factor> getFactorListByScale(int scaleId) throws ClassNotFoundException, SQLException {
		
		Connection conn = getConn();
		Statement stmt = conn.createStatement();
		
		List<Factor> factorLst = new ArrayList<Factor>();
		String sql = String.format("select factorId, name, formula from factor where scaleId = %d order by factorId", scaleId);
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			Factor factor = new Factor();
			factor.setFactorId(rs.getInt("factorId"));
			factor.setName(rs.getString("name"));
			factor.setFormula(rs.getString("formula"));
			factorLst.add(factor);
		}
		
		rs.close();
		
		List<Level> levelLst = new ArrayList<Level>();
		sql = String.format("select factorId, levelId, description, advice from `level` where scaleId = %d order by factorId, levelId", scaleId);
		rs = stmt.executeQuery(sql);
		while(rs.next()) {
			Level level = new Level();
			level.setFactorId(rs.getInt("factorId"));
			level.setLevelId(rs.getInt("levelId"));
			level.setDescription(rs.getString("description"));
			level.setAdvice(rs.getString("advice"));
			levelLst.add(level);
		}
		
		rs.close();
		stmt.close();
		conn.close();
		
		for (Factor factor : factorLst) {
			List<Level> lst = new ArrayList<Level>();
			factor.setLevelList(lst);
			int len = levelLst.size();
			for(int i = len - 1; i >= 0; i--) {
				Level level = levelLst.get(i);
				if (factor.getFactorId() == level.getFactorId()) {
					lst.add(level);
					levelLst.remove(level);
				}
			}
		}
		
		return factorLst;
	}
	
	@Override
	public List<Group> getGroupListByScale(int scaleId) throws ClassNotFoundException, SQLException {
		Connection conn = getConn();
		Statement stmt = conn.createStatement();
		
		List<Group> groupLst = new ArrayList<Group>();
		String sql = String.format("select groupId, name, formula from `group` where scaleId = %d order by groupId", scaleId);
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			Group group = new Group();
			group.setGroupId(rs.getInt("groupId"));
			group.setName(rs.getString("name"));
			group.setFormula(rs.getString("formula"));
			groupLst.add(group);
		}
		
		rs.close();
		stmt.close();
		conn.close();
		
		return groupLst;
	}
	
	@Override
	public List<Relation> getRelationByScale(int scaleId) throws ClassNotFoundException, SQLException {
		Connection conn = getConn();
		Statement stmt = conn.createStatement();
		
		List<Relation> relationLst = new ArrayList<Relation>();
		String sql = String.format("select factorId, groupId, points from relation where scaleId = %d order by factorId, groupId", scaleId);
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			Relation relation = new Relation();
			relation.setFactorId(rs.getInt("factorId"));
			relation.setGroupId(rs.getInt("groupId"));
			relation.setPoints(rs.getString("points"));
			relationLst.add(relation);
		}
		
		rs.close();
		stmt.close();
		conn.close();
		
		return relationLst;
	}
	
	@Override
	public int insertTesteeBase(int scaleId, TesteeData data) throws ClassNotFoundException, SQLException {
		Connection conn = getConn();
		Statement stmt = conn.createStatement();
		
		String sql = String.format("insert into testeebase(scaleId, name, gender, age, testTime) values(%s, '%s', '%s', %f, now())", 
				scaleId,
				data.getInfo().getName(), 
				data.getInfo().getGender(),
				data.getInfo().getAge());
		
		stmt.execute(sql, Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = stmt.getGeneratedKeys();
		rs.next();
		int id = rs.getInt(1);
		
		rs.close();
		stmt.close();
		conn.close();
		
		return id;
	}
	
	@Override
	public void insertTesteePersonalInfo(int scaleId, int baseId, TesteeData data) throws ClassNotFoundException, SQLException {
		Connection conn = getConn();
		conn.setAutoCommit(false);
		Statement stmt = conn.createStatement();
		
		String userName = data.getInfo().getName();
		for(InfoItem item : data.getInfo().getItems()) {
			String sql = String.format("insert into testeepersonalinfo(scaleId, baseId, userName, name, title, content) values(%d, %d, '%s', '%s', '%s', '%s')",
					scaleId,
					baseId,
					userName,
					item.getName(),
					item.getTitle(),
					item.getContent());
			stmt.addBatch(sql);
		}
		int[] rs = stmt.executeBatch();		
		conn.commit();
		conn.setAutoCommit(true);
		
		stmt.close();
		conn.close();
	}
	
	@Override
	public void insertTesteeData(int scaleId, int baseId, TesteeData data) throws ClassNotFoundException, SQLException {
		Connection conn = getConn();
		Statement stmt = conn.createStatement();
		
		Collections.sort(data.getData().getItems());
		
		StringBuilder questionIds = new StringBuilder(); 
		StringBuilder optionSelected = new StringBuilder();
		StringBuilder scoreSelected = new StringBuilder();
		
		for(OptionSelected selected : data.getData().getItems()) {
			questionIds.append(selected.getQuestionId() + ",");
			optionSelected.append(selected.getOptionId() + ",");
			scoreSelected.append(selected.getScore() + ",");
		}
		
		String sql = String.format("insert into testeedata(scaleId, baseId, questionIds, optionSelected, ScoreSelected) values(%d, %d, '%s', '%s', '%s')", 
				scaleId,
				baseId,
				questionIds.toString(),
				optionSelected.toString(),
				scoreSelected.toString());
		
		stmt.execute(sql);
		
		stmt.close();
		conn.close();
	}
	
	@Override
	public void insertResultBase(int scaleId, int testeeBaseId, Result result)
			throws ClassNotFoundException, SQLException {
		Connection conn = getConn();
		String sql = "insert into resultbase(scaleId, testeeBaseId, `groups`) values(?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		StringBuilder groups = new StringBuilder();
		for(String group : result.getGroupLst()) {
			groups.append(group + ",");
		}
		
		pstmt.setInt(1, scaleId);
		pstmt.setInt(2, testeeBaseId);
		pstmt.setString(3, groups.toString());		
		
		pstmt.execute();
		
		pstmt.close();
		conn.close();
	}
	
	@Override
	public void insertResultFactor(int scaleId, int testeeBaseId, Result result)
			throws ClassNotFoundException, SQLException {
		Connection conn = getConn();
		conn.setAutoCommit(false);
		String sql = "insert into resultfactor(scaleId, factorId, testeeBaseId, name, score, levelId, description, advice) values(?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		for(FactorResult fr : result.getItems()) {
			pstmt.setInt(1, scaleId);
			pstmt.setInt(2,  fr.getFactorId());
			pstmt.setInt(3,  testeeBaseId);
			pstmt.setString(4, fr.getName());
			pstmt.setDouble(5, fr.getScore());
			pstmt.setInt(6, fr.getLevelId());
			
			if (fr.getDescription() != null) {
				pstmt.setString(7, fr.getDescription());
			} else {
				pstmt.setNull(7, Types.VARCHAR);
			}
			
			if (fr.getAdvice() != null) {
				pstmt.setString(8, fr.getAdvice());
			}else {
				pstmt.setNull(8, Types.VARCHAR);
			}
			
			pstmt.addBatch();
		}
		
		pstmt.executeBatch();
		conn.commit();
		
		conn.setAutoCommit(true);
		
		pstmt.close();
		conn.close();
	}
	
	private Connection getConn() throws SQLException, ClassNotFoundException {
		return ds.getConnection();
	}

}
