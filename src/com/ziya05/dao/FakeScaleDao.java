package com.ziya05.dao;

import java.sql.SQLException;
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
import com.ziya05.entities.Result;
import com.ziya05.entities.Scale;
import com.ziya05.entities.TesteeData;

public class FakeScaleDao implements IScaleDao {
	
	@Override
	public List<Scale> getAllScales() {
		List<Scale> scales = new ArrayList<Scale>();
		
		scales.add(new Scale(1, "��һ�������������", "����һ���������̵����� �����ǵ�������ѧ��ʦĳĳĳ"));
		scales.add(new Scale(3, "�����˸��������", "����һ���������̵����� �����ǵ�������ѧ��ʦĳĳĳ"));
		scales.add(new Scale(4, "��ÿ����׬����Ǯ����", "����һ���������̵����� �����ǵ�������ѧ��ʦĳĳĳ"));
		scales.add(new Scale(5, "�����㻹�ܻ������", "����ѧ��һ���о���������������Ӱ���µľ����ܺ���Ϊ��Ŀ�ѧ�����ͻ���������Ժ�Ӧ�ã�ʵ�����ԡ� ����ѧ������������ѧ��Ӧ������ѧ�����������о��漰֪������֪��������˼ά���˸���Ϊϰ�ߡ��˼ʹ�ϵ������ϵ���������Ҳ���ճ������������򡪡���ͥ�����������������ȷ���������"));
		scales.add(new Scale(6, "�����������˹������", "����һ���������̵����� �����ǵ�������ѧ��ʦĳĳĳ"));
		scales.add(new Scale(7, "ranger����", "����һ���������̵����� �����ǵ�������ѧ��ʦĳĳĳ"));
		scales.add(new Scale(8, "˵����Ĺ���", "����һ���������̵����� �����ǵ�������ѧ��ʦĳĳĳ"));
		scales.add(new Scale(9, "�˹���δ��", "����һ���������̵����� �����ǵ�������ѧ��ʦĳĳĳ"));
		scales.add(new Scale(10, "ÿ���ɶ����", "����һ���������̵����� �����ǵ�������ѧ��ʦĳĳĳ"));
		scales.add(new Scale(11, "���������ַ�", "���и�\"�ĺ�[]djʫ��ɶ{Ϊʲô}��Ҳ���ǵ�, ���Ǹ�ʲô"));
		
		return scales;
	}
	
	@Override
	public PersonalInfo getPersonalInfoByScaleId(int id) {
		PersonalInfo info = new PersonalInfo();
		info.setItems(new ArrayList<InfoItem>() {{
			add(new InfoItem("school", "��ҵԺУ"));
			add(new InfoItem("major", "רҵ"));
			add(new InfoItem("hobby", "��Ȥ����"));
			add(new InfoItem("age", "����"));
		}});
		
		return info;
	}
	
	@Override
	public Scale getScaleByScaleId(int id) {
		List<Question> questionLst = new ArrayList();
		
		questionLst.add(new Question(1, "���ʲô��", 1, getOptionList()));
		questionLst.add(new Question(2, "����ڹ���ô����", 1, getOptionList2()));
		questionLst.add(new Question(3, "��ÿ������ô��", 1, getOptionList3(6)));
		questionLst.add(new Question(4, "����������", 1, getOptionList2()));
		questionLst.add(new Question(5, "����һ·�ˣ� ʲô��û�п����� ���Ǽ����ˣ� ��Ҳ�����ˣ��㶼�����ˣ�ֻ��û����������ʲô��û������", 1, getOptionList3(8)));
		questionLst.add(new Question(6, "����ע�ͱ���д�ڷ�������֮ǰ����ע�Ͱ����������书�ܵļ� �����������ĲΣ�", 1, getOptionList2()));
		questionLst.add(new Question(7, "What do you think about shit��", 1, getOptionList()));
		questionLst.add(new Question(8, "�����ô��", 1, getOptionList2()));
		questionLst.add(new Question(9, "�����ô��", 1, getOptionList()));
		
		return new Scale(4, 
				"�����㻹�ܻ������", 
				id + ":����ѧ��һ���о���������������Ӱ���µľ����ܺ���Ϊ��Ŀ�ѧ�����ͻ���������Ժ�Ӧ�ã�ʵ�����ԡ� ����ѧ������������ѧ��Ӧ������ѧ�����������о��漰֪������֪��������˼ά���˸���Ϊϰ�ߡ��˼ʹ�ϵ������ϵ���������Ҳ���ճ������������򡪡���ͥ�����������������ȷ���������", 
				questionLst);
	}
	
