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
		
		scales.add(new Scale(1, "测一测你的智商量表", "这是一个测试智商的量表， 作者是当代心理学大师某某某"));
		scales.add(new Scale(2, "九型人格测试量表", "这是一个测试智商的量表， 作者是当代心理学大师某某某"));
		scales.add(new Scale(3, "你每个月赚多少钱量表", "这是一个测试智商的量表， 作者是当代心理学大师某某某"));
		scales.add(new Scale(4, "看看你还能活几年量表", "心理学是一门研究人类心理现象及其影响下的精神功能和行为活动的科学，兼顾突出的理论性和应用（实践）性。 心理学包括基础心理学与应用心理学两大领域，其研究涉及知觉、认知、情绪、思维、人格、行为习惯、人际关系、社会关系等许多领域，也与日常生活的许多领域——家庭、教育、健康、社会等发生关联。"));
		scales.add(new Scale(5, "玛格利特普拉斯及量表", "这是一个测试智商的量表， 作者是当代心理学大师某某某"));
		scales.add(new Scale(6, "ranger量表", "这是一个测试智商的量表， 作者是当代心理学大师某某某"));
		scales.add(new Scale(7, "说出你的故事", "这是一个测试智商的量表， 作者是当代心理学大师某某某"));
		scales.add(new Scale(8, "人鬼情未了", "这是一个测试智商的量表， 作者是当代心理学大师某某某"));
		scales.add(new Scale(9, "每天吃啥量表", "这是一个测试智商的量表， 作者是当代心理学大师某某某"));
		scales.add(new Scale(10, "试试特殊字符", "这有个\"的和[]dj诗歌啥{为什么}我也不是到, 这是个什么"));
		
		return scales;
	}
	
	@Override
	public PersonalInfo getPersonalInfoByScaleId(int id) {
		PersonalInfo info = new PersonalInfo();
		info.setItems(new ArrayList<InfoItem>() {{
			add(new InfoItem("school", "毕业院校"));
			add(new InfoItem("major", "专业"));
			add(new InfoItem("hobby", "兴趣爱好"));
		}});
		
		return info;
	}
	
	@Override
	public Scale getScaleByScaleId(int id) {
		List<Question> questionLst = new ArrayList();
		
		questionLst.add(new Question(1, "你叫什么？", getOptionList()));
		questionLst.add(new Question(2, "你对于狗怎么看？", getOptionList2()));
		questionLst.add(new Question(3, "你每天吃早餐么？", getOptionList3(6)));
		questionLst.add(new Question(4, "哈哈哈哈？", getOptionList2()));
		questionLst.add(new Question(5, "走了一路了， 什么都没有看见， 鸟是见到了， 树也见到了，鱼都见到了，只是没见到她，就什么都没见到？", getOptionList3(8)));
		questionLst.add(new Question(6, "方法注释必须写在方法定义之前。该注释包括：方法其功能的简单 描述，方法的参？", getOptionList2()));
		questionLst.add(new Question(7, "What do you think about shit？", getOptionList()));
		questionLst.add(new Question(8, "你饿了么？", getOptionList2()));
		questionLst.add(new Question(9, "你渴了么？", getOptionList()));
		
		return new Scale(4, 
				"看看你还能活几年量表", 
				id + ":心理学是一门研究人类心理现象及其影响下的精神功能和行为活动的科学，兼顾突出的理论性和应用（实践）性。 心理学包括基础心理学与应用心理学两大领域，其研究涉及知觉、认知、情绪、思维、人格、行为习惯、人际关系、社会关系等许多领域，也与日常生活的许多领域——家庭、教育、健康、社会等发生关联。", 
				questionLst);
	}
	
	@Override
	public List<Factor> getFactorListByScale(int id) {
		List<Factor> lst = new ArrayList<Factor>();
		
		Factor factor1 = new Factor();
		factor1.setName("任性程度");
		factor1.setItems(new ArrayList<Integer>() {{
			add(new Integer(1));
			add(new Integer(2));
			add(new Integer(5));
		}});
		factor1.setLevelList(new ArrayList() {{
			add(new Level(0, 3, "不咋任性", "你是一个不咋任性的人，坚持这个性格，对你没好处！"));
			add(new Level(4, 6, "有点任性", "你是一个有点任性的人， 哈哈哈哈"));
			add(new Level(7, 10, "特别任性", "你太任性了， 真是一个不知悔改的傻蛋！"));
			add(new Level(11, 50, "有点作了", "你咋能如此地任性， 我不懂！"));
		}});
		lst.add(factor1);
		
		
		Factor factor2 = new Factor();
		factor2.setName("败家程度");
		factor2.setItems(new ArrayList() {{
			add(new Integer(3));
			add(new Integer(4));
			add(new Integer(7));
		}});
		factor2.setLevelList(new ArrayList() {{
			add(new Level(0, 4, "不咋败家", "肯定是挣钱少！！！"));
			add(new Level(5, 9, "亦败家，亦不败家", "不知道该说啥，此刻的世界是如此的渺小！"));
			add(new Level(10, 50, "非常败家", "滚！"));
		}});
		lst.add(factor2);
		
		Factor factor3 = new Factor();
		factor3.setName("智商");
		factor3.setItems(new ArrayList() {{
			add(new Integer(3));
			add(new Integer(4));
			add(new Integer(7));
		}});
		factor3.setLevelList(new ArrayList() {{
			add(new Level(0, 3, "愚蠢的人类", "快去自我毁灭吧！"));
			add(new Level(4, 6, "不愚蠢，只是白痴", "(￣▽￣)\""));
			add(new Level(7, 10, "一般人儿", "就是一般人儿， 一般地人儿啊。。。。"));
			add(new Level(11, 50, "人精", "建国后不许成精！"));
		}});
		lst.add(factor3);
		
		return lst;
	}
	
	private List<Option> getOptionList(){
		List<Option> lst = new ArrayList();
		
		lst.add(new Option(1, "是", 1));
		lst.add(new Option(2, "不是", 2));
		lst.add(new Option(3, "不好说", 3));
		lst.add(new Option(4, "要怎样啦", 4));
		lst.add(new Option(5, "不理你了啦", 5));
		
		return lst;
	}
	
	private List<Option> getOptionList2() {
		List<Option> lst = new ArrayList();
		
		lst.add(new Option(1, "随便啦", 2));
		lst.add(new Option(2, "吃屎啦", 4));
		lst.add(new Option(3, "不要嘛", 6));
		
		return lst;
	}
	
	private List<Option> getOptionList3(int nextId){
		List<Option> lst = new ArrayList();
		
		lst.add(new Option(1, "是", 1));
		lst.add(new Option(2, "不是", 2));
		lst.add(new Option(3, "点我会跳转到：" + nextId, 3, nextId));
		lst.add(new Option(4, "要怎样啦", 4));
		
		return lst;
	}
}
