package com.ziya05.dao;

import java.util.ArrayList;
import java.util.List;

import com.ziya05.entities.Factor;
import com.ziya05.entities.InfoItem;
import com.ziya05.entities.Level;
import com.ziya05.entities.Option;
import com.ziya05.entities.PersonalInfo;
import com.ziya05.entities.Question;
import com.ziya05.entities.Scale;

public class FakeScaleDao implements IScaleDao {
	
	@Override
	public List<Scale> getAllScales() {
		List<Scale> scales = new ArrayList<Scale>();
		
		scales.add(new Scale(1, "��һ�������������", "����һ���������̵����� �����ǵ�������ѧ��ʦĳĳĳ"));
		scales.add(new Scale(2, "�����˸��������", "����һ���������̵����� �����ǵ�������ѧ��ʦĳĳĳ"));
		scales.add(new Scale(3, "��ÿ����׬����Ǯ����", "����һ���������̵����� �����ǵ�������ѧ��ʦĳĳĳ"));
		scales.add(new Scale(4, "�����㻹�ܻ������", "����ѧ��һ���о���������������Ӱ���µľ����ܺ���Ϊ��Ŀ�ѧ�����ͻ���������Ժ�Ӧ�ã�ʵ�����ԡ� ����ѧ������������ѧ��Ӧ������ѧ�����������о��漰֪������֪��������˼ά���˸���Ϊϰ�ߡ��˼ʹ�ϵ������ϵ���������Ҳ���ճ������������򡪡���ͥ�����������������ȷ���������"));
		scales.add(new Scale(5, "�����������˹������", "����һ���������̵����� �����ǵ�������ѧ��ʦĳĳĳ"));
		scales.add(new Scale(6, "ranger����", "����һ���������̵����� �����ǵ�������ѧ��ʦĳĳĳ"));
		scales.add(new Scale(7, "˵����Ĺ���", "����һ���������̵����� �����ǵ�������ѧ��ʦĳĳĳ"));
		scales.add(new Scale(8, "�˹���δ��", "����һ���������̵����� �����ǵ�������ѧ��ʦĳĳĳ"));
		scales.add(new Scale(9, "ÿ���ɶ����", "����һ���������̵����� �����ǵ�������ѧ��ʦĳĳĳ"));
		scales.add(new Scale(10, "���������ַ�", "���и�\"�ĺ�[]djʫ��ɶ{Ϊʲô}��Ҳ���ǵ�, ���Ǹ�ʲô"));
		
		return scales;
	}
	
	@Override
	public PersonalInfo getPersonalInfoByScaleId(int id) {
		PersonalInfo info = new PersonalInfo();
		info.setItems(new ArrayList<InfoItem>() {{
			add(new InfoItem("school", "��ҵԺУ"));
			add(new InfoItem("major", "רҵ"));
			add(new InfoItem("hobby", "��Ȥ����"));
		}});
		
		return info;
	}
	
	@Override
	public Scale getScaleByScaleId(int id) {
		List<Question> questionLst = new ArrayList();
		
		questionLst.add(new Question(1, "���ʲô��", getOptionList()));
		questionLst.add(new Question(2, "����ڹ���ô����", getOptionList2()));
		questionLst.add(new Question(3, "��ÿ������ô��", getOptionList3(6)));
		questionLst.add(new Question(4, "����������", getOptionList2()));
		questionLst.add(new Question(5, "����һ·�ˣ� ʲô��û�п����� ���Ǽ����ˣ� ��Ҳ�����ˣ��㶼�����ˣ�ֻ��û����������ʲô��û������", getOptionList3(8)));
		questionLst.add(new Question(6, "����ע�ͱ���д�ڷ�������֮ǰ����ע�Ͱ����������书�ܵļ� �����������ĲΣ�", getOptionList2()));
		questionLst.add(new Question(7, "What do you think about shit��", getOptionList()));
		questionLst.add(new Question(8, "�����ô��", getOptionList2()));
		questionLst.add(new Question(9, "�����ô��", getOptionList()));
		
		return new Scale(4, 
				"�����㻹�ܻ������", 
				id + ":����ѧ��һ���о���������������Ӱ���µľ����ܺ���Ϊ��Ŀ�ѧ�����ͻ���������Ժ�Ӧ�ã�ʵ�����ԡ� ����ѧ������������ѧ��Ӧ������ѧ�����������о��漰֪������֪��������˼ά���˸���Ϊϰ�ߡ��˼ʹ�ϵ������ϵ���������Ҳ���ճ������������򡪡���ͥ�����������������ȷ���������", 
				questionLst);
	}
	
