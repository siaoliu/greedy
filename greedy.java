package networks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public class greedy {

	public Vector greedy_node(HashMap sign) {//sign ��ʾ�Ǹ��ٽӾ���
		/*
		 *���ƴ�����Ҫ�������¼������֣�
		 * 1.����ĳ����Ը�ͼ��Ӱ����
		 * 2.����ʧȥĳ�������ٽӾ���̰���㷨ÿ��ʧȥһ�����ͼ�ͷ����˱仯
		 * ������Effect�������calculateEffect������deleteNode����
		 */
		// TODO �Զ����ɵķ������
		double max_suminfo = 0;
		Vector best_node = new Vector();
		
		ReadFile test = new ReadFile();
		test.load(".\\links.txt");
		int num_of_node = test.max; //�ڵ���ܸ���
		int iter = 0; //ѭ������
		Effect ef = new Effect();
		while(iter < 5000) { 
		//����ֻѭ��5000�Σ�ÿ�εó�10���ڵ�������������ͳ�Ƴ���Ƶ����ߵ���ʮ���ڵ����Ҫ�Ľ��
			double suminfo = 0;
			HashMap my_sign = (HashMap) sign.clone();
			Vector init_node = new Vector();
			//1.������Ǹ��ڵ�Ч�����
			for(int k = 0;k<10;k++) {
				double max_info = 0;//�������Ӱ����Ϊ0
				int maxnode_index = -1;
				for(int i = 0 ; i <= num_of_node ; i++) {
					if (init_node.contains(i)==false){//�����ʱδɾ���ýڵ㣬����һ��
						double i_info = ef.calculateEffect(my_sign,i);//����ĳ���ڵ��Ӱ����
						if(i_info > max_info) {//���forѭ���ҵ�Ӱ�������Ǹ�����
							max_info = i_info;
							maxnode_index = i;
						}
					}
				}
			//���ߴ����Ѿ��ҵ��˵�ǰ����ĳ��Ӱ�������Ľڵ�maxnode_index,���ýڵ����ɾ���㼯�ϣ�����ͼ��ȥ���õ��Ӱ���
				init_node.add(maxnode_index);
				suminfo += max_info;
				//10��ѭ��֮��init_node ����������Ҫ��10���㣬��sum_info����ʮ�����Ӱ����֮��
				my_sign = ef.deleteNode(my_sign,maxnode_index);//ÿ��ɾ��һ���ڵ㣬ͼ��仯һ��
			}
			
			/*
			 * ���е�����Ѿ������һ�󲿷֣�10�����Ѿ����˳���
			 * ���ǿ���ͨ��5000��ѭ����10����Ѱ������������
			 * ������һ�£�������ɾȥ10�ڵ�Ӱ������������һ��
			 * ��������˼·���ܰ����޸���һ��
			 * 
			 */
			if(suminfo>max_suminfo) {
				max_suminfo = max_suminfo;
				best_node = init_node;
			}
		}
		return best_node;
	}

}