	@Override
	public List<Factor> getFactorListByScale(int id) {
		List<Factor> lst = new ArrayList<Factor>();
		
		Factor factor1 = new Factor();
		factor1.setFactorId(1);
		factor1.setName("���Գ̶�");
		factor1.setFormula("Q1+Q2+Q5");

		factor1.setLevelList(new ArrayList() {{
			add(new Level(1, 1, "��զ����", "����һ����զ���Ե��ˣ��������Ը񣬶���û�ô���"));
			add(new Level(1, 2, "�е�����", "����һ���е����Ե��ˣ� ��������"));
			add(new Level(1, 3, "�ر�����", "��̫�����ˣ� ����һ����֪�ڸĵ�ɵ����"));
			add(new Level(1, 4, "�е�����", "��զ����˵����ԣ� �Ҳ�����"));
		}});
		lst.add(factor1);
		
		
		Factor factor2 = new Factor();
		factor2.setFactorId(2);
		factor2.setName("�ܼҳ̶�");
		factor2.setFormula("if (Q3 == 2) {Q4} else {Q7}"); 

		factor2.setLevelList(new ArrayList() {{
			add(new Level(2, 1, "��զ�ܼ�", "�϶�����Ǯ�٣�����"));
			add(new Level(2, 2, "��ܼң��಻�ܼ�", "��֪����˵ɶ���˿̵���������˵���С��"));
			add(new Level(2, 3, "�ǳ��ܼ�", "����"));
		}});
		lst.add(factor2);
		
		Factor factor3 = new Factor();
		factor3.setFactorId(3);
		factor3.setName("����");
		factor3.setFormula("Q3 + Q4 + Q7");

		factor3.setLevelList(new ArrayList() {{
			add(new Level(3, 1, "�޴�������", "��ȥ���һ���ɣ�"));
			add(new Level(3, 2, "���޴���ֻ�ǰ׳�", "(������)\""));
			add(new Level(3, 3, "һ���˶�", "����һ���˶��� һ����˶�����������"));
			add(new Level(3, 4, "�˾�", "��������ɾ���"));
		}});
		lst.add(factor3);
		
		return lst;
	}
	
	private List<Option> getOptionList(){
		List<Option> lst = new ArrayList();
		
		lst.add(new Option("A", "��", 1));
		lst.add(new Option("B", "����", 2));
		lst.add(new Option("C", "����˵", 3));
		lst.add(new Option("D", "Ҫ������", 4));
		lst.add(new Option("E", "����������", 5));
		
		return lst;
	}
	
	private List<Option> getOptionList2() {
		List<Option> lst = new ArrayList();
		
		lst.add(new Option("A", "�����", 2));
		lst.add(new Option("B", "��ʺ��", 4));
		lst.add(new Option("C", "��Ҫ��", 6));
		
		return lst;
	}
	
	private List<Option> getOptionList3(int nextId){
		List<Option> lst = new ArrayList();
		
		lst.add(new Option("A", "��", 1));
		lst.add(new Option("B", "����", 2));
		lst.add(new Option("C", "���һ���ת����" + nextId, 3, nextId));
		lst.add(new Option("D", "Ҫ������", 4));
		
		return lst;
	}

	@Override
	public List<Group> getGroupListByScale(int scaleId) {
		List<Group> lst = new ArrayList<Group>();	
		
		lst.add(new Group(1, "������", "�Ա�=='��' && ���� < 10"));
		lst.add(new Group(2, "Ů����", "�Ա�=='Ů' && ���� < 10"));
		lst.add(new Group(3, "������", "�Ա�=='��' && ���� >= 10 && ���� < 40"));
		lst.add(new Group(4, "Ů����", "�Ա�=='Ů' && ���� >= 10 && ���� < 35"));
		lst.add(new Group(5, "������", "(�Ա�=='��' && ���� >= 40) || (�Ա� == 'Ů' && ���� >= 35)"));
		lst.add(new Group(6, "ȫ��", "1==1"));
		
		
		
		return lst;
	}

	@Override
	public List<Relation> getRelationByScale(int scaleId) {
		List<Relation> lst = new ArrayList<Relation>();
		
		lst.add(new Relation(1, 1, " 5, 8, 15"));
		lst.add(new Relation(1, 2, " 7, 10, 12"));
		lst.add(new Relation(1, 3, " 6, 8, 11"));
		lst.add(new Relation(1, 4, " 5, 7, 10"));
		lst.add(new Relation(1, 5, " 4, 8, 12"));

		lst.add(new Relation(2, 6, "5, 10"));
		
		lst.add(new Relation(3, 1, "6, 12, 18"));
		lst.add(new Relation(3, 2, "4, 8, 10"));

		return lst;
	}

	@Override
	public int insertTesteeBase(int scaleId, TesteeData data) {
		return 1;
	}

	@Override
	public void insertTesteePersonalInfo(int scaleId, int baseId, TesteeData data) {
		
	}

	@Override
	public void insertTesteeData(int scaleId, int baseId, TesteeData data) throws ClassNotFoundException, SQLException {

	}

	@Override
	public void insertResultBase(int scaleId, int testeeBaseId, Result result)
			throws ClassNotFoundException, SQLException {
		
	}

	@Override
	public void insertResultFactor(int scaleId, int testeeBaseId, Result result)
			throws ClassNotFoundException, SQLException {		
	}
}