	@Override
	public List<Factor> getFactorListByScale(int id) {
		List<Factor> lst = new ArrayList<Factor>();
		
		Factor factor1 = new Factor();
		factor1.setName("���Գ̶�");
		factor1.setItems(new ArrayList<Integer>() {{
			add(new Integer(1));
			add(new Integer(2));
			add(new Integer(5));
		}});
		factor1.setLevelList(new ArrayList() {{
			add(new Level(0, 3, "��զ����", "����һ����զ���Ե��ˣ��������Ը񣬶���û�ô���"));
			add(new Level(4, 6, "�е�����", "����һ���е����Ե��ˣ� ��������"));
			add(new Level(7, 10, "�ر�����", "��̫�����ˣ� ����һ����֪�ڸĵ�ɵ����"));
			add(new Level(11, 50, "�е�����", "��զ����˵����ԣ� �Ҳ�����"));
		}});
		lst.add(factor1);
		
		
		Factor factor2 = new Factor();
		factor2.setName("�ܼҳ̶�");
		factor2.setItems(new ArrayList() {{
			add(new Integer(3));
			add(new Integer(4));
			add(new Integer(7));
		}});
		factor2.setLevelList(new ArrayList() {{
			add(new Level(0, 4, "��զ�ܼ�", "�϶�����Ǯ�٣�����"));
			add(new Level(5, 9, "��ܼң��಻�ܼ�", "��֪����˵ɶ���˿̵���������˵���С��"));
			add(new Level(10, 50, "�ǳ��ܼ�", "����"));
		}});
		lst.add(factor2);
		
		Factor factor3 = new Factor();
		factor3.setName("����");
		factor3.setItems(new ArrayList() {{
			add(new Integer(3));
			add(new Integer(4));
			add(new Integer(7));
		}});
		factor3.setLevelList(new ArrayList() {{
			add(new Level(0, 3, "�޴�������", "��ȥ���һ���ɣ�"));
			add(new Level(4, 6, "���޴���ֻ�ǰ׳�", "(������)\""));
			add(new Level(7, 10, "һ���˶�", "����һ���˶��� һ����˶�����������"));
			add(new Level(11, 50, "�˾�", "��������ɾ���"));
		}});
		lst.add(factor3);
		
		return lst;
	}
	
	private List<Option> getOptionList(){
		List<Option> lst = new ArrayList();
		
		lst.add(new Option(1, "��", 1));
		lst.add(new Option(2, "����", 2));
		lst.add(new Option(3, "����˵", 3));
		lst.add(new Option(4, "Ҫ������", 4));
		lst.add(new Option(5, "����������", 5));
		
		return lst;
	}
	
	private List<Option> getOptionList2() {
		List<Option> lst = new ArrayList();
		
		lst.add(new Option(1, "�����", 2));
		lst.add(new Option(2, "��ʺ��", 4));
		lst.add(new Option(3, "��Ҫ��", 6));
		
		return lst;
	}
	
	private List<Option> getOptionList3(int nextId){
		List<Option> lst = new ArrayList();
		
		lst.add(new Option(1, "��", 1));
		lst.add(new Option(2, "����", 2));
		lst.add(new Option(3, "���һ���ת����" + nextId, 3, nextId));
		lst.add(new Option(4, "Ҫ������", 4));
		
		return lst;
	}
}
