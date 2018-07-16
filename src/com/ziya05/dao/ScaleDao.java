package com.ziya05.dao;

import java.sql.Connection;
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
import com.ziya05.entities.FactorMap;
import com.ziya05.entities.FactorResult;
import com.ziya05.entities.GlobalJump;
import com.ziya05.entities.Group;
import com.ziya05.entities.InfoItem;
import com.ziya05.entities.InfoItemOption;
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

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		List<Scale> lst = new ArrayList<Scale>();
		try
		{
			conn = getConn();
			stmt = conn.createStatement();
			
			
			String sql = "select id, name, questionCount, description from scale where isdelete = 0 order by id";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Scale scale = new Scale();
				scale.setId(rs.getInt("id"));
				scale.setName(rs.getString("name"));
				scale.setQuestionCount(rs.getInt("questionCount"));
				scale.setDescription(rs.getString("description"));
				lst.add(scale);
			}
		
		}finally {
			this.closeResultSet(rs);
			this.closeStatement(stmt);
			this.closeConn(conn);
		}
		
		
		
		return lst;
	}

	@Override
	public PersonalInfo getPersonalInfoByScaleId(int scaleId) throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		PersonalInfo info = new PersonalInfo();
		
		try
		{
			conn = getConn();
			stmt = conn.createStatement();
				
			List<InfoItem> items = new ArrayList<InfoItem>();
			info.setItems(items);
			String sql = String.format("select name, title, `type` from scalepersonalconfig where scaleId = %d", scaleId);
			
			rs = stmt.executeQuery(sql);
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
			
			for (InfoItem item : items) {
				List<InfoItemOption> lst = new ArrayList<InfoItemOption>();
				item.setItemOptions(lst);
				for(InfoItemOption itemOption : itemOptionLst) {
					if (item.getName().equals(itemOption.getName())) {
						lst.add(itemOption);
					}
				}
			}
		} finally {
			this.closeResultSet(rs);
			this.closeStatement(stmt);
			this.closeConn(conn);
		}
		
		return info;
	}

	@Override
	public Scale getScaleByScaleId(int scaleId) throws ClassNotFoundException, SQLException {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Scale scale = new Scale();
		
		try
		{
			conn = getConn();
			stmt = conn.createStatement();
			
			String sql = String.format("select id, name, questionCount, description from scale where isdelete = 0 and id = %d", scaleId);
			rs = stmt.executeQuery(sql);
			rs.next();
	
			
			scale.setId(rs.getInt("id"));
			scale.setName(rs.getString("name"));
			scale.setQuestionCount(rs.getInt("questionCount"));
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
			
		} finally {
			this.closeResultSet(rs);
			this.closeStatement(stmt);
			this.closeConn(conn);
		}
		
		return scale;
	}

	@Override
	public List<Factor> getFactorListByScale(int scaleId) throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		List<Factor> factorLst = new ArrayList<Factor>();
		
		try {
			conn = getConn();
			stmt = conn.createStatement();
	
			String sql = String.format("select factorId, name, formula, levelCount from factor where scaleId = %d order by factorId", scaleId);
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Factor factor = new Factor();
				factor.setFactorId(rs.getInt("factorId"));
				factor.setName(rs.getString("name"));
				factor.setFormula(rs.getString("formula"));
				factor.setLevelCount(rs.getInt("levelCount"));
				factorLst.add(factor);
			}
			
			rs.close();
			
		} finally {
			this.closeResultSet(rs);
			this.closeStatement(stmt);
			this.closeConn(conn);
		}
		
		return factorLst;
	}
	
	@Override
	public List<Group> getGroupListByScale(int scaleId) throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Group> groupLst = new ArrayList<Group>();
		
		try {
			conn = getConn();
			stmt = conn.createStatement();
			
			String sql = String.format("select groupId, name, formula from `group` where scaleId = %d order by groupId", scaleId);
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Group group = new Group();
				group.setGroupId(rs.getInt("groupId"));
				group.setName(rs.getString("name"));
				group.setFormula(rs.getString("formula"));
				groupLst.add(group);
			}
		
		} finally {
			this.closeResultSet(rs);
			this.closeStatement(stmt);
			this.closeConn(conn);
		}
		
		return groupLst;
	}
	
	@Override
	public List<Relation> getRelationByScale(int scaleId) throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		List<Relation> relationLst = new ArrayList<Relation>();
		
		try {
			conn = getConn();
			stmt = conn.createStatement();
			
			
			String sql = String.format("select factorId, groupId, points from relation where scaleId = %d order by factorId, groupId", scaleId);
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Relation relation = new Relation();
				relation.setFactorId(rs.getInt("factorId"));
				relation.setGroupId(rs.getInt("groupId"));
				relation.setPoints(rs.getString("points"));
				relationLst.add(relation);
			}
		
		} finally {
			this.closeResultSet(rs);
			this.closeStatement(stmt);
			this.closeConn(conn);
		}
		
		return relationLst;
	}
	
	@Override
	public List<FactorMap> getFactorMapByScale(int scaleId) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		List<FactorMap> factorMapLst = new ArrayList<FactorMap>();
		
		try {
			conn = getConn();
			stmt = conn.createStatement();
			
			
			String sql = String.format("select factorId, name, formula from factormap where scaleId = %d order by factorId, name", scaleId);
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				FactorMap factorMap = new FactorMap();
				factorMap.setFactorId(rs.getInt("factorId"));
				factorMap.setName(rs.getString("name"));
				factorMap.setFormula(rs.getString("formula"));
				factorMapLst.add(factorMap);
			}
		
		} finally {
			this.closeResultSet(rs);
			this.closeStatement(stmt);
			this.closeConn(conn);
		}
		
		return factorMapLst;
	}
	
	@Override
	public List<GlobalJump> getGlobalJumpByScale(int scaleId) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		List<GlobalJump> globalJumpLst = new ArrayList<GlobalJump>();
		try {
			conn = getConn();
			stmt = conn.createStatement();
			
			
			String sql = String.format("select name, begin, end, continuous, questionCount, score, jumpNo from globaljump where scaleId = %d order by name", scaleId);
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				GlobalJump globalJump = new GlobalJump();

				globalJump.setName(rs.getString("name"));
				globalJump.setBegin(rs.getInt("begin"));
				globalJump.setEnd(rs.getInt("end"));
				globalJump.setContinuous(rs.getInt("continuous"));
				globalJump.setQuestionCount(rs.getInt("questionCount"));
				globalJump.setScore(rs.getDouble("score"));
				globalJump.setJumpNo(rs.getInt("jumpNo"));
				
				globalJumpLst.add(globalJump);
			}
		
		} finally {
			this.closeResultSet(rs);
			this.closeStatement(stmt);
			this.closeConn(conn);
		}
		
		return globalJumpLst;
	}
	
	@Override
	public int insertTesteeBase(int scaleId, TesteeData data) throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		int id;
		
		try {
			conn = getConn();
			stmt = conn.createStatement();
			
			String sql = String.format("insert into testeebase(scaleId, name, gender, age, testTime) values(%s, '%s', '%s', %f, now())", 
					scaleId,
					data.getInfo().getName(), 
					data.getInfo().getGender(),
					data.getInfo().getAge());
			
			stmt.execute(sql, Statement.RETURN_GENERATED_KEYS);
			rs = stmt.getGeneratedKeys();
			rs.next();
			id = rs.getInt(1);
		
		} finally {
			this.closeResultSet(rs);
			this.closeStatement(stmt);
			this.closeConn(conn);
		}
		
		return id;
	}
	
	@Override
	public void insertTesteePersonalInfo(int scaleId, int baseId, TesteeData data) throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		Statement stmt = null;
		
		try {
			conn = getConn();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			
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
			
			stmt.executeBatch();	
			
			conn.commit();
			conn.setAutoCommit(true);
		
		} finally {
			this.closeStatement(stmt);
			this.closeConn(conn);
		}
	}
	
	@Override
	public void insertTesteeData(int scaleId, int baseId, TesteeData data) throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		Statement stmt = null;
		
		try {
			conn = getConn();
			stmt = conn.createStatement();
			
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
		
		} finally {
			this.closeStatement(stmt);
			this.closeConn(conn);
		}
	}
	
	@Override
	public void insertTesteeDataText(int scaleId, int baseId, List<OptionSelected> selectedDataLst)
			throws ClassNotFoundException, SQLException {

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			
			conn = getConn();
			conn.setAutoCommit(false);
	
			String sql = "insert into testeedatatext(scaleId, baseId, questionId, text) values(?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			for(OptionSelected os : selectedDataLst) {
				pstmt.setInt(1, scaleId);
				pstmt.setInt(2,  baseId);
				pstmt.setInt(3,  os.getQuestionId());
				pstmt.setString(4, os.getText());
				
				pstmt.addBatch();
			}
			
			pstmt.executeBatch();
			conn.commit();
			
			conn.setAutoCommit(true);	
		
		} finally {
			this.closeStatement(pstmt);
			this.closeConn(conn);
		}
	}
	
	@Override
	public void insertResultBase(int scaleId, int testeeBaseId, Result result)
			throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConn();
			String sql = "insert into resultbase(scaleId, testeeBaseId, `groups`) values(?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			StringBuilder groups = new StringBuilder();
			for(String group : result.getGroupLst()) {
				groups.append(group + ",");
			}
			
			pstmt.setInt(1, scaleId);
			pstmt.setInt(2, testeeBaseId);
			pstmt.setString(3, groups.toString());		
			
			pstmt.execute();
		
		} finally {
			this.closeStatement(pstmt);
			this.closeConn(conn);
		}
	}
	
	@Override
	public void insertResultFactor(int scaleId, int testeeBaseId, Result result)
			throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			
			conn = getConn();
			conn.setAutoCommit(false);
	
			String sql = "insert into resultfactor(scaleId, factorId, testeeBaseId, name, score, levelId) values(?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			for(FactorResult fr : result.getItems()) {
				pstmt.setInt(1, scaleId);
				pstmt.setInt(2,  fr.getFactorId());
				pstmt.setInt(3,  testeeBaseId);
				pstmt.setString(4, fr.getName());
				pstmt.setDouble(5, fr.getScore());
				pstmt.setInt(6, fr.getLevelId());
				
				pstmt.addBatch();
			}
			
			pstmt.executeBatch();
			conn.commit();
			
			conn.setAutoCommit(true);	
		
		} finally {
			this.closeStatement(pstmt);
			this.closeConn(conn);
		}
	}
	
	@Override
	public void updateResultScore(int scaleId, int testeeBaseId, String score)
			throws ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			
			conn = getConn();
			
			String sql = "update testeedata set ScoreSelected=? where scaleId=? and baseId=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, score);
			pstmt.setInt(2, scaleId);
			pstmt.setInt(3, testeeBaseId);
			
			pstmt.execute();
			
		} finally {
			this.closeStatement(pstmt);
			this.closeConn(conn);
		}
		
	}
	
	private Connection getConn() throws SQLException, ClassNotFoundException {
		return ds.getConnection();
	}

	private void closeResultSet(ResultSet rs) throws SQLException {
		if (rs != null && !rs.isClosed()) {
			rs.close();
		}
	}
	
	private void closeStatement(Statement stmt) throws SQLException {
		if (stmt != null && !stmt.isClosed()) {
			stmt.close();
		}
	}
	
	private void closeConn(Connection conn) throws SQLException {
		if (conn != null && !conn.isClosed() ) {
			conn.close();
		}
	}

}
